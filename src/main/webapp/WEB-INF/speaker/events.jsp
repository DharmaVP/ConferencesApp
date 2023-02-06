<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>


<fmt:setLocale value="${language}"/>
<fmt:setBundle basename="resources"/>




<c:set var="base" value="controller?action=view_events_to_speak"/>
<c:set var="setSort" value="&sort="/>
<c:set var="sortField" value="&sort=${param.sort}"/>
<c:set var="chooseOrder"
       value="&order=${param.order eq 'DESC' ? 'ASC' : 'DESC'}"/>
<c:set var="orderField"
       value="&order=${param.order}"/>
<c:set var="limits" value="&page=1&records=${param.records}"/>
<c:set var="searchField" value="&search=${param.search}"/>
<c:set var="dateFrom" value="&date_from=${param.date_from}"/>
<c:set var="dateTo" value="&date_to=${param.date_to}"/>
<c:set var="speakerEvents" value="&speaker_events=${param.speaker_events}"/>


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


    <div class="w3-container w3-responsive" style="padding:16px 16px">
        <div>

            <div class="w3-container w3-border-red">
                <h1>Search</h1>
                <form method="GET" action="controller" class="w3-panel">
                    <input type="hidden" name="action" value="view_events_to_speak">
                    <label>Show events where I speak</label>
                    <input class="w3-check w3-margin-top" type="checkbox" name="speaker_events" value="true" ${param.speaker_events == true ? 'checked' : ''}>
                    <input type="hidden" name="sort" value=${param.sort}>
                    <input type="hidden" name="order" value=${param.order}>
                    <div class="w3-container">
                        <input class="w3-input w3-border w3-hover-light-gray" type="search" placeholder="Search Event"
                               name="search"
                               value="${fn:escapeXml(param.search)}">

                        <input type="date" name="date_from" placeholder="from"
                               value="${fn:escapeXml(param.date_from)}"/>
                        <input type="date" name="date_to" placeholder="to" value="${fn:escapeXml(param.date_to)}"/>
                        <input type="hidden" name=page value="1">
                        <input type="hidden" name=records value=${param.records}>
                        <button class="w3-button w3-green w3-section w3-padding" type="submit">Search</button>
                    </div>
                </form>
                <button class="w3-button w3-pink w3-section w3-padding" type="reset">
                    <a href=${base}>Reset search</a>
                </button>
            </div>
        </div>


        <table class="w3-table w3-striped">
            <tr class="w3-pink">
                <th>Name
                    <a href="${base}${setSort}name${chooseOrder}${searchField}${dateFrom}${dateTo}${speakerEvents}${limits}">
                        <i class="fa fa-unsorted"></i>
                    </a>
                </th>
                <th>Description</th>
                <th>Date
                    <a href="${base}${setSort}event_date${chooseOrder}${searchField}${dateFrom}${dateTo}${speakerEvents}${limits}">
                        <i class="fa fa-unsorted"></i>
                    </a>
                </th>
                <th>Place</th>
                <th>Participants
                    <a href="${base}${setSort}participants${chooseOrder}${searchField}${dateFrom}${dateTo}${speakerEvents}${limits}">
                        <i class="fa fa-unsorted"></i>
                    </a>
                </th>
                <th>View details</th>
            </tr>


            <c:forEach var="event" items="${eventList}">
                <tr>
                    <td>${event.name}</td>
                    <td>${event.description}</td>
                    <td>${event.eventDateTime}</td>
                    <td>${event.city}, ${event.country}</td>
                    <td>${event.participants}</td>
                    <td>
                        <form action="controller" method="GET">
                            <input name="action" value="view_event_to_speak" type="hidden"/>
                            <input name="event_id" value="${event.id}" type="hidden"/>
                            <button class="w3-button w3-green w3-hover" onclick="submit()">View</button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <%--    <jsp:include page="/WEB-INF/fragments/paginationbar.jsp"/>--%>

    <div>
        <c:if test="${requestScope.noOfPages > 1}">
            <div style="float: left;">
                <!--For displaying previous link except for the 1st page -->
                <c:if test="${requestScope.page != 1}">
                    <td>
                        <a href="${base}${sortField}${orderField}${dateType}${searchField}${dateFrom}${dateTo}${speakerEvents}&page=${param.page-1}&records=${param.records}">Previous</a>
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
                                        <a href="${base}${sortField}${orderField}${dateType}${searchField}${dateFrom}${dateTo}${speakerEvents}&page=${i}&records=${param.records}">${i}</a>
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
                        <a href="${base}${sortField}${orderField}${dateType}${searchField}${dateFrom}${dateTo}${speakerEvents}&page=${param.page+1}&records=${param.records}">Next</a>
                    </td>
                </c:if>
            </div>
        </c:if>
    </div>
    <div>
        <br>
        <label>Records per page</label>
        <select name="records" onchange="updateRecords(value)">
            <option value="3" ${records eq "3" ? "selected" : ""}>3</option>
            <option value="5" ${records eq "5" ? "selected" : ""}>5</option>
            <option value="10" ${records eq "10" ? "selected" : ""}>10</option>
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


<jsp:include page="../fragments/footer.jsp"/>
</body>
</html>
