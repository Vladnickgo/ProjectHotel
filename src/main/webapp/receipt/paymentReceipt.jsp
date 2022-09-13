<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Payment receipt"/>

<c:import url="/views/head.jsp"/>
<html>
<head>
    <title>Payment receipt</title>
</head>
<body>
<div class="">
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
                <input name="bookingId" value="${paymentInfo.booking.id}" hidden>
            </form>
        </div>
    </div>
    <div class="row text-center" style="">
        <div class="col-3"></div>
        <div class="col-6" style="margin-top:25px ;padding: 20px;
                                box-shadow: inset 0 0 1em rgba(0,0,0,0.1),
                                0 0  0 2px rgb(255,255,255),
                                0.3em 0.3em 1em rgba(0,0,0,0.3);">
            <table class="table">
                <thead>
                <tr style="color: darkslateblue">
                    <th scope="col"><h6>{Hello Hotel}</h6></th>
                    <th scope="col" style="text-align: center">
                        <h5>
                            <f:message key="receipt" bundle="${bunCont}"/>
                            ${paymentInfo.id}
                            <f:message key="dated" bundle="${bunCont}"/>
                            <tags:fulldate date="${paymentInfo.paymentDate}"/>
                        </h5>
                    </th>
                </tr>
                </thead>
                <tbody>
                <tr>
                    <th scope="row" colspan="2" style="text-transform: uppercase; background-color: antiquewhite">
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
                    <th scope="row" colspan="2" style="text-transform: uppercase; background-color: antiquewhite ">
                        <f:message key="bookingData" bundle="${bunCont}"/></th>
                </tr>
                <tr>
                    <th scope="row" style="padding-left: 50px"><f:message key="bookingNumber" bundle="${bunCont}"/></th>
                    <td>${paymentInfo.booking.id}</td>
                </tr>
                <tr>
                    <th scope="row" style="padding-left: 50px"><f:message key="hotel" bundle="${bunCont}"/></th>
                    <td>${paymentInfo.booking.room.hotel.name}</td>
                </tr>
                <tr>
                    <th scope="row" style="padding-left: 50px"><f:message key="roomType" bundle="${bunCont}"/></th>
                    <td>${paymentInfo.booking.room.roomType.typeName}</td>
                </tr>
                <tr>
                    <th scope="row" style="padding-left: 50px"><f:message key="checkIn" bundle="${bunCont}"/></th>
                    <td><tags:fulldate date="${paymentInfo.booking.checkIn}"/></td>
                </tr>
                <tr>
                    <th scope="row" style="padding-left: 50px"><f:message key="checkOut" bundle="${bunCont}"/></th>
                    <td><tags:fulldate date="${paymentInfo.booking.checkOut}"/></td>
                </tr>
                <tr>
                    <th scope="row" style="padding-left: 50px"><f:message key="price" bundle="${bunCont}"/></th>
                    <td>${paymentInfo.booking.room.price} <f:message key="uah" bundle="${bunCont}"/></td>
                </tr>
                <tr>
                    <th scope="row" style="padding-left: 50px"><f:message key="nights" bundle="${bunCont}"/></th>
                    <td>${paymentInfo.booking.nights}</td>
                </tr>
                <tr>
                    <th scope="row" style="padding-left: 50px"><f:message key="toPay" bundle="${bunCont}"/></th>
                    <td>${paymentInfo.booking.room.price}
                        <f:message key="uah" bundle="${bunCont}"/> x ${paymentInfo.booking.nights}
                        = ${paymentInfo.booking.room.price*paymentInfo.booking.nights} <f:message key="uah"
                                                                                                  bundle="${bunCont}"/></td>
                </tr>
                </tbody>
            </table>
        </div>
        <div class="col-3"></div>
    </div>
</body>
</html>
