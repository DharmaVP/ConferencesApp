package ua.com.vp.confapp.command.action.speaker;

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
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.services.ReportService;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.services.UserService;
import ua.com.vp.confapp.utils.PaginatorUtil;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;
import ua.com.vp.confapp.utils.querybuilder.ReportQueryBuilder;

import java.util.List;

import static ua.com.vp.confapp.command.constants.Parameters.SESSION_USER;
import static ua.com.vp.confapp.command.constants.WebPages.*;

public class ViewEventToSpeakCommand implements Command {

    private EventService eventService;
    private ReportService reportService;

    public ViewEventToSpeakCommand() {
        eventService = ServiceFactory.getInstance().getEventService();
        reportService = ServiceFactory.getInstance().getReportService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = EVENT_TO_SPEAK;
        EventDTO event;
        List<ReportDTO> reports;

        QueryBuilder queryBuilder = createQueryBuilder(request);

        Long eventId = Long.parseLong(request.getParameter("event_id"));

        try {
            event = eventService.getById(eventId);
            reports = reportService.getAll(queryBuilder);
            request.setAttribute("event", event);
            request.setAttribute("reportList", reports);
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
