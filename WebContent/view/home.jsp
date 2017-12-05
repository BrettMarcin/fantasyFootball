<!DOCTYPE HTML>
<html>
<head> 
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<spring:url value="/WebContent/resources/scripts/home.js" var="theJS" />
	<spring:url value="/WebContent/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" var="bootstrap" />
	<script type="text/javascript" src="http://code.jquery.com/jquery-1.7.1.min.js"></script>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
	<script src="/WebContent/resources/js/sockjs-0.3.4.js"></script>
	<script src="/WebContent/resources/js/stomp.js"></script>
	<script src ="/WebContent/resources/messaging.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/particlesjs/2.0.2/particles.js"></script>
	<script src="${theJS}"></script>
	<link href="${bootstrap}" rel="stylesheet" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <%@ page import ="java.util.*" %>
	<%@ page import ="com.home.Team" %>
    <% List<Team> teams = (List<Team>)request.getAttribute("theTeams"); %>
    <% Team localTeam = (Team)request.getAttribute("localTeam"); %>
</head>
<body>

<h1 id = "welcome"> Welcome to Fantasy Football! </h1>
<h2>Created by Brett Marcinkiewicz and Jacob Kahn</h2>
<button id="testButton" type="submit" class="btn btn-primary" style="margin-left: 10px; margin-bottom: 10px" onclick="sendForm()">Click</button>
<% if(localTeam == null) { %>
<table id="homeTable" class="table table-striped">
	<td>
		<div class="container panel panel-default" style="width:100%;">
			<h3>Create your team!</h3>
			<div class="row">
				<div class="col-sm-8 panel">
			<form action="new-question" method="POST">
				<div class="form-group">
					<label for="TeamNameInput">Enter Team Name:</label>
					<input class="form-control" name="TeamNameInput">
				</div>
				<div class="form-group">
					<label for="theName">Enter Your Name:</label>
					<input class="form-control" name="theName">
				</div>
				<button type="submit" class="btn btn-primary">Submit</button>
			</form>
				</div>
			</div>
		</div>
	</td>
	<td>
		<div>
			<div>
				<input type="text" id="author" placeholder="Choose a nickname"/>
			</div>
			<br />
			<div>
				<button id="connect">Connect</button>
				<button id="disconnect" disabled="disabled">
					Disconnect
				</button>
			</div>
			<br />
			<div id="conversationDiv">
				<input type="text" id="text" placeholder="Write a message..."/>
				<button id="send">Send</button>
				<p id="response"></p>
			</div>
		</div>
	</td>
</table>
<% } else { %>
	<h3>Welcome! <%=localTeam.teamName%> press start to start the draft</h3>
	<form action="startDraft" method="GET">
		<button type="submit" class="btn btn-primary">Start Draft!</button>
	</form>
<div class="container panel panel-default">
    <h3>Create a CPU!</h3>
    <div class="row">
        <div class="col-sm-8 panel">
            <form action="addACpu" method="POST">
                <div class="form-group">
                    <label for="CpuName">Enter CPU Team Name:</label>
                    <input class="form-control" name="CpuName">
                </div>
                <button type="submit" class="btn btn-primary">Add CPU</button>
            </form>
        </div>
    </div>
</div>

<% } %>

<div class="container panel panel-default">
<h3> Current users in Session:<h3>
	<div class="row">
		<div class="col-sm-8">
<div class="list-group" id="listOfSessionTeams">
	<% for(Team aTeam : teams){ %>
		<% if (aTeam != null) %>
	<a href="#" class="list-group-item currentTeamInSession">Team Name: <%=aTeam.teamName%>, Owned by: <%=aTeam.name%></a>
	<% } %>>
</div>
		</div>
	</div>
</div>
</body>
</html>