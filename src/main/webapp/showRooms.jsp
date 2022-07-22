<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Show Rooms"/>

<c:import url="views/head.jsp"/>

<c:import url="views/header.jsp"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<body>

<div class="row mt-4">
    <div class="col-2">
        <h1 style="color: darkslateblue; padding-left: 10px;"> ${hotelName}</h1>

        <jsp:useBean id="now" class="java.util.Date"/>
        <f:formatDate var="currentDate" value="${now}" pattern="yyyy-MM-dd"/>

    </div>
    <div class="col-8">
        <div style="margin-top: 10px;" id="fieldsForSorting">
            <form style="display: flex;flex-direction: row;" class="" action="home" method="get" onchange="submit()">

                <div id="inputData">

                    <label for="start"
                           style="margin-left: 0px; width: 60px;font-size: 8pt; margin-right: 5px;font-weight: bold">Start
                        date:</label>

                    <input type="date" id="start" name="signIn" value="${signIn}"
                           min="${minSignIn}" max="${maxSignIn}">

                    <label for="start"
                           style="margin-left: 20px; width: 60px;font-size: 8pt; margin-right: 5px;font-weight: bold">Finish
                        date:</label>

                    <input type="date" id="finish" name="signOut"
                           value="${signOut}"
                           min="${minSignOut}" max="${maxSignOut}">
                </div>

                <label style="margin-left: 50px; width: 40px;font-size: 8pt; margin-right: 5px;font-weight: bold">Room
                    status:</label>
                <div style="font-size: 10pt;margin-left: 10px;">
                    <input type="checkbox" id="free" name="statusFree" value="free" ${statusFree=='free'?'checked':''}>
                    <label for="free">free</label><br>
                    <input type="checkbox" id="booked" name="statusBooked"
                           value="booked" ${statusBooked=='booked'?'checked':''}>
                    <label for="booked">booked</label><br>
                </div>

                <label for="roomsOnPage"
                       style="width: 70px;font-size: 8pt; margin-left: 30px; margin-right: 5px;font-weight: bold">
                    Order items by
                </label>
                <select class="select-size form-control" name="sorting" style="width: 140px;">
                    <option value="price" ${sorting == 'price' ? 'selected' : ''}>Price</option>
                    <option value="number_of_beds" ${sorting == 'number_of_beds' ? 'selected' : ''}>
                        Number of beds
                    </option>
                    <option value="type_name" ${sorting == 'type_name'? 'selected' : ''}>Room type</option>
                    </option>
                </select>

                <select class="select-size form-control" name="ordering"
                        style="width: 70px;margin-left: 3px;">
                    <option value="ASC" ${ordering == 'ASC' ? 'selected' : ''}>A->Z</option>
                    <option value="DESC" ${ordering == 'DESC' ? 'selected' : ''}>Z->A</option>
                </select>

                <label for="roomsOnPage"
                       style="width: 50px;font-size: 8pt;margin-left: 30px; margin-right: 5px;font-weight: bold">
                    Items per page
                </label>
                <select class="select-size form-control" id="roomsOnPage" name="recordsOnPage"
                        style="width: 60px;margin-right: 15%;">
                    <option value="5" ${recordsOnPage == '5'? 'selected' : ''}>5</option>
                    <option value="10" ${recordsOnPage == '10' ? 'selected' : ''}>10</option>
                    <option value="15" ${recordsOnPage == '15' ? 'selected' : ''}>15</option>
                </select>

                <input name="command" value="showRooms" hidden>
                <input name="hotelId" value="${hotelId}" hidden>
                <input name="hotelName" value="${hotelName}" hidden>

            </form>
        </div>
    </div>

    <div class="col-2">
        <table class="table table-sm">
            <tr ${user.firstName==null?'hidden':''}>
                <th><f:message key="firstName" bundle="${bunCont}"/></th>
                <td>${user.firstName}: ${user.id}</td>
            </tr>
            <tr ${user.lastName==null?'hidden':''}>
                <th><f:message key="lastName" bundle="${bunCont}"/></th>
                <td>${user.lastName}</td>
            </tr>
            <tr ${user.email==null?'hidden':''}>
                <th><f:message key="email" bundle="${bunCont}"/></th>
                <td>${user.email}</td>
            </tr>
        </table>
    </div>

</div>

