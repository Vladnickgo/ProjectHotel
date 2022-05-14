<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="LogIn"/>

<c:import url="views/head.jsp"/>

<body>

<c:import url="views/header.jsp"/>
<div class="container mt-5">
    <div class="row text-center">
        <div class="col-4"></div>
        <div class="col-4" align="center">
            <h1 class="mb-5">
                <f:message key="logForUsers" bundle="${bunCont}"></f:message>
            </h1>
            <form action="home" method="post">
                <label>
                    <input name="command" value="login" hidden>
                </label>
                <table>
                    <tr>
                        <td>email</td>
                        <td><input type="text" name="email"></td>
                    </tr>
                    <tr>
                        <td>
                            <f:message key="password" bundle="${bunCont}"></f:message>
                        </td>
                        <td><input type="password" name="password"></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td><input type="submit" value=<f:message key="submit" bundle="${bunCont}"></f:message>></td>
                    </tr>
                    <tr>
                        <td class="text-danger">${message}</td>
                    </tr>
                    <input type="text" name="pageName" value="login" hidden>
                </table>
            </form>
        </div>
        <div class="col-4"></div>
    </div>
</div>
<c:import url="views/footer.jsp"/>
</body>
</html>
