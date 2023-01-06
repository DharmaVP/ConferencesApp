<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>
<fmt:message key="header.sign.in" var="signin"/>

<c:choose>
    <c:when test="${empty sessionScope.user}">
        <a href="sign_in.jsp" class="w3-bar-item w3-button w3-hover-pink"><i class="fa fa-sign-in"></i> ${signin}</a>
    </c:when>
    <c:otherwise>
        <a href="my_space.jsp" class="w3-bar-item w3-button w3-hover-pink"><i class="fa fa-calendar"></i> MY SPACE</a>
    </c:otherwise>
</c:choose>


