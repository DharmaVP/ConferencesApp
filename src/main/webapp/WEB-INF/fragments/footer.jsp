<%--<%@ page contentType="text/html;charset=UTF-8" language="java" %>--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}" />

<fmt:setBundle basename="resources"/>
<fmt:message key="footer.top" var="top"/>
<fmt:message key="footer.develop" var="develop"/>

<footer class="w3-center w3-pink w3-padding-24">
<%--    style="position: absolute; bottom: 0; width: 100%;"--%>
    <a href="#home" class="w3-button w3-light-grey"><i class="fa fa-arrow-up w3-margin-right"></i>${top}</a>
    <div class="w3-xlarge w3-section">
        <a href="https://www.linkedin.com/in/volodymyr-ponomarenko/" target="_blank"><i class="fa fa-linkedin w3-hover-opacity"></i></a>
        <a href="https://www.facebook.com/vladimir.ponomarenko.73/" target="_blank"><i class="fa fa-facebook-official w3-hover-opacity"></i></a>
        <a href="https://github.com/DharmaVP" target="_blank"><i class="fa fa-github w3-hover-opacity"></i></a>
    </div>
    <div>
    <p>${develop} <a href="https://github.com/DharmaVP" title="VP" target="_blank" class="w3-hover-text-cyan">VOLODYMYR PONOMARENKO</a>, 2022</p>
    </div>
</footer>
