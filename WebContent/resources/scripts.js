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
	    		team: team,
	    		rank: parseInt(playerRank)
	    	}
	    	$.ajax({
	            url: '/FantasyFootball/draftPlayer',
	            type: "POST",
	            headers: { 
	                'Accept': 'application/json',
	                'Content-Type': 'application/json' 
	            },
	            processData: false,
	            dataType: "json",
	            data: JSON.stringify(jsonData),
	            async: true,
	            success: function () {
                    window.location.reload();
                }
	        });
	    }
	    location.reload();
	    infoOfLastCLicked = null;
	    playerRank = null;
	    team = null;
		pos = null;
	});
});