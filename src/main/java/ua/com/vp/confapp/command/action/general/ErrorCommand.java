package ua.com.vp.confapp.command.action.general;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;
import ua.com.vp.confapp.exception.ServiceException;

import static ua.com.vp.confapp.command.constants.WebPages.ERROR_PAGE;

public class ErrorCommand implements Command {

    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException {
        return new CommandResult(ERROR_PAGE);
    }
}
