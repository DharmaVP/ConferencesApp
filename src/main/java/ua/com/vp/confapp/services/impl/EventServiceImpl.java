package ua.com.vp.confapp.services.impl;

import ua.com.vp.confapp.dao.*;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.exception.NoSuchEntityException;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.util.ArrayList;
import java.util.List;

import static ua.com.vp.confapp.utils.MapperDTO.convertDTOToEvent;
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

    @Override
    public void create(EventDTO eventDTO) throws ServiceException {
        Event event = convertDTOToEvent(eventDTO);
        Event.EventAddress address = event.getAddress();
        try {
            transaction.begin(List.of(eventDAO, eventAddressDAO));
            Long addressId = eventAddressDAO.findByParams(address);
            if (addressId == null) {
                eventAddressDAO.create(address);
            } else {
                address.setId(addressId);
            }
            eventDAO.create(event);
            transaction.commit();
            eventDTO.setId(event.getId());
        } catch (DAOException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }


    @Override
    public EventDTO getById(Long id) throws ServiceException {
        Event event;
        try {
            transaction.beginNoTransaction(eventDAO);
            event = eventDAO.read(id).orElseThrow(NoSuchEntityException::new);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return convertToEventDTO(event);
    }

    @Override
    public List<EventDTO> getAll(QueryBuilder queryBuilder) throws ServiceException {
        List<EventDTO> eventsDTO = new ArrayList<>();
        List<Event> events;

        try {
            transaction.beginNoTransaction(eventDAO);
            events = eventDAO.getAll(queryBuilder);
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
    public int getNumberOfRecords(QueryBuilder queryBuilder) throws ServiceException {
        int numberOfRecords = 0;
        try {
            transaction.beginNoTransaction(eventDAO);
            numberOfRecords = eventDAO.getNoOfRecords(queryBuilder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return numberOfRecords;
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

    @Override
    public void cancelRegistration(Long userId, Long eventId) throws ServiceException {
        try {
            transaction.beginNoTransaction(eventDAO);
            eventDAO.deleteParticipant(userId, eventId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
    }
}
