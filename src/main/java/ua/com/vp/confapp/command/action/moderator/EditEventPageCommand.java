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
import static ua.com.vp.confapp.command.constants.WebPages.EVENTS_MANAGEMENT;

public class EditEventPageCommand implements Command {
    private EventService eventService;

    public EditEventPageCommand() {
        eventService = ServiceFactory.getInstance().getEventService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = EDIT_EVENT_PAGE;

        Long eventId = Long.parseLong(request.getParameter("event_id"));

        try {
            EventDTO eventDTO = eventService.getById(eventId);
            request.setAttribute("event", eventDTO);
        } catch (ServiceException e) {
            page = EVENTS_MANAGEMENT;
            return new CommandResult(page);
        }
        return new CommandResult(page);
    }
}
