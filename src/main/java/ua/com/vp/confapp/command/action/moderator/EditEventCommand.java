package ua.com.vp.confapp.command.action.moderator;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.exception.ValidationException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.utils.Validator;

import java.time.LocalDateTime;

import static ua.com.vp.confapp.command.constants.CommandType.GET_EVENT_TO_EDIT;
import static ua.com.vp.confapp.command.constants.Parameters.*;
import static ua.com.vp.confapp.command.constants.WebPages.ADD_EVENT_PAGE;
import static ua.com.vp.confapp.command.constants.WebPages.EDIT_EVENT_PAGE;

public class EditEventCommand implements Command {
    private EventService eventService;

    public EditEventCommand() {
        eventService = ServiceFactory.getInstance().getEventService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        EventDTO eventDTO = createEventDTO(request);
        String page = CommandUtil.getCommandToRedirect(GET_EVENT_TO_EDIT)+"&event_id="+eventDTO.getId();

        try {
            Validator.validateEventDate(request.getParameter(DATE_TIME));
            Validator.validateIntValue(request.getParameter(FLOOR));
            Validator.validateIntValue(request.getParameter(POSTAL_CODE));
            Validator.validateNonEmptyFields(
                    request.getParameter(BUILDING),
                    request.getParameter(STREET_NUMBER),
                    request.getParameter(STREET_NAME),
                    request.getParameter(CITY),
                    request.getParameter(POSTAL_CODE),
                    request.getParameter(COUNTRY));
        } catch (ValidationException e){
            request.getSession().setAttribute(ERROR, e.getMessage());
            return new CommandResult(page, true);
        }


        try {
            eventService.update(eventDTO);
            page = CommandUtil.getCommandToRedirect(GET_EVENT_TO_EDIT)+"&event_id="+eventDTO.getId();
            request.getSession().setAttribute("success", "success");
        } catch (ServiceException e) {
            request.getSession().setAttribute(ERROR, e.getMessage());
            return new CommandResult(page, true);
        }
        return new CommandResult(page, true);
    }

    private EventDTO createEventDTO(HttpServletRequest request) {
        LocalDateTime eventDateTime = LocalDateTime.parse(request.getParameter(DATE_TIME));

        return EventDTO.builder()
                .id(Long.parseLong(request.getParameter(EVENT_ID)))
                .name(request.getParameter(EVENT_NAME))
                .description(request.getParameter(DESCRIPTION))
                .placeId(Long.parseLong(request.getParameter(PLACE_ID)))
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
