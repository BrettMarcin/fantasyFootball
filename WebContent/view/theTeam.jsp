<%@ page import ="java.util.*" %>
<%@ page import ="com.home.Team" %>
<%@ page import ="com.home.Player" %>
<% Team localTeam = (Team)request.getAttribute("localTeam"); %>
<% List<Player> RB = localTeam.RB; %>

<h2><%=localTeam.teamName%></h2>
<% if(RB != null){ %>
	<% for(Player aPlayer : RB){ %>
		<p><%=aPlayer.first%> <%=aPlayer.last%></p>
	<% } %>
<% } %>