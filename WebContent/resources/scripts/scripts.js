var theLastRowSelected = '';
var infoOfLastCLicked = null;
var playerRank = null;
var team = null;
var pos = null;
var rank = null;
var player = null;
var draftStompClient = null;

$(function() {
    getTeam($('#theTeamName').text());
    clickRow();
    $( "#resizable" ).resizable();

    $('a.dropdown-item').click(function(){
        getTeam($(this).text());
    });
    getTime();
});

function connectDraft() {
    var draftSocket = new SockJS('/chat');
    draftStompClient = Stomp.over(draftSocket);
    draftStompClient.connect({}, function (frame) {
        draftStompClient.subscribe('/topic/addPlayer', function (success) {
            postDraft(success);
        });
    });
}

function postDraft(success){
    updateTeam();
    updateTimeline();
    getLastPick();
    pollServer();
    checkIfDraftIsRunning();
    $('#SelectedPlayerP').text('');
}

function getTeam(theTeam){
    $.ajax({
        url: '/getTeam/' + theTeam,
        type: "GET",
        headers: {
            'Accept': 'application/json'
        },
        processData: false,
        async: true,
        success: function (data) {
            addToPlayerTable(data);
        }
    });
};

function getDraftHistory(){
    var stringBuilder;
    $.ajax({
        dataType: 'json',
        url: '/getDraftHistory',
        type: "GET",
        processData: false,
        async: true,
        success: function (data) {
            $('.draftRow').remove();
            if(data != null){
                for(var i = 0; i < data.length; i++){
                    stringBuilder = '<tr class="draftRow"><td>' + (i + 1) + '</td><td>' + data[i].pos + '</td>';
                    stringBuilder += '<td>' + data[i].first + ' ' + data[i].last + '</td>';
                    stringBuilder += '<td>' + data[i].teamOwner + '</td></tr>';
                    $('#DraftHistoryBody').append(stringBuilder);
                }
            }
        }
    });
};

function checkIfDraftIsRunning(){
    $.ajax({
        url: '/isDraftStillGoing',
        type: "GET",
        headers: {
            'Accept': 'application/json'
        },
        processData: false,
        async: false,
        success: function (data) {
            if (!data) {
                window.location.reload();
            }
        }
    });
};

