package dao;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static ua.com.vp.confapp.dao.jdbc_impl.mysql_queries.UserQueries.SQL_ADD_USER;

import java.sql.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.vp.confapp.dao.DAOFactory;
import ua.com.vp.confapp.dao.UserDAO;
import ua.com.vp.confapp.dao.jdbc_impl.JDBCEntityDAO;
import ua.com.vp.confapp.dao.jdbc_impl.JDBCUserDAO;
import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.exception.DAOException;

@ExtendWith(MockitoExtension.class)
class JDBCUserDAOTest {


    @Mock
    private Connection connection;

    @Mock
    private PreparedStatement statement;

    @Mock
    private ResultSet resultSet;

    private UserDAO userDAO;

    @BeforeEach
    public void setUp() throws SQLException {
        userDAO = new JDBCUserDAO();
        ((JDBCEntityDAO) userDAO).setConnection(connection);
        when(connection.prepareStatement(any(), eq(PreparedStatement.RETURN_GENERATED_KEYS))).thenReturn(statement);
        when(statement.getGeneratedKeys()).thenReturn(resultSet);
        when(resultSet.next()).thenReturn(true);
        when(resultSet.getLong(1)).thenReturn(1L);
    }

    @Test
    public void testCreate_ShouldCreateUser() throws Exception {
        User user = new User();
        user.setEmail("cat@gmail.com");
        user.setPassword("meow");
        userDAO.create(user);
        verify(statement).executeUpdate();
    }

    @Test
    public void testCreate_ShouldThrowIllegalArgumentException_WhenIdIsNotNull() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setEmail("cat@gmail.com");
        user.setPassword("meow");
        assertThrows(IllegalArgumentException.class, () -> userDAO.create(user));
    }

    @Test
    public void testCreate_ShouldThrowDAOException_WhenCreatingUserFailed() throws Exception {
        User user = new User();
        user.setEmail("cat@gmail.com");
        user.setPassword("meow");
        when(statement.executeUpdate()).thenReturn(0);
        assertThrows(DAOException.class, () -> userDAO.create(user));
    }

    @Test
    public void testCreate_ShouldThrowDAOException_WhenSQLExceptionOccurs() throws Exception {
        User user = new User();
        user.setEmail("cat@gmail.com");
        user.setPassword("meow");
        doThrow(new SQLException()).when(statement).executeUpdate();
        assertThrows(DAOException.class, () -> userDAO.create(user));
    }
}