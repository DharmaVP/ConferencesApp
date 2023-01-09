package ua.com.vp.confapp.services.impl;

import ua.com.vp.confapp.dao.*;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.exception.NoSuchEntityException;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.exception.ValidationException;
import ua.com.vp.confapp.services.UserService;
import ua.com.vp.confapp.utils.EmailNotifier;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ua.com.vp.confapp.utils.MapperDTO.convertToUser;
import static ua.com.vp.confapp.utils.MapperDTO.convertToUserDTO;
import static ua.com.vp.confapp.utils.PBKDF2Hasher.checkPassword;
import static ua.com.vp.confapp.utils.PBKDF2Hasher.hashPassword;

public class UserServiceImpl implements UserService {
    private final Transaction<DAO> transaction;
    private final UserDAO userDAO;
    private final RoleDAO roleDAO;


    public UserServiceImpl(Transaction<DAO> transaction, UserDAO userDAO, RoleDAO roleDAO) {
        this.transaction = transaction;
        this.userDAO = userDAO;
        this.roleDAO = roleDAO;
    }


    @Override
    public UserDTO create(String email, String password) throws ServiceException {
        User user = new User();
        user.setEmail(email);
        user.setPassword(hashPassword(password));
        try {
            transaction.begin(Arrays.asList(userDAO, roleDAO));
            userDAO.create(user);
            User.Role role = roleDAO.read(user.getId()).orElseThrow(DAOException::new);
            user.setRole(role);
            EmailNotifier notifier = new EmailNotifier();
            notifier.sendWelcomeLetter(email);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
        return convertToUserDTO(user);
    }

    @Override
    public UserDTO findByEmailAndPassword(String email, String password) throws ServiceException {
        User user;
        try {
            transaction.beginNoTransaction(userDAO);
            user = userDAO.find(email).orElseThrow(NoSuchEntityException::new);
            if(!checkPassword(password, user.getPassword()))
                throw new ValidationException("incorrect password");
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        return convertToUserDTO(user);
    }

    @Override
    public void changeRole(Long userId, String newRole) throws ServiceException{


    }

    @Override
    public UserDTO getById(String id) throws ServiceException {
        Long userId = Long.parseLong(id);
        Optional<User> user;

        try {
            transaction.beginNoTransaction(userDAO);
            user = userDAO.read(userId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        }
        UserDTO userDTO = convertToUserDTO(user.orElseThrow());
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll() throws ServiceException {
        return null;
    }

    @Override
    public boolean update(UserDTO userDTO) throws ServiceException {
        User user = convertToUser(userDTO);

        try {
            transaction.beginNoTransaction(userDAO);
            userDAO.update(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return true;
    }

    @Override
    public boolean delete(UserDTO userDTO) throws ServiceException {
        User user = convertToUser(userDTO);

        try {
            transaction.beginNoTransaction(userDAO);
            userDAO.delete(user);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return true;
    }
}
