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


    <form action="controller?action=change_speaker" method="POST">
        <input type="hidden" name="report_id" value="${report.id}"/>

        <div class="w3-row w3-padding-64">
            <div class="w3-half w3-container">

                <h1 class="w3-text-teal">Report Info </h1>

                <div class="w3-margin-bottom">
                    <label><b>Topic:</b></label>
                    <div class="w3-input w3-border w3-light-gray">
                        ${fn:escapeXml(report.topic)}
                    </div>
                </div>

                <div class="w3-margin-bottom">
                    <label><b>Outline:</b></label>
                    <div class="w3-input w3-border w3-light-gray w3-textarea" style="height:300px">
                        ${fn:escapeXml(report.outline)}
                    </div>
                </div>

                <div class="w3-margin-bottom">
                    <label><b>Speaker:</b></label>
                    <label><b>Speaker</b></label>
                    <select name="speaker_id">
                        <option value="">Not assigned</option>
                        <c:forEach var="speaker" items="${speakerList}">
                            <option value="${speaker.id}">${speaker.prefix} ${speaker.firstName} ${speaker.lastName}, ${speaker.jobTitle}, ${speaker.organisation}</option>
                        </c:forEach>
                    </select>
                </div>
                <button class="w3-button  w3-green w3-section w3-padding" type="submit">Update</button>
            </div>


        </div>
    </form>
</div>


<jsp:include page="/WEB-INF/fragments/footer.jsp"/>

</body>
</html>


