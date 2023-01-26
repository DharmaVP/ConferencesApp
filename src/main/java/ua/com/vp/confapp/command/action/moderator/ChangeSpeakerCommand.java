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

import static ua.com.vp.confapp.command.constants.CommandType.EDIT_REPORT;
import static ua.com.vp.confapp.command.constants.CommandType.GET_ALL_REPORTS;
import static ua.com.vp.confapp.command.constants.Parameters.ERROR;

public class ChangeSpeakerCommand implements Command {
    private ReportService reportService;

    public ChangeSpeakerCommand() {
        reportService = ServiceFactory.getInstance().getReportService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        Long reportId = Long.parseLong(request.getParameter("report_id"));
        String speaker = request.getParameter("speaker_id");
        Long speakerId;
        if (!speaker.isEmpty()) {
            speakerId = Long.parseLong(speaker);
        } else {
            speakerId = null;
        }

        String page = CommandUtil.getCommandToRedirect(GET_ALL_REPORTS);

        try {
            ReportDTO report = reportService.getById(reportId);
            report.setSpeakerId(speakerId);
            reportService.update(report);
            request.getSession().setAttribute("success", "the speaker was changed");
        } catch (ServiceException e) {
            page = CommandUtil.getCommandToRedirect(EDIT_REPORT)+"&report_id="+reportId;
            request.getSession().setAttribute(ERROR, "Couldn't change speaker");
            return new CommandResult(page, true);
        }
        return new CommandResult(page, true);
    }
}
