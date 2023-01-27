<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<%@ taglib prefix="ctg" uri="/WEB-INF/tld/tags.tld" %>

<c:set var="base" value="controller?action=view_event&event_id=${param.event_id}"/>
<c:set var="setSort" value="&sort="/>
<c:set var="sortField" value="&sort=${param.sort}"/>
<c:set var="chooseOrder"
       value="&order=${param.order eq 'DESC' ? 'ASC' : 'DESC'}"/>
<c:set var="orderField"
       value="&order=${param.order}"/>
<c:set var="limits" value="&page=1&records=${param.records}"/>


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

    </style>
</head>
<jsp:include page="/WEB-INF/fragments/header.jsp"/>
<br><br>
<jsp:include page="/WEB-INF/fragments/navbar.jsp"/>

<div class="w3-main" style="margin-left:250px; margin-top: 25px; margin-bottom: 175px">

    <jsp:include page="/WEB-INF/fragments/message.jsp"/>
    <div class="w3-row">
        <div class="w3-padding-64">
            <div class="w3-panel w3-border-pink w3-twothird">
                <p><b>Event: </b> ${event.name}</p>
                <p><b>Description: </b> ${event.description}</p>
                <p><b>Date: </b> ${event.eventDateTime}</p>
                <p><b>Place: </b> ${event.building},
                    <b>Floor: </b> ${event.floor},
                    <b>Address: </b> ${event.streetNumber},
                    ${event.streetName},
                    ${event.city},
                    ${event.postalCode},
                    ${event.country}
                </p>
            </div>
            <div class="w3-auto w3-onethird">
                <ctg:date dateTime="${event.eventDateTime}">
                    <c:choose>
                        <c:when test="${isRegistered == true}">
                            <form action=controller method="POST">
                                <input type="hidden" name="action" value="cancel_registration">
                                <input type="hidden" name="event_id" value="${param.event_id}">
                                <button class="w3-pink w3-button">Cancel Registration</button>
                            </form>
                        </c:when>
                        <c:otherwise>
                            <form action=controller method="POST">
                                <input type="hidden" name="action" value="register_for_event">
                                <input type="hidden" name="event_id" value="${param.event_id}">
                                <button class="w3-green w3-button">Register</button>
                            </form>
                        </c:otherwise>
                    </c:choose>
                </ctg:date>
            </div>
        </div>
        <table class="w3-table w3-striped">
            <caption>Reports</caption>
            <tr class="w3-pink">
                <th>Report number
                    <a href="${base}${setSort}report_id${chooseOrder}${limits}">
                        <i class="fa fa-unsorted"></i>
                    </a>
                </th>
                <th>Topic
                    <a href="${base}${setSort}topic${chooseOrder}${limits}">
                        <i class="fa fa-unsorted"></i>
                    </a>
                </th>
                <th>Outline</th>
                <th>Speaker</th>
            </tr>


            <c:forEach var="report" items="${reportList}">
                <tr>

                    <td>${report.id}</td>
                    <td>${report.topic}</td>
                    <td>${report.outline}</td>
                    <td>
                            ${report.prefix} ${report.firstName} ${report.lastName}, ${report.jobTitle}, ${report.organisation}
                    </td>

                </tr>
            </c:forEach>
        </table>

        <div>
            <c:if test="${requestScope.noOfPages > 1}">
                <div style="float: left;">
                    <!--For displaying previous link except for the 1st page -->
                    <c:if test="${requestScope.page != 1}">
                        <td>
                            <a href="${base}${sortField}${orderField}&page=${param.page-1}&records=${param.records}">Previous</a>
                        </td>
                    </c:if>
                </div>

                <div id="div3" style="float: left;">
                        <%--For displaying Page numbers.
                        The when condition does not display a link for the current page--%>
                    <table border="1" cellpadding="3" cellspacing="0">
                        <tr>
                            <c:forEach begin="1" end="${requestScope.noOfPages}" var="i">
                                <c:choose>
                                    <c:when test="${requestScope.page eq i}">
                                        <td>${i}</td>
                                    </c:when>
                                    <c:otherwise>
                                        <td>
                                            <a href="${base}${sortField}${orderField}&page=${i}&records=${param.records}">${i}</a>
                                        </td>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                        </tr>
                    </table>
                </div>

                <div style="float: left;">
                        <%--For displaying Next link except for the last page --%>
                    <c:if test="${requestScope.page lt requestScope.noOfPages}">
                        <td>
                            <a href="${base}${sortField}${orderField}&page=${param.page+1}&records=${param.records}">Next</a>
                        </td>
                    </c:if>
                </div>
            </c:if>
        </div>
        <div>
            <br>
            <label>Records per page</label>
            <select name="records" onchange="updateRecords(value)">
                <option value="" selected="selected" hidden="hidden">Choose</option>
                <option value="3" ${param.records eq "3" ? "selected" : ""}>3</option>
                <option value="5" ${param.records eq "5" ? "selected" : ""}>5</option>
                <option value="10" ${param.records eq "10" ? "selected" : ""}>10</option>
            </select>

        </div>

    </div>
</div>
<script>
    function updateRecords(value) {
        const url = new URL(window.location);
        url.searchParams.set('page', 1);
        url.searchParams.set('records', value);
        window.history.pushState(null, '', url.toString());
        location.reload()
    }
</script>

<jsp:include page="/WEB-INF/fragments/footer.jsp"/>
</body>
</html>
