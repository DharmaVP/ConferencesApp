package ua.com.vp.confapp.services;

import ua.com.vp.confapp.entities.Entity;
import ua.com.vp.confapp.exception.ServiceException;

import java.util.List;

public interface Service <T> {

    T getById(String id) throws ServiceException;

    List<T> getAll() throws ServiceException;

    boolean update(T entity) throws ServiceException;

    boolean delete(T entity) throws ServiceException;
}
