package ua.com.vp.confapp.dao.jdbc_impl;


import ua.com.vp.confapp.dao.*;

public class JDBCDAOFactory extends DAOFactory {

    private static volatile JDBCDAOFactory instance;


    private JDBCDAOFactory() {
    }

    public static JDBCDAOFactory getInstance() {
        if (instance == null) {
            synchronized (JDBCDAOFactory.class) {
                if (instance == null) {
                    instance = new JDBCDAOFactory();
                }
            }
        }
        return instance;
    }


    @Override
    public UserDAO getUserDAO() {
        return new JDBCUserDAO();
    }

    @Override
    public EventDAO getEventDAO() {
        return new JDBCEventDAO();
    }

    @Override
    public ReportDAO getReportDAO() {
        return new JDBCReportDAO();
    }

    @Override
    public RoleDAO getRoleDAO() {
        return new JDBCRoleDAO();
    }

    @Override
    public EventAddressDAO getEventAddressDAO() {
        return new JDBCEventAddressDAO();
    }

    @Override
    public JDBCTransaction getTransaction() {
        return new JDBCTransaction();
    }
}
