<%@ page import ="java.util.*" %>
<%@ page import ="com.home.Team" %>
<%@ page import ="com.home.Player" %>
<% Team localTeam = (Team)request.getAttribute("localTeam"); %>
<% ArrayList<Team> teams = (ArrayList<Team>)request.getAttribute("theTeams"); %>

<div class="dropdown">
    <button class="btn btn-primary dropdown-toggle" type="button" data-toggle="dropdown">Teams
        <span class="caret"></span></button>
    <ul class="dropdown-menu">
        <% for(Team aTeam : teams) { %>
        <li><a class='dropdown-item' role="menuitem" href="javascript:getTeam('<%=aTeam.teamName%>');"><%=aTeam.teamName%></a></li>
        <% }%>
    </ul>
</div>

<h2 id="theTeamName"><%=localTeam.teamName%></h2>
</div>
<h3>Starters</h3>
<table class="theTeam table-bordered table-sm">
    <thead>
        <tr>
            <th>Position</th>
            <th>First</th>
            <th>Last</th>
        </tr>
    </thead>
    <tr id="qb_id">
        <th>QB</th>
    </tr>
	<tr id="rb1_id">
		<th>RB1</th>
	</tr>
    <tr id="rb2_id">
        <th>RB2</th>
    </tr>
    <tr id="wr1_id">
        <th>WR1</th>
    </tr>
    <tr id="wr2_id">
        <th>WR2</th>
    </tr>
    <tr id="te_id">
        <th>TE</th>
    </tr>
    <tr id="flex_id">
        <th>FLEX</th>
    </tr>
    <tr id="dst_id">
        <th>dst</th>
    </tr>
</table>
<h3>Bench</h3>
<table class="table-bordered">
    <thead id="bench_header">
    <tr>
        <th>Position</th>
        <th>First</th>
        <th>Last</th>
    </tr>
    </thead>
</table>