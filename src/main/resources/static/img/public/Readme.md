<h1 align="center">WEB CHAT APPLICATION (ChatMate)</h1>
<div style="display: flex; align-items: center; justify-content: center">
<img src="./readme-img/cm.png" style="margin: auto" alt="ChatMate application" width="100">
</div>

<h4 align="center">
Spring boot web chat application with websocket (SockJS) messaging, Spring Security authentication and 
email registration confirmation. The frontend JavaScript part is also included (HTML + CSS)
</h4>

<div style="padding: 10px 0"></div> <!--spacing 20px-->

<h2>Contents</h2>
<ul>
    <li>
        <a href="#overview">Overview</a>
    </li>
    <li>
        <a href="#technologies-stack">Technologies stack</a>
    </li>
    <li>
        <a href="#demonstration">Demonstration</a>
    </li>
    <li>
        <a href="#how-to-start">How to start</a>
    </li>
</ul>

<div style="padding: 10px 0"></div> <!--spacing 20px-->

<h2 id="overview">Overview</h2>
<p>This is a web chat application ChatMate that can be used to chatting with another connected users.</p>
<p>The fist step any user should pass through is the authentication and authorisation process that is provided by Spring Security configuration. 
Then, using post request, a new user record will be saved in database as well as a new registration token. This token is needed to confirm the emeil. 
An email message will be sent to user's specified email address and a 15 minutes period token will be included in the inner mail link, so user should 
just click on it and email address will be confirmed according to the token. Registration process is completed. </br>
Now, user can use the username (email) and password to sign in the system and get access to the secured page of chat.</br>
Dashboard page (chat page) includes personal information of the user received by fetch request to backend api with get request. The legged in user is abel 
to change its nickname at any time. Also, on the dashboard page the chat window is displayed and can be used to connect, disconnect and message sending/receiving. 
Each new connected user will be displayed in the chat window, also, the event message will be sent when any user is disconnecting. 
When user disconnects from the chat, messages from another user will not be received anymore. 
Message sending process is provided by SockJS with STOMP (simple text oriented message protocol) and backend configured WebSocketMessageBroker.</p>
<p>The application can be tested using the link to AWS: <a href="http://chat-mate.us-east-1.elasticbeanstalk.com/">ChatMate</a></p>

<div style="padding: 10px 0"></div> <!--spacing 20px-->

<h2 id="technologies-stack">Technologies stack</h2>
<ul>
    <li>
        <p>Spring Boot</p>
    </li>
    <li>
        <p>Spring Security</p>
    </li>
    <li>
        <p>Spring Mail</p>
    </li>
    <li>
        <p>PostgreSQL database</p>
    </li>
    <li>
        <p>WebSocket messaging</p>
    </li>
    <li>
        <p>JavaScript + HTML + CSS(SCSS)</p>
    </li>
    <li>
        <p>SockJS, Stomp</p>
    </li>
    <li>
        <p>Lombok, Slf4j</p>
    </li>
    <li>
        <p>Maven</p>
    </li>
    <li>
        <p>Deployed to AWS</p>
    </li>
    <!--<li>
        <p>JUnit and Mockito</p>
    </li>-->
    
</ul>

<div style="padding: 10px 0"></div> <!--spacing 20px-->

<h2 id="demonstration">Demonstration</h2>
<ul>
    <li>
        <p>LOGIN <b>http://localhost:8095/login</b></p>
        <p>
            Login form connected to Spring Security
        </p>
        <img src="./readme-img/login.png" alt="ChatMate login">
    </li>
<div style="padding: 5px 0"></div> <!--spacing 10px-->
    <li>
        <p>REGISTRATION <b>http://localhost:8095/register</b></p>
        <p>
            Registration form connected to Spring Security and Confirmation email sender
        </p>
        <img src="./readme-img/register.png" alt="ChatMate register">
    </li>
<div style="padding: 5px 0"></div> <!--spacing 10px-->
    <li>
        <p>DASHBOARD <b>http://localhost:8095/dashboard</b></p>
        <p>
            Dashboard page with user interface.
        </p>
        <img src="./readme-img/dashboard.png" alt="ChatMate dashboard">
    </li>
<div style="padding: 5px 0"></div> <!--spacing 10px-->
    <li>
        <p>PERSONAL INFORMATION <b>http://localhost:8095/dashboard</b></p>
        <p>
            User information displayed from the fetch GET request to endpoint http://localhost:8095/api/user
        </p>
        <img src="./readme-img/nickname.png" alt="ChatMate personal information">
    </li>
<div style="padding: 5px 0"></div> <!--spacing 10px-->
    <p>CHATTING <b>http://localhost:8095/dashboard</b></p>
        <p>
            Chat messaging with SockJS requests to /chat endpoint. Messages are displayed according to the user information: 
            generated avatar, nickname, generated message body that includes inputted message text. 
        </p>
        <img src="./readme-img/chatting.png" alt="ChatMate chat">
</ul>

<div style="padding: 10px 0"></div> <!--spacing 20px-->

<h2 id="how-to-start">How to start</h2>
<p>Download the project from GitHub (using fork is appreciated)</p>
<p>Start the app in your IDE (don't forget about database configuration)</p>
<p>Email confirmation configuration file example is attached below</p>
<p>Go to the link: http://localhost:8095/login</p>
<div style="padding: 5px 0"></div> <!--spacing 10px-->

<img src="./readme-img/email.png" alt="ChatMate chat">

<div style="padding: 10px 0"></div> <!--spacing 20px-->