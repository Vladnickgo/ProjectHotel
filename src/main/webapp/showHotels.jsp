<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="LogIn"/>

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
    <form action="home" method="post" onchange="submit()">
        <div style="text-align: right">
            <label>
                <input name="command" value="showHotels" hidden>
            </label>
            <select name="recordsOnPage">
                <option value="8" ${recordsOnPage==8?'selected':''}>8 на сторінці</option>
                <option value="16" ${recordsOnPage==16?'selected':''}>16 на сторінці</option>
                <option value="20" ${recordsOnPage==20?'selected':''}>20 на сторінці</option>
            </select>
<%--            <input type="submit" value="Send"/>--%>
        </div>

        <div class="fixed-bottom row text-center" style="bottom: 65px">
            <ul class="pagination" name="page"
                style="display:flex; flex-direction: row; justify-content: center;">
                <c:forEach var="i" begin="1" end="${pages}">
                    <input class="${(numberOfPage==i)?'btn-primary':'btn btn-light'}" style="width: 35px;
                            border-radius: ${(numberOfPage==i)?'20%':''};"
                           type="submit" name="page" value=${i}>
                </c:forEach>
            </ul>
        </div>

    </form>

    <%--    <table>--%>
    <%--        <c:forEach items="${list}" var="item">--%>
    <%--            <tr style="border: 1px solid lightgray; margin: 3px;padding: 3px;">--%>
    <%--                <td><i class='fas fa-hotel' style='font-size:48px;color:lightskyblue'></i></td>--%>
    <%--                <td style="font-size: 14pt"><c:out value="${item}"/></td>--%>
    <%--            </tr>--%>
    <%--        </c:forEach>--%>
    <%--    </table>--%>

    <div class="row text-center">
        <c:set var="salary" value="0"/>
        <table>
            <c:forEach items="${listOfHotels}" var="item">
                <c:set var="row" value="${row=(row+1)%2}"/>
                <c:if test="${row==1}">
                    <tr style="margin: 50px;padding: 30px;">
                    <td>
                            <%--                        <i class='fas fa-hotel' style='font-size:48px;color:lightskyblue'></i>--%>
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
<%--                                <c:out value="${item.id}"/>--%>
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
