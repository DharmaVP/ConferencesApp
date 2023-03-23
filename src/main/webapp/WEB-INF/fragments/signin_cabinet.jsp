<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>

<fmt:message key="header.sign.in" var="signin"/>
<fmt:message key="header.cabinet" var="cabinet"/>

<c:choose>
    <c:when test="${empty sessionScope.user}">
        <a href="controller?action=get_sign_in_page" class="w3-bar-item w3-button w3-hover-pink"><i class="fa fa-sign-in"></i> ${signin}</a>
    </c:when>
    <c:otherwise>
        <a href="controller?action=enter_cabinet" class="w3-bar-item w3-button w3-hover-pink"><i class="fa fa-calendar"></i> ${cabinet}</a>
    </c:otherwise>
</c:choose>


