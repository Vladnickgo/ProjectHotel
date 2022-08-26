<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Create Booking"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>CreateBookingPage</title>
</head>
<body>
<c:import url="views/header.jsp"/>

<div class="container mt-5">
    <div class="row">
        <div class="col-3">
        </div>
        <div class="col-6">
            <h1 class="mb-5">
                <f:message key="orderCreation" bundle="${bunCont}"/>
            </h1>
            <hr>
            <table class="table" style="width: 100%;">
                <tr>
                    <th></th>
                    <td></td>
                </tr>
                <tr>

                </tr>
                <tr style="background-color: lightsteelblue">
                    <th style="margin-top: 15px; margin-bottom: 20px" colspan="2">
                        <f:message key="hotel" bundle="${bunCont}"/>
                        <h2>
                            ${sessionScope.hotelName}
                        </h2>
                    </th>
                </tr>
                <tr>
                    <th><f:message key="roomType" bundle="${bunCont}"/></th>
                    <td>${sessionScope.typeName}</td>
                </tr>
                <tr>
                    <th><f:message key="numberOfBeds" bundle="${bunCont}"/></th>
                    <td>${sessionScope.numberOfBeds}</td>
                </tr>
                <tr>
                    <th><f:message key="checkIn" bundle="${bunCont}"/></th>
                    <td>${sessionScope.checkIn}</td>
                </tr>
                <tr>
                    <th><f:message key="checkOut" bundle="${bunCont}"/></th>
                    <td>${sessionScope.checkOut }</td>
                </tr>
                <tr>
                    <th><f:message key="nights" bundle="${bunCont}"/></th>
                    <td>${sessionScope.numberOfNights}</td>
                </tr>
                <tr>
                    <th><f:message key="price" bundle="${bunCont}"/></th>
                    <td>${sessionScope.price} <f:message key="uah" bundle="${bunCont}"/></td>
                </tr>
                <tr style="background-color: lightgray">
                    <th><f:message key="toPay" bundle="${bunCont}"/></th>
                    <td>${sessionScope.price*sessionScope.numberOfNights} <f:message key="uah"
                                                                                     bundle="${bunCont}"/></td>
                </tr>
            </table>
            <div style="text-align: center">
                <form action="home" method="post">
                    <a class="btn btn-outline-primary"
                       style="margin: 2px;width: 100px" ${user==null||user.role!='USER'?'hidden':''}
                       href="home?command=showRooms&dateStart=${sessionScope.dateStart}&dateEnd=${sessionScope.dateEnd}&checkIn=${checkIn}&checkOut=${checkOut}&sorting=${sorting}&ordering=${ordering}&hotelId=${hotelId}&hotelName=${hotelName}&statusFree=${statusFree}&statusBooked=${statusBooked}&recordsOnPage=${recordsOnPage}"
                    >Cancel
                    </a>

                    <button class="btn btn-outline-primary"
                            name="command" value="confirmBookingCommand"
                            type="submit"
                            style="margin: 2px;width: 100px"
                    ${user==null||user.role!='USER'?'hidden':''}>
                        Confirm
                    </button>

                </form>
            </div>


        </div>
        <div class="col-3">
        </div>
    </div>
</div>
<c:import url="views/footer.jsp"/>
</body>
</html>
