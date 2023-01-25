'use strict'

function addNotification (notification) {

    let notificationBlock = $("<div class='notification-block " + notification.status + "'></div>");
    notificationBlock.text(notification.message);
    $('#notificationsHub').append(notificationBlock)

    setTimeout(function() {
        notificationBlock.fadeOut( 1000, function() {
            notificationBlock.remove();
        });
    }, 5000)

}

function createNotification(message, status){
    return {
        message: message,
        status: status,
        time: new Date().toUTCString()
    };
}