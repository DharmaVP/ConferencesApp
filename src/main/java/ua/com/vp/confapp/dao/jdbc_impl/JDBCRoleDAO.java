package ua.com.vp.confapp.dao.jdbc_impl;

import ua.com.vp.confapp.dao.RoleDAO;
import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.utils.DAOUtil;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.RoleQueries.*;
import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.TablesColumns.*;


public class JDBCRoleDAO extends JDBCEntityDAO implements RoleDAO {


    @Override
    public boolean create(User.Role role) throws IllegalArgumentException, DAOException {
        DAOUtil.isIdNull(role);

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_ADD_ROLE, true, role.getName())) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Creating role failed, no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                role.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public Optional<User.Role> read(Long key) throws DAOException {
        User.Role role = null;

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_FIND_ROLE_BY_ID, false, key);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                role = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(role);
    }


    @Override
    public boolean update(User.Role role) throws IllegalArgumentException, DAOException  {
        DAOUtil.isIdNotNull(role);
        Object[] values = DAOUtil.getRoleParamsUpdate(role);

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_UPDATE_ROLE, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating role failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public boolean delete(User.Role role) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Unsupported Operation");
    }

    @Override
    public List<User.Role> getAll(QueryBuilder queryBuilder) throws DAOException  {
        List<User.Role> roles = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ROLES);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                roles.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return roles;
    }

    @Override
    public int getNoOfRecords(QueryBuilder queryBuilder) throws DAOException {
        return 0;
    }


    @Override
    public Optional<User.Role> find(String roleName) throws DAOException {
        User.Role role = null;

        try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_ROLE_BY_NAME, false, roleName);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                role = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(role);
    }

    private User.Role map(ResultSet resultSet) throws SQLException {
        User.Role role = new User.Role();
        role.setId(resultSet.getLong(ROLE_ID));
        role.setName(resultSet.getString(NAME));
        return role;
    }
}
