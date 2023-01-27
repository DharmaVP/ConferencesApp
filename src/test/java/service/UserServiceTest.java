package service;

import org.junit.jupiter.api.*;
import org.mockito.Mockito;
import ua.com.vp.confapp.dao.UserDAO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.exception.DAOException;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.services.UserService;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;

public class UserServiceTest {
    private final static String LOGIN = "yulia.tokan.11@gmail.com";
    private final static String LOGIN_DONT_EXIST = "notexist@gmail789.com";

    UserService userService;

    UserDAO userDao;

    private static final UserDTO user = UserDTO.builder().email("test.user@gmail.com").build();
    private static final UserDTO emptyUser = UserDTO.builder().build();
    private static final Long ID = new Long(4);

    @BeforeAll
    public void setUp() throws Exception {
        userService = ServiceFactory.getInstance().getUserService();
        userDao = mock(UserDAO.class);
    }

    @AfterAll
    public void tearDown() throws Exception {
        userService = null;
    }

//    @Test
//    public void createUser() throws ServiceException {
//        assertTrue(userService.create("vladimir@gmail.com", "ff12ff12%"));
//    }

//    @Test(expected = ServiceException.class)
//    public void createUserWithException() throws ServiceException {
//        assertTrue(userService.createUser(emptyUser));
//    }

    @Test
    void isRegistered() throws ServiceException {
        assertTrue(userService.isRegistered(user, 4L));
    }


//    @Test
//    public void findUserByLogin() throws ServiceException, DAOException {
//        assertNotNull(userService.findUserByLogin(LOGIN).get());
//    }

//    @Test
//    public void countCalories() throws ServiceException {
//        Mockito.doReturn(1500).when(userService).countCalories(user);
//        Integer expected = 1500;
//        assertEquals(expected, userService.countCalories(user));
//    }
//
//    @Test
//    public void countProtein() throws ServiceException {
//        Integer expected = 500;
//        assertEquals(expected, userService.countProtein(user));
//    }
//
//    @Test
//    public void countFats() throws ServiceException {
//        Integer expected = 400;
//        assertEquals(expected, userService.countFats(user));
//    }
//
//    @Test
//    public void countCarbohydrates() throws ServiceException {
//        Integer expected = 100;
//        assertEquals(expected, userService.countCarbohydrates(user));
//    }


}
