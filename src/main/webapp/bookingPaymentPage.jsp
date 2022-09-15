<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<%@ taglib prefix="simple" uri="https://tomcat.apache.org/example-taglib" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Booking Payment"/>

<c:import url="views/head.jsp"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Booking Payment Page</title>
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
                <th><f:message key="orderCreation" bundle="${bunCont}"/></th>
            </h1>
            <hr>
            <table class="table" style="width: 100%;">
                <tr>
                    <th><f:message key="bookingDate" bundle="${bunCont}"/></th>
                    <td><i>${sessionScope.bookTime}</i></td>
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
                    <td><tags:fulldate date="${sessionScope.checkIn}"/></td>
                </tr>
                <tr>
                    <th><f:message key="checkOut" bundle="${bunCont}"/></th>
                    <td><tags:fulldate date="${sessionScope.checkOut}"/></td>
                </tr>
                <tr>
                    <th><f:message key="nights" bundle="${bunCont}"/></th>
                    <td>${sessionScope.days}</td>
                </tr>
                <tr>
                    <th><f:message key="price" bundle="${bunCont}"/></th>
                    <td>${sessionScope.price} <f:message key="uah" bundle="${bunCont}"/></td>
                </tr>
                <tfoot style="background-color: lightgray">
                <th><f:message key="toPay" bundle="${bunCont}"/></th>
                <td>${sessionScope.price*sessionScope.days} <f:message key="uah" bundle="${bunCont}"/></td>
                </tfoot>
            </table>
            <div style="display: flex; flex-direction: row;justify-content: center" ${changeRoomStatus==true?'hidden':''}>
                <form action="home" method="get">
                    <input name="roomId" value="${roomId}" hidden>
                    <input name="checkIn" value="${checkIn}" hidden>
                    <input name="checkOut" value="${checkOut}" hidden>
                    <input name="dateStart" value="${dateStart}" hidden>
                    <input name="dateEnd" value="${dateEnd}" hidden>
                    <input name="roomStatusId" value="${roomStatusId}" hidden>
                    <input name="hotelId" value="${hotelId}" hidden>
                    <input name="hotelName" value="${hotelName}" hidden>
                    <input name="statusFree" value="${statusFree}" hidden>
                    <input name="statusBooked" value="${statusBooked}" hidden>
                    <input name="recordsOnPage" value="${recordsOnPage}" hidden>
                    <input name="roomStatusDto" value="${roomStatusBuId}" hidden>
                    <input name="typeName" value="${typeName}" hidden>
                    <input name="numberOfBeds" value="${numberOfBeds}" hidden>
                    <input name="price" value="${price}" hidden>
                </form>
            </div>
            <div style="display: flex; flex-direction: row; justify-content: center">
                <div>
                    <form action="home" method="get">
                        <button class="btn btn-outline-primary"
                                name="command" value="payment"
                                type="submit"
                                style="">
                            <f:message key="payNow" bundle="${bunCont}"/>
                        </button>
                        <a class="btn btn-outline-primary"
                           href="home?command=showUserProfile"
                        ><f:message key="payLater" bundle="${bunCont}"/>
                        </a>
                    </form>
                </div>
            </div>
        </div>
        <div class="col-3">
        </div>
    </div>
</div>
<c:import url="views/footer.jsp"/>
</body>
</html>
