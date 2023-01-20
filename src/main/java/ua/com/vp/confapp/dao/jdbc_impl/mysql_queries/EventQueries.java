package ua.com.vp.confapp.dao.jdbc_impl.mysql_queries;

public final class EventQueries {

    public static final String SQL_ADD_EVENT =
            "INSERT INTO event (name, description, event_date, place_place_id) VALUES(?, ?, ?, ?)";

    public static final String SQL_FIND_EVENT_BY_ID =
            "SELECT e.*, ad.*, COUNT(DISTINCT p.users_user_id) AS participants, COUNT(DISTINCT r.report_id) AS reports " +
                    "FROM event AS e LEFT JOIN event_address AS ad ON e.place_place_id = ad.place_id " +
                    "LEFT JOIN participant p ON e.event_id = p.event_event_id LEFT JOIN report r ON e.event_id = r.event_event_id " +
                    "WHERE e.event_id = ? GROUP BY e.event_id";

    public static final String SQL_FIND_ALL_EVENTS =
            "SELECT e.*, ad.*, COUNT(DISTINCT p.users_user_id) AS participants, COUNT(DISTINCT r.report_id) AS reports " +
                    "FROM event AS e LEFT JOIN event_address AS ad ON e.place_place_id = ad.place_id " +
                    "LEFT JOIN participant p ON e.event_id = p.event_event_id LEFT JOIN report r ON e.event_id = r.event_event_id ";


    public static final String SQL_UPDATE_EVENT =
            "UPDATE event SET name = ?, description = ?, event_date = ?, place_place_id = ? WHERE event_id = ?";

    public static final String SQL_DELETE_EVENT =
            "DELETE FROM event WHERE id = ?";

    public static final String SQL_COUNT_EVENTS =
            "SELECT COUNT(DISTINCT event_id) AS events FROM event LEFT JOIN participant AS p ON event_id = p.event_event_id";

    public static final String SQL_SET_VISITORS =
            "UPDATE event SET visitors = ? WHERE event_id = ?";

    public static final String SQL_REGISTER_FOR_EVENT =
            "INSERT INTO participant (users_user_id, event_event_id) VALUES(?, ?)";

    public static final String SQL_FIND_REGISTRATION =
            "SELECT * FROM participant WHERE users_user_id = ? and event_event_id = ?";

    public static final String SQL_CANCEL_REGISTRATION =
            "DELETE FROM participant WHERE users_user_id = ? AND event_event_id = ?";

    public static final String SQL_SET_SPEAKER =
            "INSERT INTO participant (users_user_id, event_event_id, speaker) VALUES(?, ?, ?)";


    private EventQueries() {}
}
