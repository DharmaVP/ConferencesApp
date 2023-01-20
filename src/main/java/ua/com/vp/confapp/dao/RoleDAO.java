package ua.com.vp.confapp.dao;

import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.exception.DAOException;

import java.util.Optional;

public interface RoleDAO extends GenericDAO<User.Role, Long> {

    Optional<User.Role> find(String roleName) throws DAOException;
}
