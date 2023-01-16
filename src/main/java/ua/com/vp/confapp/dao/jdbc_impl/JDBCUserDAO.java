package ua.com.vp.confapp.dao.jdbc_impl;


import ua.com.vp.confapp.dao.UserDAO;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.utils.DAOUtil;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.sql.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.TablesColumns.*;
import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.UserQueries.*;


public class JDBCUserDAO extends JDBCEntityDAO implements UserDAO {


    @Override
    public boolean create(User user) throws IllegalArgumentException, DAOException {
        DAOUtil.isIdNull(user);
        Object[] values = DAOUtil.getUserRegistrationParams(user);

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_ADD_USER, true, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Creating user failed, no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                user.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {

            throw new DAOException(e);
        }
        return true;
    }


    @Override
    public Optional<User> read(Long key) throws DAOException {
        return find(SQL_FIND_USER_BY_ID, key);
    }

    @Override
    public boolean update(User user) throws IllegalArgumentException, DAOException {
        DAOUtil.isIdNotNull(user);
        Object[] values = DAOUtil.getUserParamsUpdate(user);

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_UPDATE_USER_INFO, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating user failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public boolean delete(User user) throws DAOException {
        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_DELETE_USER, false, user.getId())) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting user failed, no rows affected.");
            } else {
                user.setId(null);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }


    @Override
    public List<User> getAll(QueryBuilder queryBuilder) throws DAOException {
        List<User> users = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_USERS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                users.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return users;
    }

    @Override
    public int getNoOfRecords(QueryBuilder queryBuilder) throws DAOException {
        return 0;
    }


    @Override
    public Optional<User> find(String email) throws DAOException {
        return find(SQL_FIND_USER_BY_EMAIL, email);
    }


    @Override
    public boolean isExistEmail(String email) throws DAOException {
        boolean isExist = false;

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_EXIST_EMAIL, false, email);
             ResultSet resultSet = statement.executeQuery()) {
            isExist = resultSet.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return isExist;
    }

    @Override
    public boolean changePassword(User user) throws IllegalArgumentException, DAOException {
        DAOUtil.isIdNull(user);
        Object[] values = DAOUtil.getUserNewPasswordParams(user);

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_CHANGE_USER_PASSWORD, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Changing password failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public boolean updateEmail(User user) throws IllegalArgumentException, DAOException {
        DAOUtil.isIdNull(user);
        Object[] values = DAOUtil.getUserNewEmailParams(user);

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_UPDATE_USER_EMAIL, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Changing email failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public boolean changeRole(User user) throws IllegalArgumentException, DAOException {
        DAOUtil.isIdNull(user);
        Object[] values = DAOUtil.getUserRoleParams(user);

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_SET_USER_ROLE, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Changing role failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    private Optional<User> find(String sql, Object... values) throws DAOException {
        User user = null;

        try (PreparedStatement statement = DAOUtil.prepareStatement(connection, sql, false, values);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                user = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(user);
    }


    private User map(ResultSet resultSet) throws SQLException {
        User user = new User();
        User.Role role = new User.Role();
        user.setId(resultSet.getLong(USER_ID));
        user.setEmail(resultSet.getString(EMAIL));
        user.setPassword(resultSet.getString(PASSWORD));
        user.setPrefix(getUserPrefix(resultSet));
        user.setFirstName(resultSet.getString(FIRST_NAME));
        user.setLastName(resultSet.getString(LAST_NAME));
        user.setPhoneNumber(resultSet.getString(PHONE));
        user.setJobTitle(resultSet.getString(JOB_TITLE));
        user.setOrganisation(resultSet.getString(ORGANISATION));
        user.setRegistrationDate(resultSet.getTimestamp(REGISTRATION_DATE).toInstant());
        user.setProfileImage(resultSet.getString(PROFILE_PICTURE));
        role.setId(resultSet.getLong(ROLE_ID));
        role.setName(resultSet.getString(NAME));
        user.setRole(role);
        return user;
    }

    private User.Prefix getUserPrefix(ResultSet resultSet) throws SQLException {
        String userPrefix = resultSet.getString(PREFIX);
        return (userPrefix == null) ? null
                : Arrays.stream(User.Prefix.values())
                .filter(prefix -> prefix.getName().equals(userPrefix))
                .findFirst().orElseThrow();
    }
}