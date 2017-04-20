<!DOCTYPE HTML>
<html>
<head> 
    <title>Home</title> 
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
</head>
<body>

<button type="button">Update Roster</button>
<button type="button">Start</button>
<button type="button">Stop</button>
<h2>${content}</h2>
<c:choose>
    <c:when test="${empty name}">
        	<form action="changeName" method="POST">
    		<input type="text" name="playerName" placeholder="What's your name?"/>
    		<input type="submit" />
    	</form>
    </c:when>
    <c:otherwise>
        <h2>${name}</h2>
    </c:otherwise>
</c:choose>

<h2> Players </h2>
<table width="50%">
	<tr>
		<th>Position</th>
		<th>First</th>
		<th>Last</th>
		<th>Team</th>
	</tr>
	</table>
<h2> My team </h2>
	<table width="50%">
	<tr>
		<th>QB</th>
		<th>RB</th>
		<th>RB</th>
		<th>WR</th>
		<th>WR</th>
		<th>TE</th>
		<th>FLEX</th>
		<th>K</th>
		<th>D/ST</th>
	</tr>
	</table>
</body>
</html>