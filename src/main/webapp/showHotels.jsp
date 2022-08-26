<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Show hotels"/>

<%--<script src='https://kit.fontawesome.com/a076d05399.js' crossorigin='anonymous'></script>--%>

<body>

<c:import url="views/head.jsp"/>

<c:import url="views/header.jsp"/>

<html>
<head>
    <title>ShowHotels</title>
</head>
<body>

<div class="container">
    <h1 style="text-align: center">
        <f:message key="hotels" bundle="${bunCont}"/>
    </h1>
    <form action="home" method="get" onchange="submit()">
        <div class="row">
            <div class="col-5">
                <input name="command" value="showHotels" hidden>
                <%--                <input name="numberOfPage" value="${numberOfPage==null?1:numberOfPage}" hidden>--%>
                <select name="recordsOnPage">
                    <option value="8" ${recordsOnPage==8?'selected':''}>8 на сторінці</option>
                    <option value="16" ${recordsOnPage==16?'selected':''}>16 на сторінці</option>
                    <option value="20" ${recordsOnPage==20?'selected':''}>20 на сторінці</option>
                </select>
            </div>
            <div class="col-7">
                <a class="btn btn-light"
                   href="home?command=showHotels&numberOfPage=${numberOfPage-1<1?1:numberOfPage-1}&recordsOnPage=${recordsOnPage}" ${numberOfPage==1?'hidden':''}><</a>
                <c:forEach var="i" begin="1" end="${totalPages}">
                    <a href="" ${numberOfPage>3&&i==1?'':'hidden'} style="text-decoration: none;">...</a>
                    <a class="${(numberOfPage==i)?'btn btn-primary':'btn btn-light'}"
                       href="home?command=showHotels&numberOfPage=${i}&recordsOnPage=${recordsOnPage}" ${Math.abs(numberOfPage-i)<3||i==totalPages?'':'hidden'}>${i}</a>
                    <a href="" ${Math.abs(numberOfPage-totalPages)>3&&i==totalPages-1?'':'hidden'}
                       style="text-decoration: none;">...</a>
                </c:forEach>
                <a class="btn btn-light"
                   href="home?command=showHotels&numberOfPage=${numberOfPage+1>totalPages?totalPages:numberOfPage+1}&recordsOnPage=${recordsOnPage}"
                ${numberOfPage==totalPages?'hidden':''}>></a>
            </div>
        </div>
    </form>

    <div class="row text-center">
        <c:set var="salary" value="0"/>
        <table>
            <c:forEach items="${listOfHotels}" var="item">
                <c:set var="row" value="${row=(row+1)%2}"/>
                <c:if test="${row==1}">
                    <tr style="margin: 50px;padding: 30px;">
                    <td>
                    </td>
                    <td style="font-size: 14pt; width: 350px;">
                        <form action="home" method="get" style="margin: auto;">
                            <button class="btn btn-outline-primary" name="command" value="showRooms" type="submit"
                                    style="width: 500px;height: 60px;">
                                <b><c:out value="${item.name}"/></b>
                                    <%--                                <c:out value="${item.id}"/>--%>
                            </button>
                            <input name="hotelId" value="${item.id}" hidden>
                            <input name="hotelName" value="${item.name}" hidden>
                        </form>
                    </td>
                    <td></td>
                </c:if>
                <c:if test="${row==0}">
                    <td><i class='fas fa-hotel' style='font-size:48px;color:lightskyblue'></i></td>
                    <td style="font-size: 14pt; width: 350px;">
                        <form action="home" method="get" style="margin: auto;">
                            <button class="btn btn-outline-primary" name="command" value="showRooms" type="submit"
                                    style="width: 500px;height: 60px;">
                                <b><c:out value="${item.name}"/></b>
                            </button>
                            <input name="hotelId" value="${item.id}" hidden>
                            <input name="hotelName" value="${item.name}" hidden>
                        </form>
                    </td>
                    </tr>
                </c:if>
            </c:forEach>
        </table>
    </div>
</div>
<c:import url="views/footer.jsp"/>
</body>
</html>