package ua.com.vp.confapp.command.action.speaker;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.command.constants.CommandType;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.ReportService;
import ua.com.vp.confapp.services.ServiceFactory;

import static ua.com.vp.confapp.command.constants.CommandType.GET_ALL_REPORTS;
import static ua.com.vp.confapp.command.constants.Parameters.ERROR;
import static ua.com.vp.confapp.command.constants.Parameters.SESSION_USER;

public class ProposeReportCommand implements Command {
    private ReportService reportService;

    public ProposeReportCommand() {
        reportService = ServiceFactory.getInstance().getReportService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        //Validate fields

        ReportDTO reportDTO = ReportDTO.builder().build();
        Long eventId = Long.parseLong(request.getParameter("event_id"));
        String topic = request.getParameter("topic");
        String outline = request.getParameter("outline");
        UserDTO speaker = (UserDTO) request.getSession().getAttribute(SESSION_USER);

        reportDTO.setTopic(topic);
        reportDTO.setOutline(outline);
        reportDTO.setEventId(eventId);
        reportDTO.setSpeakerId(speaker.getId());
        reportDTO.setAcceptedByModerator(false);
        reportDTO.setAcceptedBySpeaker(false);

        String page = CommandUtil.getCommandToRedirect(CommandType.VIEW_EVENT_TO_SPEAK)+"&event_id="+eventId;

        try {
            reportService.create(reportDTO);
        } catch(ServiceException e){

        }
        return new CommandResult(page, true);
    }


}
