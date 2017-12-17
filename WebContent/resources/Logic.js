function fillMessages(){
    var messages = getMessages();
    var i;
    if(messages !== ""){
        $('#area').val(messages[0].author + ": " + messages[0].text);
        for(i = 1; i < messages.length; i++){
            $('#area').val($('#area').val() + "\n" + messages[i].author + ": " + messages[i].text);
        }
    }
}
$(document).ready(function(){
    $("#text").keyup(function(event) {
        if (event.keyCode === 13) {
            console.log("DOING");
            $("#send").click();
        }
    });
});