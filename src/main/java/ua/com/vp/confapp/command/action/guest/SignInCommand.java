package ua.com.vp.confapp.command.action.guest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.command.constants.CommandType;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.exception.ValidationException;
import ua.com.vp.confapp.filters.AppUtils;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.services.UserService;
import ua.com.vp.confapp.utils.Validator;

import java.util.HashMap;
import java.util.Map;

import static ua.com.vp.confapp.command.constants.Parameters.*;
import static ua.com.vp.confapp.command.constants.WebPages.*;

public class SignInCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(SignUpCommand.class);
    private UserService userService;

    public SignInCommand() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String email = request.getParameter(EMAIL);
        String password = request.getParameter(PASSWORD);

        String page = CommandUtil.getCommandToRedirect(CommandType.ENTER_CABINET);
        Map<String, String> messages = new HashMap<>();
        request.setAttribute("errors", messages);

        try {
            Validator.validateNonEmptyFields(email, password);
        } catch (ValidationException e) {
            messages.put("email", e.getMessage());
            page = SIGN_IN_PAGE;
            return new CommandResult(page);
        }
        try {
            UserDTO user = userService.findByEmailAndPassword(email, password);
            HttpSession session = request.getSession();
            session.setAttribute(SESSION_USER, user);
        } catch (ServiceException e) {
            messages.put("error", e.getMessage());
            page = SIGN_IN_PAGE;
            return new CommandResult(page);
        }
        int redirectId = -1;
        try {
            redirectId = Integer.parseInt(request.getParameter("redirectId"));
        } catch (Exception e) {
        }
        String requestUri = AppUtils.getRedirectAfterLoginUrl(request.getSession(), redirectId);
        if (requestUri != null) {
            return new CommandResult(requestUri, true);
        }
        return new CommandResult(page, true);
    }
}
