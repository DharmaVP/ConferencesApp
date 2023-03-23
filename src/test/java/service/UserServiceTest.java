package service;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.parallel.Execution;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ua.com.vp.confapp.dao.DAO;
import ua.com.vp.confapp.dao.RoleDAO;
import ua.com.vp.confapp.dao.Transaction;
import ua.com.vp.confapp.dao.UserDAO;
import ua.com.vp.confapp.dao.jdbc_impl.JDBCEntityDAO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.exception.NoSuchEntityException;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.exception.ValidationException;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.services.UserService;
import ua.com.vp.confapp.services.impl.UserServiceImpl;
import ua.com.vp.confapp.utils.EmailNotifier;
import ua.com.vp.confapp.utils.PBKDF2Hasher;

import java.util.Arrays;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UserServiceTest {

    @Mock
    private Transaction<DAO> transaction;

    @Mock
    private UserDAO userDAO;

    @Mock
    private RoleDAO roleDAO;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void testCreate() throws DAOException, ServiceException {
        // Create a mock EmailNotifier
        EmailNotifier emailNotifier = mock(EmailNotifier.class);

        // Set up mock objects for the test scenario
        User user = new User();
        user.setEmail("vladimirponomarenko@yahoo.com");
        user.setPassword("password");
        User.Role role = new User.Role();
        role.setId(1L);
        role.setName("ROLE_USER");
        when(roleDAO.read(user.getId())).thenReturn(Optional.of(role));

        // Call the method being tested
        UserDTO result = userService.create(user.getEmail(), user.getPassword());

        // Verify the result
        assertNotNull(result);
        assertEquals(user.getEmail(), result.getEmail());
        assertEquals(role.getName(), result.getRole());

        // Verify that the DAOs were called with the correct arguments
        verify(transaction).begin(Arrays.asList(userDAO, roleDAO));
        verify(userDAO).create(user);
        verify(roleDAO).read(user.getId());

    }

    @Test
    void testFindByEmailAndPassword() throws Exception {
        // Set up test data
        String email = "test@example.com";
        String password = "password123";
        String hashedPassword = PBKDF2Hasher.hashPassword(password);
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword(hashedPassword);
        User.Role role = new User.Role();
        role.setId(1L);
        role.setName("test_role");
        user.setRole(role);

        // Set up mock behavior for userDAO
        when(userDAO.find(email)).thenReturn(Optional.of(user));


        // Call the method under test
        UserDTO result = userService.findByEmailAndPassword(email, password);

        // Verify the result
        assertNotNull(result);
        assertEquals(email, result.getEmail());


        // Verify that the userDAO method was called with the correct argument
        verify(userDAO).find(email);

        // Verify that no other userDAO methods were called
        verifyNoMoreInteractions(userDAO);

        // Verify that transaction methods were called as expected
        verify(transaction).beginNoTransaction(userDAO);
        verify(transaction).endNoTransaction();
        verifyNoMoreInteractions(transaction);
    }

    @Test
    void testFindByEmailAndPasswordWithIncorrectPassword() throws Exception {
        // Set up test data
        String email = "test@example.com";
        String password = "password123";
        String incorrectPassword = "wrongpassword";
        String hashedPassword = PBKDF2Hasher.hashPassword(password);
        User user = new User();
        user.setId(1L);
        user.setEmail(email);
        user.setPassword(hashedPassword);

        // Set up mock behavior for userDAO
        when(userDAO.find(email)).thenReturn(Optional.of(user));



        // Call the method under test and verify that a ValidationException is thrown
        assertThrows(ValidationException.class, () -> userService.findByEmailAndPassword(email, incorrectPassword));

        // Verify that the userDAO method was called with the correct argument
        verify(userDAO).find(email);

        // Verify that no other userDAO methods were called
        verifyNoMoreInteractions(userDAO);

        // Verify that transaction methods were called as expected
        verify(transaction).beginNoTransaction(userDAO);
        verify(transaction).endNoTransaction();
        verifyNoMoreInteractions(transaction);
    }

    @Test
    void testFindByEmailAndPasswordThrowsNoSuchEntityExceptionWhenUserNotFound() throws DAOException, ServiceException {
        // Arrange
        String email = "test@example.com";
        String password = "password123";
        when(userDAO.find(email)).thenReturn(Optional.empty());

        // Act + Assert
        ServiceException exception = assertThrows(ServiceException.class, () -> userService.findByEmailAndPassword(email, password));
        assertEquals("ua.com.vp.confapp.exception.NoSuchEntityException: no entity", exception.getMessage());
    }

    @Test
    void testChangeRole() throws DAOException, ServiceException {
        Long userId = 1L;
        String newRole = "ADMIN";
        User user = new User();
        user.setId(userId);

        // Mock the roleDAO to return a role when a role with the name "ADMIN" is requested
        User.Role role = new User.Role();
        role.setName(newRole);
        when(roleDAO.find(newRole)).thenReturn(Optional.of(role));

        // Call the service method
        userService.changeRole(userId, newRole);

        // Verify that the correct methods were called on the DAOs
        verify(userDAO).changeRole(user);
        verify(roleDAO).find(newRole);

        // Verify that the transaction was committed
        verify(transaction).commit();

        // Verify that the transaction was ended
        verify(transaction).end();
    }

    @Test
    void testChangeRoleWithException() throws DAOException, ServiceException {
        Long userId = 1L;
        String newRole = "ADMIN";
        User user = new User();
        user.setId(userId);

        // Mock the roleDAO to throw a DAOException when a role with the name "ADMIN" is requested
        when(roleDAO.find(newRole)).thenThrow(new DAOException());

        // Call the service method and assert that a ServiceException is thrown
        assertThrows(ServiceException.class, () -> userService.changeRole(userId, newRole));

        // Verify that the correct methods were called on the DAOs
        verify(roleDAO).find(newRole);

        // Verify that the transaction was rolled back
        verify(transaction).rollback();

        // Verify that the transaction was ended
        verify(transaction).end();
    }

    @Test
    void testIsRegistered() throws DAOException, ServiceException {
        Long userId = 1L;
        Long eventId = 2L;

        // Mock the userDAO to return true when the user is registered for the event
        when(userDAO.isRegistered(userId, eventId)).thenReturn(true);

        // Call the service method
        boolean isRegistered = userService.isRegistered(UserDTO.builder().id(userId).build(), eventId);

        // Verify that the correct method was called on the DAO
        verify(userDAO).isRegistered(userId, eventId);

        // Verify that the transaction was ended
        verify(transaction).endNoTransaction();

        // Assert that the correct value was returned
        assertTrue(isRegistered);
    }

    @Test
    void testIsRegisteredWithException() throws DAOException, ServiceException {
        Long userId = 1L;
        Long eventId = 2L;

        // Mock the userDAO to throw a DAOException when checking if the user is registered for the event
        when(userDAO.isRegistered(userId, eventId)).thenThrow(new DAOException());

        // Call the service method and assert that a ServiceException is thrown
        assertThrows(ServiceException.class, () -> userService.isRegistered(UserDTO.builder().id(userId).build(), eventId));

        // Verify that the correct method was called on the DAO
        verify(userDAO).isRegistered(userId, eventId);

        // Verify that the transaction was ended
        verify(transaction).endNoTransaction();
    }


}
