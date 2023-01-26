package ua.com.vp.confapp.services;

import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.exception.ServiceException;

import java.util.List;

public interface EventService extends Service<EventDTO> {

    void create(EventDTO eventDTO) throws ServiceException;

    void registerForEvent(Long userId, Long eventId) throws ServiceException;

    void cancelRegistration(Long userId, Long eventId) throws ServiceException;

    void setVisitors(Long eventId, Integer noOfVisitors) throws ServiceException;
}
