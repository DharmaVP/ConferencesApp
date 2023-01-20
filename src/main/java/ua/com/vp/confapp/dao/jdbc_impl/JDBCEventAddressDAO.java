package ua.com.vp.confapp.dao.jdbc_impl;

import ua.com.vp.confapp.dao.EventAddressDAO;
import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.utils.DAOUtil;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.EventAddressQueries.*;
import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.TablesColumns.*;


public class JDBCEventAddressDAO extends JDBCEntityDAO implements EventAddressDAO {


    @Override
    public boolean create(Event.EventAddress address) throws IllegalArgumentException, DAOException {
        DAOUtil.isIdNull(address);
        Object[] values = DAOUtil.getEventAddressParams(address);

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_ADD_ADDRESS, true, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Creating address failed, no rows affected.");
            }
            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) {
                address.setId(generatedKeys.getLong(1));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public Optional<Event.EventAddress> read(Long key) throws DAOException {
        Event.EventAddress address = null;

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_FIND_ADDRESS_BY_ID, false, key);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                address = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(address);
    }

    @Override
    public Long findByParams(Event.EventAddress address) throws DAOException {
        Long addressId = null;
        Object[] values = DAOUtil.getEventAddressParams(address);

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_FIND_ADDRESS, false, values);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                addressId = resultSet.getLong(PLACE_ID);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return addressId;
    }


    @Override
    public boolean update(Event.EventAddress address) throws IllegalArgumentException, DAOException {
        DAOUtil.isIdNotNull(address);
        Object[] values = DAOUtil.getEventAddressParamsUpdate(address);

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_UPDATE_ADDRESS, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating address failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public boolean delete(Event.EventAddress address) throws UnsupportedOperationException {
        throw new UnsupportedOperationException("Not allowed");
    }

    @Override
    public List<Event.EventAddress> getAll(QueryBuilder queryBuilder) throws DAOException {
        List<Event.EventAddress> addresses = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_ADDRESS);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                addresses.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return addresses;
    }

    @Override
    public int getNoOfRecords(QueryBuilder queryBuilder) throws DAOException {
        return 0;
    }

    @Override
    public boolean isAddressExist(Event.EventAddress address) throws DAOException {
        boolean isExist = false;
        Object[] values = DAOUtil.getEventAddressParams(address);

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_FIND_ADDRESS, false, values);
             ResultSet resultSet = statement.executeQuery()) {
            isExist = resultSet.next();
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return isExist;
    }

    private Event.EventAddress map(ResultSet resultSet) throws SQLException {
        Event.EventAddress address = new Event.EventAddress();
        address.setId(resultSet.getLong(PLACE_ID));
        address.setBuildingName(resultSet.getString(BUILDING));
        address.setFloor(resultSet.getInt(FLOOR));
        address.setStreetNumber(resultSet.getString(STREET_NUMBER));
        address.setStreetName(resultSet.getString(STREET_NAME));
        address.setCity(resultSet.getString(CITY));
        address.setPostalCode(resultSet.getInt(POSTAL));
        address.setCountry(resultSet.getString(COUNTRY));
        return address;
    }
}
