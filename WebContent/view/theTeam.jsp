<%@ page import ="java.util.*" %>
<%@ page import ="com.home.team.Team" %>
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

<div class="container">
            <div class="col-sm-5">
<h2 id="theTeamName"><%=localTeam.teamName%></h2>
<h3>Starters</h3>
<table class="theTeam table table-sm table-bordered">
    <thead>
        <tr>
            <th>Position</th>
            <th>First</th>
            <th>Last</th>
        </tr>
    </thead>
    <tbody>
    <tr id="qb_id">
        <th>QB</th>
        <td class="pos_name" id="QB_first"></td>
        <td class="pos_name" id="QB_last"></td>
    </tr>
	<tr id="rb1_id">
		<th>RB1</th>
        <td class="pos_name" id="RB1_first"></td>
        <td class="pos_name" id="RB1_last"></td>
	</tr>
    <tr id="rb2_id">
        <th>RB2</th>
        <td class="pos_name" id="RB2_first"></td>
        <td class="pos_name" id="RB2_last"></td>
    </tr>
    <tr id="wr1_id">
        <th>WR1</th>
        <td class="pos_name" id="WR1_first"></td>
        <td class="pos_name" id="WR1_last"></td>
    </tr>
    <tr id="wr2_id">
        <th>WR2</th>
        <td class="pos_name" id="WR2_first"></td>
        <td class="pos_name" id="WR2_last"></td>
    </tr>
    <tr id="te_id">
        <th>TE</th>
        <td class="pos_name" id="TE_first"></td>
        <td class="pos_name" id="TE_last"></td>
    </tr>
    <tr id="flex_id">
        <th>FLEX</th>
        <td class="pos_name" id="FLEX_first"></td>
        <td class="pos_name" id="FLEX_last"></td>
    </tr>
    <tr id="dst_id">
        <th>DS/T</th>
        <td class="pos_name" id="DST"></td>
        <td></td>
    </tr>
    </tbody>
</table>
<h3>Bench</h3>
<table class="table table-sm table-bordered">
    <thead id="bench_header">
    <tr>
        <th>Position</th>
        <th>First</th>
        <th>Last</th>
    </tr>
    </thead>
    <tbody id="bench_body">

    </tbody>
</table>
            </div>
    </div>
</div>