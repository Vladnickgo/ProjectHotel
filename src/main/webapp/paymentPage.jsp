<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Payment Page"/>

<c:import url="views/head.jsp"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <meta charset="UTF-8"/>
    <title>PaymentPage</title>
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
                <f:message key="paymentConfirmation" bundle="${bunCont}"/>
            </h1>
            <hr>
            <table class="table" style="width: 100%;">
                <tr>
                    <th><f:message key="bookingNumber" bundle="${bunCont}"/></th>
                    <td><i>${bookingByParameters.id}</i></td>
                </tr>
                <tr>
                    <th><f:message key="bookingDate" bundle="${bunCont}"/></th>
                    <td><i>${bookingByParameters.bookTime}</i></td>
                </tr>
                <tr style="background-color: lightsteelblue">
                    <th style="margin-top: 15px; margin-bottom: 20px" colspan="2">
                        <f:message key="hotel" bundle="${bunCont}"/>
                        <h2>
                            ${bookingByParameters.room.hotel.name}
                        </h2>
                    </th>
                </tr>
                <tr>
                    <th><f:message key="roomType" bundle="${bunCont}"/></th>
                    <td>${bookingByParameters.room.roomType.typeName}</td>
                </tr>
                <tr>
                    <th><f:message key="numberOfBeds" bundle="${bunCont}"/></th>
                    <td>${bookingByParameters.room.numberOfBeds}</td>
                </tr>
                <tr>
                    <th><f:message key="checkIn" bundle="${bunCont}"/></th>
                    <td>${bookingByParameters.checkIn}</td>
                </tr>
                <tr>
                    <th><f:message key="checkOut" bundle="${bunCont}"/></th>
                    <td>${bookingByParameters.checkOut}</td>
                </tr>
                <tr>
                    <th><f:message key="nights" bundle="${bunCont}"/></th>
                    <td>${bookingByParameters.nights}</td>
                </tr>
                <tr>
                    <th><f:message key="price" bundle="${bunCont}"/></th>
                    <%--                    <td>${roomStatusById.roomDtoResponse.price} uah</td>--%>
                    <td>${bookingByParameters.room.price} <f:message key="uah" bundle="${bunCont}"/></td>
                </tr>
                <tr style="background-color: lightgray">
                    <th><f:message key="toPay" bundle="${bunCont}"/></th>
                    <td>${bookingByParameters.room.price*bookingByParameters.nights} <f:message key="uah"
                                                                                                bundle="${bunCont}"/></td>
                </tr>
                <tr ${cardErrorMessage=='error'?'':'hidden'} style="text-align: center;font-size: 14pt;color: crimson">
                    <td colspan="2">
                        <f:message key="cardDataError" bundle="${bunCont}"/>
                    </td>
                </tr>
            </table>

            <div style="display: flex; flex-direction: row; justify-content: center">
                <form action="home" method="post">
                    <table>
                        <tr>
                            <th><f:message key="cardNumber" bundle="${bunCont}"/></th>
                            <td><input type="text" name="cardNumber"/></td>
                        </tr>
                        <tr>
                            <th>CVV</th>
                            <td><input type="text" name="cvvCode"/></td>
                        </tr>
                        <tr></tr>
                    </table>


                    <input name="command" value="confirmPayment" hidden>
                    <input name="bookingByParameters.id" value="${bookingByParameters.id}" hidden>
                    <input name="bookingByParameters.userId" value="${bookingByParameters.userId}" hidden>
                    <input name="toPay" value="${bookingByParameters.room.price*bookingByParameters.nights}" hidden>
                    <input style="margin-top: 20px" type="submit" value="Confirm payment">
                </form>
            </div>
        </div>
        <div class="col-3">
        </div>
    </div>
</div>


</body>
</html>
