<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Confirm group payment"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="views/header.jsp"/>
<div>
    <div class="row">
        <div class="col-2"></div>
        <div class="col-8">
            <form action="home" method="post">
                <table class="table table-striped" style="width: 100%;padding-bottom: 30px">
                    <tr class="table-primary">
                        <th>№ замовлення</th>
                        <th>Дата замовлення</th>
                        <th>Готель</th>
                        <th>Дата заїзду</th>
                        <th>Дата від'їзду</th>
                        <th>Тип кімнати</th>
                        <th>Кількість місць</th>
                        <th>Ціна</th>
                        <th>Кількість ночей</th>
                        <th>Сума</th>
                        <th>
                            <button class="btn btn-outline-primary" ${statusNotPaid=='notPaid'?'':'hidden'}
                                    name="command" value="groupPayment"
                                    type="submit"
                                    style="">
                                Сплатити
                            </button>
                        </th>
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
                        <div style="">
                            <div>
                                <table>
                                    <tr>
                                        <th>№ карти</th>
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
