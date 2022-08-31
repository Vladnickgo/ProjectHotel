<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="t" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Сancel booking result"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Title</title>
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
                <input name="command" value="groupCancelGetCommand" hidden>
                <input name="bookingDtoList" value="${bookingDtoList}" hidden>
            </form>
        </div>
    </div>
</div>
<div class="row" style="margin-top: 30px">
    <div class="col-2"></div>

    <div class="col-8">
        <h2><f:message key="orderCancelled" bundle="${bunCont}"/></h2>
        <form action="home" method="get">
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
                    <th><t:message key="toPay" bundle="${bunCont}"/></th>
                </tr>
                <c:set var="sum" value="0"/>
                <c:forEach var="booking" items="${bookingDtoList}">
                    <tr>
                        <td>${booking.id}</td>
                        <td>${booking.bookTime}</td>
                        <td>${booking.room.hotel.name}</td>
                        <td>${booking.checkIn}</td>
                        <td>${booking.checkOut}</td>
                        <td>${booking.room.roomType.typeName}</td>
                        <td>${booking.room.numberOfBeds}</td>
                        <td>${booking.room.price} UAH</td>
                        <td>${booking.nights}</td>
                        <td>${booking.room.price*booking.nights} UAH</td>
                        <td hidden>
                            <input type="checkbox" name="bookingId"
                                   value="${booking.id}" checked hidden>
                            <div hidden>${sum=sum+(booking.room.price*booking.nights)}</div>
                        </td>
                        <td></td>
                    </tr>

                </c:forEach>
                <tr>
                    <td colspan="9" style="text-align: right"><f:message key="toPay" bundle="${bunCont}"/></td>
                    <td>${sum} UAH</td>
                    <td></td>
                </tr>
            </table>
            <div class="row">
                <div class="col-4"></div>
                <div class="col-4">
                    <div style="text-align: center">
                        <input name="securityString" value="${securityString}" hidden>
                        <button class="btn btn-outline-primary"
                                name="command" value="showUserProfile"
                                type="submit"
                                style="">
                            Ok
                        </button>
                    </div>
                </div>
                <div class="col-4"></div>
            </div>
        </form>
    </div>
    <div class="col-2"></div>
</div>
</div>

<c:import url="views/footer.jsp"/>
</body>
</html>
