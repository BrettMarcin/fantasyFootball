/*globals jQuery */
var source = new EventSource('/questions');
source.addEventListener('spring', function(event){
    $('#questions').prepend('<div class="row"><div class="col s12"><div class="card grey-text"><div class="card-content center"><p>' + event.data + '</p></div></div></div></div>')

});
function sendForm(){
    $.post('/new-question', ("hello"));
}

$(function() {

    checkIfDraftHasStarted();
    updateCurrentTeams();
});

function updateCurrentTeams(){
    window.setTimeout(function () {
        $.ajax({
            async: false,
            type: "GET",
            url: '/getTeams',
            success: function (result) {
                var stringBuilder = '';
                $('.list-group-item.currentTeamInSession').remove();
                for(var i = 0; i < result.length; i++){
                    stringBuilder = '<a href="#" class="list-group-item currentTeamInSession">Team Name: ' + result[i].teamName + ', Owned by: ' + result[i].name + '</a>';
                    $('#listOfSessionTeams').append(stringBuilder);
                }
                updateCurrentTeams();
            },
            error: function () {
                updateCurrentTeams();
            }});
    }, 980);
}

function checkIfDraftHasStarted(){
    window.setTimeout(function () {
        $.ajax({
            async: false,
            type: "GET",
            url: '/checkIfDraftHasStarted',
            success: function (result) {
                if(result){
                    window.location.reload();
                }
                checkIfDraftHasStarted();
            },
            error: function () {
                checkIfDraftHasStarted();
            }});
    }, 980);
}