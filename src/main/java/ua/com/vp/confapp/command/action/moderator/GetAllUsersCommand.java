package ua.com.vp.confapp.command.action.moderator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.command.action.guest.ViewAllEventsCommand;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.services.UserService;
import ua.com.vp.confapp.utils.PaginatorUtil;
import ua.com.vp.confapp.utils.querybuilder.EventQueryBuilder;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;
import ua.com.vp.confapp.utils.querybuilder.UserQueryBuilder;

import java.util.List;

import static ua.com.vp.confapp.command.constants.WebPages.*;

public class GetAllUsersCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(GetAllUsersCommand.class);
    private UserService userService;

    public GetAllUsersCommand() {
        userService = ServiceFactory.getInstance().getUserService();
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = USERS_MANAGEMENT;
        List<UserDTO> users;

        QueryBuilder queryBuilder = createQueryBuilder(request);

        try {
            users = userService.getAll(queryBuilder);
            request.setAttribute("userList", users);
            int numberOfRecords = userService.getNumberOfRecords(queryBuilder);
            PaginatorUtil.paginate(numberOfRecords, request);
        } catch (ServiceException e) {
            page = ERROR_PAGE;
            return new CommandResult(page);
        }
        return new CommandResult(page);
    }

    private QueryBuilder createQueryBuilder(HttpServletRequest request) {
        return new UserQueryBuilder()
                .setSearchedUser(request.getParameter("search"))
                .setRoleFilter(request.getParameter("search"))
                .setSortField(request.getParameter("sort"))
                .setOrder(request.getParameter("order"))
                .setLimits(request.getParameter("page"), request.getParameter("records"));
    }
}
