<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Order handler"/>

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
                <input name="roomStatusDtoList" value="${roomStatusDtoList}" hidden>
                <input name="hotelName" value="${hotelName}" hidden>
                <input name="dateStart" value="${dateStart}" hidden>
                <input name="dateEnd" value="${dateEnd}" hidden>

                <input name="userId" value="${userId}" hidden>
                <input name="firstName" value="${firstName}" hidden>
                <input name="lastName" value="${lastName}" hidden>
                <input name="email" value="${email}" hidden>

                <input name="roomType" value="${roomType}" hidden>
                <input name="hotelId" value="${hotelId}" hidden>
                <input name="hotelName" value="${hotelName}" hidden>
                <input name="numberOfPersons" value="${numberOfPersons}" hidden>
                <input name="userOrderId" value="${userOrderId}" hidden>
            </form>
        </div>
    </div>
</div>
<div class="row container-fluid">
    <div class="col-2" style="">

    </div>
    <div class="col-8">
        <h1 class="text-center">
            <f:message key="adminProfile" bundle="${bunCont}"/>
        </h1>
        <hr>
        <h3 style="text-align: left; margin-bottom: 20px; color: darkslateblue">
            <f:message key="applicationProcessing" bundle="${bunCont}"/>
        </h3>
        <h5 style="width: 100%">${hotelName}</h5>
        <div ${error=='true'?'':'hidden'} style="color: crimson;font-size: 16pt">
            <f:message key="notChooseElements" bundle="${bunCont}"/>
        </div>
        <div>
            <form action="home" method="get">
                <table class="table table-striped" style="width: 100%;padding-bottom: 30px">
                    <tr class="table-primary">
                        <th><f:message key="roomType" bundle="${bunCont}"/></th>
                        <th style="text-align: center"><f:message key="numberOfBeds" bundle="${bunCont}"/></th>
                        <th>Free from</th>
                        <th>Free to</th>
                        <th><f:message key="note" bundle="${bunCont}"/></th>
                        <th></th>
                    </tr>
                    <c:forEach var="item" items="${roomStatusDtoList}">
                        <tr>
                            <td>${item.roomDtoResponse.roomType.typeName}</td>
                            <td style="text-align: center">${item.roomDtoResponse.numberOfBeds}</td>
                            <td style="text-align: center; ${item.dateStart>dateStart?'background-color:pink':''}">
                                <div>
                                        <tags:fulldate date="${item.dateStart>dateStart?item.dateStart:dateStart}"/>
                                </div>
                            </td>
                            <td style="${item.dateEnd<dateEnd?'background-color:pink':''};text-align: center">
                                <div>
                                        <tags:fulldate date="${item.dateEnd<dateEnd?item.dateEnd:dateEnd}"/>
                                </div>
                            </td>
                            <td>
                                <div ${sessionScope.language=='ua'||sessionScope.language==null?'':'hidden'}>
                                        ${dateStart<item.dateStart||dateEnd>item.dateEnd?'Частково вільна':'Вільна'}
                                </div>
                                <div ${sessionScope.language=='en'?'':'hidden'}>
                                        ${dateStart<item.dateStart||dateEnd>item.dateEnd?'Partially free':'Free'}
                                </div>
                            </td>
                            <td>
                                <input type="checkbox" name="roomStatusId" value="${item.statusId}">
                                <input type="checkbox" name="allRoomStatusesId" value="${item.statusId}" checked hidden>
                            </td>
                        </tr>
                    </c:forEach>
                </table>
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

                <div ${roomStatusDtoList=='[]'?'hidden':''}>
                    <button class="btn btn-outline-primary"
                            name="command" value="showAdminProfile"
                            type="submit"
                            style="">
                        <f:message key="backward" bundle="${bunCont}"/>
                    </button>
                    <button class="btn btn-outline-primary"
                            name="command" value="correctOrderHandler"
                            type="submit"
                            style="">
                        <f:message key="forward" bundle="${bunCont}"/>
                    </button>
                </div>
                <div ${roomStatusDtoList=='[]'?'':'hidden'}>
                    <div class="row">
                        <div class="col-3"></div>
                        <div class="col-6 mt-5"
                             style="margin-top:25px ;padding: 20px;
                                box-shadow: inset 0 0 1em rgba(0,0,0,0.1),
                                0 0  0 2px rgb(255,255,255),
                                0.3em 0.3em 1em rgba(0,0,0,0.3);">
                            <div style="text-align: center; color: darkslateblue">
                                <h4>
                                    <f:message key="noFreeRooms" bundle="${bunCont}"/>
                                </h4>
                            </div>
                            <div style="text-align: center">
                                <button class="btn btn-outline-primary"
                                        name="command" value="orderCancellation"
                                        type="submit"
                                        style="width: 100px">
                                    Ok
                                </button>
                            </div>
                        </div>
                        <div class="col-3"></div>
                    </div>
                </div>
            </form>

        </div>
    </div>
    <div class="col-2" style="margin-top: 170px">
        <h5><f:message key="client" bundle="${bunCont}"/></h5>
        <table>
            <tr>
                <th><f:message key="name" bundle="${bunCont}"/></th>
                <td class="ml-2">${firstName} ${lastName}</td>
            </tr>
            <tr>
                <th><f:message key="email" bundle="${bunCont}"/></th>
                <td>${email}</td>
            </tr>
            <tr>
                <th>
                    <f:message key="checkIn" bundle="${bunCont}"/>
                </th>
                <td><tags:fulldate date="${dateStart}"/></td>
            </tr>
            <tr>
                <th>
                    <f:message key="checkOut" bundle="${bunCont}"/>
                </th>
                <td><tags:fulldate date="${dateEnd}"/></td>
            </tr>
            <tr>
                <th>
                    <f:message key="numberOfPersons" bundle="${bunCont}"/>
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
</body>
</html>