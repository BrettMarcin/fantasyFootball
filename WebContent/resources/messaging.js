var stompClient = null;

function setConnected(connected) {
    $("#connect").prop("disabled", connected);
    $("#disconnect").prop("disabled", !connected);
    if (connected) {
        $("#conversation").show();
    }
    else {
        $("#conversation").hide();
    }
    $("#greetings").html("");
}

function connect() {
    var socket = new SockJS('/chat');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function (frame) {
        setConnected(true);
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/greetings', function (greeting) {
            showGreeting(JSON.parse(greeting.body));
        });
    });
}

function disconnect() {
    if (stompClient !== null) {
        stompClient.disconnect();
    }
    setConnected(false);
    console.log("Disconnected");
}

function send() {
    var authorName = getAuthor();
    console.log("AUTHOR NAME: " + authorName);
    stompClient.send("/app/hello", {}, JSON.stringify({'text': $("#text").val(), 'author':authorName}));
}

function showGreeting(message) {
    console.log("AREA");
    var textArea = $('#area');
    if(textArea.val() === "") {
        textArea.val(textArea.val() + message.author + ": " + message.text);
    }
    else{
        textArea.val(textArea.val() + "\n" + message.author + ": " + message.text);
    }
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
    $( "#send" ).click(function() { send(); });
});

