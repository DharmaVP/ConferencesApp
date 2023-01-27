%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>


<fmt:message key="signup.label.email" var="email"/>
<fmt:message key="signup.label.password" var="password"/>
<fmt:message key="signup.label.confirm_password" var="confirm_password"/>
<fmt:message key="signup.button.submit" var="signup"/>


<!DOCTYPE html>
<html lang="${language}">
<head>
    <title>Conferences World</title>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">

    <style>
        <jsp:include page="../../css/w3style.css"/>
        body, h1, h2, h3, h4, h5, h6 {
            font-family: "Raleway", sans-serif
        }

        body, html {
            height: 100%;
            line-height: 1.8;
        }

        .w3-bar .w3-button {
            padding: 16px;
        }

        tr:hover {
            background-color: #D6EEEE;
        }
    </style>
</head>
<body>
<jsp:include page="/WEB-INF/fragments/header.jsp"/>
<br><br>
<jsp:include page="/WEB-INF/fragments/navbar.jsp"/>


<div class="w3-main" style="margin-left:250px; margin-top: 25px; margin-bottom: 75px">

    <jsp:include page="/WEB-INF/fragments/message.jsp"/>
    <div class="w3-padding-64">
        <p class="w3-text-large w3-text_center">USER INFO</p>
        <p><strong>UserId: </strong> ${userInfo.id}</p>
        <p><strong>Email: </strong> ${userInfo.email}</p>
        <p><strong>Prefix: </strong> ${userInfo.prefix}</p>
        <p><strong>First Name: </strong> ${userInfo.firstName}</p>
        <p><strong>Last Name: </strong> ${userInfo.lastName}</p>
        <p><strong>Cell Phone: </strong> ${userInfo.phoneNumber}</p>
        <p><strong>Job Title: </strong> ${userInfo.jobTitle}</p>
        <p><strong>Organisation: </strong> ${userInfo.organisation}</p>
        <p><strong>Current role: </strong> ${userInfo.role}</p>


        <c:choose>
            <c:when test="${sessionScope.user.role eq 'admin'}">

                <form action=controller method="POST">
                    <input type="hidden" name="action" value="change_role">
                    <input type="hidden" name="user_id" value="${userInfo.id}">
                    <div class="w3-margin-bottom">
                        <label><b>Role:</b></label>

                        <select class="w3-select w3-border w3-hover-light-gray" name="role_name">
                            <c:forEach var="role" items="${roleList}">
                                <option value="${role.name}" ${role.name eq userInfo.role ? 'selected' : ''}>${role.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button class="w3-green w3-button">Change Role</button>
                </form>

            </c:when>
            <c:when test="${sessionScope.user.role eq 'moderator' and (userInfo.role eq 'attendee' or userInfo.role eq 'speaker')}">

                <form action=controller method="POST">
                    <input type="hidden" name="action" value="change_role">
                    <input type="hidden" name="user_id" value="${userInfo.id}">
                    <div class="w3-margin-bottom">
                        <label><b>Role:</b></label>

                        <select class="w3-select w3-border w3-hover-light-gray" name="role_name">
                            <c:forEach var="role" items="${roleList}">
                                <option value="${role.name}" ${role.name eq userInfo.role ? 'selected' : ''}>${role.name}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <button class="w3-green w3-button">Change Role</button>
                </form>

            </c:when>
            <c:otherwise>

            </c:otherwise>
        </c:choose>


    </div>

</div>
<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
