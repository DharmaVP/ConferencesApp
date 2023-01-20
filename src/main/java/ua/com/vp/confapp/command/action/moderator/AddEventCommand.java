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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import static ua.com.vp.confapp.command.constants.CommandType.EDIT_EVENT;
import static ua.com.vp.confapp.command.constants.CommandType.EDIT_EVENT_PAGE;
import static ua.com.vp.confapp.command.constants.Parameters.*;
import static ua.com.vp.confapp.command.constants.WebPages.ADD_EVENT_PAGE;


public class AddEventCommand implements Command {
    private EventService eventService;

    public AddEventCommand() {
        eventService = ServiceFactory.getInstance().getEventService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        String page;

        try {
            Validator.validateEventDate(request.getParameter(DATE_TIME));
            Validator.validateIntValue(request.getParameter(FLOOR));
            Validator.validateIntValue(request.getParameter(POSTAL_CODE));
            Validator.validateNonEmptyFields(request.getParameter(EVENT_NAME),
                    request.getParameter(BUILDING),
                    request.getParameter(STREET_NUMBER),
                    request.getParameter(STREET_NAME),
                    request.getParameter(CITY),
                    request.getParameter(POSTAL_CODE),
                    request.getParameter(COUNTRY));
        } catch (ValidationException e){
            page = ADD_EVENT_PAGE;
            return new CommandResult(page);
        }

        EventDTO eventDTO = createEventDTO(request);
        try {
            eventService.create(eventDTO);
            page = CommandUtil.getCommandToRedirect(EDIT_EVENT_PAGE)+"&event_id="+eventDTO.getId();
        } catch (ServiceException e) {
            page = ADD_EVENT_PAGE;
            return new CommandResult(page);
        }
        return new CommandResult(page, true);
    }

    private EventDTO createEventDTO(HttpServletRequest request) {
        LocalDateTime eventDateTime = LocalDateTime.parse(request.getParameter(DATE_TIME));

        return EventDTO.builder()
                .name(request.getParameter(EVENT_NAME))
                .description(request.getParameter(DESCRIPTION))
                .eventDateTime(eventDateTime)
                .building(request.getParameter(BUILDING))
                .floor(Integer.parseInt(request.getParameter(FLOOR)))
                .streetNumber(request.getParameter(STREET_NUMBER))
                .streetName(request.getParameter(STREET_NAME))
                .city(request.getParameter(CITY))
                .postalCode(Integer.parseInt(request.getParameter(POSTAL_CODE)))
                .country(request.getParameter(COUNTRY))
                .build();
    }
}
