<%@ page import ="java.util.*" %>
<%@ page import ="com.home.Team" %>
<%@ page import ="com.home.Player" %>
<% Team localTeam = (Team)request.getAttribute("localTeam"); %>
<% //List<Player> RB = (List<Player>)request.getAttribute("RB"); %>

<h2><%=localTeam.teamName%></h2>
<table class="table-bordered">
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
    <tr id="we1_id">
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
<script> getTeam() </script>