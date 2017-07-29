$(function() {
	
	var infoOfLastCLicked = null;
	var playerRank = null;
	var team = null;
	var pos = null;
	var rank = null;
	
	$("tr.playerInfo").click(function(){
	    $("tr.playerInfo").removeClass('selectedRow');
	    $(this).addClass('selectedRow');
	    infoOfLastCLicked = $(this).children('td.playerName').text();
	    playerRank = $(this).children('td.playerRank').text();
	    pos = $(this).children('td.playerPos').text();
	    team = $(this).children('td.playerTeam').text();
	    infoOfLastCLicked = (String)(infoOfLastCLicked);
	    infoOfLastCLicked = (infoOfLastCLicked).split(/\s+/);;
	});
	
	$("#draftButton").click(function(){
		
	    if(infoOfLastCLicked != null){
	    	jsonData = {
                    first: infoOfLastCLicked[0],
                    last: infoOfLastCLicked[1],
                    pos: pos,
                    team: team
	    	};
	    	console.log(jsonData);
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
	});
});

function getTeam(){
    $.ajax({
        url: '/getTeam',
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

function addToPlayerTable(data){
    console.log(data);
    var theTableRow = '';
    if (data.QB !== null){
        theTableRow = '<td>' + data.QB.first + '</td><td>' + data.QB.last + '</td>';
        $('#qb_id').append(theTableRow);
    }
    if (data.RB !== null){
        theTableRow = '<td>' + data.RB.first + '</td><td>' + data.RB.last + '</td>';
        $('#rb1_id').append(theTableRow);
    }
    if (data.WR !== null){
        theTableRow = '<td>' + data.WR.first + '</td><td>' + data.WR.last + '</td>';
        $('#wr1_id').append(theTableRow);
    }
    if (data.TE !== null){
        theTableRow = '<td>' + data.TE.first + '</td><td>' + data.TE.last + '</td>';
        $('#te_id').append(theTableRow);
    }
}