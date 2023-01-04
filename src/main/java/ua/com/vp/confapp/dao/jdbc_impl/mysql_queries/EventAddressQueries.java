package ua.com.vp.confapp.dao.jdbc_impl.mysql_queries;

public final class EventAddressQueries {

    public static final String SQL_ADD_ADDRESS =
            "INSERT INTO event_address (building, floor, street_number, street_name, city, postal_code, country) " +
                    "VALUES(?, ?, ?, ?, ?, ?, ?)";

    public static final String SQL_FIND_ADDRESS_BY_ID =
            "SELECT * FROM event_address WHERE place_id = ?";

    public static final String SQL_FIND_ALL_ADDRESS =
            "SELECT * FROM event_address WHERE";

    public static final String SQL_FIND_ADDRESS =
            "SELECT place_id FROM event_address WHERE building = ? AND floor = ? AND street_number = ? AND street_name = ? " +
                    "AND city = ? AND postal_code = ? AND country = ?";

    public static final String SQL_UPDATE_ADDRESS =
            "UPDATE event_address SET building = ?, floor = ?, street_number = ?, street_name = ?, " +
                    "city = ?, postal_code = ?, country = ? WHERE place_id = ?";


    private EventAddressQueries() {

    }
}
