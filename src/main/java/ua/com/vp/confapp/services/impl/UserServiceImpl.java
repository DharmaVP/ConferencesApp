package ua.com.vp.confapp.services.impl;

import ua.com.vp.confapp.dao.*;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.exception.NoSuchEntityException;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.exception.ValidationException;
import ua.com.vp.confapp.services.UserService;
import ua.com.vp.confapp.utils.EmailNotifier;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static ua.com.vp.confapp.utils.MapperDTO.*;
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
        } finally {
            transaction.endNoTransaction();
        }
        return convertToUserDTO(user);
    }

    @Override
    public void changeRole(Long userId, String newRole) throws ServiceException{
        User user = new User();
        user.setId(userId);
        try {
            transaction.begin(List.of(userDAO, roleDAO));
            User.Role role = roleDAO.find(newRole).orElseThrow(NoSuchEntityException::new);
            user.setRole(role);
            userDAO.changeRole(user);
            transaction.commit();
        } catch (DAOException e) {
            transaction.rollback();
            throw new ServiceException(e);
        } finally {
            transaction.end();
        }
    }

    @Override
    public boolean isRegistered(UserDTO userDTO, Long eventId) throws ServiceException{
        Long userId = userDTO.getId();
        boolean isRegistered;
        try {
            transaction.beginNoTransaction(userDAO);
            isRegistered = userDAO.isRegistered(userId, eventId);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return isRegistered;
    }

    @Override
    public UserDTO getById(Long id) throws ServiceException {
        Optional<User> user;

        try {
            transaction.beginNoTransaction(userDAO);
            user = userDAO.read(id);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        UserDTO userDTO = convertToUserDTO(user.orElseThrow());
        return userDTO;
    }

    @Override
    public List<UserDTO> getAll(QueryBuilder queryBuilder) throws ServiceException {
        List<UserDTO> usersDTO = new ArrayList<>();
        List<User> users;

        try {
            transaction.beginNoTransaction(userDAO);
            users = userDAO.getAll(queryBuilder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        for (User user : users) {
            usersDTO.add(convertToUserDTO(user));
        }
        return usersDTO;
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

    @Override
    public int getNumberOfRecords(QueryBuilder queryBuilder) throws ServiceException {
        int numberOfRecords = 0;
        try {
            transaction.beginNoTransaction(userDAO);
            numberOfRecords = userDAO.getNoOfRecords(queryBuilder);
        } catch (DAOException e) {
            throw new ServiceException(e);
        } finally {
            transaction.endNoTransaction();
        }
        return numberOfRecords;
    }
}
