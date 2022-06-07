<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/fmt" %>
<html lang="${param.language}">
<f:setLocale value="${sessionScope.language}"/>
<f:setBundle basename="resources"/>
<nav class="navbar navbar-expand-sm navbar-light bg-light pb-2">
    <div class="container-fluid">
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav me-auto mb-2 ">
                <a class="navbar-brand" href="#">{Hello Hotel} </a>

                <form class="d-flex" action="home" method="get">
                    <button class="btn btn-outline-primary" name="command" value="homePage" type="submit">
                        <f:message key="home"></f:message>
                    </button>
                    <button class="btn btn-outline-primary" name="command" value="showHotels" type="submit">
                        <f:message key="hotels"></f:message>
                    </button>
                    <button class="btn btn-outline-primary" name="command" value="aboutPage" type="submit">
                        <f:message key="about"></f:message>
                    </button>
                    <button class="btn btn-outline-primary" name="command" value="contactsPage" type="submit">
                        <f:message key="contacts"></f:message>
                    </button>
                    <c:if test="${user == null}">
                        <button class="btn btn-outline-primary" name="command" value="loginPage" type="submit">
                            <f:message key="login"></f:message>
                        </button>
                        <button class="btn btn-outline-primary" name="command" value="registerPage" type="submit">
                            <f:message key="signin"></f:message>
                        </button>
                    </c:if>
                    <c:if test="${user != null}">
                        <button class="btn btn-outline-primary" name="command" value="show-profile"
                                type="submit">
                            <f:message key="cabinet"></f:message>
                        </button>
                    </c:if>
                </form>
                <c:if test="${user != null}">
                    <li class="nav-item ">
                        <a>
                            <form class="d-flex" action="user" method="post">
                                <button class="btn btn-primary" name="command" value="logout" type="submit">
                                    <f:message key="logout"></f:message>
                                </button>
                            </form>
                        </a>
                    </li>
                </c:if>
            </ul>
            <form class="d-flex" method="post" onchange="submit()">
                <select class=" form-control select-size" id="language" name="language"
                        style="width: 120px;">
                    <option value="ua" ${language == 'ua' ? 'selected' : ''}>Українська</option>
                    <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                </select>
            </form>
        </div>
    </div>
</nav>




