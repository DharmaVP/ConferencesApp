<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>

<fmt:message key="header.events" var="events"/>
<fmt:message key="header.about" var="about"/>
<fmt:message key="header.contact" var="contact"/>
<fmt:message key="header.sign.in" var="signin"/>
<fmt:message key="header.close" var="close"/>


<!-- Navbar (sit on top) -->
<div class="w3-top">
    <div class="w3-bar w3-white w3-card" id="myNavbar">
        <a href="${pageContext.request.contextPath}/index.jsp" class="w3-bar-item w3-button w3-hover-white"><img src="${pageContext.request.contextPath}/images/logo2.png" alt="logo"
                                                                                                                 style="width:90%;height:90%;"/></a>
        <!-- Right-sided navbar links -->
        <div class="w3-right w3-hide-small w3-hide-medium w3-margin">
            <a href="controller?action=get_all_events&date_type=upcoming" class="w3-bar-item w3-button w3-hover-pink"> ${events}</a>
            <a href="${pageContext.request.contextPath}/index.jsp#about" class="w3-bar-item w3-button w3-hover-pink"> ${about}</a>
            <a href="${pageContext.request.contextPath}/index.jsp#contact" class="w3-bar-item w3-button w3-hover-pink"><i
                    class="fa fa-envelope"></i> ${contact}</a>
            <jsp:include page="signin_cabinet.jsp" />
            <jsp:include page="language_selector.jsp" />
            </form>
        </div>


        <!-- Hide right-floated links on small screens and replace them with a menu icon -->

        <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hover-pink"
           onclick="w3_open()">
            <i class="fa fa-bars"></i>
        </a>
    </div>
</div>

<!-- Sidebar on small screens when clicking the menu icon -->
<nav class="w3-sidebar w3-bar-block w3-pink w3-card w3-animate-left w3-hide-large" style="display:none"
     id="mySidebar">
    <a href="javascript:void(0)" onclick="w3_close()" class="w3-bar-item w3-button w3-large w3-padding-16">${close}
        ×</a>
    <a href="controller?action=all_events&date_type=upcoming" class="w3-bar-item w3-button w3-hover-pink"> ${events}</a>
    <a href="${pageContext.request.contextPath}/index.jsp#about" onclick="w3_close()" class="w3-bar-item w3-button"> ${about}</a>
    <a href="${pageContext.request.contextPath}/index.jsp#contact" onclick="w3_close()" class="w3-bar-item w3-button"> ${contact}</a>
    <jsp:include page="signin_cabinet.jsp"/>
    <jsp:include page="language_selector.jsp" />
</nav>

<script>
    // Toggle between showing and hiding the sidebar when clicking the menu icon
    var mySidebar = document.getElementById("mySidebar");

    function w3_open() {
        if (mySidebar.style.display === 'block') {
            mySidebar.style.display = 'none';
        } else {
            mySidebar.style.display = 'block';
        }
    }

    // Close the sidebar with the close button
    function w3_close() {
        mySidebar.style.display = "none";
    }
</script>

