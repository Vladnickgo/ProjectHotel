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
<div class="">
    <div class="row text-center" style="background-color:#F8F9FA">
        <div class="col-11">
            <c:import url="views/header.jsp"/>
        </div>
        <div class="col-1 pt-2">
            <form class="d-flex" method="get" onchange="submit()">
                <select class=" form-control select-size" id="language" name="language"
                        style="width: 120px;">
                    <option value="ua" ${language == 'ua' ? 'selected' : ''}>Українська</option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                </select>
                <input name="command" value="${command}" hidden>
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
            </form>
        </div>
    </div>
</div>

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
                            ${hotelName}
                        </h2>
                    </th>
                </tr>
                <tr>
                    <th><f:message key="roomType" bundle="${bunCont}"/></th>
                    <td>${typeName}</td>
                </tr>
                <tr>
                    <th><f:message key="numberOfBeds" bundle="${bunCont}"/></th>
                    <td>${numberOfBeds}</td>
                </tr>
                <tr>
                    <th><f:message key="checkIn" bundle="${bunCont}"/></th>
                    <td>${checkIn}</td>
                </tr>
                <tr>
                    <th><f:message key="checkOut" bundle="${bunCont}"/></th>
                    <td>${checkOut }</td>
                </tr>
                <tr>
                    <th><f:message key="nights" bundle="${bunCont}"/></th>
                    <td>${numberOfNights}</td>
                </tr>
                <tr>
                    <th><f:message key="price" bundle="${bunCont}"/></th>
                    <td>${price} <f:message key="uah" bundle="${bunCont}"/></td>
                </tr>
                <tr style="background-color: lightgray">
                    <th><f:message key="toPay" bundle="${bunCont}"/></th>
                    <td>${price*numberOfNights} <f:message key="uah"
                                                           bundle="${bunCont}"/></td>
                </tr>
            </table>
            <div style="text-align: center">
                <form action="home" method="post">
                    <a class="btn btn-outline-primary"
                       style="margin: 2px;width: 100px" ${user==null||user.role!='USER'?'hidden':''}
                       href="home?command=showRooms&dateStart=${dateStart}&dateEnd=${dateEnd}&checkIn=${checkIn}&checkOut=${checkOut}&sorting=${sorting}&ordering=${ordering}&hotelId=${hotelId}&hotelName=${hotelName}&statusFree=${statusFree}&statusBooked=${statusBooked}&recordsOnPage=${recordsOnPage}"
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
