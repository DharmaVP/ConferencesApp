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
import ua.com.vp.confapp.utils.PaginatorUtil;
import ua.com.vp.confapp.utils.querybuilder.EventQueryBuilder;
import ua.com.vp.confapp.utils.querybuilder.QueryBuilder;

import java.util.List;

import static ua.com.vp.confapp.command.constants.WebPages.*;

public class ViewAllEventsCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(ViewAllEventsCommand.class);
    private EventService eventService;

    public ViewAllEventsCommand() {
        eventService = ServiceFactory.getInstance().getEventService();
    }


    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        String page = ALL_EVENTS_PAGE;
        List<EventDTO> events;

        QueryBuilder queryBuilder = createQueryBuilder(request);


//        int pageNumber = 1;
//        int recordsPerPage = Integer.parseInt(request.getParameter("recordsPerPage"));
//        if (request.getParameter("page") != null)
//            pageNumber = Integer.parseInt(
//                    request.getParameter("page"));
//        EmployeeDAO dao = new EmployeeDAO();
//        List<Employee> list = dao.viewAllEmployees(
//                (page - 1) * recordsPerPage,
//                recordsPerPage);
//        int noOfRecords = dao.getNoOfRecords();


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
        return new EventQueryBuilder()
                .setDateTypeFilter(request.getParameter("date_type"))
                .setSearchedEvent(request.getParameter("search"))
                .setDateFilter(request.getParameter("date_from"), request.getParameter("date_to"))
                .setSortField(request.getParameter("sort"))
                .setOrder(request.getParameter("order"))
                .setLimits(request.getParameter("page"), request.getParameter("records"));
    }
}
