<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${sessionScope.language}"/>
<fmt:setBundle basename="resources"/>

<fmt:message key="index.start" var="start"/>
<fmt:message key="index.stop" var="stop"/>
<fmt:message key="index.find" var="find"/>
<fmt:message key="header.about" var="about"/>
<fmt:message key="header.contact" var="contact"/>
<fmt:message key="index.about.features" var="features"/>
<fmt:message key="index.about.speakers" var="speakers"/>
<fmt:message key="index.about.reports" var="reports"/>
<fmt:message key="index.about.world" var="world"/>
<fmt:message key="index.about.support" var="support"/>
<fmt:message key="index.about.speakers.details" var="speakersInfo"/>
<fmt:message key="index.about.reports.details" var="reportsInfo"/>
<fmt:message key="index.about.world.details" var="worldInfo"/>
<fmt:message key="index.about.support.details" var="supportInfo"/>
<fmt:message key="index.contacts.contact" var="contacts"/>
<fmt:message key="index.contacts.geo" var="geo"/>
<fmt:message key="index.contacts.phone" var="phone"/>
<fmt:message key="index.contacts.mail" var="mail"/>


<!DOCTYPE html>
<html lang="${sessionScope.language}">

<head>
    <title>Conferences World</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        <jsp:include page="css/w3style.css"/>
        body, h1, h2, h3, h4, h5, h6 {
            font-family: "Raleway", sans-serif
        }

        body, html {
            height: 100%;
            line-height: 1.8;
        }

        /* Full height image header */
        .bgimg {
            background-position: center;
            background-size: cover;
            background-image: url("images/background2.jpg");
            min-height: 100%;
        }

        .w3-bar .w3-button {
            padding: 16px;
        }
    </style>
</head>
<body>

<jsp:include page="WEB-INF/fragments/header.jsp"/>


<!-- Header with full-height image -->
<header class="bgimg w3-display-container w3-grayscale-min" id="home">
    <div class="w3-display-left w3-text-white" style="padding:48px">
        <span class="w3-jumbo w3-hide-small">${start}</span><br>
        <span class="w3-xxlarge w3-hide-large w3-hide-medium">${start}</span><br>
        <span class="w3-large">${stop}</span>
        <p>
            <a href="controller?action=get_all_events&date_type=upcoming"
               class="w3-button w3-white w3-padding-large w3-large w3-margin-top w3-opacity w3-hover-opacity-off">
                ${find}
            </a>
        </p>
    </div>
</header>

<!-- About Section -->
<div class="w3-container" style="padding:128px 16px" id="about">
    <h3 class="w3-center"><strong>${about}</strong></h3>
    <p class="w3-center w3-large">${features}</p>
    <div class="w3-row-padding w3-center" style="margin-top:64px">
        <div class="w3-quarter">
            <i class="fa fa-users w3-margin-bottom w3-jumbo w3-center"></i>
            <p class="w3-large"><strong>${speakers}</strong></p>
            <p>${speakersInfo}</p>
        </div>
        <div class="w3-quarter">
            <i class="fa fa-university w3-margin-bottom w3-jumbo"></i>
            <p class="w3-large"><strong>${reports}</strong></p>
            <p>${reportsInfo}</p>
        </div>
        <div class="w3-quarter">
            <i class="fa fa-plane w3-margin-bottom w3-jumbo"></i>
            <p class="w3-large"><strong>${world}</strong></p>
            <p>${worldInfo}</p>
        </div>
        <div class="w3-quarter">
            <i class="fa fa-cog w3-margin-bottom w3-jumbo"></i>
            <p class="w3-large"><strong>${support}</strong></p>
            <p>${supportInfo}</p>
        </div>
    </div>
</div>


<!-- Contact Section -->
<div class="w3-container w3-light-grey" style="padding:128px 16px" id="contact">
    <div class="w3-row">
        <div class="w3-container w3-third">
            <h3 class="w3-center"><strong>${contact}</strong></h3>
            <p class="w3-center w3-large">${contacts}</p>
            <div style="margin-top:48px">

                <p><i class="fa fa-map-marker fa-fw w3-xxlarge w3-margin-right"></i> ${geo}</p>
                <p><i class="fa fa-phone fa-fw w3-xxlarge w3-margin-right"></i> ${phone} <a href="tel:+380508888888">+380(50)888-88-88</a>
                </p>
                <p><i class="fa fa-envelope fa-fw w3-xxlarge w3-margin-right"> </i> ${mail} <a
                        href="mailto:myconferences.world@gmail.com">myconferences.world@gmail.com</a></p>
                <br>
            </div>
        </div>
        <div class="w3-container w3-twothird">
            <!-- Image of location/map -->
            <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d9419.657437066824!2d30.508136851067402!3d50.45247508104787!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x40d4ce59e3ab65a7%3A0x694b8565cab02eaf!2z0JfQvtC70L7RgtGL0LUg0LLQvtGA0L7RgtCw!5e0!3m2!1sru!2sua!4v1676569745530!5m2!1s${language}!2s${language}"
                    width="900" height="450" style="border:0;" allowfullscreen="" loading="lazy"
                    referrerpolicy="no-referrer-when-downgrade"></iframe>
        </div>

    </div>
</div>

<jsp:include page="WEB-INF/fragments/footer.jsp"/>

<script>
    // Modal Image Gallery
    function onClick(element) {
        document.getElementById("img01").src = element.src;
        document.getElementById("modal01").style.display = "block";
        var captionText = document.getElementById("caption");
        captionText.innerHTML = element.alt;
    }
</script>


</body>
</html>


