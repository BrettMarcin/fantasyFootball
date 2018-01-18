/*globals jQuery */
$(function() {

    checkIfDraftHasStarted();
    updateCurrentTeams();
});

function addACpu(){
    jsonData = {
        teamName: $('#CpuName').val(),
        userName: "CPU"
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

function updating(){
    var updateResult;
    $.ajax({
        url: '/updating',
        type: 'GET',
        async: false,
        success: function (result) {
            updateResult = result;
        }
    });
    return updateResult;
}

function updatePlayers(){
    $('#updatingPopup').modal('show')
    var error;
    var alert;
    if(!(updating())) {
        window.setTimeout(function(){
            $.ajax({
                url: '/updatePlayers',
                type: 'GET',
                async: false,
                success: function (result) {
                    error = result;
                    if(!error) {
                        alert = $('<div id="successAlert" class="alert alert-success alert-dismissable fade in" role="alert" style="margin-left:10px; width:300px"/>')
                            .append('<span class="close" data-dismiss="alert" aria-label="close">&times;</span>')
                            .append('Players updated successfully!');
                        $(alert).insertAfter('#updateBtn');
                        $('#updatingPopup').modal('hide');
                        window.setTimeout(function () {
                            $('#successAlert').alert('close');
                        }, 5000);
                    }
                    else{
                        alert = $('<div id="errorAlert" class="alert alert-danger alert-dismissable fade in" role="alert" style="margin-left:10px; width:500px"/>')
                            .append('<span class="close" data-dismiss="alert" aria-label="close">&times;</span>')
                            .append('<strong>Error. </strong>')
                            .append('An error occurred while updating. Default rosters will be used.');
                        $(alert).insertAfter('#updateBtn');
                        window.setTimeout(function(){
                            $('#errorAlert').alert('close');
                        }, 5000);
                    }
                }
            });
        }, 500);
    }
    else{
        alert = $('<div id="errorAlert" class="alert alert-danger alert-dismissable fade in" role="alert" style="margin-left:10px; width:500px"/>')
            .append('<span class="close" data-dismiss="alert" aria-label="close">&times;</span>')
            .append('<strong>Error. </strong>')
            .append('Someone is currently updating the rosters. Please wait.');
        $(alert).insertAfter('#updateBtn');
        window.setTimeout(function(){
            $('#errorAlert').alert('close');
        }, 5000);
    }
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
    if(!(updating())) {
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
    else{
        alert = $('<div id="errorAlert" class="alert alert-danger alert-dismissable fade in" role="alert" style="margin-left:10px; width:500px"/>')
            .append('<span class="close" data-dismiss="alert" aria-label="close">&times;</span>')
            .append('<strong>Error. </strong>')
            .append('Someone is currently updating the rosters. Please wait.');
        $(alert).insertAfter('#startBtn');
        window.setTimeout(function(){
            $('#errorAlert').alert('close');
        }, 5000);
    }
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