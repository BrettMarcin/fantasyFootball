<%@ page import ="java.util.*" %>
<%@ page import ="com.home.Player" %>
<% ArrayList<Player> players = (ArrayList<Player>)request.getAttribute("listOfPlayers"); %>

<table>
	<tr>
		<th>Rank</th>
		<th>Player</th>
		<th>Fantasy Points Last year</th>
		<th>Pass YDS</th>
		<th>Pass TDS</th>
		<th>INT</th>
		<th>Rush YDS</th>
		<th>Rush TDS</th>
		<th>REC YDS</th>
		<th>REC TDS</th>
	</tr>
	<% for(Player aPlayer : players){ %>
		<tr>
			<td><%= aPlayer.rank %></td>
			<td><%= aPlayer.first + " " + aPlayer.last %></td>
		</tr>
	<% } %>
</table>