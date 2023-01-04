package ua.com.vp.confapp.dao;

import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.exception.DAOException;

public interface EventDAO extends GenericDAO<Event, Long> {
    boolean createParticipant(Long userId, Long eventId) throws DAOException;

    boolean deleteParticipant(Long userId, Long eventId) throws DAOException;

    boolean updateParticipant(Long userId, Long eventId, Boolean isSpeaker) throws DAOException;

}
