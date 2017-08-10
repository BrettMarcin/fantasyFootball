<!DOCTYPE HTML>
<html>
<head> 
	<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
	<spring:url value="/WebContent/resources/scripts/home.js" var="theJS" />
	<spring:url value="/WebContent/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" var="bootstrap" />
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
	<script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
	<script src="${theJS}"></script>
	<link href="${bootstrap}" rel="stylesheet" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <%@ page import ="java.util.*" %>
	<%@ page import ="com.home.Team" %>
    <% List<Team> teams = (List<Team>)request.getAttribute("theTeams"); %>
    <% Team localTeam = (Team)request.getAttribute("localTeam"); %>
</head>
<body>

<h1> Welcome to Fantasy Football! </h1>
<h2>Created by Brett Marcinkiewicz and Jacob Kahn</h2>

<% if(localTeam == null) { %>
<div class="container panel panel-default">
	<h3>Create your team!</h3>
	<div class="row">
		<div class="col-sm-8 panel">
	<form action="setLocalTeam" method="POST">
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
<% } else { %>
	<h3>Welcome! <%=localTeam.teamName%> press start to start the draft</h3>
	<form action="startDraft" method="GET">
		<button type="submit" class="btn btn-primary">Start Draft!</button>
	</form>
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