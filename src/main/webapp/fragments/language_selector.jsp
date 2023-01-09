<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>

<fmt:setLocale value="${language}"/>


<form class="w3-bar-item w3-button w3-hover-white">
  <select name="language" onchange="submit()">
    <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
    <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Uk</option>
  </select>
</form>
