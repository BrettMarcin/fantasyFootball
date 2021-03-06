<!DOCTYPE HTML>
<html>
<head>
    <%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
    <spring:url value="/WebContent/resources/scripts/scripts.js" var="theJS" />
    <spring:url value="/WebContent/resources/stylesheets/table.css" var="theCSS" />
    <spring:url value="/WebContent/resources/bootstrap-3.3.7-dist/css/bootstrap.min.css" var="bootstrap" />
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
    <link rel="stylesheet" href="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/themes/smoothness/jquery-ui.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jqueryui/1.12.1/jquery-ui.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
    <script src="https://cdn.jsdelivr.net/particles.js/2.0.0/particles.min.js"></script>
    <script src="${theJS}"></script>
    <link rel="stylesheet" type="text/css" href="${theCSS}" />
    <link href="${bootstrap}" rel="stylesheet" />
    <meta http-equiv="X-UA-Compatible" content="IE=edge" />
    <%@ page import ="java.util.*" %>
    <%@ page import ="com.home.Team" %>
    <%@ page import ="com.home.Player" %>
    <% List<Team> teams = (List<Team>)request.getAttribute("theTeams"); %>
    <% ArrayList<Player> players = (ArrayList<Player>)request.getAttribute("listOfPlayers"); %>
    <% ArrayList<Team> theTimeline = (ArrayList<Team>)request.getAttribute("theTimeline"); %>
    <% int pickNumber = (int)request.getAttribute("pickNumber"); %>
    <% int round = (int)request.getAttribute("round"); %>
</head>
<body>
<div class="modal fade" id="historyModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLongTitle" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <h5 class="modal-title" id="exampleModalLongTitle">Draft History</h5>
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">&times;</span>
                </button>
            </div>
            <div class="modal-body">
                <table class="table table-sm table-bordered table-hover">
                    <thead>
                    <tr class="TableHead">
                        <th>Pick #</th>
                        <th>Position</th>
                        <th>Name</th>
                        <th>Draft by</th>
                    </tr>
                    </thead>
                    <tbody id="DraftHistoryBody">

                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
            </div>
        </div>
    </div>
</div>
<div id="particles-js">
    <div id="theDraft">
        <h3>Fantasy Football</h3>
        <div class="row">
            <div class="col-sm-2" style="margin-right: 50px;">
                <div class="clock panel panel-default" id="theClock">
                    <p id="minute">02</p><p style="display: inline; font-size: 50px;">:</p><p id="seconds">00</p>
                </div>
            </div>

            <div class="col-sm-9">
                <ul class="list-group timeline panel panel-default" id="timeline">
                    <% for (Team aTeam : theTimeline) {%>
                    <% String theRound = String.valueOf(round); %>
                    <% String pickNum = String.valueOf(pickNumber); %>
                    <% if(aTeam.teamName == "Round"){ %>
                    <li class="list-group-item timelineItem round">Round <%=theRound%></li>
                    <% round++; %>
                    <%} else { %>
                    <li class="list-group-item timelineItem team">#<%=pickNum%> <%=aTeam.teamName%></li>
                    <% pickNumber++;%>
                    <%} %>
                    <% } %>
                </ul>
                <br>
            </div>
            <div class="row">
                <div class="container">
                    <div class="col-md-2">
                        <div class="panel panel-default" id="lastSelectedPlayer">
                            <h4>Last Selected Player</h4>
                            <p id="lastSelectedPlayerP"></p>
                        </div>
                    </div>
                    <div class="col-md-2">
                        <div class="panel panel-default" >
                            <button type="button" class="btn btn-info btn-sm" data-toggle="modal" data-target="#historyModal" onclick="getDraftHistory()">Show Draft History</button>
                        </div>
                    </div>
                </div>
            </div>


            <div class="row">
                <div class="col-md-8">
                    <h2>Draft List</h2>
                    <table id="resizable" class="ui-widget-content allPlayers table table-sm table-bordered table-hover ">
                        <thead>
                        <tr class="TableHead">
                            <th>Rank</th>
                            <th>Player</th>
                            <th>Team</th>
                            <th>Position</th>
                            <th>Fantasy Total 2016</th>
                            <th>Pass YDS</th>
                            <th>Pass TDS</th>
                            <th>INT</th>
                            <th>Rush YDS</th>
                            <th>Rush TDS</th>
                            <th>REC YDS</th>
                            <th>REC TDS</th>
                        </tr>
                        </thead>
                        <tbody id="tbody_draft_table" class="table-striped">
                        <% for(Player aPlayer : players){ %>
                        <tr class="thePlayer playerInfo <%= aPlayer.first %> <%= aPlayer.last %> <%= aPlayer.pos %> <%= aPlayer.team %>">
                            <td class="thePlayer playerRank"><%= aPlayer.rank %></td>
                            <td class="thePlayer playerName"><%= aPlayer.first + " " + aPlayer.last %></td>
                            <td class="thePlayer playerTeam"><%= aPlayer.team %></td>
                            <td class="thePlayer playerPos"><%= aPlayer.pos %></td>
                            <td class = "thePlayer pointsLast"><%= aPlayer.Fpoints  %></td>
                            <td class = "thePlayer passYDs"><%= aPlayer.passYards %></td>
                            <td class = "thePlayer passTDs"><%= aPlayer.passTDs %></td>
                            <td class = "thePlayer ints"><%= aPlayer.ints %></td>
                            <td class = "thePlayer rushYDs"><%= aPlayer.rushYards %></td>
                            <td class = "thePlayer rushTDs"><%= aPlayer.rushTDs %></td>
                            <td class = "thePlayer recYards"><%= aPlayer.recYards %></td>
                            <td class = "thePlayer recTDs"><%= aPlayer.recTDs %></td>
                        </tr>
                        <% } %>
                        </tbody>
                    </table>
                </div>
                <div class="col-md-4 panel panel-default">
                    <jsp:include page="theTeam.jsp" />
                </div>
            </div>
            <div class="spacer">
            </div>
        </div>
        <script>
            particlesJS.load('particles-js', '/WebContent/resources/scripts/particles.json', function(){
                console.log('particles.json loaded...');
            });
        </script>
</body>
</div>
</html>
