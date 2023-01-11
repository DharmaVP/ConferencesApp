package ua.com.vp.confapp.command;

import jakarta.servlet.http.HttpServletRequest;
import ua.com.vp.confapp.command.constants.CommandType;
import ua.com.vp.confapp.exception.CommandException;

import java.util.Optional;
import java.util.stream.Stream;

import static ua.com.vp.confapp.command.constants.Parameters.ACTION;
import static ua.com.vp.confapp.command.constants.Parameters.CONTROLLER;

public class CommandUtil {

    public static String getCommandToRedirect(CommandType commandType){
        return "/" + CONTROLLER + "?" + ACTION + "=" + commandType.getCommandName();
    }

    /**
     * Of command type.
     *
     * @param command the command
     * @return the command type
     */
    public static CommandType getCommandType(String command) throws CommandException {
        return Stream.of(CommandType.values())
                .filter(c -> c.getCommandName().equalsIgnoreCase(command))
                .findFirst().orElseThrow(CommandException::new);
    }

    public static CommandType getCommandType(HttpServletRequest request) throws CommandException {
        CommandType commandType = null;
        if (hasCommand(request)) {
            commandType = getCommandType(request.getParameter(ACTION));
        }
        return commandType;
    }


    private static boolean hasCommand(HttpServletRequest request){
        return request.getParameter(ACTION) != null;
    }

}
