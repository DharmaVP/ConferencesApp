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
    <div class="w3-row w3-padding-64">
        <jsp:include page="/WEB-INF/fragments/message.jsp"/>
        <div class="w3-twothird w3-container">
            <h1 class="w3-text-teal">New report </h1>
            <p>
            <form action="controller?action=add_report" method="POST">

                <div class="w3-margin-bottom">
                    <label><b>Topic:</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="text" placeholder="Enter topic"
                           name="topic" value="${fn:escapeXml(report.topic)}" required/>
                </div>

                <div class="w3-margin-bottom">
                    <label><b>Description: </b></label>
                    <textarea class="w3-input w3-border w3-hover-light-gray"
                              name="outline" rows="10" cols="15">${fn:escapeXml(report.outline)}</textarea>
                </div>

                <input type="hidden" name="event_id" value="${param.event_id}">

                <div class="w3-margin-bottom">
                    <label><b>Speaker</b></label>
                    <select name="speaker_id">
                        <option value="">Not assigned</option>
                        <c:forEach var="speaker" items="${speakerList}">
                            <option value="${speaker.id}">${speaker.prefix} ${speaker.firstName} ${speaker.lastName}, ${speaker.jobTitle}, ${speaker.organisation}</option>
                        </c:forEach>
                    </select>
                </div>

                <button class="w3-button  w3-green w3-section w3-padding" type="submit">Create</button>


            </form>
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
