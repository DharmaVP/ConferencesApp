package ua.com.vp.confapp.utils;

import ua.com.vp.confapp.entities.Entity;
import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.entities.User;

import java.sql.*;

/**
 * Utility class for DAO's. This class contains commonly used DAO logic which is been refactored in
 * single static methods. As far it contains a PreparedStatement values setter and a
 * <code>java.util.Date</code> to <code>java.sql.Date</code> converter.
 *
 * @author BalusC
 * @link http://balusc.blogspot.com/2008/07/dao-tutorial-data-layer.html
 */
public final class DAOUtil {

    // Constructors -------------------------------------------------------------------------------

    private DAOUtil() {
        // Utility class, hide constructor.
    }

    // Actions ------------------------------------------------------------------------------------

    /**
     * Returns a PreparedStatement of the given connection, set with the given SQL query and the
     * given parameter values.
     *
     * @param connection          The Connection to create the PreparedStatement from.
     * @param sql                 The SQL query to construct the PreparedStatement with.
     * @param returnGeneratedKeys Set whether to return generated keys or not.
     * @param values              The parameter values to be set in the created PreparedStatement.
     * @throws SQLException If something fails during creating the PreparedStatement.
     */
    public static PreparedStatement prepareStatement
    (Connection connection, String sql, boolean returnGeneratedKeys, Object... values)
            throws SQLException {
        PreparedStatement statement = connection.prepareStatement(sql,
                returnGeneratedKeys ? Statement.RETURN_GENERATED_KEYS : Statement.NO_GENERATED_KEYS);
        setValues(statement, values);
        return statement;
    }

    /**
     * Set the given parameter values in the given PreparedStatement.
     *
     * @param values     The parameter values to be set in the created PreparedStatement.
     * @throws SQLException If something fails during setting the PreparedStatement values.
     */
    public static void setValues(PreparedStatement statement, Object... values)
            throws SQLException {
        for (int i = 0; i < values.length; i++) {
            statement.setObject(i + 1, values[i]);
        }
    }

    public static void isIdNull(Entity entity) throws IllegalArgumentException {
        if (entity.getId() != null) {

            String clazzName = entity.getClass().getName();
            String errorMessage = clazzName + " has been already created, the " + clazzName + "ID is not null.";
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static void isIdNotNull(Entity entity) throws IllegalArgumentException {
        if (entity.getId() == null) {

            String clazzName = entity.getClass().getName();
            String errorMessage = clazzName + " hasn't been created yet, the " + clazzName + " ID is null.";
            throw new IllegalArgumentException(errorMessage);
        }
    }

    public static Object[] getUserRegistrationParams(User user) {
        return new Object[]{
                user.getEmail(),
                user.getPassword()
        };
    }

    public static Object[] getUserParamsUpdate(User user) {
        return new Object[]{
                (user.getPrefix() == null) ? null : user.getPrefix().getName(),
                user.getFirstName(),
                user.getLastName(),
                user.getPhoneNumber(),
                user.getJobTitle(),
                user.getOrganisation(),
                user.getId()
        };
    }

    public static Object[] getUserNewPasswordParams(User user) {
        return new Object[]{
                user.getPassword(),
                user.getId()
        };
    }

    public static Object[] getUserNewEmailParams(User user) {
        return new Object[]{
                user.getEmail(),
                user.getId()
        };
    }

    public static Object[] getUserRoleParams(User user) {
        return new Object[]{
                user.getRole().getId(),
                user.getId()
        };
    }

    public static Object[] getRoleParamsUpdate(User.Role role) {
        return new Object[]{
                role.getName(),
                role.getId()
        };
    }

    public static Object[] getEventAddressParams(Event.EventAddress address) {
        return new Object[]{
                address.getBuildingName(),
                address.getFloor(),
                address.getStreetNumber(),
                address.getStreetName(),
                address.getCity(),
                address.getPostalCode(),
                address.getCountry()
        };
    }

    public static Object[] getEventAddressParamsUpdate(Event.EventAddress address) {
        return new Object[]{
                address.getBuildingName(),
                address.getFloor(),
                address.getStreetNumber(),
                address.getStreetName(),
                address.getCity(),
                address.getPostalCode(),
                address.getCountry(),
                address.getId()
        };
    }
}
