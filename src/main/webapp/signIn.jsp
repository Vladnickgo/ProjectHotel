<%@ page contentType="text/html;charset=UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="SignIn"/>

<c:import url="views/head.jsp"/>

<body>

<c:import url="views/header.jsp"/>

<div class="container mt-5">
    <div class="row">
        <div class="col-4"></div>
        <div class="col-4" align="center">
            <h1 class="mb-5">
                <f:message key="userRegistration" bundle="${bunCont}"></f:message>
            </h1>
            <form action="home" method="post">
                <label>
                    <input name="command" value="register" hidden>
                </label>
                <table>
                    <tr>
                        <td>
                            <f:message key="firstName" bundle="${bunCont}"></f:message><sup>*</sup>
                        </td>
                        <td>
                            <input type="text" name="firstName" value=${firstName}><br>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <f:message key="lastName" bundle="${bunCont}"></f:message><sup>*</sup>
                        </td>
                        <td>
                            <input type="text" name="lastName" value=${lastName}><br>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            Email<sup>*</sup>
                        </td>
                        <td>
                            <input type="text" name="email" value=${email}><br>
                        </td>
                    </tr>
                    <tr>
                        <td>
                            <f:message key="password" bundle="${bunCont}"></f:message><sup>*</sup>
                        </td>
                        <td>
                            <input type="text" name="password"><br>
                        </td>
                    </tr>
                    <tr>
                        <td pr-2>
                            <f:message key="passwordConfirmation" bundle="${bunCont}"></f:message><sup>*</sup>
                        </td>
                        <td>
                            <input type="text" name="confirmationPassword"><br>
                        </td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>${confirmationPassword}
                            ${message}</td>
                    </tr>
                    <tr>
                        <td>
                            <label></label>
                        </td>
                        <td>
                            <input type="submit" value=<f:message key="submit" bundle="${bunCont}"></f:message>>
                        </td>
                    </tr>
                    <tr>
                        <td class="text-muted colspan-2">
                            <sup>*</sup> - <f:message key="requireFields" bundle="${bunCont}"></f:message>
                        </td>
                        <td>
                        </td>
                    </tr>
                </table>
            </form>
        </div>
        <div class="col-4"></div>
    </div>
</div>

<c:import url="views/footer.jsp"/>
</body>
</html>
