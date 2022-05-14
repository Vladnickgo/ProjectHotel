<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page import="java.util.*" %>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

    <style type="text/css">
        footer {
            position: fixed; /* Фиксированное положение */
            left: 0;
            bottom: 0; /* Левый нижний угол */
            padding: 10px; /* Поля вокруг текста */
            background: #F8F9FA; /* Цвет фона */
            color: darkgray; /* Цвет текста */
            width: 100%; /* Ширина слоя */
        }
    </style>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
    <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js"
            integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p"
            crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js"
            integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF"
            crossorigin="anonymous"></script>
    <title>Sign In Page</title>
</head>
<f:setLocale value="${sessionScope.language}"/>
<f:setBundle var="bunCont" basename="resources"/>

<body>
<nav class="navbar navbar-expand-lg navbar-light bg-light pb-2">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">Hello Hotel</a>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/index.jsp">
                        <f:message key="home" bundle="${bunCont}"></f:message>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/aboutUs.jsp">
                        <f:message key="about" bundle="${bunCont}"></f:message>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/contacts.jsp">
                        <f:message key="contacts" bundle="${bunCont}"></f:message>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/loginPage.jsp">
                        <f:message key="login" bundle="${bunCont}"></f:message>
                    </a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="${pageContext.request.contextPath}/signIn.jsp">
                        <f:message key="signin" bundle="${bunCont}"></f:message>
                    </a>
                </li>
            </ul>

            <form class="d-flex" action="hello-servlet" method="get" onchange="submit()">
                <select class="form-select" aria-label="Default select example" name="lang">
                    <option selected value="ua_UA" ${optionUkr}>укр</option>
                    <option value="en_US" ${optionEng}>eng</option>
                </select>
                <input type="text" name="pageName" value="/success.jsp" hidden>
            </form>
        </div>
    </div>
</nav>


<div class="container mt-5">
    <div class="row text-center">
        <div class="col-4"></div>
        <div class="col-4">
            <h1 class="mb-5">Contacts</h1>
        </div>
        <div class="col-4"></div>
    </div>
</div>

<div class="container mt-1">
    <div class="row text-center">
        <div class="col-4"></div>
        <div class="col-4">
            <h3>User is added</h3><br>
            <table>
                <tr>
                    <th>First name:</th>
                    <td>${firstName}</td>
                </tr>
                <tr>
                    <th>Last name:</th>
                    <td>${lastName}</td>
                </tr>
                <tr>
                    <th>Email:</th>
                    <td>${email}</td>
                </tr>
            </table>
            ${message}
        </div>
        <div class="col-4"></div>
    </div>
</div>
</body>
</html>
