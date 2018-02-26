<!DOCTYPE HTML>
<html>
<head>
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<spring:url value="/WebContent/resources/scripts/home.js" var="theJS" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/3.3.5/js/bootstrap.min.js"></script>
	<spring:url value="/WebContent/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" var="bootstrap" />
	<spring:url value="/WebContent/resources/stylesheets/table.css" var="theCSS" />
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
	<script src="https://cdnjs.cloudflare.com/ajax/libs/particlesjs/2.0.2/particles.js"></script>
	<script src="/WebContent/resources/js/sockjs-0.3.4.js"></script>
	<script src="/WebContent/resources/js/stomp.js"></script>
	<script src ="/WebContent/resources/messaging.js"></script>
	<script src ="/WebContent/resources/Logic.js"></script>
	<script src="${theJS}"></script>
	<link href="${bootstrap}" rel="stylesheet" />
	<link rel="stylesheet" type="text/css" href="${theCSS}" />
	<meta http-equiv="X-UA-Compatible" content="IE=edge" />
	<%@ page import ="java.util.*" %>
	<%@ page import ="com.home.Team" %>
	<% List<Team> teams = (List<Team>)request.getAttribute("theTeams"); %>
	<% Team localTeam = (Team)request.getAttribute("localTeam"); %>
</head>
<body>

<h1 style="margin-left: 10px;"> Welcome to Fantasy Football! </h1>
<h2 style="margin-left: 10px;">Created by Brett Marcinkiewicz and Jacob Kahn</h2>

<% if(localTeam == null) { %>
<button id="updateBtn" class="btn btn-primary" style="margin-left: 10px; margin-bottom: 10px" onclick="updatePlayers()">Update Rosters</button>
<div class="modal fade" id="updatingPopup" tabindex="-1" role="dialog" aria-labelledby="updateTitle" aria-hidden="true">
	<div class="modal-dialog" role="document">
		<div class="modal-content">
			<div class="modal-header">
				<h1 class="modal-title" id="updateTitle">Updating.....</h1>
			</div>
		</div>
	</div>
</div>
<script>
	connectTeams();
</script>
<div class="container panel panel-default">
	<h3>Create your team!</h3>
	<div class="row">
		<div class="col-sm-8 panel">
			<div class="form-group">
				<label for="teamName">Enter Team Name:</label>
				<input class="form-control" id="teamName">
			</div>
			<div class="form-group">
				<label for="theName">Enter Your Name:</label>
				<input class="form-control" id="theName">
			</div>
			<button type="submit" class="btn btn-primary" onclick="setLocalTeam()">Submit</button>
		</div>
	</div>
</div>
<% } else { %>
<script>
	connectMessage();
</script>
<h3 style="margin-left:10px;">Welcome! <%=localTeam.teamName%> press "Start Draft!" to start the draft</h3>
<button id="startBtn" class="btn btn-primary" onclick="startDraft()" style="margin-left:10px; margin-bottom:10px">Start Draft!</button>
<div class="row">
	<div class="col-sm-4" style="clear:left;margin-left:10px">
		<div class="container panel panel-default" style="width:500px;">
			<h3>Create a CPU!</h3>
			<div class="row">
				<div class="col-sm-8 panel">
					<form action="addACpu" method="POST">
						<div class="form-group">
							<label for="CpuName">Enter CPU Team Name:</label>
							<input class="form-control" id="CpuName">
						</div>
						<button type="submit" class="btn btn-primary" onclick="addACpu()">Add CPU</button>
					</form>
				</div>
			</div>
		</div>
	</div>
	<div class="col-sm-4" style="margin-left:70px; margin-top:-55px">
		<h3>Live Messaging</h3>
		<textarea id="area" style="width: 500px; height: 150px;overflow-y:scroll" readonly></textarea>
		<div class="form-group">
			<label>Enter message:</label>
			<input type="text" class="form-control" id="text<%=localTeam.id%>">
		</div>
		<button id="send" class="btn btn-default" onclick="send(<%=localTeam.id%>)">Submit</button>
		<script>
            fillMessages();
		</script>
	</div>
</div>

<% } %>

<div class="container panel panel-default">
	<h3> Current users in Session:</h3>
		<div class="row">
			<div class="col-sm-8">
				<div class="list-group" id="listOfSessionTeams">
					<% for(Team aTeam : teams){ %>
					<% if (aTeam != null) %>
					<a href="#" class="list-group-item currentTeamInSession">Team Name: <%=aTeam.teamName%>, Owned by: <%=aTeam.name%></a>
					<% } %>
				</div>
			</div>
		</div>
</div>

</body>
</html>