function updateTeam(){
    var theTeam;
    theTeam = $('#theTeamName').text();
    getTeam(theTeam);
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

function updateTimeline(){
    $.ajax({
        async: false,
        type: "GET",
        url: '/getTimeline',
        success: function (result) {
            var stringBuilder = '';
            var round = getCurrentRound();
            var pick = getCurrentPick();
            $('.timelineItem').remove();
            for (var i = 0; i < result.length; i++){
                if(result[i].teamName === 'Round'){
                    stringBuilder += '<li class="list-group-item timelineItem round">Round ' + round + '</li>';
                    round++;
                } else {
                    stringBuilder += '<li class="list-group-item timelineItem team">#' + pick + ' ' + result[i].teamName + '</li>';
                    pick++;
                }
            }
            $('#timeline').append(stringBuilder);
        },
        error: function () {
            //updateTimeline();
        }});
}

function addToPlayerTable(data){
    var theTableRow = '';
    $("#theTeamName").text(data.teamName);
    //$('td.teamPlayers').remove();
    $('td.pos_name').text('');
    if (data.QB !== null){
        //theTableRow = '<td class="teamPlayers">' + data.QB.first + '</td><td class="teamPlayers">' + data.QB.last + '</td>';
        //$('#qb_id').append(theTableRow);
        $('#QB_first').text(data.QB.first);
        $('#QB_last').text(data.QB.last);
    }
    if (data.RB1 !== null){
        //theTableRow = '<td class="teamPlayers">' + data.RB1.first + '</td><td class="teamPlayers">' + data.RB1.last + '</td>';
        //$('#rb1_id').append(theTableRow);
        $('#RB1_first').text(data.RB1.first);
        $('#RB1_last').text(data.RB1.last);
    }
    if (data.RB2 !== null){
        //theTableRow = '<td class="teamPlayers">' + data.RB2.first + '</td><td class="teamPlayers">' + data.RB2.last + '</td>';
        //$('#rb2_id').append(theTableRow);
        $('#RB2_first').text(data.RB2.first);
        $('#RB2_last').text(data.RB2.last);
    }
    if (data.WR1 !== null){
        //theTableRow = '<td class="teamPlayers">' + data.WR1.first + '</td><td class="teamPlayers">' + data.WR1.last + '</td>';
        //$('#wr1_id').append(theTableRow);
        $('#WR1_first').text(data.WR1.first);
        $('#WR1_last').text(data.WR1.last);
    }
    if (data.WR2 !== null){
        //theTableRow = '<td class="teamPlayers">' + data.WR1.first + '</td><td class="teamPlayers">' + data.WR1.last + '</td>';
        //$('#wr2_id').append(theTableRow);
        $('#WR2_first').text(data.WR2.first);
        $('#WR2_last').text(data.WR2.last);
    }
    if (data.TE !== null){
        //theTableRow = '<td class="teamPlayers">' + data.TE.first + '</td><td class="teamPlayers">' + data.TE.last + '</td>';
        //$('#te_id').append(theTableRow);
        $('#TE_first').text(data.TE.first);
        $('#TE_last').text(data.TE.last);
    }
    if (data.FLEX !== null){
        //theTableRow = '<td class="teamPlayers">' + data.FLEX.first + '</td><td class="teamPlayers">' + data.FLEX.last + '</td>';
        //$('#flex_id').append(theTableRow);
        $('#FLEX_first').text(data.FLEX.first);
        $('#FLEX_last').text(data.FLEX.last);
    }
    $('#bench_body').remove();
    theTableRow = '<tbody id="bench_body"></tbody>';
    $(theTableRow).insertAfter('#bench_header');
    for (var i = 0; i < data.bench.length; i++){
        theTableRow = '<tr><td class="pos_name">' +  data.bench[i].pos + '</td><td class="pos_name">' + data.bench[i].first + '</td><td class="pos_name">' + data.bench[i].last + '</td></tr>';
        $('#bench_body').append(theTableRow);
    }
}

function getTime(){
    window.setTimeout(function () {
        $.ajax({
            url: '/getTime',
            type: "GET",
            success: function (result) {
                var minutes = 1 - parseInt((result/(1000*60))%60);
                var sec = 60 - parseInt((result/1000)%60);
                if (sec < 10){
                    sec = '0' + sec.toString();
                }
                minutes = '0' + minutes.toString();
                $('#minute').text(minutes);
                $('#seconds').text(sec);
                getTime();
            },
            error: function () {
                //getTime();
            }});
    }, 980);
}

function pollServer() {
    $.ajax({
        url: '/getPlayers',
        type: "GET",
        success: function (result) {
            updateDraftTable(result);
        },
        error: function () {
            //pollServer();
        }});
}

function updateDraftTable(players){
    var StringBuilder;
    $('.thePlayer').remove();
    var foundThePlayer = true;
    //var foundThePlayer = false;
    for(var i = 0; i < players.length; i++){
        if(infoOfLastCLicked != null && (players[i].first === infoOfLastCLicked[0] && players[i].last === infoOfLastCLicked[1])){
            foundThePlayer = true;
        }
        StringBuilder = '<tr class="thePlayer playerInfo ' + players[i].first + players[i].last + players[i].pos + players[i].team + '">';
        StringBuilder += '<td class="thePlayer playerRank">' + players[i].rank + '</td>';
        StringBuilder += '<td class="thePlayer playerName">' + players[i].first + ' ' + players[i].last + '</td>';
        StringBuilder += '<td class="thePlayer playerTeam">' + players[i].team + '</td>';
        StringBuilder += '<td class="thePlayer playerPos">' + players[i].pos + '</td>';
        StringBuilder += '<td class="thePlayer pointsLast">' + players[i].Fpoints + '</td>';
        StringBuilder += '<td class="thePlayer passYDs">' + players[i].passYards + '</td>';
        StringBuilder += '<td class="thePlayer passTDs">' + players[i].passTDs + '</td>';
        StringBuilder += '<td class="thePlayer ints">' + players[i].ints + '</td>';
        StringBuilder += '<td class="thePlayer rushYDs">' + players[i].rushYards + '</td>';
        StringBuilder += '<td class="thePlayer rushTDs">' + players[i].rushTDs + '</td>';
        StringBuilder += '<td class="thePlayer recYards">' + players[i].recYards + '</td>';
        StringBuilder += '<td class="thePlayer recTDs">' + players[i].recTDs + '</td></tr>';
        $('#tbody_draft_table').append(StringBuilder);
    }

    if(foundThePlayer) {
        var theArray = theLastRowSelected.split(' ');
        if (theArray.length === 6) {
            var theRow = '.' + theArray[0] + '.' + theArray[1] + '.' + theArray[2] + theArray[3] + theArray[4] + theArray[5];
        } else {
            var theRow = '.' + theArray[0] + '.' + theArray[1] + '.' + theArray[2];
        }
        if (theLastRowSelected !== '') {
            $(theRow).addClass('selectedRow');
        }
        clickRow();
    } else {
        infoOfLastCLicked = null;
        playerRank = null;
        team = null;
        pos = null;
        $('#SelectedPlayerP').text('');
    }
}

function clickRow(){
    $('tr.thePlayer.playerInfo').click(function(){
        $(".thePlayer.playerInfo").removeClass('selectedRow');
        theLastRowSelected = $(this).attr("class");
        $(this).addClass('selectedRow');
        infoOfLastCLicked = $(this).children('td.thePlayer.playerName').text();
        playerRank = $(this).children('td.thePlayer.playerRank').text();
        pos = $(this).children('td.thePlayer.playerPos').text();
        team = $(this).children('td.thePlayer.playerTeam').text();
        infoOfLastCLicked = (String)(infoOfLastCLicked);
        infoOfLastCLicked = (infoOfLastCLicked).split(/\s+/);
        $('#SelectedPlayerP').text(infoOfLastCLicked[0] + ' ' + infoOfLastCLicked[1]);
    });
}

function getCookie(){
    var cookie = null;
    $.ajax({
        async: false,
        type: 'GET',
        url: '/getCookie',
        dataType: 'text',
        success: function(result){
            cookie = result;
        }
    });
    return cookie;
}

function draftButton() {
    if (infoOfLastCLicked != null) {
        player = {
            first: infoOfLastCLicked[0],
            last: infoOfLastCLicked[1],
            pos: pos,
            team: team
        };
        var cookieVal = getCookie();
        infoOfLastCLicked = null;
        playerRank = null;
        team = null;
        pos = null;
        draftStompClient.send("/app/draftPlayer", {}, JSON.stringify({'player': player, 'cookieValue': cookieVal}));
    }
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

function getCurrentPick(){
    var theData = -79;
    $.ajax({
        async: false,
        type: "GET",
        url: '/getPick',
        success: function (result) {
            theData = result;
        }
    });
    return theData;
}

function getLastPick() {
    $.ajax({
        dataType: 'json',
        url: '/getLastPlayerDrafted',
        type: "GET",
        success: function (result) {
            if(result !== null){
                $('#lastSelectedPlayerP').text(result.first + ' ' + result.last);
            }
        },
        error: function () {
        }});
}

function getCurrentRound(){
    var theData = -79;
    $.ajax({
        async: false,
        type: "GET",
        url: '/getRound',
        success: function (result) {
            theData = result;
        }
    });
    return theData;
}

function resetDraft(){
    $.ajax({
        async: false,
        type: "GET",
        url: '/resetDraft',
        success: function () {
            window.location.reload();
        }
    });
}