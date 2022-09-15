<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="tags" tagdir="/WEB-INF/tags" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Users orders"/>

<c:import url="views/head.jsp"/>
<html>
<head>
    <title>Users orders</title>
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
                <input name="statusNotDone" value="${statusNotDone}" hidden>
                <input name="statusCompleted" value="${statusCompleted}" hidden>
                <input name="sorting" value="${sorting}" hidden>
                <input name="ordering" value="${ordering}" hidden>
                <input name="itemsOnPage" value="${itemsOnPage}" hidden>
            </form>
        </div>
    </div>


    <div>
        <div class="row container-fluid">
            <div class="col-2" style="">

            </div>
            <div class="col-8">
                <h1 class="text-center"><f:message key="userProfile" bundle="${bunCont}"/></h1>
                <hr>
                <h3 style="text-align: left; margin-bottom: 20px; color: darkslateblue">
                    <f:message key="myOrders" bundle="${bunCont}"/>
                </h3>
                <div style="margin-top: 10px;" id="fieldsForSorting">
                    <form style="display: flex;flex-direction: row;" class="" action="home" method="get"
                          onchange="submit()">

                        <label style="margin-left: 50px; width: 60px;font-size: 8pt; margin-right: 5px;font-weight: bold"><f:message
                                key="bookingStatus" bundle="${bunCont}"/>:</label>
                        <div style="font-size: 9pt;margin-left: 10px;">
                            <input type="checkbox" id="notDone" name="statusNotDone"
                                   value="notDone" ${statusNotDone=='notDone'?'checked':''}>
                            <label><f:message key="notDone" bundle="${bunCont}"/></label><br>
                            <input type="checkbox" id="completed" name="statusCompleted"
                                   value="completed" ${statusCompleted=='completed'?'checked':''}>
                            <label><f:message key="completed" bundle="${bunCont}"/></label><br>
                        </div>
                        <label for="sorting"
                               style="width: 70px;font-size: 8pt; margin-left: 30px; margin-right: 5px;font-weight: bold">
                            <f:message key="orderItemsBy" bundle="${bunCont}"/>
                        </label>
                        <select class="select-size form-control" name="sorting" id="sorting"
                                style="width: 140px;height: 40px">
                            <option value="order_date" ${sorting == 'order_date' ? 'selected' : ''}><f:message
                                    key="orderDate"
                                    bundle="${bunCont}"/></option>
                            <option value="hotel_name" ${sorting == 'hotel_name' ? 'selected' : ''}>
                                <f:message key="hotel" bundle="${bunCont}"/>
                            </option>
                            <option value="type_name" ${sorting == 'type_name'? 'selected' : ''}><f:message
                                    key="roomType"
                                    bundle="${bunCont}"/></option>
                            </option>
                            <option value="number_of_persons" ${sorting == 'number_of_persons'? 'selected' : ''}>
                                <f:message
                                        key="numberOfPersons"
                                        bundle="${bunCont}"/></option>
                            </option>
                        </select>
                        <select class="select-size form-control" name="ordering"
                                style="width: 70px;margin-left: 3px;height: 40px">
                            <option value="ASC" ${ordering == 'ASC' ? 'selected' : ''}>A->Z</option>
                            <option value="DESC" ${ordering == 'DESC' ? 'selected' : ''}>Z->A</option>
                        </select>

                        <label for="itemsOnPage"
                               style="width: 65px;font-size: 8pt;margin-left: 30px; margin-right: 5px;font-weight: bold">
                            <f:message key="itemsPerPage" bundle="${bunCont}"/>
                        </label>
                        <select class="select-size form-control" id="itemsOnPage" name="itemsOnPage"
                                style="width: 60px;margin-right: 15%;height: 40px">
                            <option value="5" ${itemsOnPage == '5'? 'selected' : ''}>5</option>
                            <option value="10" ${itemsOnPage == '10' ? 'selected' : ''}>10</option>
                            <option value="15" ${itemsOnPage == '15' ? 'selected' : ''}>15</option>
                        </select>
                        <input name="command" value="myOrdersPage" hidden>
                    </form>
                </div>
            </div>
        </div>
    </div>
    <div class="row">
        <div class="col-2">
        </div>
        <div class="col-8 text-center">
            <table class="table table-striped" style="width: 100%;padding-bottom: 30px">
                <tr class="table-primary">
                    <th><f:message key="orderDate" bundle="${bunCont}"/></th>
                    <th><f:message key="hotel" bundle="${bunCont}"/></th>
                    <th><f:message key="checkIn" bundle="${bunCont}"/></th>
                    <th><f:message key="checkOut" bundle="${bunCont}"/></th>
                    <th><f:message key="roomType" bundle="${bunCont}"/></th>
                    <th><f:message key="numberOfPersons" bundle="${bunCont}"/></th>
                    <th><f:message key="status" bundle="${bunCont}"/></th>
                </tr>
                <c:forEach var="usersOrder" items="${listOfOrders}">
                    <tr>
                        <td><tags:fulldate date="${usersOrder.orderDate}"/></td>
                        <td>${usersOrder.hotelDto.name}</td>
                        <td><tags:fulldate date="${usersOrder.dateStart}"/></td>
                        <td><tags:fulldate date="${usersOrder.dateEnd}"/></td>
                        <td>${usersOrder.roomDtoResponse.roomType.typeName}</td>
                        <td>${usersOrder.numberOfPersons}</td>
                        <td ${usersOrder.orderStatus=='PROCESSED'?'':'hidden'}>
                            <f:message key="processed" bundle="${bunCont}"/>
                        </td>
                        <td ${usersOrder.orderStatus=='COMPLETED'?'':'hidden'}>
                            <f:message key="done" bundle="${bunCont}"/>
                        </td>
                    </tr>
                </c:forEach>
            </table>

            <div style="padding-bottom: 100px">
                <a class="btn btn-light"
                   href="home?command=myOrdersPage&numberOfPage=${numberOfPage-1<1?1:numberOfPage-1}&itemsOnPage=${itemsOnPage}&statusNotDone=${statusNotDone}&statusCompleted=${statusCompleted}&sorting=${sorting}&ordering=${ordering}"
                ${numberOfPage==1?'hidden':''}><</a>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="" ${numberOfPage>3&&i==1?'':'hidden'} style="text-decoration: none;">...</a>
                    <a class="${(numberOfPage==i)?'btn btn-primary':'btn btn-light'}"
                       href="home?command=myOrdersPage&numberOfPage=${i}&itemsOnPage=${itemsOnPage}&statusNotDone=${statusNotDone}&statusCompleted=${statusCompleted}&sorting=${sorting}&ordering=${ordering}"
                        ${Math.abs(numberOfPage-i)<3&&totalPages>1?'':'hidden'}>${i}</a>
                    <a href="" ${Math.abs(numberOfPage-totalPages)>3&&i==totalPages-1?'':'hidden'}
                       style="text-decoration: none;">...</a>
                </c:forEach>
                <a class="btn btn-light"
                   href="home?command=myOrdersPage&numberOfPage=${numberOfPage+1>totalPages?totalPages:numberOfPage+1}&itemsOnPage=${itemsOnPage}&statusNotDone=${statusNotDone}&statusCompleted=${statusCompleted}&sorting=${sorting}&ordering=${ordering}"
                ${numberOfPage>=totalPages?'hidden':''}>></a>
            </div>
        </div>
        <div class="col-2"></div>
    </div>
</div>
</body>
</html>
