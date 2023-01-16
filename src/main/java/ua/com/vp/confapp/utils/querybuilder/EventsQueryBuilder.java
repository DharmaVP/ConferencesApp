package ua.com.vp.confapp.utils.querybuilder;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.TablesColumns.*;
import static ua.com.vp.confapp.utils.querybuilder.QueryConstants.NOW;

public class EventsQueryBuilder implements QueryBuilder {
    private final List<String> filters;
    private final Set<String> allowedSortFields;

    private String sortField;
    private String order;

    private int limit;
    private int offset;


    {
        sortField = EVENT_DATE;
        filters = new ArrayList<>();
        allowedSortFields = Set.of(EVENT_ID, EVENT_NAME, EVENT_DATE);
        order = "ASC";
        offset = 0;
        limit = 5;
    }


    public EventsQueryBuilder setSearchedEvent(String searchFilter) {
        if (searchFilter != null && !searchFilter.isEmpty()) {
            filters.add("name LIKE '%" + searchFilter + "%'");
        }
        return this;
    }

    public EventsQueryBuilder setDateTypeFilter(String dateFilter) {
        if (dateFilter != null && dateFilter.equals("passed")) {
            filters.add(EVENT_DATE + " < " + NOW);
        } else if (dateFilter != null && dateFilter.equals("upcoming")) {
            filters.add(EVENT_DATE + " > " + NOW);
        }
        return this;
    }

    public EventsQueryBuilder setDateFilter(String dateFrom, String dateTo) {
        if ((dateFrom != null && !dateFrom.isEmpty())) {
            filters.add(EVENT_DATE + " >= '" + dateFrom + "'");
        }
        if ((dateTo != null && !dateTo.isEmpty())) {
            filters.add(EVENT_DATE + " <= '" + dateTo + "'");
        }
        return this;
    }


    public EventsQueryBuilder setUserIdFilter(long userIdFilter) {
        filters.add("user_id=" + userIdFilter);
        return this;
    }

    public EventsQueryBuilder setRoleFilter(String roleFilter) {
        if (roleFilter != null && isPositiveInt(roleFilter)) {
            filters.add("role_id=" + roleFilter);
        }
        return this;
    }


    public EventsQueryBuilder setSortField(String sortField) {
        if (sortField != null) {
            this.sortField = checkSortField(sortField);
        }
        return this;
    }

    public EventsQueryBuilder setOrder(String order) {
        if (order != null && order.equalsIgnoreCase("DESC")) {
            this.order = "DESC";
        }
        return this;
    }

    public EventsQueryBuilder setLimits(String currenPage, String records) {
        if (records != null && isPositiveInt(records)) {
            this.limit = Integer.parseInt(records);
        }
        if (currenPage != null && isPositiveInt(currenPage)) {
            this.offset = limit * (Integer.parseInt(currenPage) - 1);
        }
        return this;
    }

    public String getQuery() {
        return getFilterQuery() + getGroupByQuery() + getSortQuery() + getLimitQuery();
    }

    public String getRecordQuery() {
        return getFilterQuery();
    }

    private String getFilterQuery() {
        if (filters.isEmpty()) {
            return "";
        }
        StringJoiner stringJoiner = new StringJoiner(" AND ", " WHERE ", " ");
        filters.forEach(stringJoiner::add);
        return stringJoiner.toString();
    }

    protected String getGroupByQuery() {
        return "";
    }

    private String getSortQuery() {
        return " ORDER BY " + sortField + " " + order;
    }

    private String getLimitQuery() {
        return " LIMIT " + offset + ", " + limit;
    }

    private String checkSortField(String sortField) {
        return allowedSortFields.contains(sortField) ? sortField : this.sortField;
    }


    private boolean isPositiveInt(String intString) {
        try {
            int i = Integer.parseInt(intString);
            if (i < 0) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }
}
