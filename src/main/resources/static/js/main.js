var usernamePage = document.querySelector('#username-page');
var chatPage = document.querySelector('#chat-page');
var usernameForm = document.querySelector('#usernameForm');
var messageForm = document.querySelector('#messageForm');
var messageInput = document.querySelector('#message');
var messageArea = document.querySelector('#messageArea');
var connectingElement = document.querySelector('.connecting');

var stompClient = null;
var username = null;


function connect(event) {
    username = document.querySelector('#name').value.trim();

    if (username) {
        usernamePage.classList.add('hidden');
        chatPage.classList.remove('hidden');

        var socket = new SockJS('/javatechie');
        stompClient = Stomp.over(socket);
        stompClient.debug = null;
        stompClient.connect({}, onConnected, onError);
    }
    event.preventDefault();
}


function onConnected() {
    stompClient.subscribe('/topic/public', onMessageReceived);
    stompClient.send("/app/chat.register",
        {},
        JSON.stringify({sender: username})
    )

    // let data = JSON.parse(event.data);
    //
    // for (let i = 0; i < data.length; i++) {
    //     let {coords, flag} = data[i];
    //     send(data[i])
    // }

    connectingElement.classList.add('hidden');
}


function onError(error) {
    connectingElement.textContent = 'Could not connect to WebSocket server. Please refresh this page to try again!';
    connectingElement.style.color = 'red';
}


function send(event) {
    var messageContent = messageInput.value.trim();
    if (messageContent && stompClient) {
        var chatMessage = {
            sender: username,
            content: messageInput.value
        };
        stompClient.send("/app/chat.send", {}, JSON.stringify(chatMessage));
        messageInput.value = '';
    }
    event.preventDefault();
}


function onMessageReceived(payload) {
    var message = JSON.parse(payload.body);

    console.log(message);
    if(message.length>1){
        for (let i = 0; i < message.length; i++) {
            var messageElement1 = document.createElement('li');
            messageElement1.classList.add('chat-message');
            var usernameElement1 = document.createElement('span');
            var usernameText1 = document.createTextNode(message[i].user.userName);
            usernameElement1.appendChild(usernameText1);
            messageElement1.appendChild(usernameElement1);
            var textElement1 = document.createElement('p');
            var messageText1 = document.createTextNode(message[i].content);
            textElement1.appendChild(messageText1);
            messageElement1.appendChild(textElement1);
            messageArea.appendChild(messageElement1);
            messageArea.scrollTop = messageArea.scrollHeight;
            }

    }

    console.log("message received");
    var messageElement = document.createElement('li');
    messageElement.classList.add('chat-message');
    var usernameElement = document.createElement('span');
    var usernameText = document.createTextNode(message.user.userName);
    usernameElement.appendChild(usernameText);
    messageElement.appendChild(usernameElement);
    var textElement = document.createElement('p');
    var messageText = document.createTextNode(message.content);
    textElement.appendChild(messageText);
    messageElement.appendChild(textElement);
    messageArea.appendChild(messageElement);
    messageArea.scrollTop = messageArea.scrollHeight;
}


usernameForm.addEventListener('submit', connect, true)
messageForm.addEventListener('submit', send, true)
