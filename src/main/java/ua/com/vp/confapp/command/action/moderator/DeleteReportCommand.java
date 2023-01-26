package ua.com.vp.confapp.command.action.moderator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.ReportService;
import ua.com.vp.confapp.services.ServiceFactory;

import static ua.com.vp.confapp.command.constants.CommandType.GET_ALL_REPORTS;
import static ua.com.vp.confapp.command.constants.Parameters.ERROR;

public class DeleteReportCommand implements Command {
    private ReportService reportService;

    public DeleteReportCommand() {
        reportService = ServiceFactory.getInstance().getReportService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Long reportId = Long.parseLong(request.getParameter("report_id"));

        String page = CommandUtil.getCommandToRedirect(GET_ALL_REPORTS);

        try {
            ReportDTO report = reportService.getById(reportId);
            reportService.delete(report);
            request.getSession().setAttribute("success", "the report was deleted");
        } catch (ServiceException e) {
            request.getSession().setAttribute(ERROR, "Couldn't change role");
            return new CommandResult(page, true);
        }
        return new CommandResult(page, true);
    }
}
