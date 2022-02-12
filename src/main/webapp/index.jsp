<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Date" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<%!
    String getFormattedDate(){
        SimpleDateFormat sdf = new SimpleDateFormat("dd.MM.yyyy hh:mm:ss");
        return sdf.format(new Date());
    }
%>

<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Hotel</title>
</head>
<body>
<h1>Hello Hotel</h1>
<h1>Сегодня <%= getFormattedDate() %>
</h1>
<br/>
<a href="${pageContext.request.contextPath}/WEB-INF/View/login.jsp">Please log in</a>
<br>
<a href="${pageContext.request.contextPath}/exception.jsp">Exception</a>
<br>

</body>
</html>