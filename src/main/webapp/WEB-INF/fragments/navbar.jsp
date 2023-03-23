<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>


<fmt:message key="sidebar.menu" var="menu"/>
<fmt:message key="sidebar.main" var="main"/>
<fmt:message key="sidebar.info" var="myInfo"/>
<fmt:message key="sidebar.events" var="myEvents"/>
<fmt:message key="sidebar.edit.events" var="editEvents"/>
<fmt:message key="sidebar.users" var="users"/>
<fmt:message key="sidebar.reports" var="reports"/>
<fmt:message key="sidebar.speaker.events" var="speakOnEvent"/>
<fmt:message key="sidebar.sign.out" var="signOut"/>


<!-- Sidebar -->
<nav class="w3-sidebar-wth-height w3-bar-block w3-collapse w3-large w3-theme-l5 w3-animate-left"
     style="top:98px" id="mySidebar">
    <a href="javascript:void(0)" onclick="w3_close()"
       class="w3-right w3-xlarge w3-padding-large w3-hover-black w3-hide-large" title="Close Menu">
        <i class="fa fa-remove"></i>
    </a>
    <h4 class="w3-bar-item"><b>${menu}</b></h4>

    <c:choose>
        <c:when test="${sessionScope.user.role eq 'admin'}">
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=enter_cabinet">${main}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=get_profile">${myInfo}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=user_events">${myEvents}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=manage_events">${editEvents}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=get_all_users">${users}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=get_all_reports&speaker_id=true&moderator=false&speaker=true">${reports}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=sign-out">${signOut}</a>
        </c:when>
        <c:when test="${sessionScope.user.role eq 'moderator'}">
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=enter_cabinet">${main}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=get_profile">${myInfo}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=user_events">${myEvents}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=manage_events">${editEvents}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=get_all_users">${users}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=get_all_reports&speaker_id=true&moderator=false&speaker=true">${reports}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=sign-out">${signOut}</a>
        </c:when>
        <c:when test="${sessionScope.user.role eq 'speaker'}">
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=enter_cabinet">${main}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=get_profile">${myInfo}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=user_events">${myEvents}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=view_events_to_speak">${speakOnEvent}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=view_speaker_reports&speaker_id=true&moderator=true&speaker=false">${reports}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=sign-out">${signOut}</a>
        </c:when>
        <c:when test="${sessionScope.user.role eq 'attendee'}">
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=enter_cabinet">${main}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=get_profile">${myInfo}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=user_events">${myEvents}</a>
            <a class="w3-bar-item w3-button w3-hover-black" href="controller?action=sign-out">${signOut}</a>
        </c:when>
    </c:choose>
</nav>

<!-- Overlay effect when opening sidebar on small screens -->
<div class="w3-overlay w3-hide-large" onclick="w3_close()" style="cursor:pointer" title="close side menu"
     id="myOverlay"></div>


<script>
    // Get the Sidebar
    var mySidebar = document.getElementById("mySidebar");

    // Get the DIV with overlay effect
    var overlayBg = document.getElementById("myOverlay");

    // Toggle between showing and hiding the sidebar, and add overlay effect
    function w3_open() {
        if (mySidebar.style.display === 'block') {
            mySidebar.style.display = 'none';
            overlayBg.style.display = "none";
        } else {
            mySidebar.style.display = 'block';
            overlayBg.style.display = "block";
        }
    }

    // Close the sidebar with the close button
    function w3_close() {
        mySidebar.style.display = "none";
        overlayBg.style.display = "none";
    }
</script>



