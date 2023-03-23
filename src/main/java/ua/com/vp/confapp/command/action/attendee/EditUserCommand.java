package ua.com.vp.confapp.command.action.attendee;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.command.action.guest.SignUpCommand;
import ua.com.vp.confapp.command.constants.CommandType;
import ua.com.vp.confapp.dto.UserDTO;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.exception.ValidationException;
import ua.com.vp.confapp.services.ServiceFactory;
import ua.com.vp.confapp.services.UserService;

import static ua.com.vp.confapp.command.constants.Parameters.SESSION_USER;
import static ua.com.vp.confapp.command.constants.WebPages.PROFILE;

public class EditUserCommand implements Command {
    private static Logger LOGGER = LogManager.getLogger(SignUpCommand.class);
    private UserService userService;

    public EditUserCommand() {
        userService = ServiceFactory.getInstance().getUserService();
    }

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {

        UserDTO userDTO = getUserDTO(request);
        String page = CommandUtil.getCommandToRedirect(CommandType.GET_PROFILE);

        try {
            userService.update(userDTO);
        } catch (ServiceException e) {
            request.setAttribute("error", "unable to update info");
            return new CommandResult(PROFILE);
        }
        return new CommandResult(page, true);
    }

    private UserDTO getUserDTO(HttpServletRequest request) {

        String prefix = request.getParameter("prefix");
        String firstName = request.getParameter("first_name");
        String lastName = request.getParameter("last_name");
        String phoneNumber = preparePhoneNumber(request.getParameter("cell_phone"));
        String jobTitle = request.getParameter("job_title");
        String organisation = request.getParameter("organisation");


        UserDTO userDTO = (UserDTO) request.getSession().getAttribute(SESSION_USER);
        userDTO.setPrefix(prefix);
        userDTO.setFirstName(firstName);
        userDTO.setLastName(lastName);
        userDTO.setPhoneNumber(phoneNumber);
        userDTO.setJobTitle(jobTitle);
        userDTO.setOrganisation(organisation);
        return userDTO;
    }

    private String preparePhoneNumber(String cellPhone) {
        if (cellPhone != null){
            cellPhone = cellPhone.replaceAll("[^0-9.]", "");
        }
        return cellPhone;
    }
}
