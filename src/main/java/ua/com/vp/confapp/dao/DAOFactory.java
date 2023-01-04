package ua.com.vp.confapp.dao;

import ua.com.vp.confapp.dao.jdbc_impl.JDBCDAOFactory;
import ua.com.vp.confapp.dao.jdbc_impl.JDBCTransaction;
import ua.com.vp.confapp.exception.DAOConfigurationException;
import java.sql.SQLException;

import static ua.com.vp.confapp.dao.DAOType.*;

public abstract class DAOFactory {

    private static final DAOType DEFAULT_IMPL = JDBC;


    protected DAOFactory() {

    }

    //Default
    public static DAOFactory getInstance() {
        return createDAOFactory(DEFAULT_IMPL);
    }

    public static DAOFactory getInstance(DAOType type) {
        return createDAOFactory(type);
    }

    /**
     * for future development when DAOFactory migrates from Factory Method to Abstract Factory
     * (e.g. XML Factory, or JPA Factory)
     */
    private static DAOFactory createDAOFactory(DAOType daoType) {
        return switch (daoType) {
            case JDBC -> JDBCDAOFactory.getInstance();
            default -> throw new DAOConfigurationException("No existing Implementation of DAOFactory");
        };
    }

    /**
     * Returns a connection to the database.
     *
     * @return A connection to the database.
     * @throws SQLException If acquiring the connection fails.
     */

    public abstract UserDAO getUserDAO();

    public abstract EventDAO getEventDAO();

    public abstract ReportDAO getReportDAO();

    public abstract RoleDAO getRoleDAO();

    public abstract EventAddressDAO getEventAddressDAO();

    public abstract JDBCTransaction getTransaction();
}
