$(function() {
	$("tr.playerInfo").click(function(){
	    $("tr.playerInfo").removeClass('selectedRow');
	    $(this).addClass('selectedRow');
	});
});