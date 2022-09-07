<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<%@ taglib prefix="simple" uri="https://tomcat.apache.org/example-taglib" %>

<c:set var="Title" scope="request" value="Confirm group payment"/>

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
                <input name="command" value="${command}" hidden>
                <input name="command" value="${command}" hidden>
                <c:forEach items="${bookingDtoList}" var="booking">
                    <input type="checkbox" value="${booking.id}" name="bookingId" checked hidden>
                </c:forEach>
            </form>
        </div>
    </div>
</div>
<div>
    <div class="row">
        <div class="col-2"></div>
        <div class="col-8">
            <form action="home" method="post">
                <table class="table table-striped" style="width: 100%;padding-bottom: 30px">
                    <tr class="table-primary">
                        <th><f:message key="order" bundle="${bunCont}"/></th>
                        <th><f:message key="orderDate" bundle="${bunCont}"/></th>
                        <th><f:message key="hotel" bundle="${bunCont}"/></th>
                        <th><f:message key="checkIn" bundle="${bunCont}"/></th>
                        <th><f:message key="checkOut" bundle="${bunCont}"/></th>
                        <th><f:message key="roomType" bundle="${bunCont}"/></th>
                        <th><f:message key="numberOfPersons" bundle="${bunCont}"/></th>
                        <th><f:message key="price" bundle="${bunCont}"/></th>
                        <th><f:message key="nights" bundle="${bunCont}"/></th>
                        <th><f:message key="toPay" bundle="${bunCont}"/></th>
                        <th>
                            <button class="btn btn-outline-primary" ${statusNotPaid=='notPaid'?'':'hidden'}
                                    name="command" value="groupPayment"
                                    type="submit"
                                    style="">
                                <f:message key="toPay" bundle="${bunCont}"/>
                            </button>
                        </th>
                    </tr>
                    <c:set var="sum" value="0"/>
                    <c:forEach var="booking" items="${bookingDtoList}">
                        <tr>
                            <td>${booking.id}</td>
                            <td><tags:fulldate date="${booking.bookTime}"/></td>
                            <td>${booking.room.hotel.name}</td>
                            <td><tags:fulldate date="${booking.checkIn}"/></td>
                            <td><tags:fulldate date="${booking.checkOut}"/></td>
                            <td>${booking.room.roomType.typeName}</td>
                            <td>${booking.room.numberOfBeds}</td>
                            <td>${booking.room.price} <f:message key="uah" bundle="${bunCont}"/></td>
                            <td>${booking.nights}</td>
                            <td>${booking.room.price*booking.nights} <f:message key="uah" bundle="${bunCont}"/></td>
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
                        <td>${sum} <f:message key="uah" bundle="${bunCont}"/></td>
                        <td></td>
                    </tr>
                </table>
                <div class="row">
                    <div class="col-4"></div>
                    <div class="col-4">
                        <div style="">
                            <div>
                                <table>
                                    <tr>
                                        <th><f:message key="cardNumber" bundle="${bunCont}"/></th>
                                        <td><input type="text" name="cardNumber" placeholder="0000000000000000"/></td>
                                    </tr>
                                    <tr>
                                        <th>CVV</th>
                                        <td><input type="text" name="cvvCode"/></td>
                                    </tr>
                                    <tr></tr>
                                    <tr ${cardErrorMessage=='error'?'':'hidden'}
                                            style="text-align: center;font-size: 14pt;color: crimson">
                                        <td colspan="2">
                                            <f:message key="cardDataError" bundle="${bunCont}"/>
                                        </td>
                                    </tr>
                                </table>
                            </div>

                            <div>
                                <button class="btn btn-outline-primary"
                                        name="command" value="showUserProfile"
                                        type="submit"
                                        style="">
                                    <f:message key="cancel" bundle="${bunCont}"/>
                                </button>
                                <button class="btn btn-outline-primary"
                                        name="command" value="confirmGroupPayment"
                                        type="submit"
                                        style="">
                                    <f:message key="paymentConfirmation" bundle="${bunCont}"/>
                                </button>
                            </div>
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
