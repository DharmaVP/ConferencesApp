<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<div>
  <c:if test="${not empty sessionScope.success}">
    <div class="w3-container" style="bottom: 150px">
      <div class="w3-panel w3-pale-green w3-leftbar w3-border-green">
        <p>${success}</p>
      </div>
    </div>
  </c:if>
  <c:if test="${not empty sessionScope.error}">
    <div class="w3-container" style="bottom: 150px">
      <div class="w3-panel w3-pale-red w3-leftbar w3-border-red">
        <p>${error}</p>
      </div>
    </div>
  </c:if>
  <c:remove var="success"/>
  <c:remove var="error"/>
</div>