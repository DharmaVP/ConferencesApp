package ua.com.vp.confapp.command.action.speaker;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.services.ReportService;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.utils.PaginatorUtil;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.util.List;

import static ua.com.vp.confapp.command.constants.WebPages.*;

public class ViewReportCommand implements Command {
    private EventService eventService;
    private ReportService reportService;

    public ViewReportCommand() {
        eventService = ServiceFactory.getInstance().getEventService();
        reportService = ServiceFactory.getInstance().getReportService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = REPORT_PAGE;

        Long reportId = Long.parseLong(request.getParameter("report_id"));

        try {
            ReportDTO report  = reportService.getById(reportId);
            EventDTO event = eventService.getById(report.getEventId());

            request.setAttribute("report", report);
            request.setAttribute("event", event);

        } catch (ServiceException e) {
            page = ERROR_PAGE;
            return new CommandResult(page);
        }
        return new CommandResult(page);
    }
}
