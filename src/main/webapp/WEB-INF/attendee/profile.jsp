<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
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
        <div class="w3-twothird w3-container">
            <c:if test="${not empty sessionScope.succeed}">
                <div class="w3-container" style="bottom: 150px">
                    <div class="w3-panel w3-pale-green w3-leftbar w3-border-green">
                        <p>${success}</p>
                    </div>
                </div>
            </c:if>
            <c:if test="${not empty sessionScope.message}">
                <div class="w3-container">
                    <div class="w3-panel w3-pale-yellow w3-leftbar w3-border-yellow">
                        <p>${finish}</p>
                    </div>
                </div>
            </c:if>
            <h1 class="w3-text-teal">Your personal info </h1>
            <p>
            <form action="controller?action=edit_user" method="POST">

                <div class="w3-margin-bottom">
                    <label><b>Prefix:</b></label>

                    <select class="w3-select w3-border w3-hover-light-gray" name="prefix">
                        <option value="${fn:escapeXml(user.prefix)}" selected>${fn:escapeXml(user.prefix)}</option>
                        <option value="Mr.">Mr.</option>
                        <option value="Mrs.">Mrs.</option>
                        <option value="Ms.">Ms.</option>
                        <option value="Miss">Miss</option>
                        <option value="Dr.">Dr.</option>
                        <option value="Prof.">Prof.</option>
                        <option value="Sir">Sir</option>
                        <option value="Lady">Lady</option>
                        <option value="Lord">Lord</option>
                        <option value="Father">Father</option>
                        <option value="Sister">Sister</option>
                        <option value="Rev.">Rev.</option>
                    </select>
                </div>

                <div class="w3-margin-bottom">
                    <label><b>First Name:</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="text" placeholder="Enter Your Name"
                           name="first_name" value="${fn:escapeXml(user.firstName)}"/>
                </div>

                <div class="w3-margin-bottom">
                    <label><b>Last Name:</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="text" placeholder="Enter Your Last Name"
                           name="last_name" value="${fn:escapeXml(user.lastName)}"/>
                </div>

                <div class="w3-margin-bottom">
                    <label><b>Cell phone:</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="tel" placeholder="+380(12)345-67-89" pattern="^[\+]?[(]?[0-9]{3}[)]?[-\s\.]?[0-9]{3}[-\s\.]?[0-9]{4,6}$"
                           name="cell_phone" value="+${fn:escapeXml(user.phoneNumber)}"/>
                </div>


                <div class="w3-margin-bottom">
                    <label><b>Job Title:</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="text" placeholder="e.g Student, CFO, HR, etc."
                           name="job_title" value="${fn:escapeXml(user.jobTitle)}"/>
                </div>


                <div class="w3-margin-bottom">
                    <label><b>Organisation:</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="text" placeholder="Enter Your Company or University"
                           name="organisation" value="${fn:escapeXml(user.organisation)}"/>
                </div>

            <button class="w3-button  w3-green w3-section w3-padding" type="submit">Update</button>


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

