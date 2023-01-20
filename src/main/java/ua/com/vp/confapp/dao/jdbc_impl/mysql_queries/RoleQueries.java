package ua.com.vp.confapp.dao.jdbc_impl.mysql_queries;

public final class RoleQueries {

    public static final String SQL_ADD_ROLE =
            "INSERT INTO role (name) VALUES (?)";

    public static final String SQL_FIND_ROLE_BY_ID =
            "SELECT role_id, name FROM users JOIN role ON role_role_id = role_id WHERE user_id = ?";

    public static final String SQL_FIND_ALL_ROLES =
            "SELECT * FROM role";

    public static final String SQL_UPDATE_ROLE=
            "UPDATE role SET name = ? WHERE role_id = ?";

    public static final String SQL_FIND_ROLE_BY_NAME=
            "SELECT * FROM role WHERE name = ?";


    private RoleQueries() {
    }
}
