/*globals jQuery */
$(function() {

    checkIfDraftHasStarted();
    updateCurrentTeams();
});

function addACpu(){
    jsonData = {
        CpuName: $('#CpuName').text()
    };
    $.ajax({
        url: '/addACpu',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'text',
        data: JSON.stringify(jsonData),
        async: true,
        success: function(response){
            window.location.reload();
        }
    });
}

function getMessages(){
    var messages;
    $.ajax({
        url: '/getMessages',
        type: 'GET',
        async: false,
        success: function(result){
            messages = result;
        }
    });
    return messages;
}

function startDraft(){
    disconnect();
    $.ajax({
        async: false,
        type: "GET",
        url: '/startDraft',
        success: function () {
            window.location.reload();
        }
    });
}
function setLocalTeam(){
    jsonData = {
        teamName: $('#teamName').val(),
        userName: $('#theName').val()
    };
    $.ajax({
        url: '/setLocalTeam',
        type: 'POST',
        contentType: 'application/json',
        dataType: 'text',
        data: JSON.stringify(jsonData),
        async: true,
        success: function(response){
            window.location.reload();
        }
    });

}
function getAuthor(){
    var author = null;
    $.ajax({
        async: false,
        type: 'GET',
        url: '/getAuthor',
        dataType: 'text',
        success: function(result){
            author = result;
        }
    });
    return author;
}

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