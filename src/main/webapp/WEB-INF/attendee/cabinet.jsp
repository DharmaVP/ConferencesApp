<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="cfg" uri="/WEB-INF/tld/tags.tld"%>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>




<fmt:message key="signup.label.email" var="email"/>
<fmt:message key="signup.label.password" var="password"/>
<fmt:message key="signup.label.confirm_password" var="confirm_password"/>
<fmt:message key="signup.button.submit" var="signup"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Conferences World</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <%@ include file="../../css/w3style.css" %>
        body, h1, h2, h3, h4, h5, h6 {
            font-family: "Raleway", sans-serif
        }

        body, html {
            height: 100%;
            line-height: 1.8;
        }

        .w3-bar .w3-button {
            padding: 16px;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/fragments/header.jsp"/>
<br><br>
<jsp:include page="/WEB-INF/fragments/navbar.jsp"/>


<!-- Overlay effect when opening sidebar on small screens -->

<!-- Main content: shift it to the right by 250 pixels when the sidebar is visible -->
<div class="w3-main" style="margin-left:250px">
    <div class="w3-row w3-padding-64">
        <div class="w3-twothird w3-container">
            <h1 class="w3-text-teal">Welcome, ${user.role} <cfg:welcome role="admin"/></h1>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et
                dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip
                ex ea commodo consequat. Lorem ipsum
                dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna
                aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo
                consequat.</p>
        </div>
        <div class="w3-third w3-container">
            <p class="w3-border w3-padding-large w3-padding-32 w3-center">AD</p>
            <p class="w3-border w3-padding-large w3-padding-64 w3-center">AD</p>
        </div>
    </div>
    <div class="w3-row">
        <div class="w3-twothird w3-container">
            <h1 class="w3-text-teal">We miss you, ${user.email} </h1>
            <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Lorem ipsum
                dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat.</p>
        </div>
        <div class="w3-third w3-container">
            <p class="w3-border w3-padding-large w3-padding-32 w3-center">AD</p>
            <p class="w3-border w3-padding-large w3-padding-64 w3-center">AD</p>
        </div>
    </div>


</div>

<%--<%@ include file="/WEB-INF/fragments/footer.jsp" %>--%>
<jsp:include page="/WEB-INF/fragments/footer.jsp"/>

</body>
</html>
