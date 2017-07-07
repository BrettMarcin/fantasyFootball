<%@ page import ="java.util.*" %>
<%@ page import ="com.home.Team" %>
<%@ page import ="com.home.Player" %>
<% Team localTeam = (Team)request.getAttribute("localTeam"); %>

<h2><%=localTeam.teamName%></h2>