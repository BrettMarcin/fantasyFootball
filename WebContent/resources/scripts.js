$(function() {
	
	var infoOfLastCLicked = null;
	var playerRank = null;
	
	$("tr.playerInfo").click(function(){
	    $("tr.playerInfo").removeClass('selectedRow');
	    $(this).addClass('selectedRow');
	    infoOfLastCLicked = $(this).children('td.playerName').text();
	    playerRank = $(this).children('td.playerRank').text();
	    infoOfLastCLicked = (String)(infoOfLastCLicked);
	    infoOfLastCLicked = (infoOfLastCLicked).split(/\s+/);;
	});
	
	$("#draftButton").click(function(){
		
		var player = infoOfLastCLicked[0].toString() + " " + infoOfLastCLicked[1].toString() + " " + playerRank;
		
	    if(infoOfLastCLicked != null){
	    	$.ajax({
	            url: '/FantasyFootball/draftPlayer',
	            type: "POST",
	            processData: false,
	            dataType: "json",
	            data: player,
	            async: false
	        });
	    }
	    infoOfLastCLicked = null;
	    playerRank = null;
	});
});