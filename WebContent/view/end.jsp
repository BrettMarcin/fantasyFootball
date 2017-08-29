<html>
<head>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <spring:url value="/WebContent/resources/scripts/scripts.js" var="theJS" />
    <spring:url value="/WebContent/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" var="bootstrap" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/particlesjs/2.0.2/particles.js"></script>
    <script src="${theJS}"></script>
    <link href="${bootstrap}" rel="stylesheet" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <%@ page import ="java.util.*" %>
    <%@ page import ="com.home.Team" %>
    <% List<Team> teams = (List<Team>)request.getAttribute("theTeams"); %>
    <% Team localTeam = (Team)request.getAttribute("localTeam"); %>
</head>
<body>


<button onclick="resetDraft()">Reset draft</button>

<% if (localTeam != null) {%>
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
                    <% if(localTeam.QB != null){ %>
                    <td class="pos_name" id="QB_first"><%=localTeam.QB.first%></td>
                    <td class="pos_name" id="QB_last"><%=localTeam.QB.last%></td>
                    <% } else { %>
                    <td class="pos_name" id="QB_first"></td>
                    <td class="pos_name" id="QB_last"></td>
                    <% } %>
                </tr>
                <tr id="rb1_id">
                    <th>RB1</th>
                    <% if(localTeam.RB1 != null){ %>
                    <td class="pos_name" id="RB1_first"><%=localTeam.RB1.first%></td>
                    <td class="pos_name" id="RB1_last"><%=localTeam.RB1.last%></td>
                    <% } else { %>
                    <td class="pos_name" id="RB1_first"></td>
                    <td class="pos_name" id="RB1_last"></td>
                    <% } %>
                </tr>
                <tr id="rb2_id">
                    <th>RB2</th>
                    <% if(localTeam.RB2 != null){ %>
                    <td class="pos_name" id="RB2_first"><%=localTeam.RB2.first%></td>
                    <td class="pos_name" id="RB2_last"><%=localTeam.RB2.last%></td>
                    <% } else { %>
                    <td class="pos_name" id="RB2_first"></td>
                    <td class="pos_name" id="RB2_last"></td>
                    <% } %>
                </tr>
                <tr id="wr1_id">
                    <th>WR1</th>
                    <% if(localTeam.WR1 != null){ %>
                    <td class="pos_name" id="WR1_first"><%=localTeam.WR1.first%></td>
                    <td class="pos_name" id="WR1_last"><%=localTeam.WR1.last%></td>
                    <% } else { %>
                    <td class="pos_name" id="WR1_first"></td>
                    <td class="pos_name" id="WR1_last"></td>
                    <% } %>
                </tr>
                <tr id="wr2_id">
                    <th>WR2</th>
                    <% if(localTeam.WR2 != null){ %>
                    <td class="pos_name" id="WR2_first"><%=localTeam.WR2.first%></td>
                    <td class="pos_name" id="WR2_last"><%=localTeam.WR2.last%></td>
                    <% } else { %>
                    <td class="pos_name" id="WR2_first"></td>
                    <td class="pos_name" id="WR2_last"></td>
                    <% } %>
                </tr>
                <tr id="te_id">
                    <th>TE</th>
                    <% if(localTeam.TE != null){ %>
                    <td class="pos_name" id="TE_first"><%=localTeam.TE.first%></td>
                    <td class="pos_name" id="TE_last"><%=localTeam.TE.last%></td>
                    <% } else { %>
                    <td class="pos_name" id="TE_first"</td>
                    <td class="pos_name" id="TE_last"></td>
                    <% } %>
                </tr>
                <tr id="flex_id">
                    <th>FLEX</th>
                    <% if(localTeam.FLEX != null){ %>
                    <td class="pos_name" id="FLEX_first"><%=localTeam.FLEX.first%></td>
                    <td class="pos_name" id="FLEX_last"><%=localTeam.FLEX.last%></td>
                    <% } else { %>
                    <td class="pos_name" id="FLEX_first"></td>
                    <td class="pos_name" id="FLEX_last"></td>
                    <% } %>
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
<% } %>

