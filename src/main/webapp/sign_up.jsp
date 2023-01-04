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



<!DOCTYPE html>
<html lang="${language}">
<head>
    <link rel="stylesheet" href="css/w3style.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <meta charset="UTF-8">
    <title>User Form</title>
    <style> .error {
        color: red;
    }

    .success {
        color: green;
    }</style>
    <style>
        body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", sans-serif}

        body, html {
            height: 100%;
            line-height: 1.8;
        }

        /* Full height image header */
        .bgimg-1 {
            background-position: center;
            background-size: cover;
            background-image: url("./images/samuel-pereira-uf2nnanwa8q-unsplash.jpg");
            min-height: 100%;
        }

        .w3-bar .w3-button {
            padding: 16px;
        }
    </style>
</head>
<body>
<jsp:include page="fragments/header.jsp"></jsp:include>
<form>
    <select id="language" name="language" onchange="submit()">
        <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
        <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Uk</option>
    </select>
</form>
<form action="controller?action=sign-up" method="POST">
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
    <p>
        <label for="confirm_password">${confirm_password}:</label><br>
        <input type="text" id="confirm_password" name="confirm_password" value="${fn:escapeXml(param.confirm_password)}" required>
        <span class="error">${errors.confirm_password}</span><br><br>
    </p>

    <input type="submit" value=${signup} />
    <span class="success">${success}</span>


<%--    <c:remove var="invalid_email" scope="session"/>--%>
<%--    <c:remove var="invalid_password" scope="session"/>--%>
<%--    <c:remove var="onfirm_password" scope="session"/>--%>
<%--    <c:remove var="invalid_email" scope="session"/>--%>
<%--    <c:remove var="error" scope="session"/>--%>
</form>
</body>
</html>

