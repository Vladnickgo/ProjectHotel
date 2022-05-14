<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="LogIn"/>

<c:import url="views/head.jsp"/>

<body>

<c:import url="views/header.jsp"/>


<div>
    <div class="row container-fluid">
        <div class="col-2">

        </div>
        <div class="col-8">
            <h1 class="text-center">USER PROFILE</h1>
        </div>
        <div class="col-2">
            <table class="table table-sm">
                <tr>
                    <th><f:message key="firstName" bundle="${bunCont}"></f:message></th>
                    <td>${user.firstName}</td>
                </tr>
                <tr>
                    <th><f:message key="lastName" bundle="${bunCont}"></f:message></th>
                    <td>${user.lastName}</td>
                </tr>
                <tr>
                    <th><f:message key="email" bundle="${bunCont}"></f:message></th>
                    <td>${user.email}</td>
                </tr>
            </table>
        </div>
    </div>
</div>


<c:import url="views/footer.jsp"/>
</body>
</html>
