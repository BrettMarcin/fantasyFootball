$(function() {
	
	var infoOfLastCLicked = null;
	
	$("tr.playerInfo").click(function(){
	    $("tr.playerInfo").removeClass('selectedRow');
	    $(this).addClass('selectedRow');
	    infoOfLastCLicked = $(this).children('td.playerName').text();
	    infoOfLastCLicked = (String)(infoOfLastCLicked);
	});
	
	$("#draftButton").click(function(){
	    if(infoOfLastCLicked != null){
	    	$.ajax({
	            url: '/FantasyFootball/draftPlayer',
	            type: "POST",
	            dataType: "json",
	            data: {"thePlayer": infoOfLastCLicked},
	            async: false
	        });
	    }
	    
	    infoOfLastCLicked = null;
	});
});