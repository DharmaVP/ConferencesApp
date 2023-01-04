package ua.com.vp.confapp.dao;



import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.dao.jdbc_impl.JDBCDAOFactory;
import ua.com.vp.confapp.exception.DAOConfigurationException;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * This class immediately loads the DAO properties file 'db.properties' once in memory and provides
 * a constructor which takes the specific key which is to be used as property key prefix of the DAO
 * properties file. There is a property getter which only returns the property prefixed with
 * 'specificKey.' and provides the option to indicate whether the property is mandatory or not.
 *
 * @author Volodymyr Ponomarenko
 */
public class DAOProperties {
    private static final Logger LOGGER = LogManager.getLogger(DAOProperties.class);
    private static final String PROPERTIES_FILE;
    private static final Properties PROPERTIES;
    private String specificKey;


    static {
        PROPERTIES_FILE = "db.properties";
        PROPERTIES = new Properties();
    }

    static {
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        InputStream propertiesFile = classLoader.getResourceAsStream(PROPERTIES_FILE);

        if (propertiesFile == null) {
            throw new DAOConfigurationException(
                    "Properties file '" + PROPERTIES_FILE + "' is missing in classpath.");
        }

        try {
            PROPERTIES.load(propertiesFile);
        } catch (IOException e) {
            throw new DAOConfigurationException(
                    "Cannot load properties file '" + PROPERTIES_FILE + "'.", e);
        }
    }

    /**
     * Construct a DAOProperties instance for the given specific key which is to be used as property
     * key prefix of the DAO properties file.
     *
     * @param specificKey The specific key which is to be used as property key prefix.
     * @throws DAOConfigurationException During class initialization if the DAO properties file is
     *                                   missing in the classpath or cannot be loaded.
     */
    public DAOProperties(String specificKey) throws DAOConfigurationException {
        this.specificKey = specificKey;
    }


    /**
     * Returns the DAOProperties instance specific property value associated with the given key with
     * the option to indicate whether the property is mandatory or not.
     *
     * @param key       The key to be associated with a DAOProperties instance specific value.
     * @return The DAOProperties instance specific property value associated with the given key.
     * @throws DAOConfigurationException If the returned property value is null or empty
     */
    public String getProperty(String key) throws DAOConfigurationException {
        String fullKey = specificKey + "." + key;
        String property = PROPERTIES.getProperty(fullKey);

        if (property == null || property.trim().length() == 0) {
            throw new DAOConfigurationException("Required property '" + fullKey + "'"
                    + " is missing in properties file '" + PROPERTIES_FILE + "'.");
        }
        return property;
    }

}
