<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>


<fmt:message key="succeed.registered" var="success"/>
<fmt:message key="finish.registration" var="finish"/>

<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Conferences World</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <jsp:include page="../../css/w3style.css"/>
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
    <jsp:include page="/WEB-INF/fragments/message.jsp"/>
    <h1 class="w3-text-teal">REPORT INFO </h1>
    <p>


    <div class="w3-margin-bottom">
        <b>Topic: </b> ${report.topic}
    </div>

    <div class="w3-margin-bottom">
        <b>Outline:</b> ${report.outline}

    </div>

    <div class="w3-margin-bottom">
        <b>Status:</b>

    </div>
    <h1 class="w3-text-teal">EVENT INFO </h1>
    <div class="w3-margin-bottom">
        <b>Event:</b> ${event.name}

    </div>
    <div class="w3-margin-bottom">
        <b>Event date and time:</b> ${event.eventDateTime}

    </div>
    <div class="w3-margin-bottom">
        <b>Event place:</b> ${event.city}

    </div>


    </p>
</div>
<div class="w3-third w3-container">
    <p class="w3-border w3-padding-large w3-padding-32 w3-center">AD</p>
    <p class="w3-border w3-padding-large w3-padding-64 w3-center">AD</p>
</div>
</div>
</div>


<jsp:include page="/WEB-INF/fragments/footer.jsp"/>

</body>
</html>


