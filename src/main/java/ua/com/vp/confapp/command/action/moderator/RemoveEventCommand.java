package ua.com.vp.confapp.command.action.moderator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.command.constants.CommandType;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.services.ServiceFactory;

import static ua.com.vp.confapp.command.constants.Parameters.ERROR;
import static ua.com.vp.confapp.command.constants.WebPages.EDIT_EVENT_PAGE;

public class RemoveEventCommand implements Command {
    private EventService eventService;

    public RemoveEventCommand() {
        eventService = ServiceFactory.getInstance().getEventService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = CommandUtil.getCommandToRedirect(CommandType.MANAGE_EVENTS);

        Long eventId = Long.parseLong(request.getParameter("event_id"));
        EventDTO eventDTO = EventDTO.builder().id(eventId).build();
        try {
            eventService.delete(eventDTO);
            request.getSession().setAttribute("success", "event has been removed");
        } catch (ServiceException e) {
            page = CommandUtil.getCommandToRedirect(CommandType.GET_EVENT_TO_EDIT)+"&event_id="+eventId;
            request.getSession().setAttribute(ERROR, "couldn't remove event");
            return new CommandResult(page, true);
        }
        return new CommandResult(page, true);
    }
}
