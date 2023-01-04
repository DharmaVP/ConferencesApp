package ua.com.vp.confapp.dao.jdbc_impl.mysql_queries;

public final class ReportQueries {

    public static final String SQL_ADD_REPORT =
            "INSERT INTO report (topic, outline, event_id) VALUES (?, ?, ?)";

    public static final String SQL_FIND_REPORT_BY_ID =
            "SELECT * FROM report AS r LEFT JOIN event AS e ON r.event_id = e.event_id " +
                    "LEFT JOIN users AS u ON r.user_id = u.user_id WHERE report_id = ?";

    public static final String SQL_FIND_ALL_REPORTS =
            "SELECT * FROM report";

    public static final String SQL_UPDATE_REPORT =
            "UPDATE report SET topic = ?, outline = ? WHERE report_id = ?";

    public static final String SQL_DELETE_REPORT =
            "DELETE FROM report WHERE report_id = ?";

    public static final String SQL_ACCEPT_SPEAKER =
            "UPDATE report SET accepted = ? WHERE report_id = ?";

    public static final String SQL_GET_FREE_REPORTS =
            "SELECT * WHERE event_id = ? AND user_id = null";

    public static final String SQL_PROPOSE_REPORT =
            "INSERT INTO report (topic, outline, event_id, user_id) VALUES (?, ?, ?, ?)";

    public static final String SQL_GET_ACCEPTED_REPORTS =
            "SELECT * WHERE event_id = ? AND accepted <> null";

    //change the topic for the speaker
    //propose topic for the speaker
    //register for topic (propose his person
    //


    private ReportQueries() {

    }
}
