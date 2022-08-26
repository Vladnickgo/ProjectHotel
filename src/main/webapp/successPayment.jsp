<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Success payment page"/>

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
            <h1>
                USER PROFILE
            </h1>

            <h2 style="color: darkslateblue">Платіж успішно виконано</h2>
            <div>
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

                                    <%--                                <a class="btn btn-primary" ${booking.bookingStatusId==1?'':'hidden'}--%>
                                    <%--                                   style="font-size: 10pt;height: 35px"--%>
                                    <%--                                   href="home?command=payment&roomId=${booking.room.id}&checkIn=${booking.checkIn}&checkOut=${booking.checkOut}">--%>
                                    <%--                                    To pay it--%>
                                    <%--                                </a>--%>
                                <div hidden>${sum=sum+(booking.room.price*booking.nights)}</div>
                            </td>
                            <td></td>
                        </tr>

                    </c:forEach>
                    <tr>
                        <td colspan="9" style="text-align: right"><f:message key="toPay" bundle="${bunCont}"/></td>
                        <td>${sum} UAH</td>
                        <td>${paymentErrorMessage}</td>
                    </tr>
                </table>
            </div>
        </div>
        <div class="col-2"></div>
    </div>
</div>
${bookingDtoList}
<c:import url="views/footer.jsp"/>
</body>
</html>
