<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Cancellation receipt"/>

<c:import url="/views/head.jsp"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="/views/style/style.css">
    <title>Cancellation receipt</title>
</head>
<body>
<div class="row text-center" style="background-color:#F8F9FA">
    <div class="col-11">
        <c:import url="/views/header.jsp"/>
    </div>
    <div class="col-1 pt-2">
        <form class="d-flex" method="get" onchange="submit()">
            <select class=" form-control select-size" id="language" name="language"
                    style="width: 120px;">
                <option value="ua" ${language == 'ua' ? 'selected' : ''}>Українська</option>
                <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
            </select>
            <input name="command" value="${command}" hidden>
            <input name="bookingId" value="${booking.id}" hidden>
        </form>
    </div>
</div>
<div class="row">
    <div class="col-3"></div>
    <div class="col-6" id="userForm">
        <h4 style="text-align: right; color: crimson; margin-bottom: 20px">
            <f:message key="orderCancelled" bundle="${bunCont}"/>
        </h4>
        <table class="table">
            <thead>
            <tr style="color: darkslateblue">
                <th scope="col"><h6>{Hello Hotel}</h6></th>
                <th scope="col" style="text-align: center">
                    <h5>
                        <f:message key="booking" bundle="${bunCont}"/>
                        <f:message key="indexNumber" bundle="${bunCont}"/>
                        ${booking.id} <f:message key="dated" bundle="${bunCont}"/>,
                        <tags:fulldate date="${booking.bookTime}"/>
                    </h5>
                </th>
            </tr>
            </thead>
            <tbody>
            <tr>
                <th scope="row" colspan="2" id="highlightedLineOfTable">
                    <f:message key="user" bundle="${bunCont}"/>

                </th>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="name" bundle="${bunCont}"/></th>
                <td>${user.firstName} ${user.lastName}</td>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="email" bundle="${bunCont}"/></th>
                <td>${user.email}</td>
            </tr>
            <tr>
                <th scope="row" colspan="2" id="highlightedLineOfTable">
                    <f:message key="bookingData" bundle="${bunCont}"/></th>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="bookingNumber" bundle="${bunCont}"/></th>
                <td>${booking.id}</td>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="hotel" bundle="${bunCont}"/></th>
                <td>${booking.room.hotel.name}</td>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="roomType" bundle="${bunCont}"/></th>
                <td>${booking.room.roomType.typeName}</td>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="checkIn" bundle="${bunCont}"/></th>
                <td><tags:fulldate date="${booking.checkIn}"/></td>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="checkOut" bundle="${bunCont}"/></th>
                <td><tags:fulldate date="${booking.checkOut}"/></td>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="price" bundle="${bunCont}"/></th>
                <td>${booking.room.price}</td>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="nights" bundle="${bunCont}"/></th>
                <td>${booking.nights}</td>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="toPay" bundle="${bunCont}"/></th>
                <td>${booking.room.price}
                    <f:message key="uah" bundle="${bunCont}"/> x ${booking.nights}
                    = ${booking.room.price*booking.nights}
                    <f:message key="uah" bundle="${bunCont}"/></td>
            </tr>
            <tr>
                <th scope="row" style="padding-left: 50px"><f:message key="reasonForCancellation"
                                                                      bundle="${bunCont}"/></th>
                <td ${booking.bookingStatusId==3?'':'hidden'}><f:message key="userCancel" bundle="${bunCont}"/></td>
                <td ${booking.bookingStatusId==4?'':'hidden'}><f:message key="autoCancel" bundle="${bunCont}"/></td>
            </tr>
            </tbody>
        </table>

    </div>
    <div class="col-3"></div>
</div>
</body>
</html>
