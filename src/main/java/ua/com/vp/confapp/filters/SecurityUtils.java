package ua.com.vp.confapp.filters;

import jakarta.servlet.http.HttpServletRequest;
import ua.com.vp.confapp.command.CommandUtil;
import ua.com.vp.confapp.command.constants.CommandType;
import ua.com.vp.confapp.exception.CommandException;

import java.util.Set;


public class SecurityUtils {

    // Проверить требует ли данный 'request' входа в систему или нет.
    public static boolean isSecurityCommand(HttpServletRequest request) throws CommandException {

        CommandType commandType = CommandUtil.getCommandType(request);
        Set<String> roles = SecurityConfig.getAllAppRoles();

        for (String role : roles) {
            Set<CommandType> commandTypes = SecurityConfig.getCommandsForRole(role);
            if (commandTypes != null && commandTypes.contains(commandType)) {
                return true;
            }
        }
        return false;
    }

    // Проверить имеет ли данный 'request' подходящую роль?
    public static boolean hasPermission(HttpServletRequest request) throws CommandException {

        CommandType commandType = CommandUtil.getCommandType(request);
        Set <String> allRoles = SecurityConfig.getAllAppRoles();

        for (String role : allRoles) {
            if (!request.isUserInRole(role)) {
                continue;
            }
            Set<CommandType> commandTypes = SecurityConfig.getCommandsForRole(role);
            if (commandTypes != null && commandTypes.contains(commandType)) {
                return true;
            }
        }
        return false;
    }
}
