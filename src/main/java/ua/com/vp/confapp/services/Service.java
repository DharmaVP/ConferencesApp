package ua.com.vp.confapp.services;

import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.util.List;

public interface Service <T> {

    T getById(Long id) throws ServiceException;

    List<T> getAll(QueryBuilder queryBuilder) throws ServiceException;

    void update(T entity) throws ServiceException;

    void delete(T entity) throws ServiceException;

    int getNumberOfRecords(QueryBuilder queryBuilder) throws ServiceException;
}
