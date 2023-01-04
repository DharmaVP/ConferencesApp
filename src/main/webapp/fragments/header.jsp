<%@ page contentType="text/html; charset=UTF-8"  pageEncoding="UTF-8" %>

<%--<fmt:message bundle="${textResources}" key="header.slide.1.title" var="title1"/>--%>
<%--<fmt:message bundle="${textResources}" key="header.slide.2.title" var="title2"/>--%>
<%--<fmt:message bundle="${textResources}" key="header.slide.3.title" var="title3"/>--%>
<%--<fmt:message bundle="${textResources}" key="header.sign.up" var="signup"/>--%>
<%--<fmt:message bundle="${textResources}" key="menu.add_conference" var="add"/>--%>



<!-- Navbar (sit on top) -->
<div class="w3-top">
  <div class="w3-bar w3-white w3-card" id="myNavbar">
    <a href="#home" class="w3-bar-item w3-button w3-wide">LOGO</a>
    <!-- Right-sided navbar links -->
    <div class="w3-right w3-hide-small">
      <a href="controller?action=all_events" class="w3-bar-item w3-button"> EVENTS</a>
      <a href="#about" class="w3-bar-item w3-button">ABOUT</a>
      <a href="#contact" class="w3-bar-item w3-button"><i class="fa fa-envelope"></i> CONTACT</a>
      <a href="sign_in.jsp" class="w3-bar-item w3-button"> SIGN IN</a>
      <a href="sign_up.jsp" class="w3-bar-item w3-button"> SIGN UP</a>

    </div>
    <!-- Hide right-floated links on small screens and replace them with a menu icon -->

    <a href="javascript:void(0)" class="w3-bar-item w3-button w3-right w3-hide-large w3-hide-medium" onclick="w3_open()">
      <i class="fa fa-bars"></i>
    </a>
  </div>
</div>




