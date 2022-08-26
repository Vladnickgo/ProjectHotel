<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Success"/>

<c:import url="views/head.jsp"/>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>

</head>
<body>
<c:import url="views/header.jsp"/>

<div class="container mt-5">
    <div class="row">
        <div class="col-3">
        </div>
        <div class="col-6">
            <h1 class="mb-5">
                <f:message key="successfullyPayment" bundle="${bunCont}"/>
            </h1>
            <hr>

            <%--            bookingServiceById: ${bookingServiceById}<br>--%>
            <%--            amount: ${amount}<br>--%>
            <%--            amount1:${bookingServiceById.nights*bookingServiceById.room.price}--%>
            <table class="table table-striped">
                <tr class="primary">
                    <td colspan="2" class="primary">
                        <h3><f:message key="paymentReceipt" bundle="${bunCont}"/> № ${bookingServiceById.id}</h3>
                    </td>
                </tr>
                <tr style="color: darkslateblue">
                    <td>
                        <h4>
                            <f:message key="hotel" bundle="${bunCont}"/>
                        </h4>
                    </td>
                    <td>
                        <h4>
                            ${bookingServiceById.room.hotel.name}
                        </h4>
                    </td>
                </tr>
                <tr>
                    <td>
                        <f:message key="orderDate" bundle="${bunCont}"/>
                    </td>
                    <th>
                        ${bookingServiceById.bookTime}
                    </th>
                </tr>
                <tr>
                    <td><f:message key="client" bundle="${bunCont}"/></td>
                    <th>${user.firstName} ${user.lastName}<br>
                        ${user.email}
                    </th>
                </tr>
                <tr>
                    <td><f:message key="roomType" bundle="${bunCont}"/></td>
                    <th>${bookingServiceById.room.roomType.typeName}</th>
                </tr>
                <tr>
                    <td><f:message key="numberOfBeds" bundle="${bunCont}"/></td>
                    <th>${bookingServiceById.room.numberOfBeds}</th>
                </tr>
                <tr>
                    <td><f:message key="checkIn" bundle="${bunCont}"/></td>
                    <th>${bookingServiceById.checkIn}</th>
                </tr>
                <tr>
                    <td><f:message key="checkOut" bundle="${bunCont}"/></td>
                    <th>${bookingServiceById.checkOut}</th>
                </tr>
                <tr>
                    <td><f:message key="nights" bundle="${bunCont}"/></td>
                    <th>${bookingServiceById.nights}</th>
                </tr>
                <tr>
                    <td><f:message key="price" bundle="${bunCont}"/></td>
                    <th>${bookingServiceById.room.price} <f:message key="uah" bundle="${bunCont}"/></th>
                </tr>
                <tr style="background-color: lightsteelblue">
                    <td><f:message key="toPay" bundle="${bunCont}"/></td>
                    <th>${bookingServiceById.room.price*bookingServiceById.nights} <f:message key="uah"
                                                                                              bundle="${bunCont}"/></th>
                </tr>
            </table>
        </div>
        <div class="col-3">

        </div>
    </div>
</div>
<c:import url="views/footer.jsp"/>
</body>
</html>
