<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Show Rooms"/>

<c:import url="views/head.jsp"/>

<c:import url="views/header.jsp"/>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<body>
<h1>
    ${hotelName}
</h1>
<div class="container">
    <div class="row">
        <div class="col-8">
            <form style="display: flex;flex-direction: row;" class="" action="home" method="post" onchange="submit()">
                <label for="roomsOnPage" style="width: 70px;font-size: 8pt; margin-right: 10px;">
                    Order items by
                </label>
                <select class="select-size form-control" name="sorting" style="width: 140px;">
                    <option value="price" ${sorting == 'price' ? 'selected' : ''}>Price</option>
                    <option value="number_of_beds" ${sorting == 'number_of_beds' ? 'selected' : ''}>Number of
                        beds
                    </option>
                    <option value="type_name" ${sorting == 'type_name'? 'selected' : ''}>Room type</option>
                    <option value="status_name" ${sorting == 'status_name' ? 'selected' : ''}>Room status
                    </option>
                </select>

                <select class="select-size form-control" name="ordering"
                        style="width: 70px;margin-left: 3px;">
                    <option value="ASC" ${ordering == 'ASC' ? 'selected' : ''}>A->Z</option>
                    <option value="DESC" ${ordering == 'DESC' ? 'selected' : ''}>Z->A</option>
                </select>

                <label for="roomsOnPage" style="width: 50px;font-size: 8pt;margin-left: 30px; margin-right: 10px;">
                    Items per page
                </label>
                <select class="select-size form-control" id="roomsOnPage" name="roomsOnPage"
                        style="width: 60px;margin-right: 15%;">
                    <option value="5" ${roomsOnPage == '5'? 'selected' : ''}>5</option>
                    <option value="10" ${roomsOnPage== '10' ? 'selected' : ''}>10</option>
                    <option value="15" ${roomsOnPage == '15' ? 'selected' : ''}>15</option>
                </select>

                <input class="btn btn-light" style="width: 40px;"
                       type="submit" name="changePage" value="<">
                <div style="padding-top:5px;">${actualPageNumber}/${numberOfPages}</div>
                <input class="btn btn-light" style="width: 40px;"
                       type="submit" name="changePage" value=">">

                <input name="command" value="showRooms" hidden>
                <input name="hotelId" value="${hotelId}" hidden>
                <input name="hotelName" value="${hotelName}" hidden>
                <input name="actualPageNumber" value="${actualPageNumber}" hidden>

            </form>
        </div>

        <div class="col-4">
        </div>

    </div>
    <table class="table table-striped">
        <tr class="table-primary">
            <th>#</th>
            <th>Room type</th>
            <th>Number of beds</th>
            <th>Room status</th>
            <th>Price</th>
        </tr>
        <c:set var="i" value="0"/>
        <c:forEach items="${listOfRooms}" var="item">
            <tr>
                <td>${i=i+1}</td>
                <td>${item.roomType.typeName}</td>
                <td>${item.numberOfBeds}</td>
                <td>${item.roomStatus.statusName}</td>
                <td>${item.price} грн</td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
