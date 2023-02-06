package ua.com.vp.confapp.command.action.moderator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.command.constants.CommandType;
import ua.com.vp.confapp.dto.ReportDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.ReportService;
import ua.com.vp.confapp.services.ServiceFactory;

public class AddReportCommand implements Command {
    private ReportService reportService;

    public AddReportCommand() {
        reportService = ServiceFactory.getInstance().getReportService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        //Validators!!!

        ReportDTO reportDTO = ReportDTO.builder().build();
        Long eventId = Long.parseLong(request.getParameter("event_id"));
        String topic = request.getParameter("topic");
        String outline = request.getParameter("outline");
        String speaker = request.getParameter("speaker_id");
        Long speakerId = null;
        reportDTO.setAcceptedByModerator(false);
        if (!speaker.isEmpty()) {
            speakerId = Long.parseLong(speaker);
            reportDTO.setAcceptedByModerator(true);
        }
        reportDTO.setTopic(topic);
        reportDTO.setOutline(outline);
        reportDTO.setEventId(eventId);
        reportDTO.setSpeakerId(speakerId);
        reportDTO.setAcceptedBySpeaker(false);

        String page = CommandUtil.getCommandToRedirect(CommandType.GET_EVENT_TO_EDIT)+"&event_id="+eventId;

        try {
            reportService.create(reportDTO);
        } catch(ServiceException e){

        }
        return new CommandResult(page, true);
    }


}
