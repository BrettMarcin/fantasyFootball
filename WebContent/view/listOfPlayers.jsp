<%@ page import ="java.util.*" %>
<%@ page import ="com.home.Player" %>
<% ArrayList<Player> players = (ArrayList<Player>)request.getAttribute("listOfPlayers"); %>

<button id="draftButton">Draft player</button>


<table class="allPlayers table table-bordered table-hover">
	<thead>
	<tr class="TableHead">
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
	</thead>
	<tbody>
	<% for(Player aPlayer : players){ %>
		<tr class="playerInfo">
			<td class="playerRank"><%= aPlayer.rank %></td>
			<td class="playerName"><%= aPlayer.first + " " + aPlayer.last %></td>
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
