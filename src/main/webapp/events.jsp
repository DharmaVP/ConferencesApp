<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<html>
<head>
    <title>Title</title>
    <style> .error {
        color: red;
    }

    .success {
        color: green;
    }</style>
</head>
<body>
<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <th>Event Id</th>
        <th>Name</th>
        <th>Description</th>
        <th>Date</th>
        <th>Register</th>
    </tr>

    <c:forEach var="event" items="${eventList}">
        <tr>
            <td>${event.id}</td>
            <td>${event.name}</td>
            <td>${event.description}</td>
            <td>${event.eventDateTime}</td>
            <td>
                <form action="controller?action=register_for_event" method="POST">
                    <input name="event.id" value="${event.id}" type="hidden"/>
                    <button>register</button>
                    <span class="error">${error}</span>
                    <span class="success">${success}</span>
                </form>

            </td>
        </tr>
    </c:forEach>

</table>

<%--For displaying Previous link except for the 1st page --%>
<c:if test="${currentPage != 1}">
    <td><a href="contorller?page=${currentPage - 1}">Previous</a></td>
</c:if>

<%--For displaying Page numbers. The when condition does not display
            a link for the current page--%>

<table border="1" cellpadding="5" cellspacing="5">
    <tr>
        <c:forEach begin="1" end="${noOfPages}" var="i">
            <c:choose>
                <c:when test="${currentPage eq i}">
                    <td>${i}</td>
                </c:when>
                <c:otherwise>
                    <td><a href="controller?page=${i}">${i}</a></td>
                </c:otherwise>
            </c:choose>
        </c:forEach>
    </tr>
</table>

<%--For displaying Next link --%>

<c:if test="${currentPage lt noOfPages}">
    <td><a href="employee.do?page=${currentPage + 1}">Next</a></td>
</c:if>

<c:remove var="success" scope="session"/>--%>

</body>
</html>
