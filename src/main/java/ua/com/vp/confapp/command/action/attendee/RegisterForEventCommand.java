package ua.com.vp.confapp.command.action.attendee;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.command.constants.CommandType;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.exception.ValidationException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.services.ServiceFactory;

import static ua.com.vp.confapp.command.constants.CommandType.VIEW_EVENT;
import static ua.com.vp.confapp.command.constants.Parameters.*;
import static ua.com.vp.confapp.command.constants.WebPages.ALL_EVENTS_PAGE;
import static ua.com.vp.confapp.command.constants.WebPages.SIGN_IN_PAGE;
import static ua.com.vp.confapp.utils.Validator.validateDate;

public class RegisterForEventCommand implements Command{
    private static Logger LOGGER = LogManager.getLogger(RegisterForEventCommand.class);
    private EventService eventService;

    public RegisterForEventCommand() {
        eventService = ServiceFactory.getInstance().getEventService();
    }
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {


        UserDTO user = (UserDTO) request.getSession().getAttribute(SESSION_USER);

        Long userId = user.getId();
        Long eventId = Long.parseLong(request.getParameter("event_id"));

        String page = CommandUtil.getCommandToRedirect(VIEW_EVENT)+"&event_id="+eventId;

        try {
            EventDTO eventDTO = eventService.getById(eventId);
            validateDate(eventDTO);
            eventService.registerForEvent(userId, eventId);
        } catch (ValidationException e) {
            request.getSession().setAttribute(ERROR, "couldn't register for event in the past");
            return new CommandResult(page, true);
        } catch (ServiceException e){
            request.getSession().setAttribute(ERROR, "couldn't register for event");
            return new CommandResult(page, true);
        }

        request.getSession().setAttribute("success", SUCCEED_REGISTER);
        return new CommandResult(page, true);
    }
}
