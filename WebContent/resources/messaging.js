/*globals jQuery */
var stompClient = null;

function setConnectedToSocket(connected) {
    document.getElementById('connect').disabled = connected;
    document.getElementById('disconnect').disabled = !connected;
    document.getElementById('conversationDiv').style.visibility
        = connected ? 'visible' : 'hidden';
    document.getElementById('response').innerHTML = '';
}

function connectToSocket() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        setConnectedToSocket(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/messages', function(messageOutput) {
            showMessageOutputFromSocket(JSON.parse(messageOutput.body));
        });
    });
}

function disconnectFromSocket() {
    if(stompClient != null) {
        stompClient.disconnect();
    }
    setConnectedToSocket(false);
    console.log("Disconnected");
}

function sendMessageToSocket() {
    var from = document.getElementById('author').value;
    var text = document.getElementById('text').value;
    stompClient.send("/app/chat", {},
        JSON.stringify({'author':from, 'text':text}));
}

function showMessageOutputFromSocket(messageOutput) {
    console.log("Getting messages!");
    // var response = document.getElementById('response');
    // var p = document.createElement('p');
    // p.style.wordWrap = 'break-word';
    // p.appendChild(document.createTextNode(messageOutput.from + ": "
    //     + messageOutput.text + " (" + messageOutput.time + ")"));
    // response.appendChild(p);
    jQuery.ajax({
        url: '/getMessages',
        type: "GET",
        headers: {
            'Accept': 'application/json'
        },
        processData: false,
        async: true,
        success: function (data) {
            console.log(data);
        }
    });
}