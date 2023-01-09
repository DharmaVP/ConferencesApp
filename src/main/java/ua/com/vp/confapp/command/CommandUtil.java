package ua.com.vp.confapp.command;

import ua.com.vp.confapp.command.constants.CommandType;

import static ua.com.vp.confapp.command.constants.Parameters.ACTION;
import static ua.com.vp.confapp.command.constants.Parameters.CONTROLLER;

public class CommandUtil {

    public static String getCommandToRedirect(CommandType commandType){
        return "/" + CONTROLLER + "?" + ACTION + "=" + commandType.getCommandName();
    }
}
