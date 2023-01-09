package ua.com.vp.confapp.command.action.guest;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.exception.ServiceException;
import static ua.com.vp.confapp.command.constants.WebPages.SIGN_UP_PAGE;

public class SignUpPageCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return new CommandResult(SIGN_UP_PAGE);
    }
}
