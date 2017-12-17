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
}

function send(id) {
    var theText = $('#text' + id).val();
    $('#text' + id).val('');
    var authorName = getAuthor();
    if(theText !== '') {
        stompClient.send("/app/hello", {}, JSON.stringify({'text': theText, 'author': authorName}));
    }
}

function showGreeting(message) {
    var textArea = $('#area');
    if(textArea.val() === "") {
        textArea.val(textArea.val() + message.author + ": " + message.text);
    }
    else{
        textArea.val(textArea.val() + "\n" + message.author + ": " + message.text);
    }
    $('#area').scrollTop($('#area')[0].scrollHeight);
}

$(function () {
    $("form").on('submit', function (e) {
        e.preventDefault();
    });
    $( "#connect" ).click(function() { connect(); });
    $( "#disconnect" ).click(function() { disconnect(); });
});

