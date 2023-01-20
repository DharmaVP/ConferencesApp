package ua.com.vp.confapp.dao.jdbc_impl.mysql_queries;

public final class UserQueries {

    public static final String SQL_ADD_USER =
            "INSERT INTO users (email, password) VALUES (?, ?)";

    public static final String SQL_FIND_USER_BY_ID =
            "SELECT * FROM users INNER JOIN role ON role_role_id = role_id " +
                    "WHERE user_id = ?";

    public static final String SQL_FIND_USER_BY_EMAIL =
            "SELECT * FROM users LEFT JOIN role ON role_role_id = role_id WHERE email = ?";

    public static final String SQL_FIND_ALL_USERS =
            "SELECT * FROM users INNER JOIN role ON role_role_id = role_id";

    public static final String SQL_UPDATE_USER_INFO =
            "UPDATE users SET prefix = ?, first_name = ?, last_name = ?, cell_phone = ?," +
            "job_title = ?, organisation = ? WHERE user_id = ?";

    public static final String SQL_DELETE_USER =
            "DELETE FROM users WHERE user_id = ?";

    public static final String SQL_CHANGE_USER_PASSWORD =
            "UPDATE users SET password = ? WHERE user_id = ?";

    public static final String SQL_UPDATE_USER_EMAIL =
            "UPDATE users SET email = ? WHERE user_id = ?";

    public static final String SQL_SET_USER_ROLE =
            "UPDATE users SET role_role_id = ? WHERE user_id = ?";

    public static final String SQL_EXIST_EMAIL =
            "SELECT user_id FROM users WHERE email = ?";

    public static final String SQL_FIND_USER_ON_EVENT =
            "SELECT * FROM participant WHERE users_user_id = ? AND event_event_id = ?";

    public static final String SQL_COUNT_USERS =
            "SELECT COUNT(*) FROM users INNER JOIN role ON role_role_id = role_id";

    private UserQueries() {
    }
}
