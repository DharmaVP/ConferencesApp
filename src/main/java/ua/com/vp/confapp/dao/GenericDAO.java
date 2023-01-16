package ua.com.vp.confapp.dao;

import ua.com.vp.confapp.entities.Entity;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.util.List;
import java.util.Optional;

public interface GenericDAO<T extends Entity, K> extends DAO {
    boolean create(T model) throws DAOException;

    Optional<T> read(K key) throws DAOException;

    boolean update(T model) throws DAOException;

    boolean delete(T model) throws DAOException;

    List<T> getAll(QueryBuilder queryBuilder) throws DAOException;

    int getNoOfRecords(QueryBuilder queryBuilder) throws DAOException;
}
