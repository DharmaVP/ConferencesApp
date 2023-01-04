package ua.com.vp.confapp.dao.jdbc_impl;


import ua.com.vp.confapp.dao.EventDAO;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.utils.DAOUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.EventQueries.*;
import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.TablesColumns.*;


public class JDBCEventDAO extends JDBCEntityDAO implements EventDAO {


    @Override
    public boolean create(Event event) throws IllegalArgumentException, DAOException {
        if (event.getId() != null) {
            throw new IllegalArgumentException("Event is already created, the event ID is not null.");
        }
        Object[] addressParams = getAddressParams(event);

        return true;
    }


    @Override
    public Optional<Event> read(Long key) throws DAOException {
        Event event = null;
        try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_FIND_EVENT_BY_ID, false, key);
             ResultSet resultSet = statement.executeQuery()) {
            if (resultSet.next()) {
                event = map(resultSet);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return Optional.ofNullable(event);
    }

    @Override
    public boolean update(Event event) throws IllegalArgumentException, DAOException {
        if (event.getId() == null) {
            throw new IllegalArgumentException("Event is not created yet, the event ID is null.");
        }
        Object[] params = getEventParamsUpdate(event);

        try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_UPDATE_EVENT, false, params);) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating event failed, no rows affected.");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }


    @Override
    public boolean delete(Event event) throws DAOException {
        try (PreparedStatement statement = DAOUtil.prepareStatement(connection, SQL_DELETE_EVENT, false, event.getId());) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting event failed, no rows affected.");
            } else {
                event.setId(null);
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public List<Event> getAll() throws DAOException {
        List<Event> events = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(SQL_FIND_ALL_EVENTS);
             ResultSet resultSet = statement.executeQuery();) {
            while (resultSet.next()) {
                events.add(map(resultSet));
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return events;
    }

    @Override
    public boolean createParticipant(Long userId, Long eventId) throws DAOException {
        Object[] values = {userId, eventId};


        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_REGISTER_FOR_EVENT, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Adding participant has been failed");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public boolean deleteParticipant(Long userId, Long eventId) throws DAOException {
        Object[] values = {userId, eventId};

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_CANCEL_REGISTRATION, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Deleting participant has been failed");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    @Override
    public boolean updateParticipant(Long userId, Long eventId, Boolean isSpeaker) throws DAOException {
        Object[] values = {userId, eventId, isSpeaker};

        try (PreparedStatement statement =
                     DAOUtil.prepareStatement(connection, SQL_SET_SPEAKER, false, values)) {
            int affectedRows = statement.executeUpdate();
            if (affectedRows == 0) {
                throw new DAOException("Updating participant has been failed");
            }
        } catch (SQLException e) {
            throw new DAOException(e);
        }
        return true;
    }

    private Event map(ResultSet resultSet) throws SQLException {
        Event event = new Event();
        Event.EventAddress address = new Event.EventAddress();
        event.setId(resultSet.getLong(EVENT_ID));
        event.setName(resultSet.getString(NAME));
        event.setDescription(resultSet.getString(DESCRIPTION));
        event.setDateTime(resultSet.getTimestamp(EVENT_DATE).toLocalDateTime());
        event.setVisitors(resultSet.getInt(VISITORS));
        address.setId(resultSet.getLong(PLACE_ID));
        address.setBuildingName(resultSet.getString(BUILDING));
        address.setFloor(resultSet.getShort(FLOOR));
        address.setStreetNumber(resultSet.getString(STREET_NUMBER));
        address.setStreetName(resultSet.getString(STREET_NAME));
        address.setCity(resultSet.getString(CITY));
        address.setPostalCode(resultSet.getInt(POSTAL));
        address.setCountry(resultSet.getString(COUNTRY));
        event.setAddress(address);
        return event;
    }

    private Object[] getEventParamsUpdate(Event event) {
        return new Object[]{
                event.getName(),
                event.getDescription(),
                event.getDateTime(),
                event.getId()
        };
    }

    private Object[] getEventParams(Event event, Integer key) {
        return new Object[]{
                event.getName(),
                event.getDescription(),
                event.getDateTime(),
                key
        };
    }

    private Object[] getAddressParams(Event event) {
        Event.EventAddress address = event.getAddress();
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



//    private Integer getEventAddressKey(Connection connection, Object[] addressParams) {
//        Integer key = null;
//        try (PreparedStatement statement = DAOUtil.prepareStatement(connection, FIND_DUPLICATE_ADDRESS, false, addressParams);
//             ResultSet resultSet = statement.executeQuery()) {
//            if (resultSet.next()) {
//                key = resultSet.getInt(PLACE_ID);
//            }
//        } catch (SQLException e) {
//            throw new DAOException(e);
//        }
//        return key;
//    }
}
