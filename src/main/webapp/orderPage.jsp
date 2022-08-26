<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Order page"/>

<c:import url="views/head.jsp"/>

<html>
<head>
    <title></title>
</head>
<body>
<c:import url="views/header.jsp"/>
<div class="row">
    <div class="col-2"></div>
    <div class="col-8">
        <h1 style="margin:25px; text-align: center">
            <f:message key="hotelBooking" bundle="${bunCont}"/>
        </h1>
        <div class="row">
            <div class="col-3"></div>
            <div class="col-6" style="margin-top:25px ;padding: 20px;
                                box-shadow: inset 0 0 1em rgba(0,0,0,0.1),
                                0 0  0 2px rgb(255,255,255),
                                0.3em 0.3em 1em rgba(0,0,0,0.3);">
                <form class="row g-3" action="home" method="get" onchange=getMinDateEnd()>
                    <div class="col-md-12">
                        <label for="hotel" class="form-label">
                            <f:message key="hotel" bundle="${bunCont}"/>
                        </label>
                        <select class="form-select" id="hotel" name="hotelId" required>
                            <option selected disabled value="">Choose...</option>
                            <c:forEach var="hotelItem" items="${allHotels}">
                                <option value="${hotelItem.id}"
                                    ${hotelItem.id==hotelId?'selected':''}
                                >${hotelItem.name}</option>
                            </c:forEach>

                        </select>
                    </div>
                    <div id="inputData">
                        <div class="row">
                            <div class="col-6">
                                <label for="start" style="">
                                    <f:message key="checkIn" bundle="${bunCont}"/>:
                                </label>
                                <input class="form-control" type="date" id="start" name="dateStart" value="${dateStart}"
                                       min="${minDateStart}" max="${maxDateStart}">
                            </div>
                            <div class="col-6">
                                <label for="finish" style="">
                                    <f:message key="checkOut" bundle="${bunCont}"/>:
                                </label>
                                <input class="form-control" type="date" id="finish" name="dateEnd"
                                       value="${dateEnd}"
                                       max="${maxDateEnd}">
                            </div>
                        </div>
                    </div>
                    <div class="col-md-12">
                        <div class="row">
                            <div class="col-md-6">
                                <label for="numberOfPersons" class="form-label">
                                    <f:message key="numberOfPersons" bundle="${bunCont}"/></label>
                                <input class="form-control" type="number" id="numberOfPersons" name="numberOfPersons"
                                       value="${numberOfPersons==null||numberOfPersons<1?1:numberOfPersons}"
                                       min="1">
                            </div>

                            <div class="col-md-6">
                                <label for="roomType" class="form-label"><f:message key="roomType"
                                                                                    bundle="${bunCont}"/></label>
                                <select class="form-select" id="roomType" name="roomTypeId" required>
                                    <option selected disabled value="">Choose...</option>
                                    <c:forEach var="roomTypeItem" items="${allRoomTypes}">
                                        <option value="${roomTypeItem.typeId}" ${roomTypeItem.typeId==roomTypeId?'selected':''}>${roomTypeItem.typeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </div>
                    <input name="hotelId" value="${hotelDto}" hidden>
                    <input name="roomTypeId" value="${roomTypeId}" hidden>
                    <button class="btn btn-outline-primary"
                            name="command" value="usersOrder"
                            type="submit"
                            style="">
                        Ok
                    </button>
                </form>
                <div class="col-12">
                </div>

            </div>
            <div class="col-3"></div>
        </div>
        <div class="col-2"></div>

    </div>
</div>
<script>
    function getMinDateEnd() {

        let formatter = new Intl.DateTimeFormat("en-US", {
            weekday: "long",
            year: "numeric",
            month: "long",
            day: "numeric"
        });

        let dateStart = document.getElementById('start').value;
        let dateStartMin = document.getElementById('start').min;
        let dateStartMax = document.getElementById('start').max;
        console.log("dateStart: " + dateStart + "   min:" + dateStartMin + "   max:" + dateStartMax);

        let dateStartNumber = new Date(document.getElementById('start').valueAsNumber);
        document.getElementById('finish').min = dateStartNumber.getFullYear() + '-';
        document.getElementById('finish').min += dateStartNumber.getMonth() + 1 < 10 ? '0' + (dateStartNumber.getMonth() + 1) + '-' : (dateStartNumber.getMonth() + 1) + '-';
        document.getElementById('finish').min += dateStartNumber.getDate() + 1;
        let dateEnd = document.getElementById('finish').value;
        let dateEndMin = document.getElementById('finish').min;
        let dateEndMax = document.getElementById('finish').max;
        console.log("dateEnd: " + dateEnd + "   min:" + dateEndMin + "   max:" + dateEndMax);

        console.log("dateEndMin: " + dateEndMin);
    }
</script>
</body>
</html>
