package ua.com.vp.confapp.command.action.moderator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.command.constants.CommandType;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.services.UserService;
import ua.com.vp.confapp.utils.querybuilder.UserQueryBuilder;

import static ua.com.vp.confapp.command.constants.CommandType.VIEW_EVENT;
import static ua.com.vp.confapp.command.constants.CommandType.VIEW_USER;
import static ua.com.vp.confapp.command.constants.Parameters.ERROR;
import static ua.com.vp.confapp.command.constants.WebPages.USERS_MANAGEMENT;


public class ChangeRoleCommand implements Command {
    private UserService userService;

    public ChangeRoleCommand() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Long userId = Long.parseLong(request.getParameter("user_id"));
        String role = request.getParameter("role_name");

        String page = CommandUtil.getCommandToRedirect(VIEW_USER)+"&user_id="+userId;
        try {
            userService.changeRole(userId, role);

            request.getSession().setAttribute("success", "Role has been changed to " + role);
        } catch (ServiceException e) {
            request.getSession().setAttribute(ERROR, "Couldn't change role");
            return new CommandResult(page, true);
        }
        return new CommandResult(page, true);
    }
}
