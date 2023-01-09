package ua.com.vp.confapp.command.action.guest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.services.ServiceFactory;

import java.util.List;

import static ua.com.vp.confapp.command.constants.WebPages.*;

public class AllEventsCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(AllEventsCommand.class);
    EventService eventService;

    public AllEventsCommand() {
        eventService = ServiceFactory.getInstance().getEventService();
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = EVENTS;
        List<EventDTO> events;
        try {
            events = eventService.getAll();
        } catch (ServiceException e) {
            page = ERROR_PAGE;
            return new CommandResult(page);
        }
        request.setAttribute("eventList", events);

        return new CommandResult(page);
    }
}
