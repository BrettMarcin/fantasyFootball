var theLastRowSelected = '';
var infoOfLastCLicked = null;
var playerRank = null;
var team = null;
var pos = null;
var rank = null;

$(function() {
    getTeam($('#theTeamName').text());
    clickRow();
    $( "#resizable" ).resizable();

    $('a.dropdown-item').click(function(){
        getTeam($(this).text());
    });
    getTime();
    pollServer();
});

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
function getMessages(){
    $.ajax({
        url: '/getMessages',
        type: "GET",
        headers: {
            'Accept': 'application/json'
        },
        processData: false,
        async: true,
        success: function (data){
            displayMessages(data);
        }
    });
}

function addToPlayerTable(data){
    var theTableRow = '';
    $("#theTeamName").text(data.teamName);
    $('td.teamPlayers').remove();
    if (data.QB !== null){
        theTableRow = '<td class="teamPlayers">' + data.QB.first + '</td><td class="teamPlayers">' + data.QB.last + '</td>';
        $('#qb_id').append(theTableRow);
    }
    if (data.RB1 !== null){
        theTableRow = '<td class="teamPlayers">' + data.RB1.first + '</td><td class="teamPlayers">' + data.RB1.last + '</td>';
        $('#rb1_id').append(theTableRow);
    }
    if (data.RB2 !== null){
        theTableRow = '<td class="teamPlayers">' + data.RB2.first + '</td><td class="teamPlayers">' + data.RB2.last + '</td>';
        $('#rb2_id').append(theTableRow);
    }
    if (data.WR1 !== null){
        theTableRow = '<td class="teamPlayers">' + data.WR1.first + '</td><td class="teamPlayers">' + data.WR1.last + '</td>';
        $('#wr1_id').append(theTableRow);
    }
    if (data.WR2 !== null){
        theTableRow = '<td class="teamPlayers">' + data.WR1.first + '</td><td class="teamPlayers">' + data.WR1.last + '</td>';
        $('#wr2_id').append(theTableRow);
    }
    if (data.TE !== null){
        theTableRow = '<td class="teamPlayers">' + data.TE.first + '</td><td class="teamPlayers">' + data.TE.last + '</td>';
        $('#te_id').append(theTableRow);
    }
    if (data.FLEX !== null){
        theTableRow = '<td class="teamPlayers">' + data.FLEX.first + '</td><td class="teamPlayers">' + data.FLEX.last + '</td>';
        $('#flex_id').append(theTableRow);
    }
    for (var i = 0; i < data.bench.length; i++){
        theTableRow = '<tr><td class="teamPlayers">' +  data.bench[i].pos + '</td><td class="teamPlayers">' + data.bench[i].first + '</td><td class="teamPlayers">' + data.bench[i].last + '</td></tr>';
        $(theTableRow).insertAfter('#bench_header');
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
                updateClock(minutes, sec);
                getTime();
            },
            error: function () {
                getTime();
            }});
    }, 980);
}

function pollServer() {
    window.setTimeout(function () {
        $.ajax({
            url: '/getPlayers',
            type: "GET",
            success: function (result) {
                updateDraftTable(result);
                pollServer();
            },
            error: function () {
                pollServer();
            }});
    }, 2000);
}

function updateDraftTable(players){
    var StringBuilder;
    $('.thePlayer').remove();

    for(var i = 0; i < players.length; i++){
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


    var theArray = theLastRowSelected.split(' ');
    if(theArray.length === 6){
        var theRow = '.' + theArray[0] + '.' + theArray[1] + '.' + theArray[2] + theArray[3] + theArray[4] + theArray[5];
    } else {
        var theRow = '.' + theArray[0] + '.' + theArray[1] + '.' + theArray[2];
    }
    if(theLastRowSelected !== ''){
        $(theRow).addClass('selectedRow');
    }
    clickRow();
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
    });
}

function draftButton() {
    theLastRowSelected = null;
    $(theLastRowSelected).removeClass('selectedRow');
    if (infoOfLastCLicked != null) {
        jsonData = {
            first: infoOfLastCLicked[0],
            last: infoOfLastCLicked[1],
            pos: pos,
            team: team
        };
        $.ajax({
            url: '/draftPlayer',
            type: "POST",
            contentType: "application/json",
            processData: false,
            dataType: "json",
            data: JSON.stringify(jsonData),
            async: true,
            success: function () {
                window.location.reload();
            }
        });
    }
    window.location.reload();
    infoOfLastCLicked = null;
    playerRank = null;
    team = null;
    pos = null;
}

function updateClock(minutes, seconds){
    var led = document.getElementById('led'),
        els = led.childNodes,
        uid=0, size=15, w=0, h=0, row=0, col=0,
        arr_lights=[];

    var mm = document.getElementById('time-mm'),
        mx = document.getElementById('time-m'),
        ss = document.getElementById('time-ss'),
        sx = document.getElementById('time-s');

    for(var k=0, len=els.length; k<len; k++){
        if(els[k].nodeType!=1)
            continue;
        w = parseInt(els[k].clientWidth);
        h	= parseInt(els[k].clientHeight);
        row	= parseInt(h/size);
        col	= parseInt(w/size);

        var t, l, sum=0;
        for(var i=0; i<row; i++){
            for(var j=0; j<col; j++){
                uid++;
                t = size*i;
                l = size*j;
                arr_lights.push( '<div uid="'+uid+'" id="l-'+uid+'" class="light row-'+i+' col-'+j+'" style="top:'+t+'px;left:'+l+'px"></div>');
            }
        }
        els[k].innerHTML = arr_lights.join("");
        arr_lights=[];
    }
    if (parseInt(minutes) === 0){
        mm.className = "block-digital num-"+parseInt(0);
        mx.className = "block-digital num-"+parseInt(0);
    } else {
        mm.className = "block-digital num-"+parseInt(minutes/10);
        mx.className = "block-digital num-"+parseInt(minutes%10);
    }
    ss.className = "block-digital num-"+parseInt(seconds/10);
    sx.className = "block-digital num-"+parseInt(seconds%10);
}