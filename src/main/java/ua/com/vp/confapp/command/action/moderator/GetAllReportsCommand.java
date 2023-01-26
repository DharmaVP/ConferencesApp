package ua.com.vp.confapp.command.action.moderator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.services.ReportService;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.utils.PaginatorUtil;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;
import ua.com.vp.confapp.utils.querybuilder.ReportQueryBuilder;

import java.util.List;

import static ua.com.vp.confapp.command.constants.Parameters.SESSION_USER;
import static ua.com.vp.confapp.command.constants.WebPages.*;
import static ua.com.vp.confapp.command.constants.WebPages.ERROR_PAGE;

public class GetAllReportsCommand implements Command {
    private ReportService reportService;

    public GetAllReportsCommand() {
        reportService = ServiceFactory.getInstance().getReportService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = REPORTS_MANAGEMENT;
        List<ReportDTO> reports;

        QueryBuilder queryBuilder = createQueryBuilder(request);

        try {
            reports = reportService.getAll(queryBuilder);
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
                .setSpeakerPresence(Boolean.parseBoolean(request.getParameter("speaker_id")))
                .setAcceptedByModerator(Boolean.parseBoolean(request.getParameter("moderator")))
                .setAcceptedBySpeaker(Boolean.parseBoolean(request.getParameter("speaker")))
                .setSortField(request.getParameter("sort"))
                .setOrder(request.getParameter("order"))
                .setLimits(request.getParameter("page"), request.getParameter("records"));
    }
}
