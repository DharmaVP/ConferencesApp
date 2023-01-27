package ua.com.vp.confapp.services;

import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.util.List;

public interface UserService extends Service<UserDTO> {


    UserDTO create(String email, String password) throws ServiceException;

    UserDTO findByEmailAndPassword(String email, String password) throws ServiceException;

    void changeRole(Long userId, String newRole) throws ServiceException;

    boolean isRegistered(UserDTO userDTO, Long eventId) throws ServiceException;

    List<User.Role> getAllRoles(QueryBuilder queryBuilder) throws ServiceException;

}
