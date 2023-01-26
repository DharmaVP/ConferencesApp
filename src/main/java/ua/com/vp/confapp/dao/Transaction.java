package ua.com.vp.confapp.dao;


import ua.com.vp.confapp.entities.Entity;
import ua.com.vp.confapp.exception.DAOException;

import java.sql.SQLException;
import java.util.List;

public interface Transaction <T extends DAO> {

    void beginNoTransaction(T dao) throws DAOException;

    void begin(List<T> daos) throws DAOException;

    void end();

    void endNoTransaction() ;

    void commit();

    void rollback();

    void setRepeatableRead();

    void cancelRepeatableRead();
}