<div class="">
    <div class="row">
        <div class="col-2">
        </div>

        <div class="col-8">

            <table class="table table-striped">
                <tr class="table-primary">
                    <th>#</th>
                    <th>Room type</th>
                    <th>Number of beds</th>
                    <th>Room status</th>
                    <th>From</th>
                    <th>To</th>
                    <th>Price</th>
                    <th></th>

                </tr>
                <c:set var="i" value="${(numberOfPage-1)*recordsOnPage}"/>
                <c:forEach items="${listOfRooms}" var="item">
                    <tr>
                        <td>${i=i+1}: ${item.roomDtoResponse.id}</td>
                        <td>${item.roomDtoResponse.roomType.typeName}</td>
                        <td>${item.roomDtoResponse.numberOfBeds}</td>
                        <td>${item.statusStatement.statusStatementName} : ${item.statusId} </td>
                        <td>${item.dateStart}</td>
                        <td>${item.dateEnd}</td>
                        <td>${item.roomDtoResponse.price} грн</td>
                        <td>
                            <form class="d-flex" action="home" method="post">
                                <button class="btn btn-primary" name="command" value="changeRoomStatus"
                                        type="submit" ${item.statusStatement.statusStatementName=='free'?'':'disabled'} ${user==null?'hidden':''}>
                                    book...
                                    <a class=""
                                       href="home?command=showRooms&numberOfPage=${i}&recordsOnPage=${recordsOnPage}&hotelId=${hotelId}&hotelName=${hotelName}&signIn=${signIn}&signOut=${signOut}&statusFree=${statusFree}&statusBooked=${statusBooked}"
                                       hidden></a>
                                </button>
                                <input name="roomId" value="${item.roomDtoResponse.id}" hidden>
                                <input name="roomStatusId" value="${item.statusId}" hidden>
                                <input name="dateStart" value="${signIn}" hidden>
                                <input name="dateEnd" value="${signOut}" hidden>
                                <input name="userId" value="${user.id}" hidden>
                                <input name="sorting" value="${sorting}" hidden>
                                <input name="ordering" value="${ordering}" hidden>
                                <input name="hotelId" value="${hotelId}" hidden>
                                <input name="hotelName" value="${hotelName}" hidden>
                                <input name="statusFree" value="${statusFree}" hidden>
                                <input name="statusBooked" value="${statusBooked}" hidden>
                                <input name="recordsOnPage" value="${recordsOnPage}" hidden>

                            </form>
                        </td>

                    </tr>
                </c:forEach>
            </table>
            <a class="btn btn-light"
               href="home?command=showRooms&numberOfPage=${numberOfPage-1<1?1:numberOfPage-1}&recordsOnPage=${recordsOnPage}&hotelId=${hotelId}&hotelName=${hotelName}&signIn=${signIn}&signOut=${signOut}&statusFree=${statusFree}&statusBooked=${statusBooked}"
            ${numberOfPage==1?'hidden':''}><</a>

            <c:forEach var="i" begin="1" end="${totalPages}">
                <a href="" ${numberOfPage>3&&i==1?'':'hidden'} style="text-decoration: none;">...</a>

                <a class="${(numberOfPage==i)?'btn btn-primary':'btn btn-light'}"
                   href="home?command=showRooms&numberOfPage=${i}&recordsOnPage=${recordsOnPage}&hotelId=${hotelId}&hotelName=${hotelName}&signIn=${signIn}&signOut=${signOut}&statusFree=${statusFree}&statusBooked=${statusBooked}"
                    ${Math.abs(numberOfPage-i)<3||i==totalPages?'':'hidden'}>${i}</a>

                <a href="" ${Math.abs(numberOfPage-totalPages)>3&&i==totalPages-1?'':'hidden'}
                   style="text-decoration: none;">...</a>

            </c:forEach>

            <a class="btn btn-light"
               href="home?command=showRooms&numberOfPage=${numberOfPage+1>totalPages?totalPages:numberOfPage+1}&recordsOnPage=${recordsOnPage}&hotelId=${hotelId}&hotelName=${hotelName}&signIn=${signIn}&signOut=${signOut}&statusFree=${statusFree}&statusBooked=${statusBooked}"
            ${numberOfPage==totalPages?'hidden':''}>></a>
        </div>

        <div class="col-2">


        </div>
    </div>
</div>

</body>
</html>