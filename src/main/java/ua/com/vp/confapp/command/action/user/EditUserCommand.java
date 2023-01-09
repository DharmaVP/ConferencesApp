package ua.com.vp.confapp.command.action.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.command.action.guest.SignUpCommand;
import ua.com.vp.confapp.command.constants.CommandType;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.services.UserService;

import static ua.com.vp.confapp.command.constants.Parameters.SESSION_USER;
import static ua.com.vp.confapp.command.constants.WebPages.PROFILE;

public class EditUserCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(SignUpCommand.class);
    private UserService userService;

    public EditUserCommand() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String firstName = request.getParameter("first_name");
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute(SESSION_USER);
        userDTO.setFirstName(firstName);


        String page = CommandUtil.getCommandToRedirect(CommandType.GET_PROFILE);

        try {
            userService.update(userDTO);
        } catch (ServiceException e) {
            request.setAttribute("error", "unable to update info");
            return new CommandResult(PROFILE);
        }
        return new CommandResult(page, true);
    }
}
