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
        String page = CommandUtil.getCommandToRedirect(CommandType.GET_ALL_EVENTS);

        EventDTO eventDTO = (EventDTO) request.getAttribute("event");
        try {
            eventService.delete(eventDTO);
            request.getSession().setAttribute("success", "event "+ eventDTO.getName() + "has been removed");
        } catch (ServiceException e) {
            page = EDIT_EVENT_PAGE;
            request.getSession().setAttribute(ERROR, "couldn't remove event");
            return new CommandResult(page);
        }
        return new CommandResult(page, true);
    }
}
