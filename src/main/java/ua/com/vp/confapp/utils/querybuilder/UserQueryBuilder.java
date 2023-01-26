package ua.com.vp.confapp.utils.querybuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.TablesColumns.*;

public class UserQueryBuilder implements QueryBuilder{
    private final List<String> filters;
    private final Set<String> allowedSortFields;

    private String sortField;
    private String order;

    private int limit;
    private int offset;


    {
        sortField = LAST_NAME;
        filters = new ArrayList<>();
        allowedSortFields = Set.of(USER_ID, EMAIL, FIRST_NAME, LAST_NAME, JOB_TITLE, ORGANISATION, NAME);
        order = "ASC";
        offset = 0;
        limit = 5;
    }


    public UserQueryBuilder setSearchedUser(String searchFilter) {
        if (searchFilter != null && !searchFilter.isEmpty()) {
            filters.add("last_name LIKE '%" + searchFilter + "%'");
        }
        return this;
    }


    public UserQueryBuilder setUserIdFilter(long userIdFilter) {
        filters.add("user_id=" + userIdFilter);
        return this;
    }

    public UserQueryBuilder setRoleFilter(String roleFilter) {
        if (roleFilter != null) {
            filters.add("name='" + roleFilter+"'");
        }
        return this;
    }


    public UserQueryBuilder setSortField(String sortField) {
        if (sortField != null) {
            this.sortField = checkSortField(sortField);
        }
        return this;
    }

    public UserQueryBuilder setOrder(String order) {
        if (order != null && order.equalsIgnoreCase("DESC")) {
            this.order = "DESC";
        }
        return this;
    }

    public UserQueryBuilder setLimits(String currenPage, String records) {
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
