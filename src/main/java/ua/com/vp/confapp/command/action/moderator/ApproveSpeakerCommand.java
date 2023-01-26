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
import ua.com.vp.confapp.services.UserService;

import static ua.com.vp.confapp.command.constants.CommandType.GET_ALL_REPORTS;
import static ua.com.vp.confapp.command.constants.CommandType.VIEW_USER;
import static ua.com.vp.confapp.command.constants.Parameters.ERROR;

public class ApproveSpeakerCommand implements Command {
    private ReportService reportService;

    public ApproveSpeakerCommand() {
        reportService = ServiceFactory.getInstance().getReportService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Long reportId = Long.parseLong(request.getParameter("report_id"));
        Boolean decision = Boolean.parseBoolean(request.getParameter("approve"));
        Boolean isProposedBySpeaker = Boolean.parseBoolean(request.getParameter("proposed"));

        String page = CommandUtil.getCommandToRedirect(GET_ALL_REPORTS);

        try {
            reportService.approve(reportId, decision, isProposedBySpeaker);
            request.getSession().setAttribute("success", "the speaker was" + (decision ? "approved": "rejected"));
        } catch (ServiceException e) {
            request.getSession().setAttribute(ERROR, "something went wrong");
            return new CommandResult(page, true);
        }
        return new CommandResult(page, true);
    }
}
