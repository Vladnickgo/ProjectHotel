<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Homepage"/>

<c:import url="views/head.jsp"/>

<body>
<div class="">
    <div class="row text-center" style="background-color:#F8F9FA">
        <div class="col-11">
            <c:import url="views/header.jsp"/>
        </div>
        <div class="col-1 pt-2">
            <form class="d-flex" method="get" onchange="submit()">
                <select class=" form-control select-size" id="language" name="language"
                        style="width: 120px;">
                    <option value="ua" ${language == 'ua' ? 'selected' : ''}>Українська</option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                </select>
                <input name="command" value="${command}" hidden>
            </form>
        </div>
    </div>
</div>


<%--<c:import url="views/header.jsp"/>--%>

<div class="container mt-5 mb-5 pb-5">
    <div class="row text-center">
        <div class="col-4"></div>
        <div class="col-4">
            <h1>Hello Hotel</h1>
        </div>
        <div class="col-4">
        </div>
    </div>
</div>

<div class="container mt-5 pt-5">
    <div class="row">
        <div class="col-4"></div>
        <div class="col-4 text-center">
            <h1 class="display-1"><f:message key="welcome" bundle="${bunCont}"></f:message></h1>
            <h2 class="mt-5">☆ ☆ ☆ ☆ ☆</h2>
        </div>
        <div class="col-4"></div>
    </div>
</div>
<c:import url="views/footer.jsp"/>
</body>
</html>