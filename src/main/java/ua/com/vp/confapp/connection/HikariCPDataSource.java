package ua.com.vp.confapp.connection;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.logging.log4j.*;
import ua.com.vp.confapp.dao.DAOProperties;
import ua.com.vp.confapp.dao.jdbc_impl.JDBCDAOFactory;
import ua.com.vp.confapp.exception.DAOConfigurationException;
import ua.com.vp.confapp.exception.DAOException;

import java.sql.Connection;
import java.sql.SQLException;

import static ua.com.vp.confapp.connection.ConnectionConstants.*;


public class HikariCPDataSource {
    private static final Logger LOGGER = LogManager.getLogger(HikariCPDataSource.class);
    private static final HikariConfig CONFIG = new HikariConfig();
    private static HikariDataSource ds;


    static {
        DAOProperties properties = new DAOProperties("conference_db.jdbc");
        try {
            CONFIG.setJdbcUrl(properties.getProperty(PROPERTY_URL));
            CONFIG.setUsername(properties.getProperty(PROPERTY_USERNAME));
            CONFIG.setPassword(properties.getProperty(PROPERTY_PASSWORD));
            CONFIG.setDriverClassName(properties.getProperty(PROPERTY_DRIVER));
            CONFIG.addDataSourceProperty(CACHE_PREP_STATEMENT, properties.getProperty(CACHE_PREP_STATEMENT));
            CONFIG.addDataSourceProperty(CACHE_SIZE, properties.getProperty(CACHE_SIZE));
            CONFIG.addDataSourceProperty(CACHE_SQL_LIMIT, properties.getProperty(CACHE_SQL_LIMIT));
            ds = new HikariDataSource(CONFIG);
        } catch (DAOConfigurationException e) {
            LOGGER.error(e);
            throw e;
        }
    }

    private HikariCPDataSource(){}

    public static Connection getConnection() throws DAOConfigurationException {
        try {
            return ds.getConnection();
        } catch (SQLException e) {
            LOGGER.error("Couldn't get Connection from HikariPool");
            throw new DAOConfigurationException(e);
        }
    }
}
