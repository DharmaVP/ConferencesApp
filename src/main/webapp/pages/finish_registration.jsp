
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>WELCOME, <c:out value="${sessionScope.user.id}"/></p>
<p>WELCOME, <c:out value="${sessionScope.user.email}"/></p>
<p>WELCOME, <c:out value="${sessionScope.user.role}"/></p>

</body>
</html>
