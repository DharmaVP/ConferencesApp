package ua.com.vp.confapp.command.action.attendee;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.services.ServiceFactory;

import static ua.com.vp.confapp.command.constants.Parameters.*;
import static ua.com.vp.confapp.command.constants.WebPages.EVENTS;
import static ua.com.vp.confapp.command.constants.WebPages.SIGN_IN_PAGE;

public class RegisterForEventCommand implements Command{

    private static Logger LOGGER = LogManager.getLogger(RegisterForEventCommand.class);
    EventService eventService;

    public RegisterForEventCommand() {
        eventService = ServiceFactory.getInstance().getEventService();
    }
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        String page = EVENTS;

        UserDTO user = (UserDTO) request.getSession().getAttribute("user");
        if (user == null) {
            page = SIGN_IN_PAGE;
            request.setAttribute(ERROR, "first you need to sign in to register");
            return new CommandResult(page);
        }

        Long userId = user.getId();
        Long eventId = Long.parseLong(request.getParameter(EVENT_ID));

        try {
            eventService.registerForEvent(userId, eventId);
        } catch (ServiceException e){
            request.setAttribute(ERROR, "couldn't register for event");
            return new CommandResult(page);
        }

        request.getSession().setAttribute(SUCCEED_REGISTER, "successful registration!");
        return new CommandResult(page, true);
    }
}
