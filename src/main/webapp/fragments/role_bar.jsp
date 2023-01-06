<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:choose>
  <c:when test="${sessionScope.role eq 'admin'}">
    <jsp:include page="admin_bar.jsp"/>
  </c:when>
  <c:when test="${sessionScope.role eq 'moderator'}">
    <jsp:include page="moderator_bar.jsp"/>
  </c:when>
  <c:when test="${sessionScope.role eq 'speaker'}">
    <jsp:include page="speaker_bar.jsp"/>
  </c:when>
  <c:when test="${sessionScope.role eq 'attendee'}">
    <jsp:include page="attendee_bar.jsp"/>
  </c:when>
</c:choose>
