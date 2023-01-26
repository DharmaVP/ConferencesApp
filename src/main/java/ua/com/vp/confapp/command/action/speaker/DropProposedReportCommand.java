package ua.com.vp.confapp.command.action.speaker;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.entities.Report;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.ReportService;
import ua.com.vp.confapp.services.ServiceFactory;

import static ua.com.vp.confapp.command.constants.CommandType.VIEW_SPEAKER_REPORTS;
import static ua.com.vp.confapp.command.constants.Parameters.ERROR;
import static ua.com.vp.confapp.command.constants.Parameters.SESSION_USER;

public class DropProposedReportCommand implements Command {
    private ReportService reportService;

    public DropProposedReportCommand() {
        reportService = ServiceFactory.getInstance().getReportService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Long reportId = Long.parseLong(request.getParameter("report_id"));

        String page = CommandUtil.getCommandToRedirect(VIEW_SPEAKER_REPORTS)+"&speaker_id=true&moderator=false&speaker=false";

        try {
            ReportDTO reportDTO = reportService.getById(reportId);
            reportService.delete(reportDTO);
            request.getSession().setAttribute("success", "You've dropped your report");
        } catch (ServiceException e) {
            request.getSession().setAttribute(ERROR, "Something went wrong");
            return new CommandResult(page, true);
        }
        return new CommandResult(page, true);
    }
}
