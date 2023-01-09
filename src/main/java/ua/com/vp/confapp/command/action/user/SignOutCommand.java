package ua.com.vp.confapp.command.action.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import ua.com.vp.confapp.command.CommandResult;
import ua.com.vp.confapp.command.action.Command;

import static ua.com.vp.confapp.command.constants.Parameters.SESSION_USER;
import static ua.com.vp.confapp.command.constants.WebPages.HOME;

public class SignOutCommand implements Command {
    @Override
    public CommandResult execute(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        session.invalidate();
        return new CommandResult(HOME, true);
    }
}
