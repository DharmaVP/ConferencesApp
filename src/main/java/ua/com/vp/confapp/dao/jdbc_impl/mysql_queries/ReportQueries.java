package ua.com.vp.confapp.dao.jdbc_impl.mysql_queries;

public final class ReportQueries {

    public static final String SQL_ADD_REPORT =
            "INSERT INTO report (topic, outline, event_event_id, users_user_id, accepted_by_speaker, accepted_by_moderator) " +
                    "VALUES (?, ?, ?, ?, ?, ?)";

    public static final String SQL_FIND_REPORT_BY_ID =
            "SELECT * FROM report LEFT JOIN event ON event_event_id = event_id " +
                    "LEFT JOIN users ON users_user_id = user_id WHERE report_id = ?";

    public static final String SQL_FIND_ALL_REPORTS =
            "SELECT * FROM report LEFT JOIN event ON event_event_id = event_id "
                    + "LEFT JOIN users ON users_user_id = user_id";

    public static final String SQL_UPDATE_REPORT =
            "UPDATE report SET topic = ?, outline = ?, users_user_id = ?, " +
                    "accepted_by_moderator = ?, accepted_by_speaker = ? WHERE report_id = ?";

    public static final String SQL_DELETE_REPORT =
            "DELETE FROM report WHERE report_id = ?";

    public static final String SQL_COUNT_REPORTS =
            "SELECT COUNT(*) FROM report";


    private ReportQueries() {

    }
}
