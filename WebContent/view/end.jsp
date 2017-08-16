<%@ page import ="java.util.*" %>
<%@ page import ="com.home.Team" %>
<%@ page import ="com.home.Player" %>
<% List<Team> teams = (List<Team>)request.getAttribute("theTeams"); %>
<% Team localTeam = (Team)request.getAttribute("localTeam"); %>
<html>
<head>
    <title>End Draft</title>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</head>
<body>
<p>Email Yourself your team!</p>

<form action="/">
    Send Yourself an Email of your team!: <input type="text" name="fname"><br>
    <input type="submit" value="Send">
</form>

<h2>Your Team: </h2>
<div class="container">
    <div class="row">
        <div class="col-sm-6 panel panel-default">
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
                    <td class="pos_name" id="QB_first"><%=localTeam.QB.first%></td>
                    <td class="pos_name" id="QB_last"><%=localTeam.QB.last%></td>
                </tr>
                <tr id="rb1_id">
                    <th>RB1</th>
                    <td class="pos_name" id="RB1_first"><%=localTeam.RB1.first%></td>
                    <td class="pos_name" id="RB1_last"><%=localTeam.RB1.last%></td>
                </tr>
                <tr id="rb2_id">
                    <th>RB2</th>
                    <td class="pos_name" id="RB2_first"><%=localTeam.RB2.first%></td>
                    <td class="pos_name" id="RB2_last"><%=localTeam.RB2.last%></td>
                </tr>
                <tr id="wr1_id">
                    <th>WR1</th>
                    <td class="pos_name" id="WR1_first"><%=localTeam.WR1.first%></td>
                    <td class="pos_name" id="WR1_last"><%=localTeam.WR1.last%></td>
                </tr>
                <tr id="wr2_id">
                    <th>WR2</th>
                    <td class="pos_name" id="WR2_first"><%=localTeam.WR2.first%></td>
                    <td class="pos_name" id="WR2_last"><%=localTeam.WR2.last%></td>
                </tr>
                <tr id="te_id">
                    <th>TE</th>
                    <td class="pos_name" id="TE_first"><%=localTeam.TE.first%></td>
                    <td class="pos_name" id="TE_last"><%=localTeam.TWEfirst%></td>
                </tr>
                <tr id="flex_id">
                    <th>FLEX</th>
                    <td class="pos_name" id="FLEX_first"><%=localTeam.FLEX.first%></td>
                    <td class="pos_name" id="FLEX_last"><%=localTeam.FLEX.last%></td>
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
                <tbody>
                <% for(int i = 0; i < localTeam.bench.size(); i++) { %>
                <tr><td><%=localTeam.bench.get(i).pos%></td><td><%=localTeam.bench.get(i).first%></td><td><%=localTeam.bench.get(i).last%></td></tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>

<% for(int x = 0; x < teams.size(); x++) { %>
<div class="container">
    <div class="row">
        <div class="col-sm-6 panel panel-default">
            <h2 id="theTeamName<%= x %>"><%=teams.get(i).teamName%></h2>
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
                <tr id="qb_id <%= x %>">
                    <th>QB</th>
                    <td class="pos_name" id="QB_first <%= x %>"><%=teams.get(i).QB.first%></td>
                    <td class="pos_name" id="QB_last <%= x %>"><%=teams.get(i).QB.last%></td>
                </tr>
                <tr id="rb1_id <%= x %>">
                    <th>RB1</th>
                    <td class="pos_name" id="RB1_first <%= x %>"><%=teams.get(i).RB1.first%></td>
                    <td class="pos_name" id="RB1_last <%= x %>"><%=teams.get(i).RB1.last%></td>
                </tr>
                <tr id="rb2_id <%= x %>">
                    <th>RB2</th>
                    <td class="pos_name" id="RB2_first <%= x %>"><%=teams.get(i).RB2.first%></td>
                    <td class="pos_name" id="RB2_last <%= x %>"><%=teams.get(i).RB2.last%></td>
                </tr>
                <tr id="wr1_id <%= x %>">
                    <th>WR1</th>
                    <td class="pos_name" id="WR1_first <%= x %>"><%=teams.get(i).WR1.first%></td>
                    <td class="pos_name" id="WR1_last <%= x %>"><%=teams.get(i).WR1.last%></td>
                </tr>
                <tr id="wr2_id <%= x %>">
                    <th>WR2</th>
                    <td class="pos_name" id="WR2_first <%= x %>"><%=teams.get(i).WR2.first%></td>
                    <td class="pos_name" id="WR2_last <%= x %>"><%=teams.get(i).WR2.last%></td>
                </tr>
                <tr id="te_id <%= x %>">
                    <th>TE</th>
                    <td class="pos_name" id="TE_first <%= x %>"><%=teams.get(i).TE.first%></td>
                    <td class="pos_name" id="TE_last <%= x %>"><%=teams.get(i).TE.first%></td>
                </tr>
                <tr id="flex_id <%= x %>">
                    <th>FLEX</th>
                    <td class="pos_name" id="FLEX_first <%= x %>"><%=teams.get(i).FLEX.first%></td>
                    <td class="pos_name" id="FLEX_last <%= x %>"><%=teams.get(i).FLEX.last%></td>
                </tr>
                <tr id="dst_id <%= x %>">
                    <th>DS/T</th>
                    <td class="pos_name" id="DST <%= x %>"></td>
                    <td></td>
                </tr>
                </tbody>
            </table>
            <h3>Bench</h3>
            <table class="table table-sm table-bordered">
                <thead id="bench_header <%= x %>">
                <tr>
                    <th>Position</th>
                    <th>First</th>
                    <th>Last</th>
                </tr>
                </thead>
                <tbody>
                <% for(int i = 0; i < teams.get(i).bench.size(); i++) { %>
                <tr><td><%=teams.get(i).bench.get(i).pos%></td><td><%=teams.get(i).bench.get(i).first%></td><td><%=teams.get(i).bench.get(i).last%></td></tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<% } %>

</body>
</html>
