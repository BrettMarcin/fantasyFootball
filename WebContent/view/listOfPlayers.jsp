<!DOCTYPE HTML>
<html>
<head> 
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<spring:url value="/WebContent/resources/scripts/scripts.js" var="theJS" />
	<spring:url value="/WebContent/resources/stylesheets/table.css" var="theCSS" />
    <spring:url value="/WebContent/resources/stylesheets/led.css" var="ledCSS" />
	<spring:url value="/WebContent/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" var="bootstrap" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
	<script src="${theJS}"></script>
	<link rel="stylesheet" type="text/css" href="${theCSS}" />
    <link rel="stylesheet" type="text/css" href="${ledCSS}" />
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

<div class="clock panel panel-default" id="theClock">
    <div id="minute">02</div>:<div id="seconds">00</div>
</div>

<ul class="list-group timeline panel panel-default" id="timeline">
	<% for (Team aTeam : theTimeline) {%>
	<% String theRound = String.valueOf(round); %>
	<% String pickNum = String.valueOf(pickNumber); %>
	<% if(aTeam.teamName == "Round"){ %>
	<li class="list-group-item timelineItem round">Round <%=theRound%></li>
	<% round++; %>
	<%} else { %>
	<li class="list-group-item timelineItem team">#<%=pickNum%> <%=aTeam.teamName%></li>
	<% pickNumber++;%>
	<%} %>
	<% } %>
</ul>
<br>
<button id="draftButton" onclick="draftButton()">Draft player</button>

<div class="row">
	<div class="col-md-8">
		<h2>Draft List</h2>
		<table id="resizable" class="ui-widget-content allPlayers table table-sm table-bordered table-hover ">
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
			<tbody id="tbody_draft_table" class="table-striped">
				<% for(Player aPlayer : players){ %>
			<tr class="thePlayer playerInfo <%= aPlayer.first %> <%= aPlayer.last %> <%= aPlayer.pos %> <%= aPlayer.team %>">
				<td class="thePlayer playerRank"><%= aPlayer.rank %></td>
				<td class="thePlayer playerName"><%= aPlayer.first + " " + aPlayer.last %></td>
				<td class="thePlayer playerTeam"><%= aPlayer.team %></td>
				<td class="thePlayer playerPos"><%= aPlayer.pos %></td>
				<td class = "thePlayer pointsLast"><%= aPlayer.Fpoints  %></td>
				<td class = "thePlayer passYDs"><%= aPlayer.passYards %></td>
				<td class = "thePlayer passTDs"><%= aPlayer.passTDs %></td>
				<td class = "thePlayer ints"><%= aPlayer.ints %></td>
				<td class = "thePlayer rushYDs"><%= aPlayer.rushYards %></td>
				<td class = "thePlayer rushTDs"><%= aPlayer.rushTDs %></td>
				<td class = "thePlayer recYards"><%= aPlayer.recYards %></td>
				<td class = "thePlayer recTDs"><%= aPlayer.recTDs %></td>
			</tr>
				<% } %>
            </tbody>
		</table>
	</div>
	<div class="col-md-4">
		<jsp:include page="theTeam.jsp" />
	</div>
</div>
