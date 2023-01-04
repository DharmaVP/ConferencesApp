package ua.com.vp.confapp.services;

import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;

public interface UserService extends Service<UserDTO> {


    UserDTO create(String email, String password) throws ServiceException;

    UserDTO findByEmailAndPassword(String email, String password) throws ServiceException;

    void changeRole(Long userId);

//    void delete(Long userId);
//
//    UserDTO findById(Long userId);
//
//    List<User> findSpeakers();
//
//    void changeRoleToSpeaker(Long userId);
//
//    void changeRoleToUser(Long userId);
//
//    User getUserByEmailPassword(String email, String password);
}
