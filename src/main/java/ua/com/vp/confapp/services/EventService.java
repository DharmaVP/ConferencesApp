package ua.com.vp.confapp.services;

import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.exception.ServiceException;

import java.util.List;

public interface EventService extends Service<EventDTO> {

    void registerForEvent(Long userId, Long eventId) throws ServiceException;

//EventDTO createEvent(EventDTO eventDTO);

//    Event findById(Long eventId);
//
//    List<Event> findAll();
//
////    List<Conference> findAll(int offset, int noOfRecords);
////
////    List<Conference> findAll(int offset, int noOfRecords, String sort, String sortDir);
//
//    List<Event> findByTitle(String text);
//
//    List<Event> getPresentationsFromConference(Long eventId);
//
//    void insertUser(User user, Long eventId);
//
//    boolean updatePresence(Long eventId, Integer visitors);
//
//    int getUserCount(Long eventId);
//
//    void delete(Long eventId);
//
//  //  void updateConference(int id, String title, String description, LocalDate date, Status status, String place);
//
//    int getNoOfRecords();
}
