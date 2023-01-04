package ua.com.vp.confapp.dao.jdbc_impl.mysql_queries;

public final class RoleQueries {

    public static final String SQL_ADD_ROLE =
            "INSERT INTO role (name) VALUES (?)";

    public static final String SQL_FIND_ROLE_BY_ID =
            "SELECT r.role_id, r.name FROM users u JOIN role r ON u.role_id = r.role_id WHERE u.user_id = ?";

    public static final String SQL_FIND_ALL_ROLES =
            "SELECT * FROM role";

    public static final String SQL_UPDATE_ROLE=
            "UPDATE role SET name = ? WHERE role_id = ?";


    private RoleQueries() {
    }
}
