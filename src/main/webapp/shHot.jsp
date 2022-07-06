<%--
  Created by IntelliJ IDEA.
  User: Computer
  Date: 03.07.2022
  Time: 13:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <form action=""></form>
</head>
<body>
<form class="d-flex" method="post" onchange="submit()">
    <select class="form-control select-size" id="sorting" name="sorting"
            style="width: 150px;">
        <option value="price " ${sorting == 'price'||'' ? 'selected' : ''}>Price</option>
        <option value="numberOfBeds" ${sorting == 'numberOfBeds' ? 'selected' : ''}>Number of beds</option>
        <option value="roomType" ${sorting == 'roomType' ? 'selected' : ''}>Room type</option>
        <option value="roomStatus" ${sorting == 'roomStatus' ? 'selected' : ''}>Room status</option>
    </select>
    <select class="form-control select-size " id="ordering" name="ordering"
            style="width: 70px;">
        <option value="natural" ${ordering == 'natural'||'' ? 'selected' : ''}>A->Z</option>
        <option value="revers" ${ordering == 'revers' ? 'selected' : ''}>Z->A</option>
    </select>
    <select class="form-control select-size " id="or" name="or"
            style="width: 70px;">
        <option value="n" ${ort == 'natural'||'' ? 'selected' : ''}>n</option>
        <option value="r" ${ort == 'revers' ? 'selected' : ''}>r</option>
    </select>
</form>
</body>
</html>
