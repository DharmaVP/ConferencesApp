package ua.com.vp.confapp.dao.jdbc_impl.mysql_queries;

public final class EventQueries {

    public static final String SQL_ADD_EVENT =
            "INSERT INTO event (name, description, event_date, place_id) VALUES(?, ?, ?, ?)";

    public static final String SQL_FIND_EVENT_BY_ID =
            "SELECT * FROM event AS e LEFT JOIN event_address AS ad ON e.place_id = ad.place_id WHERE e.place_id = ?";

    public static final String SQL_FIND_ALL_EVENTS =
            "SELECT * FROM event AS e LEFT JOIN event_address AS ad ON e.place_id = ad.place_id ";

    public static final String SQL_UPDATE_EVENT =
            "UPDATE event SET name = ?, description = ?, event_date = ?, place_id = ? WHERE event_id = ?";

    public static final String SQL_DELETE_EVENT =
            "DELETE FROM event WHERE id = ?";

    public static final String SQL_COUNT_EVENTS =
            "SELECT COUNT(*) FROM event";

    public static final String SQL_SET_VISITORS =
            "UPDATE event SET visitors = ? WHERE event_id = ?";

    public static final String SQL_REGISTER_FOR_EVENT =
            "INSERT INTO participant (user_id, event_id) VALUES(?, ?)";

    public static final String SQL_FIND_REGISTRATION =
            "SELECT * FROM participant WHERE user_id = ? and event_id = ?";

    public static final String SQL_CANCEL_REGISTRATION =
            "DELETE FROM participant WHERE user_id = ? AND event_id = ?";

    public static final String SQL_SET_SPEAKER =
            "INSERT INTO participant (user_id, event_id, speaker) VALUES(?, ?, ?)";


    private EventQueries() {}
}
