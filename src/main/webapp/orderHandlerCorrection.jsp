<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Order correction"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Title</title>
</head>
<body>
<c:import url="views/header.jsp"/>
<div class="row container-fluid">
    <div class="col-2" style="">

    </div>
    <div class="col-8">
        <h1 class="text-center">ADMIN PROFILE</h1>
        <hr>
        <h3 style="text-align: left; margin-bottom: 20px; color: darkslateblue">
            <f:message key="applicationProcessing" bundle="${bunCont}"/>
        </h3>
        <h5 style="width: 100%">${hotelName}</h5>

        <div>
            <form action="home" method="get">
                <table class="table table-striped" style="width: 100%;padding-bottom: 30px">
                    <tr class="table-primary">
                        <th><f:message key="roomType" bundle="${bunCont}"/></th>
                        <th style="text-align: center"><f:message key="numberOfBeds" bundle="${bunCont}"/></th>
                        <th>Free from</th>
                        <th>Free to</th>

                    </tr>
                    <c:forEach var="item" items="${roomStatusDtoList}">

                        <tr>
                            <td>${item.roomDtoResponse.roomType.typeName}</td>
                            <td style="text-align: center">${item.roomDtoResponse.numberOfBeds}</td>
                            <td>
                                <input class="form-control"
                                       style="text-align: center; ${item.dateStart>dateStart?'background-color:pink':''}"
                                       type="date" id="start" name="signIn"
                                       style="width: 150px; ${item.dateStart>dateStart?'background-color:pink':''}"
                                    <%--                                       value="${item.dateStart>dateStart?item.dateStart:dateStart}"--%>
                                       value="${item.dateStart>dateStart?item.dateStart:dateStart}"

                                       min="${item.dateStart>dateStart?item.dateStart:dateStart}"
                                       max="${item.dateEnd<dateEnd?item.dateEnd:dateEnd}">
                            </td>
                            <td>
                                    <%--                                <div>--%>
                                    <%--                                        ${item.dateEnd<dateEnd?item.dateEnd:dateEnd}--%>
                                    <%--                                </div>--%>


                                    <%--                                    ${item.dateEnd}--%>
                                <input class="form-control"
                                       style="${item.dateEnd<dateEnd?'background-color:pink':''};text-align: center"
                                       type="date" id="end" name="signOut"
                                       style="width: 150px; ${item.dateEnd<dateEnd?'background-color:pink':''}"
                                       value="${item.dateEnd<dateEnd?item.dateEnd:dateEnd}"
                                       min="${item.dateStart>dateStart?item.dateStart:dateStart}"
                                       max="${item.dateEnd>dateEnd?dateEnd:item.dateEnd}">
                                <input type="checkbox" name="statusDtoId" value="${item.statusId}" checked hidden>
                                <input type="checkbox" name="statusDtoDateStart" value="${item.dateStart}" checked hidden>
                                <input type="checkbox" name="statusDtoDateEnd" value="${item.dateEnd}" checked hidden>

                                <input type="checkbox" name="roomId" value="${item.roomDtoResponse.id}" checked hidden>
                                <input type="checkbox" name="roomTypeId" value="${item.roomDtoResponse.roomType.typeId}" checked hidden>

                            </td>
                        </tr>

                    </c:forEach>
                </table>
                <div>
                    <%--                    <input name="signIn" value="signIn" hidden>--%>
                    <%--                    <input name="signOut" value="signOut" hidden>--%>
                    <input name="userId" value="${userId}" hidden>
                    <input name="firstName" value="${firstName}" hidden>
                    <input name="lastName" value="${lastName}" hidden>
                    <input name="email" value="${email}" hidden>
                    <input name="dateStart" value="${dateStart}" hidden>
                    <input name="dateEnd" value="${dateEnd}" hidden>
                    <input name="roomType" value="${roomType}" hidden>
                    <input name="hotelId" value="${hotelId}" hidden>
                    <input name="hotelName" value="${hotelName}" hidden>
                    <input name="numberOfPersons" value="${numberOfPersons}" hidden>
                    <input name="userOrderId" value="${userOrderId}" hidden>
<%--                    <input name="roomId" value="${userOrderId}" hidden>--%>



                    <button class="btn btn-outline-primary"
                            name="command" value="orderHandlerPage"
                            type="submit"
                            style="">
                        ←Backward
                    </button>
                    <button class="btn btn-outline-primary"
                            name="command" value="OrderCompletionConfirmationCommand"
                            type="submit"
                            style="">
                        Подтвердить
                    </button>
                </div>
            </form>
        </div>
        ${roomStatusDtoList}
    </div>
    <div class="col-2" style="margin-top: 170px">
        <h6>Замовник</h6>
        <table>
            <tr>
                <th>Ім'я</th>
                <td>${firstName} ${lastName}</td>
            </tr>
            <tr>
                <th>
                    <f:message key="signIn" bundle="${bunCont}"/>
                </th>
                <td>${dateStart}</td>
            </tr>
            <tr>
                <th>
                    <f:message key="signOut" bundle="${bunCont}"/>
                </th>
                <td>${dateEnd}</td>
            </tr>
            <tr>
                <th>
                    Кількість місць
                </th>
                <td>
                    ${numberOfPersons}
                </td>
            </tr>
            <tr>
                <th>
                    <f:message key="roomType" bundle="${bunCont}"/>
                </th>
                <td>
                    ${roomType}
                </td>
            </tr>
        </table>
    </div>
</div>
<c:import url="views/footer.jsp"/>
</body>
</html>
