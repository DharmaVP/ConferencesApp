package ua.com.vp.confapp.command.action.moderator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.command.action.attendee.RegisterForEventCommand;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.entities.User;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.services.ReportService;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.services.UserService;
import ua.com.vp.confapp.utils.PaginatorUtil;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;
import ua.com.vp.confapp.utils.querybuilder.UserQueryBuilder;

import java.util.List;

import static ua.com.vp.confapp.command.constants.Parameters.ERROR;
import static ua.com.vp.confapp.command.constants.Parameters.SESSION_USER;
import static ua.com.vp.confapp.command.constants.WebPages.*;

public class ViewUserCommand implements Command {
    private UserService userService;

    public ViewUserCommand() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = VIEW_USER;

        Long userId = Long.parseLong(request.getParameter("user_id"));

        QueryBuilder queryBuilder = createQueryBuilder(request);
        try {
            UserDTO user = userService.getById(userId);
            List<User.Role> roles = userService.getAllRoles(queryBuilder);
            request.setAttribute("userInfo", user);
            request.setAttribute("roleList", roles);
        } catch (ServiceException e) {
            page = USERS_MANAGEMENT;
            request.getSession().setAttribute(ERROR, "Couldn't open users profile");
            return new CommandResult(page);
        }
        return new CommandResult(page);
    }

    private QueryBuilder createQueryBuilder(HttpServletRequest request) {
        UserDTO user = (UserDTO) request.getSession().getAttribute(SESSION_USER);
        return new UserQueryBuilder()
                .showAllRows(true)
                .setRolesFilter(user.getRole().equals("admin") ? 1 : 3)
                .setSortField("name");
    }
}

