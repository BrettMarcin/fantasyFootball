<!DOCTYPE HTML>
<html>
<head> 
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
	<script src="/resources/scripts.js" /></script>
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
<%} %>

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