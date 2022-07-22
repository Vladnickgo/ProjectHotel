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
            <h1 class="text-center">${user.role} PROFILE</h1>
        </div>
        <div class="col-2">
            <table class="table table-sm">
                <tr>
                    <th><f:message key="firstName" bundle="${bunCont}"/></th>
                    <td>${user.firstName}</td>
                </tr>
                <tr>
                    <th><f:message key="lastName" bundle="${bunCont}"/></th>
                    <td>${user.lastName}</td>
                </tr>
                <tr>
                    <th><f:message key="email" bundle="${bunCont}"/></th>
                    <td>${user.email}</td>
                </tr>
            </table>
        </div>
    </div>
</div>


<div class="container mt-5 pt-5">
    <div class="row">
        <div class="col-4">
            <form action="">

            </form>

        </div>
        <div class="col-4 text-center">
            <h1 class="display-1"></h1>
        </div>
        <div class="col-4"></div>
    </div>
</div>


<c:import url="views/footer.jsp"/>
</body>
</html>
