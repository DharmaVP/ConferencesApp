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


    <form action="controller?action=edit_event" method="POST">
        <input type="hidden" name="event_id" value="${event.id}"/>
        <input type="hidden" name="name" value="${event.name}"/>
        <input type="hidden" name="description" value="${event.description}"/>

        <div class="w3-row w3-padding-64">
            <div class="w3-half w3-container">

                <h1 class="w3-text-teal">Event Info </h1>

                <div class="w3-margin-bottom">
                    <label><b>Name:</b></label>
                    <div class="w3-input w3-border w3-light-gray">
                        ${fn:escapeXml(event.name)}
                    </div>
                </div>

                <div class="w3-margin-bottom">
                    <label><b>Description:</b></label>
                    <div class="w3-input w3-border w3-light-gray w3-textarea" style="height:300px">
                        ${fn:escapeXml(event.description)}
                    </div>
                </div>

                <div class="w3-margin-bottom">
                    <label><b>Event Date and Time</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="datetime-local"
                           name="date_time" value="${fn:escapeXml(event.eventDateTime)}" required/>
                </div>
                <button class="w3-button  w3-green w3-section w3-padding" type="submit">Update</button>
            </div>

            <div class="w3-half w3-container">


                <h1 class="w3-text-teal">Address </h1>

                <input type="hidden" name="place_id" value="${event.placeId}"/>

                <div class="w3-margin-bottom">
                    <label><b>Building:</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="text"
                           placeholder="e.g Hilton, Kyiv Expo, etc."
                           name="building" value="${fn:escapeXml(event.building)}" required/>
                </div>


                <div class="w3-margin-bottom">
                    <label><b>Floor:</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="text"
                           placeholder="Enter Floor"
                           name="floor" value="${fn:escapeXml(event.floor)}" required pattern="^\d{1,4}$"/>
                </div>

                <div class="w3-margin-bottom">
                    <label><b>Street Number:</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="text"
                           placeholder="Enter Street Number"
                           name="street_number" value="${fn:escapeXml(event.streetNumber)}" required/>
                </div>

                <div class="w3-margin-bottom">
                    <label><b>Street Name:</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="text"
                           placeholder="Enter Street Name"
                           name="street_name" value="${fn:escapeXml(event.streetName)}" required/>
                </div>

                <div class="w3-margin-bottom">
                    <label><b>City:</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="text"
                           placeholder="Enter City"
                           name="city" value="${fn:escapeXml(event.city)}" required/>
                </div>

                <div class="w3-margin-bottom">
                    <label><b>Postal Code:</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="text"
                           placeholder="Enter zip-code"
                           name="postal_code" value="${fn:escapeXml(event.postalCode)}" required pattern="^\d{5}$"/>
                </div>

                <div class="w3-margin-bottom">
                    <label><b>Country:</b></label>
                    <input class="w3-input w3-border w3-hover-light-gray" type="text"
                           placeholder="Enter Country"
                           name="country" value="${fn:escapeXml(event.country)}" required/>
                </div>
            </div>

        </div>
    </form>
</div>


<jsp:include page="/WEB-INF/fragments/footer.jsp"/>

</body>
</html>


