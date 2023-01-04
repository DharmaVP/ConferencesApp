package ua.com.vp.confapp.dao.jdbc_impl;

import ua.com.vp.confapp.dao.DAO;

import java.sql.Connection;

public abstract class JDBCEntityDAO implements DAO {

    protected Connection connection;


    public void setConnection(Connection connection) {
        this.connection = connection;
    }
}
