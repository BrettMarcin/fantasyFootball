<!DOCTYPE HTML>
<html>
<head> 
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<spring:url value="/WebContent/resources/scripts.js" var="theJS" />
	<spring:url value="/WebContent/resources/table.css" var="theCSS" />
	<spring:url value="/WebContent/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" var="bootstrap" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="${theJS}"></script>
	<link href="${theCSS}" rel="stylesheet" />
	<link href="${bootstrap}" rel="stylesheet" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <%@ page import ="java.util.*" %>
	<%@ page import ="com.home.Team" %>
	<%@ page import ="com.home.Player" %>
    <% List<Team> teams = (List<Team>)request.getAttribute("theTeams"); %>
    <% Team localTeam = (Team)request.getAttribute("localTeam"); %>
    <% ArrayList<Player> players = (ArrayList<Player>)request.getAttribute("listOfPlayers"); %>
    <% ArrayList<Team> theTimeline = (ArrayList<Team>)request.getAttribute("theTimeline"); %>
    <% int pickNumber = (int)request.getAttribute("pickNumber"); %>
    <% int round = (int)request.getAttribute("round"); %>
</head>

<ul class="list-group" id="timeline">
<% for (Team aTeam : theTimeline) {%>
	<% String theRound = String.valueOf(round); %>
	<% String pickNum = String.valueOf(pickNumber); %>
	<% if(aTeam.teamName == "Round"){ %>
		<li class="list-group-item">Round <%=theRound%></li>
		<% round++; %>
	<%} else { %>
		<li class="list-group-item"><%=pickNum%> <%=aTeam.teamName%></li>
		<% pickNumber++;%>
	<%} %>
<% } %>
</ul>

<button id="draftButton">Draft player</button>

<h2>Draft List</h2>
<table class="allPlayers table table-bordered table-hover">
	<thead>
	<tr class="TableHead">
		<th>Rank</th>
		<th>Player</th>
		<th>Team</th>
		<th>Position</th>
		<th>Fantasy Total 2016</th>
		<th>Pass YDS</th>
		<th>Pass TDS</th>
		<th>INT</th>
		<th>Rush YDS</th>
		<th>Rush TDS</th>
		<th>REC YDS</th>
		<th>REC TDS</th>
	</tr>
	</thead>
	<tbody class-"table-striped">
	<% for(Player aPlayer : players){ %>
		<tr class="playerInfo">
			<td class="playerRank"><%= aPlayer.rank %></td>
			<td class="playerName"><%= aPlayer.first + " " + aPlayer.last %></td>
			<td class="player"><%= aPlayer.team %></td>
			<td class="player"><%= aPlayer.pos %></td>
			<td class = "pointsLast"><%= aPlayer.Fpoints  %></td>
			<td class = "passYDs"><%= aPlayer.passYards %></td>
			<td class = "passTDs"><%= aPlayer.passTDs %></td>
			<td class = "ints"><%= aPlayer.ints %></td>
			<td class = "rushYDs"><%= aPlayer.rushYards %></td>
			<td class = "rushTDs"><%= aPlayer.rushTDs %></td>
			<td class = "recYards"><%= aPlayer.recYards %></td>
			<td class = "recTDs"><%= aPlayer.recTDs %></td>
		</tr>
	<% } %>
	</tbody>
</table>
