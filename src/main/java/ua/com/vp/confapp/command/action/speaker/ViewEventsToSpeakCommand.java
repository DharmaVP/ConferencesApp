package ua.com.vp.confapp.command.action.speaker;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.dto.EventDTO;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.services.EventService;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.utils.PaginatorUtil;
import ua.com.vp.confapp.utils.querybuilder.EventQueryBuilder;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.util.List;

import static ua.com.vp.confapp.command.constants.Parameters.SESSION_USER;
import static ua.com.vp.confapp.command.constants.WebPages.*;

public class ViewEventsToSpeakCommand implements Command {
    private EventService eventService;

    public ViewEventsToSpeakCommand() {
        eventService = ServiceFactory.getInstance().getEventService();
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = EVENTS_TO_SPEAK;
        List<EventDTO> events;

        QueryBuilder queryBuilder = createQueryBuilder(request);
        try {
            events = eventService.getAll(queryBuilder);
            request.setAttribute("eventList", events);
            int numberOfRecords = eventService.getNumberOfRecords(queryBuilder);
            PaginatorUtil.paginate(numberOfRecords, request);
        } catch (ServiceException e) {
            page = ERROR_PAGE;
            return new CommandResult(page);
        }
        return new CommandResult(page);
    }

    private QueryBuilder createQueryBuilder(HttpServletRequest request) {
        UserDTO speaker = (UserDTO) request.getSession().getAttribute(SESSION_USER);
        boolean speakerFilter = Boolean.parseBoolean(request.getParameter("speaker_events"));

        return new EventQueryBuilder()
                .setSpeakerFilter(speakerFilter ? speaker.getId() : -1)
                .setAcceptedByModerator(speakerFilter)
                .setAcceptedBySpeaker(speakerFilter)
                .setDateTypeFilter("upcoming")
                .setSearchedEvent(request.getParameter("search"))
                .setDateFilter(request.getParameter("date_from"), request.getParameter("date_to"))
                .setSortField(request.getParameter("sort"))
                .setOrder(request.getParameter("order"))
                .setLimits(request.getParameter("page"), request.getParameter("records"));
    }
}
