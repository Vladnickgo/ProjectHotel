<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
<c:import url="views/header.jsp"/>

<div class="container mt-5">
    <div class="row">
        <div class="col-3">
        </div>
        <div class="col-6">
            <h1 class="mb-5">
                <%--                <f:message key="confirmationBooking" bundle="${bunCont}"/>--%>
                Створення замовлення
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
                    <%--                    <td>${roomStatusById.roomDtoResponse.numberOfBeds}</td>--%>
                    <td>${sessionScope.numberOfBeds}</td>
                </tr>
                <tr>
                    <th><f:message key="checkIn" bundle="${bunCont}"/></th>
                    <td>${sessionScope.checkIn}</td>
                </tr>
                <tr>
                    <th><f:message key="checkOut" bundle="${bunCont}"/></th>
                    <td>${sessionScope.checkOut}</td>
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
<%--                    <a class="btn btn-outline-primary"--%>
<%--                       style="margin: 2px;width: 100px" ${user==null||user.role!='USER'?'hidden':''}--%>
<%--                    &lt;%&ndash;                       href="home?command=showRooms&roomId=${item.roomDtoResponse.id}&roomStatusId=${item.statusId}&dateStart=${dateStart}&dateEnd=${dateEnd}&checkIn=${checkIn}&checkOut=${checkOut}&userId=${user.id}&sorting=${sorting}&ordering=${ordering}&hotelId=${hotelId}&hotelName=${hotelName}&statusFree=${statusFree}&statusBooked=${statusBooked}&recordsOnPage=${recordsOnPage}"&ndash;%&gt;--%>
<%--                       href="home?command=showRooms&dateStart=${dateStart}&dateEnd=${dateEnd}&checkIn=${checkIn}&checkOut=${checkOut}&sorting=${sorting}&ordering=${ordering}&hotelId=${hotelId}&hotelName=${hotelName}&statusFree=${statusFree}&recordsOnPage=${recordsOnPage}"--%>
<%--                    >Cancel--%>
<%--                    </a>--%>
                    <input name="roomId" value="${roomId}" hidden>
                    <input name="checkIn" value="${checkIn}" hidden>
                    <input name="checkOut" value="${checkOut}" hidden>
                    <input name="dateStart" value="${dateStart}" hidden>
                    <input name="dateEnd" value="${dateEnd}" hidden>
                    <input name="roomStatusId" value="${roomStatusId}" hidden>
                    <%--                    <input name="userId" value="${user.id}" hidden>--%>
                    <%--                    <input name="sorting" value="${sorting}" hidden>--%>
                    <%--                    <input name="ordering" value="${ordering}" hidden>--%>
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
                                    <input name="command" value="payment" hidden>
<%--                                    <input name="roomId" value="${roomId}" hidden>--%>
<%--                                    <input name="checkIn" value="${checkIn}" hidden>--%>
<%--                                    <input name="checkOut" value="${checkOut}" hidden>--%>
<%--                                    <input name="dateStart" value="${dateStart}" hidden>--%>
<%--                                    <input name="dateEnd" value="${dateEnd}" hidden>--%>
                                    <input class="btn btn-outline-primary" type="submit" value="Pay now">
                                    <a class="btn btn-outline-primary"
                                       href="home?command=showUserProfile"
                                    >Pay later
                                    </a>
                                </form>


                            </div>
                        </div>
        </div>
        <div class="col-3">
            <c:import url="views/user/userInfo.jsp"/>
        </div>
    </div>
</div>
<%--${user}--%>
<c:import url="views/footer.jsp"/>


</body>
</html>
