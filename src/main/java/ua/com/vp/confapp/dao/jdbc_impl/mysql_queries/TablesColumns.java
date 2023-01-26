package ua.com.vp.confapp.dao.jdbc_impl.mysql_queries;

public final class TablesColumns {
    // tables: users + role
    public static final String USER_ID = "user_id";
    public static final String EMAIL = "email";
    public static final String PASSWORD = "password";
    public static final String PREFIX = "prefix";
    public static final String FIRST_NAME = "first_name";
    public static final String LAST_NAME = "last_name";
    public static final String PHONE = "cell_phone";
    public static final String JOB_TITLE = "job_title";
    public static final String ORGANISATION = "organisation";
    public static final String REGISTRATION_DATE = "registration_date";
    public static final String PROFILE_PICTURE = "profile_picture";
    public static final String ROLE_ROLE_ID = "role_role_id";
    public static final String ROLE_ID = "role_id";
    public static final String NAME = "name";
    // table: event
    public static final String EVENT_ID = "event_id";
    public static final String EVENT_NAME = "name";
    public static final String DESCRIPTION = "description";
    public static final String EVENT_DATE = "event_date";
    public static final String VISITORS = "visitors";
    public static final String PLACE_PLACE_ID = "place_id";
    // table: event-address
    public static final String PLACE_ID = "place_id";
    public static final String BUILDING = "building";
    public static final String FLOOR = "floor";
    public static final String STREET_NUMBER = "street_number";
    public static final String STREET_NAME = "street_name";
    public static final String CITY = "city";
    public static final String POSTAL = "postal_code";
    public static final String COUNTRY = "country";
    // table: report
    public static final String REPORT_ID = "report_id";
    public static final String TOPIC = "topic";
    public static final String OUTLINE = "outline";
    public static final String ACCEPTED_BY_MODERATOR = "accepted_by_moderator";
    public static final String ACCEPTED_BY_SPEAKER = "accepted_by_speaker";
    public static final String USERS_USER_ID = "users_user_id";
    public static final String EVENT_EVENT_ID = "event_event_id";

    // functions
    public static final String COUNT = "COUNT(*)";
    public static final String EVENTS = "events";
    public static final String PARTICIPANTS = "participants";
    public static final String REPORTS = "reports";
}
