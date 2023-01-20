package ua.com.vp.confapp.dao.jdbc_impl.mysql_queries;

public final class ReportQueries {

    public static final String SQL_ADD_REPORT =
            "INSERT INTO report (topic, outline, event_event_id) VALUES (?, ?, ?)";

    public static final String SQL_FIND_REPORT_BY_ID =
            "SELECT * FROM report LEFT JOIN event ON event_event_id = event_id " +
                    "LEFT JOIN users ON users_user_id = user_id WHERE report_id = ?";

    public static final String SQL_FIND_ALL_REPORTS =
            "SELECT * FROM report LEFT JOIN event ON event_event_id = event_id "
                    + "LEFT JOIN users ON users_user_id = user_id";

    public static final String SQL_UPDATE_REPORT =
            "UPDATE report SET topic = ?, outline = ? WHERE report_id = ?";

    public static final String SQL_DELETE_REPORT =
            "DELETE FROM report WHERE report_id = ?";

    public static final String SQL_ACCEPT_SPEAKER =
            "UPDATE report SET accepted = ? WHERE report_id = ?";

    public static final String SQL_GET_FREE_REPORTS =
            "SELECT * FROM report WHERE event_event_id = ? AND users_user_id = null";

    public static final String SQL_PROPOSE_REPORT =
            "INSERT INTO report (topic, outline, event_event_id, users_user_id) VALUES (?, ?, ?, ?)";

    public static final String SQL_GET_ACCEPTED_REPORTS =
            "SELECT * FROM report WHERE event_event_id = ? AND accepted <> null";

    public static final String SQL_COUNT_REPORTS =
            "SELECT COUNT(*) FROM report";

    //change the topic for the speaker
    //propose topic for the speaker
    //register for topic (propose his person
    //


    private ReportQueries() {

    }
}
