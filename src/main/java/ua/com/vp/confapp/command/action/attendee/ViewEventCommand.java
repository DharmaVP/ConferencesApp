package ua.com.vp.confapp.command.action.attendee;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.services.ReportService;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.services.UserService;
import ua.com.vp.confapp.utils.PaginatorUtil;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;
import ua.com.vp.confapp.utils.querybuilder.ReportQueryBuilder;

import java.util.List;

import static ua.com.vp.confapp.command.constants.Parameters.*;
import static ua.com.vp.confapp.command.constants.WebPages.ERROR_PAGE;
import static ua.com.vp.confapp.command.constants.WebPages.EVENT_PAGE;

public class ViewEventCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(RegisterForEventCommand.class);
    private EventService eventService;
    private ReportService reportService;
    private UserService userService;

    public ViewEventCommand() {
        eventService = ServiceFactory.getInstance().getEventService();
        reportService = ServiceFactory.getInstance().getReportService();
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = EVENT_PAGE;
        EventDTO event;
        List<ReportDTO> reports;
        boolean isRegistered;

        QueryBuilder queryBuilder = createQueryBuilder(request);

        Long eventId = Long.parseLong(request.getParameter("event_id"));
        UserDTO userDTO = (UserDTO) request.getSession().getAttribute(SESSION_USER);
        try {
            event = eventService.getById(eventId);
            reports = reportService.getReportsByEventId(queryBuilder);
            isRegistered = userService.isRegistered(userDTO, eventId);
            request.setAttribute("event", event);
            request.setAttribute("reportList", reports);
            request.setAttribute("isRegistered", isRegistered);
            int numberOfRecords = reportService.getNumberOfRecords(queryBuilder);
            PaginatorUtil.paginate(numberOfRecords, request);
        } catch (ServiceException e) {
            page = ERROR_PAGE;
            return new CommandResult(page);
        }
        return new CommandResult(page);
    }

    private QueryBuilder createQueryBuilder(HttpServletRequest request) {
        return new ReportQueryBuilder()
                .setEventIdFilter(Long.parseLong(request.getParameter("event_id")))
                .setSortField(request.getParameter("sort"))
                .setOrder(request.getParameter("order"))
                .setLimits(request.getParameter("page"), request.getParameter("records"));
    }
}
