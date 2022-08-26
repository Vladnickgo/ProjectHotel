<%@ page contentType="text/html;charset=UTF-8" %>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>

<f:setLocale value="${sessionScope.language}"/>

<f:setBundle var="bunCont" basename="resources"/>

<c:set var="Title" scope="request" value="Homepage"/>

<c:import url="/views/head.jsp"/>

<head>
</head>
<body>
<c:import url="views/header.jsp"/>
<p style="text-align: center; margin:30px;">
    <img src="/images/image-not-found.png" alt="Page is not available">
</p>
<c:import url="views/footer.jsp"/>
</body>
</html>
