package ua.com.vp.confapp.services.impl;

import ua.com.vp.confapp.dao.*;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.EventService;

import java.util.ArrayList;
import java.util.List;

import static ua.com.vp.confapp.utils.MapperDTO.convertToEventDTO;

public class EventServiceImpl implements EventService {
    private final Transaction<DAO> transaction;
    private final EventDAO eventDAO;
    private final EventAddressDAO eventAddressDAO;

    public EventServiceImpl(Transaction<DAO> transaction, EventDAO eventDAO, EventAddressDAO eventAddressDAO) {
        this.transaction = transaction;
        this.eventDAO = eventDAO;
        this.eventAddressDAO = eventAddressDAO;
    }


    //    public EventDTO createEvent(eventDTO) throws ServiceException {
//        validateEvent(eventDTO);
//        Event event = convertDTOToEvent(eventDTO);
//        try {
//            eventDAO.add(event);
//        } catch (DAOException e) {
//            checkExceptionType(e);
//        }
//        return convertEventToDTO(event);
//    }


    @Override
    public EventDTO getById(String id) throws ServiceException {
        return null;
    }

    @Override
    public List<EventDTO> getAll() throws ServiceException {
        List<EventDTO> eventsDTO = new ArrayList<>();
        List<Event> events;

        try {
            transaction.beginNoTransaction(eventDAO);
            events = eventDAO.getAll();
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        for (Event event : events) {
            eventsDTO.add(convertToEventDTO(event));
        }
        return eventsDTO;
    }

    @Override
    public boolean update(EventDTO entity) throws ServiceException {
        return false;
    }

    @Override
    public boolean delete(EventDTO entity) throws ServiceException {
        return false;
    }


    @Override
    public void registerForEvent(Long userId, Long eventId) throws ServiceException {
        try {
            transaction.beginNoTransaction(eventDAO);
            eventDAO.createParticipant(userId, eventId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
    }
}
