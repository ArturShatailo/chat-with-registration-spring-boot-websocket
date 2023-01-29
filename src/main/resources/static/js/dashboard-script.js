'use strict'

let stompClient
let chatUser
let user
let isActive = true;
let connection;

let menuToggler = true;

$(".button-show-hide").click(function (){

    if (menuToggler) {
        $(this).html("&#128314;");
        $(".chats-list-controls").css({maxHeight: 0+"px", height: 0 + "px", padding: "25px 10px"})
        //.children('.contact-connections-container').hide(1000);*/
        menuToggler = false;
    } else {
        $(this).html("&#10060;");
        $(".chats-list-controls").css({maxHeight: 160+"px", height: 160 + "px", padding: "10px 10px"})
/*
        $(".chats-list-controls")
            .animate({
                maxHeight: 160,
                height: 160,
                //padding: "10px 10px"
            }, 300)
            //.children('.contact-connections-container').show(1000);*/
        menuToggler = true;
    }
});

async function GetUserInfo() {
    let url = '/api/user/';
    let res = await fetch(url, {method: 'GET'});

    if (res.ok) {
        return res.json();
    } else {
        return `HTTP error: ${res.status}`;
    }
}

GetUserInfo().then(data => {
    updateChatUser(data)
    fillUserInformation();
});

function fillUserInformation(){
    $('#user-name').html(chatUser.name);
    $('#user-name-header').html(chatUser.name);
    $('#user-email').html(chatUser.email);
    $('#user-nickname').html(chatUser.nickname);
    $('.avatar-container').empty().append(createAvatarImage(chatUser.nickname));
}

async function handleSetUserNickname(event) {

    event.preventDefault();
    let prevNickname = chatUser.nickname;
    let nickname = $('#nickname').val();
    let form = event.currentTarget;
    let url = form.action;

    await doChangeNicknameRequest(url, prevNickname, nickname);

}

async function doChangeNicknameRequest(url, prevNickname, nickname){
    let notification;
    chatUser.nickname = nickname;

    let fetchOptions = {
        method: "PUT",
        headers: {
            "Content-Type": "application/json",
            Accept: "application/json",
        },
        body: JSON.stringify(chatUser),
    };

    let res = await fetch(url, fetchOptions);

    if (res.ok) {
        $('#user-nickname').html(nickname);
        $('.avatar-container').empty().append(createAvatarImage(chatUser.nickname));
        user = nickname;
        sendMessageChangeNickname(prevNickname)

        notification = createNotification("Nickname successfully changed", "success-notification")
        addNotification(notification);
    } else {
        let response = res.json()
        response.then((data) => {
            notification = createNotification(data.message, "fail-notification")
            addNotification(notification);
        })
    }
}

function updateChatUser(data){
    chatUser = {
        name: data.firstname +" "+ data.lastname,
        nickname: data.nickname,
        email: data.email
    }
}

function showInfoDetails(event){
    event.preventDefault()
    isActive = !isActive;
    $(".information-details-container").toggleClass( 'hide', isActive );
}


const connect = (event) => {
    user = document.querySelector('#user-nickname').textContent;

    if (user && !connection) {
        connection = true;
        const socket = new SockJS('/chat')
        stompClient = Stomp.over(socket)
        stompClient.connect({}, onConnected, onError)
    }
    event.preventDefault()
}

const disconnect = (event) => {
    if (connection) {
        connection = false;
        stompClient.disconnect()
    }
    event.preventDefault()
}

const onConnected = () => {
    stompClient.subscribe('/topic/public', onMessageReceived);
    stompClient.send(
        "/app/new-user",
        {},
        JSON.stringify({from: user, type: 'CONNECT'})
    )
}

const sendMessage = (event) => {
    const messageInput = document.querySelector('#message')
    const messageContent = messageInput.value.trim()

    if (messageContent && stompClient) {
        const chatMessage = {
            from: user,
            message: messageInput.value,
            type: 'CHAT',
            timestamp: new Date().toUTCString()
        }
        stompClient.send("/app/room-message", {}, JSON.stringify(chatMessage))
        messageInput.value = ''
    }
    event.preventDefault();
}

