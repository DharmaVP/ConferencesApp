package ua.com.vp.confapp.utils.querybuilder;


import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.TablesColumns.*;
import static ua.com.vp.confapp.utils.querybuilder.QueryConstants.NOW;

public class EventQueryBuilder implements QueryBuilder {
    private final List<String> filters;
    private final Set<String> allowedSortFields;

    private String sortField;
    private String order;

    private int limit;
    private int offset;


    {
        sortField = EVENT_DATE;
        filters = new ArrayList<>();
        allowedSortFields = Set.of(EVENT_ID, EVENT_NAME, EVENT_DATE, PARTICIPANTS, REPORTS);
        order = "ASC";
        offset = 0;
        limit = 5;
    }


    public EventQueryBuilder setSearchedEvent(String searchFilter) {
        if (searchFilter != null && !searchFilter.isEmpty()) {
            filters.add("name LIKE '%" + searchFilter + "%'");
        }
        return this;
    }

    public EventQueryBuilder setDateTypeFilter(String dateFilter) {
        if (dateFilter != null && dateFilter.equals("passed")) {
            filters.add(EVENT_DATE + " < " + NOW);
        } else if (dateFilter != null && dateFilter.equals("upcoming")) {
            filters.add(EVENT_DATE + " > " + NOW);
        }
        return this;
    }

    public EventQueryBuilder setDateFilter(String dateFrom, String dateTo) {
        if ((dateFrom != null && !dateFrom.isEmpty())) {
            filters.add(EVENT_DATE + " >= '" + dateFrom + "'");
        }
        if ((dateTo != null && !dateTo.isEmpty())) {
            filters.add(EVENT_DATE + " <= '" + dateTo + "'");
        }
        return this;
    }


    public EventQueryBuilder setUserIdFilter(long userIdFilter) {
        filters.add("p.users_user_id=" + userIdFilter);
        return this;
    }

    public EventQueryBuilder setSpeakerFilter(long speakerIdFilter) {
        if (speakerIdFilter > 0) {
            filters.add("r.users_user_id=" + speakerIdFilter);
        }
        return this;
    }

    public EventQueryBuilder setAcceptedByModerator(boolean speakerFilter) {
        if (speakerFilter) {
            filters.add("r.accepted_by_moderator=true");
        }
        return this;
    }

    public EventQueryBuilder setAcceptedBySpeaker(boolean speakerFilter) {
        if (speakerFilter) {
            filters.add("r.accepted_by_speaker=true");
        }
        return this;
    }


    public EventQueryBuilder setSortField(String sortField) {
        if (sortField != null) {
            this.sortField = checkSortField(sortField);
        }
        return this;
    }

    public EventQueryBuilder setOrder(String order) {
        if (order != null && order.equalsIgnoreCase("DESC")) {
            this.order = "DESC";
        }
        return this;
    }

    public EventQueryBuilder setLimits(String currenPage, String records) {
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
        return "GROUP BY event_id";
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