<% for(int x = 0; x < teams.size(); x++) { %>
<div class="container">
    <div class="row">
        <div class="col-sm-6 panel panel-default">
            <h2 id="theTeamName<%= x %>"><%=teams.get(x).teamName%></h2>
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
                    <% if(teams.get(x).QB != null){ %>
                    <td class="pos_name" id="QB_first <%= x %>"><%=teams.get(x).QB.first%></td>
                    <td class="pos_name" id=" <%= x %>"><%=teams.get(x).QB.last%></td>
                    <% } else { %>
                    <td class="pos_name" id="QB_first"></td>
                    <td class="pos_name" id="QB_last"></td>
                    <% } %>
                </tr>
                <tr id="rb1_id <%= x %>">
                    <th>RB1</th>
                    <% if(teams.get(x).RB1 != null){ %>
                    <td class="pos_name" id="RB1_first <%= x %>"><%=teams.get(x).RB1.first%></td>
                    <td class="pos_name" id="RB1_last <%= x %>"><%=teams.get(x).RB1.last%></td>
                    <% } else { %>
                    <td class="pos_name" id="RB1_first <%= x %>"></td>
                    <td class="pos_name" id="RB1_last <%= x %>"></td>
                    <% } %>
                </tr>
                <tr id="rb2_id <%= x %>">
                    <th>RB2</th>
                    <% if(teams.get(x).RB2 != null){ %>
                    <td class="pos_name" id="RB2_first <%= x %>"><%=teams.get(x).RB2.first%></td>
                    <td class="pos_name" id="RB2_last <%= x %>"><%=teams.get(x).RB2.last%></td>
                    <% } else { %>
                    <td class="pos_name" id="RB2_first <%= x %>"></td>
                    <td class="pos_name" id="RB2_last <%= x %>"></td>
                    <% } %>
                </tr>
                <tr id="wr1_id <%= x %>">
                    <th>WR1</th>
                    <% if(teams.get(x).WR1 != null){ %>
                    <td class="pos_name" id="WR1_first <%= x %>"><%=teams.get(x).WR1.first%></td>
                    <td class="pos_name" id="WR1_last <%= x %>"><%=teams.get(x).WR1.last%></td>
                    <% } else { %>
                    <td class="pos_name" id="WR1_first <%= x %>"></td>
                    <td class="pos_name" id="WR1_last <%= x %>"></td>
                    <% } %>
                </tr>
                <tr id="wr2_id <%= x %>">
                    <th>WR2</th>
                    <% if(teams.get(x).WR1 != null){ %>
                    <td class="pos_name" id="WR2_first <%= x %>"><%=teams.get(x).WR2.first%></td>
                    <td class="pos_name" id="WR2_last <%= x %>"><%=teams.get(x).WR2.last%></td>
                    <% } else { %>
                    <td class="pos_name" id="WR2_first <%= x %>"></td>
                    <td class="pos_name" id="WR2_last <%= x %>"></td>
                    <% } %>
                </tr>
                <tr id="te_id <%= x %>">
                    <th>TE</th>
                    <% if(teams.get(x).TE != null){ %>
                    <td class="pos_name" id="TE_first <%= x %>"><%=teams.get(x).TE.first%></td>
                    <td class="pos_name" id="TE_last <%= x %>"><%=teams.get(x).TE.last%></td>
                    <% } else { %>
                    <td class="pos_name" id="TE_first <%= x %>"></td>
                    <td class="pos_name" id="TE_last <%= x %>"></td>
                    <% } %>
                </tr>
                <tr id="flex_id <%= x %>">
                    <th>FLEX</th>
                    <td class="pos_name" id="FLEX_first <%= x %>"><%=teams.get(x).FLEX.first%></td>
                    <td class="pos_name" id="FLEX_last <%= x %>"><%=teams.get(x).FLEX.last%></td>
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
                <% for(int i = 0; i < teams.get(x).bench.size(); i++) { %>
                <tr><td><%=teams.get(x).bench.get(i).pos%></td><td><%=teams.get(x).bench.get(i).first%></td><td><%=teams.get(x).bench.get(i).last%></td></tr>
                <% } %>
                </tbody>
            </table>
        </div>
    </div>
</div>
<% } %>

</body>
</html>
