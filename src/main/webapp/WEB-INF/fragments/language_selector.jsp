<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%--<c:set var="language"--%>
<%--       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"--%>
<%--       scope="session"/>--%>

<fmt:setLocale value="${language}"/>


<form class="w3-bar-item w3-button w3-hover-white">
  <select id="language" name="language" onchange="changeLanguage()">
    <option value="en" ${language == 'en' ? 'selected' : ''}>En</option>
    <option value="uk_UA" ${language == 'uk_UA' ? 'selected' : ''}>Uk</option>
  </select>
</form>


<script>
  function changeLanguage() {
    let language = document.getElementById("language").value;
    let request = new XMLHttpRequest();
    request.open("GET", "${requestPath}?language=" + language, true);

    request.onreadystatechange = function () {
      location.reload();
    };

    request.send();
  }
</script>
