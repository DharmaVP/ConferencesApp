package ua.com.vp.confapp.command.action.moderator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.ReportService;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.services.UserService;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;
import ua.com.vp.confapp.utils.querybuilder.UserQueryBuilder;

import java.util.List;

import static ua.com.vp.confapp.command.constants.WebPages.*;

public class EditReportPageCommand implements Command {
    private ReportService reportService;
    private UserService userService;

    public EditReportPageCommand() {
        reportService = ServiceFactory.getInstance().getReportService();
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = EDIT_REPORT;

        Long reportId = Long.parseLong(request.getParameter("report_id"));

        QueryBuilder queryBuilder = createQueryBuilder(request);

        try {
            ReportDTO reportDTO = reportService.getById(reportId);
            request.setAttribute("report", reportDTO);
            List<UserDTO> speakers = userService.getAll(queryBuilder);
            request.setAttribute("speakerList", speakers);
        } catch (ServiceException e) {
            page = REPORTS_MANAGEMENT;
            return new CommandResult(page);
        }
        return new CommandResult(page);
    }

    private QueryBuilder createQueryBuilder(HttpServletRequest request) {
        return new UserQueryBuilder().setRoleFilter("speaker").showAllRows(true);
    }
}
