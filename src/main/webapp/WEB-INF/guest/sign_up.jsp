<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<c:set var="language"
       value="${not empty param.language ? param.language : not empty language ? language : pageContext.request.locale}"
       scope="session"/>
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
    <link rel="stylesheet" href="../../css/toggle_switcher.css">
    <link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
    <style>
        <jsp:include page="../../css/w3style.css"/>
        <jsp:include page="../../css/toggle_switcher.css"/>
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
    </style>
    <script>
        function toggle() {
            const fields = [password, retypedpassword]

            fields.forEach( x => {
                if (x.type === "password") {
                    x.type = "text";
                } else {
                    x.type = "password";
                }
            });
        }
    </script>
</head>
<body>
<jsp:include page="../../fragments/header.jsp"></jsp:include>

<div class="w3-container w3-margin-top" style="padding:128px 16px" id="home">
    <form class="w3-container w3-card-4 w3-auto" style="max-width:400px" action="controller?action=sign-up"
          method="POST">
        <div class="w3-section">
            <div class="w3-margin-bottom">
                <label><b>${email}:</b></label>
                <input class="w3-input w3-border w3-hover-light-gray" type="text"
                       placeholder="Enter Your Email"
                       name="email" value="${fn:escapeXml(param.email)}" required>
                <span class="w3-text-red">${errors.email}</span>
            </div>
            <div class="w3-margin-bottom">
                <label><b>${password}:</b></label>
                <input class="w3-input w3-border w3-hover-light-gray" type="password" placeholder="Enter Password"
                       name="password" id="password"
                       value="${fn:escapeXml(param.password)}" required>
                <span class="w3-text-red">${errors.password}</span>
            </div>
            <div class="w3-margin-bottom">
                <label><b>${confirm_password}:</b></label>
                <input class="w3-input w3-border w3-hover-light-gray" type="password" placeholder="Confirm password"
                       name="confirm_password" id="retypedpassword"
                       value="${fn:escapeXml(param.confirm_password)}" required>
                <span class="w3-text-red">${errors.confirm_password}</span>
            </div>
            <div class="w3-bar">
                <label class="toggle">
                    <input type="checkbox" onclick="toggle()">
                    <span class="slider"></span>
                </label>
                <span>Show password</span>
            </div>
            <button class="w3-button w3-block w3-pink w3-section w3-padding" type="submit">Sign up</button>
            <div w3-panel>
                <span>Already have account?</span>
                <a href="controller?action=get_sign_in_page" class="w3-right w3-hover-text-blue">Sign in</a>
            </div>
            <c:if test="${not empty error}">
                <div class="w3-panel w3-pale-red w3-leftbar w3-border-red">
                    <p>${error}</p>
                </div>
            </c:if>
        </div>
    </form>
</div>


    <%--    <c:remove var="invalid_email" scope="session"/>--%>
    <%--    <c:remove var="invalid_password" scope="session"/>--%>
    <%--    <c:remove var="onfirm_password" scope="session"/>--%>
    <%--    <c:remove var="invalid_email" scope="session"/>--%>
    <%--    <c:remove var="error" scope="session"/>--%>

<jsp:include page="../../fragments/footer.jsp"/>
</body>
</html>

