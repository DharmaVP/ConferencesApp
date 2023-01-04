package ua.com.vp.confapp.services;

import ua.com.vp.confapp.dao.*;
import ua.com.vp.confapp.dao.jdbc_impl.JDBCTransaction;
import ua.com.vp.confapp.entities.Event;
import ua.com.vp.confapp.services.impl.EventServiceImpl;
import ua.com.vp.confapp.services.impl.ReportServiceImpl;
import ua.com.vp.confapp.services.impl.UserServiceImpl;

import java.util.ResourceBundle;

public class ServiceFactory {
    private DAOFactory daoFactory;


    private ServiceFactory() {
        daoFactory = DAOFactory.getInstance(DAOType.JDBC);
    }

    public static ServiceFactory getInstance() {
        return new ServiceFactory();
    }


    public UserService getUserService() {
        UserDAO userDAO = daoFactory.getUserDAO();
        RoleDAO roleDAO = daoFactory.getRoleDAO();
        Transaction transaction = daoFactory.getTransaction();
        return new UserServiceImpl(transaction, userDAO, roleDAO);
    }

    public ReportService getReportService() {
        return new ReportServiceImpl(daoFactory);
    }

    public EventService getEventService() {
        EventDAO eventDAO = daoFactory.getEventDAO();
        EventAddressDAO eventAddressDAO = daoFactory.getEventAddressDAO();
        Transaction transaction = daoFactory.getTransaction();
        return new EventServiceImpl(transaction, eventDAO, eventAddressDAO);
    }
}
