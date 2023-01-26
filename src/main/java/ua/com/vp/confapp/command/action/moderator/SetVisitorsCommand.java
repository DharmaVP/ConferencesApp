package ua.com.vp.confapp.command.action.moderator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.command.constants.CommandType;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.exception.ValidationException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.utils.Validator;

import static ua.com.vp.confapp.command.constants.CommandType.GET_EVENT_TO_EDIT;
import static ua.com.vp.confapp.command.constants.Parameters.DATE_TIME;
import static ua.com.vp.confapp.command.constants.Parameters.ERROR;
import static ua.com.vp.confapp.command.constants.WebPages.EDIT_EVENT_PAGE;

public class SetVisitorsCommand implements Command {
    private EventService eventService;

    public SetVisitorsCommand() {
        eventService = ServiceFactory.getInstance().getEventService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        Long eventId = Long.parseLong(request.getParameter("event_id"));
        Long participants = Long.parseLong(request.getParameter("participants"));
        String visitors = request.getParameter("visitors");
        String page = CommandUtil.getCommandToRedirect(GET_EVENT_TO_EDIT) + "&event_id=" + eventId;


        if (!Validator.validateEventDate(request.getParameter(DATE_TIME))) {
            request.getSession().setAttribute(ERROR, "You can set visitors  only to past events");
            return new CommandResult(page, true);
        }

        Integer numberOfVisitors;

        try {
            Validator.validateIntValue(visitors);
            numberOfVisitors = Integer.parseInt(visitors);
            if (numberOfVisitors > participants)
                throw new ValidationException("The value can't be greater than participants");
        } catch (ValidationException e) {
            request.getSession().setAttribute(ERROR, e.getMessage());
            return new CommandResult(page, true);
        }



        try {
            eventService.setVisitors(eventId, numberOfVisitors);
            request.getSession().setAttribute("success", "visitors have been changed");
        } catch (ServiceException e) {
            request.getSession().setAttribute(ERROR, "something wrong, try again");
            return new CommandResult(page, true);
        }
        return new CommandResult(page, true);
    }
}
