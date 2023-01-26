package ua.com.vp.confapp.command.action.moderator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.services.UserService;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;
import ua.com.vp.confapp.utils.querybuilder.UserQueryBuilder;

import java.util.List;

import static ua.com.vp.confapp.command.constants.WebPages.ADD_EVENT_PAGE;
import static ua.com.vp.confapp.command.constants.WebPages.ADD_REPORT_PAGE;

public class AddReportPageCommand implements Command {
    private UserService userService;

    public AddReportPageCommand() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        QueryBuilder queryBuilder = new UserQueryBuilder()
                .setRoleFilter("speaker");

        List<UserDTO> speakers = null;

        try {
           speakers = userService.getAll(queryBuilder);
        } catch (ServiceException e) {

        }
        request.setAttribute("speakerList", speakers);
        return new CommandResult(ADD_REPORT_PAGE);
    }
}
