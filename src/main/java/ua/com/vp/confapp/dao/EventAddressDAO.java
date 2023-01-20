package ua.com.vp.confapp.dao;

import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.exception.DAOException;

public interface EventAddressDAO extends GenericDAO<Event.EventAddress, Long>, DAO {

    boolean isAddressExist(Event.EventAddress address) throws DAOException;

    Long findByParams(Event.EventAddress address) throws DAOException;
}