const sendMessageChangeNickname = (prevNickname) => {
    const messageContent = prevNickname + " changed nickname to " + user

    if (messageContent && stompClient) {
        const chatMessage = {
            from: user,
            message: messageContent,
            type: 'CHANGE_NICKNAME'
        }
        stompClient.send("/app/room-message", {}, JSON.stringify(chatMessage))
    }
}

const onMessageReceived = (payload) => {

    const message = JSON.parse(payload.body);

    const messageCard = document.createElement('div')
    messageCard.className = 'chat-message-body'

    const flexBox = document.createElement('div')
    flexBox.className = 'chat-message-flex-box'

    const messageElement = document.createElement('div')
    messageElement.className = 'chat-message-container'

    messageCard.appendChild(flexBox)
    flexBox.appendChild(messageElement)
    //flexBox.appendChild(nameElement)

    if (message.type === 'CONNECT') {
        messageElement.classList.add('event-message')
        message.message = message.from + ' connected!'
    } else if (message.type === 'DISCONNECT') {
        messageElement.classList.add('event-message')
        message.message = message.from + ' left!'
    } else if (message.type === 'CHANGE_NICKNAME') {
        messageElement.classList.add('event-message')
    } else {
        const nameElement = document.createElement('div')
        nameElement.className = 'chat-message-name-from';
        nameElement.textContent = message.from;

        messageElement.appendChild(nameElement)

        messageElement.classList.add('chat-message')
        messageElement.style['background-color'] = getAvatarColor(message.from)

        const time = document.createElement('span')
        time.className = 'time_send'
        time.innerHTML = message.timestamp

        flexBox.appendChild(createAvatarImageForMessage(message.from))
        messageElement.appendChild(time)
    }

    //add time
    messageElement.innerHTML += message.message

    //add message body to chat container
    const chat = document.querySelector('#chat')
    chat.appendChild(messageCard)

    //scroll to bottom
    $("#chat-body").animate({ scrollTop: $('#chat-body').prop("scrollHeight")}, 1000);
}

const onError = (error) => {
    const status = document.querySelector('#status')
    status.innerHTML = 'Please reload the page and try again'
    status.style.color = '#F0E68CFF'
}

const hashCode = (str) => {
    let hash = 0
    for (let i = 0; i < str.length; i++) {
        hash = str.charCodeAt(i) + ((hash << 5) - hash)
    }
    return hash
}

const createAvatarImageForMessage = (nickname) => {
    const avatarContainer = document.createElement('div')
    avatarContainer.className = 'img_cont_msg'
    const avatarElement = document.createElement('div')
    avatarElement.className = 'circle user_img_msg'
    const avatarText = document.createTextNode(nickname[0])
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(nickname)
    avatarContainer.appendChild(avatarElement)
    return avatarContainer;
}

const createAvatarImage = (nickname) => {
    const avatarContainer = document.createElement('div')
    avatarContainer.className = 'img_cont_ava'
    const avatarElement = document.createElement('div')
    avatarElement.className = 'rectangle user_img_ava'
    const avatarText = document.createTextNode(nickname[0])
    avatarElement.appendChild(avatarText);
    avatarElement.style['background-color'] = getAvatarColor(nickname)
    avatarContainer.appendChild(avatarElement)
    return avatarContainer;
}

const getAvatarColor = (messageSender) => {
    const colours = ['#ef1ec4', '#d7d779', '#1BC6B4', '#A1B4C4', '#2196F3', '#32c787']
    const index = Math.abs(hashCode(messageSender) % colours.length)
    return colours[index]
}

const changeNicknameButton = document.querySelector('#nickname-form')
changeNicknameButton.addEventListener('submit', handleSetUserNickname, true)

const showInfoDetailsButton = document.querySelector('#account-details-button')
showInfoDetailsButton.addEventListener('click', showInfoDetails, true)

const connectForm = document.querySelector('#connect-form')
connectForm.addEventListener('submit', connect, true)

const disconnectForm = document.querySelector('#disconnect-form')
disconnectForm.addEventListener('submit', disconnect, true)

const messageControls = document.querySelector('#message-controls')
messageControls.addEventListener('submit', sendMessage, true)

// $('#chat').bind('DOMNodeInserted', function() {
//     console.log( $( this ).width() );
// });