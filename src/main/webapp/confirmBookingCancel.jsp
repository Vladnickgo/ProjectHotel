<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Confirm Booking Cancel"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title></title>
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
<div class="row" style="margin-top: 30px">
    <div class="col-2"></div>
    <div class="col-8">
        <h2>
            <f:message key="orderCancel" bundle="${bunCont}"/>
        </h2>
        <form action="home" method="post">
            <table class="table table-striped" style="width: 100%;padding-bottom: 30px">
                <tr class="table-primary">
                    <th><f:message key="order" bundle="${bunCont}"/></th>
                    <th><f:message key="orderDate" bundle="${bunCont}"/></th>
                    <th><f:message key="hotel" bundle="${bunCont}"/></th>
                    <th><f:message key="checkIn" bundle="${bunCont}"/></th>
                    <th><f:message key="checkOut" bundle="${bunCont}"/></th>
                    <th><f:message key="roomType" bundle="${bunCont}"/></th>
                    <th><f:message key="numberOfBeds" bundle="${bunCont}"/></th>
                    <th><f:message key="price" bundle="${bunCont}"/></th>
                    <th><f:message key="nights" bundle="${bunCont}"/></th>
                    <th><f:message key="toPay" bundle="${bunCont}"/></th>
                </tr>

                <c:forEach items="${bookingDtoList}" var="booking">
                    <tr>
                        <td>${booking.id}
                            <input type="checkbox" value="${booking.id}" name="bookingId" checked hidden>
                        </td>
                        <td>${booking.bookTime}</td>
                        <td>${booking.room.hotel.name}</td>
                        <td>${booking.checkIn}</td>
                        <td>${booking.checkOut}</td>
                        <td>${booking.room.roomType.typeName}</td>
                        <td>${booking.room.numberOfBeds}</td>
                        <td>${booking.room.price} UAH</td>
                        <td>${booking.nights}</td>
                        <td>${booking.room.price*booking.nights} UAH</td>

                    </tr>
                </c:forEach>
            </table>
            <div style="display: flex;flex-direction: row;justify-content: center">
                <button class="btn btn-outline-primary"
                        name="command" value="showUserProfile"
                        type="submit">
                    <f:message key="back" bundle="${bunCont}"/>
                </button>

                <button class="btn btn-outline-primary"
                        name="command" value="confirmBookingGroupCancel"
                        type="submit"
                        style="">
                    <f:message key="orderCancel" bundle="${bunCont}"/>
                </button>
            </div>
        </form>
    </div>
    <div class="col-2"></div>
</div>

<c:import url="views/footer.jsp"/>
</body>
</html>
