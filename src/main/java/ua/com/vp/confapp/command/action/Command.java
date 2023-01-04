package ua.com.vp.confapp.command.action;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.exception.CommandException;
import ua.com.vp.confapp.exception.ServiceException;
import ua.com.vp.confapp.exception.ValidationException;

public interface Command {
        CommandResult execute(HttpServletRequest request, HttpServletResponse response) throws ServiceException;
}
