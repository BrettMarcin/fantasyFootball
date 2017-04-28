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
</head>
<body>

<button type="button">Update Roster</button>
<button type="button">Start</button>
<button type="button">Stop</button>


<% if(request.getAttribute("name") == null){ %>
	 <form action="changeName" method="POST">
    		<input type="text" name="playerName" placeholder="What's your name?"/>
    		<input type="submit" />
    	</form>
<% } else { %>
        <h2>${name}</h2>
        <jsp:include page="listOfPlayers.jsp" />
<%} %>
</body>
</html>