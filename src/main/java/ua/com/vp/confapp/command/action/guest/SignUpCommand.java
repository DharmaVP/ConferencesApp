package ua.com.vp.confapp.command.action.guest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.exception.ValidationException;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.services.UserService;
import ua.com.vp.confapp.utils.Validator;

import java.util.HashMap;
import java.util.Map;

import static ua.com.vp.confapp.command.constants.CommandType.*;
import static ua.com.vp.confapp.command.constants.Parameters.*;
import static ua.com.vp.confapp.command.constants.WebPages.*;

public class SignUpCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(SignUpCommand.class);
    private UserService userService;

    public SignUpCommand() {
        userService = ServiceFactory.getInstance().getUserService();
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);
        String confirmPassword = request.getParameter(CONFIRM_PASSWORD);

        String page = CommandUtil.getCommandToRedirect(GET_PROFILE);


        Map<String, String> messages = new HashMap<>();
        request.setAttribute("errors", messages);

        try {
            Validator.validateEmail(email);
        } catch (ValidationException e) {
            messages.put("email", e.getMessage());
        }

        try {
            Validator.validatePassword(password);
        } catch (ValidationException e) {
            messages.put("password", e.getMessage());
        }
        try {
            Validator.validateConfirmPassword(password, confirmPassword);
        } catch (ValidationException e) {
            messages.put("confirm_password", e.getMessage());
        }

        if (messages.size() > 0) {
            return new CommandResult(SIGN_UP_PAGE);
        }

        try {
            UserDTO user = userService.create(email, password);
            HttpSession session = request.getSession();
            session.setAttribute(SUCCEED, SUCCEED_REGISTER);
            session.setAttribute(MESSAGE, FINISH_REGISTRATION);
            session.setAttribute(SESSION_USER, user);
            LOGGER.info("user with login = " + email + " was registered");
        } catch (ServiceException e) {
            request.getSession().removeAttribute(ERROR);
            request.getSession().setAttribute(ERROR, e.getMessage());
            page = CommandUtil.getCommandToRedirect(GET_SIGN_UP_PAGE);
            return new CommandResult(page);
        }
        return new CommandResult(page, true);
    }
}
