<%--
  Created by IntelliJ IDEA.
  User: Computer
  Date: 20.08.2022
  Time: 20:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form id="transitionForm" action="home" method="get" onload="submit()">
    <input name="command" value="confirmBookingCommand" hidden>
    <input name="roomId" value="${roomId}" hidden>
    <input name="roomStatusId" value="${roomStatusId}" hidden>
    <input name="dateStart" value="${dateStart}" hidden>
    <input name="dateEnd" value="${dateEnd}" hidden>
    <input name="checkIn" value="${checkIn}" hidden>
    <input name="checkOut" value="${checkOut}" hidden>
    <input name="sorting" value="${sorting}" hidden>
    <input name="ordering" value="${ordering}" hidden>
    <input name="hotelId" value="${hotelId}" hidden>
    <input name="hotelName" value="${hotelName}" hidden>
    <input name="statusFree" value="${statusFree}" hidden>
    <input name="statusBooked" value="${statusBooked}" hidden>
    <input name="recordsOnPage" value="${recordsOnPage}" hidden>
    <input type="submit">

</form>
<script>
    let form = document.getElementById("transitionForm");
    form.submit();
</script>
</body>
</html>
