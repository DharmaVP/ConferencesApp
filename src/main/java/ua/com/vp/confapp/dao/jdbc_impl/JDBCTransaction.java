package ua.com.vp.confapp.dao.jdbc_impl;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.connection.HikariCPDataSource;
import ua.com.vp.confapp.dao.Transaction;
import ua.com.vp.confapp.exception.DAOConfigurationException;
import ua.com.vp.confapp.exception.DAOException;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class JDBCTransaction implements Transaction<JDBCEntityDAO>{
    private static final Logger logger = LogManager.getLogger();
    private Connection connection;

    public JDBCTransaction() {
    }

    public void beginNoTransaction(JDBCEntityDAO dao) throws DAOException {
        setTransactionConn();
        dao.setConnection(connection);
    }

    public void begin(List<JDBCEntityDAO> daos) throws DAOException {
        setTransactionConn();
        try {
            connection.setAutoCommit(false);
        } catch (SQLException e) {
            logger.log(Level.ERROR, " Error executing query ", e);
        }
        for (JDBCEntityDAO dao : daos) {
            dao.setConnection(connection);
        }
    }


    public void end() {
        if (!isCurrentConnNull()) {
            try {
                connection.setAutoCommit(true);
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, " Error changing autocommit status",  e);
            }
            connection = null;
        }
    }

    public void endNoTransaction() {
        if (!isCurrentConnNull()) {
            try {
                connection.close();
            } catch (SQLException e) {
                logger.log(Level.ERROR, " Error closing connection",  e);
            }
            connection = null;
        }
    }

    public void commit() {
        try {
            connection.commit();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Commit transaction error", e);
        }
    }

    public void rollback() {
        try {
            connection.rollback();
        } catch (SQLException e) {
            logger.log(Level.ERROR, "Rollback transaction error", e);
        }
    }

    private void setTransactionConn() throws DAOException {
        if (isCurrentConnNull()) {
            try {
                connection = HikariCPDataSource.getConnection();
            } catch (DAOConfigurationException e) {
                throw new DAOException(e);
            }
        }
    }

    private boolean isCurrentConnNull() {
        return connection == null;
    }
}
