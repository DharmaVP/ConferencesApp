<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language" value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}" scope="session" />
<fmt:setLocale value="${language}" />
<fmt:setBundle basename="resources" />


<fmt:message key="signup.label.email" var="email"/>
<fmt:message key="signup.label.password" var="password"/>
<fmt:message key="signup.label.confirm_password" var="confirm_password"/>
<fmt:message key="signup.button.submit" var="signup"/>
<html>
<head>
    <title>sign-in</title>
</head>
<body>
<form action="controller?action=sign-in" method="POST">
    <p>
        <label for="email">${email}:</label><br>
        <input type="text" id="email" name="email" value="${fn:escapeXml(param.email)}" required>
        <span class="error">${errors.email}</span>
    </p>
    <p>
        <label for="password">${password}:</label><br>
        <input type="text" id="password" name="password" value="${fn:escapeXml(param.password)}" required>
        <span class="error">${errors.password}</span>
    </p>
    <input type="submit" value=${signup} />
    <span class="success">${errors.error}</span>
</form>
</body>
</html>
