package ua.com.vp.confapp.command;


import jakarta.servlet.http.HttpServletRequest;
import ua.com.vp.confapp.command.action.*;
import ua.com.vp.confapp.command.action.guest.*;
import ua.com.vp.confapp.command.action.attendee.*;
import ua.com.vp.confapp.command.action.moderator.*;
import ua.com.vp.confapp.command.constants.CommandType;
import ua.com.vp.confapp.exception.CommandException;

import java.util.EnumMap;
import java.util.Map;

import static ua.com.vp.confapp.command.constants.CommandType.*;
import static ua.com.vp.confapp.command.constants.Parameters.ACTION;

public class CommandFactory {

    private static Map<CommandType, Command> commands = new EnumMap<>(CommandType.class);
    private static volatile CommandFactory instance;

    static {
        commands.put(SIGN_IN, new SignInCommand());
        commands.put(SIGN_UP, new SignUpCommand());
        commands.put(SIGN_OUT, new SignOutCommand());
        commands.put(GET_SIGN_IN_PAGE, new SignInPageCommand());
        commands.put(GET_SIGN_UP_PAGE, new SignUpPageCommand());
        commands.put(GET_ALL_EVENTS, new ViewAllEventsCommand());
        commands.put(REGISTER_FOR_EVENT, new RegisterForEventCommand());
        commands.put(ENTER_CABINET, new EnterCabinetCommand());
        commands.put(GET_PROFILE, new GetProfileCommand());
        commands.put(EDIT_USER, new EditUserCommand());

        commands.put(ADD_EVENT, new AddEventCommand());
        commands.put(VIEW_EVENT, new ViewEventCommand());
        commands.put(CANCEL_REGISTRATION, new CancelRegistrationCommand());
        commands.put(USER_EVENTS, new ViewUserEventsCommand());
        commands.put(GET_ALL_USERS, new GetAllUsersCommand());

        commands.put(VIEW_USER, new ViewUserCommand());
        commands.put(CHANGE_ROLE, new ChangeRoleCommand());
        commands.put(MANAGE_EVENTS, new ManageEventsCommand());
        commands.put(EDIT_EVENT_PAGE, new EditEventPageCommand());
        commands.put(ADD_EVENT_PAGE, new AddEventPageCommand());

//        commands.put(REMOVE_EVENT, new RemoveEventCommand());
//        commands.put(EDIT_EVENT, new EditEventCommand());

//        commands.put(PROFILE, new ProfileCommand());

//        commands.put(EDIT_USER_INFO, new EditUserInfoCommand());
//        commands.put(EDIT_USER_PHOTO, new EditUserPhotoCommand());
//        commands.put(DELETE_USER, new DeleteUserCommand());


//        commands.put(HOME_PAGE, new HomePageCommand());


//        commands.put(START_PAGE, new HomePageCommand());

//        commands.put(ADD_ROLE, new AddRoleCommand());
//        commands.put(ADD_ROLE_PAGE, new AddRolePageCommand());
//        commands.put(ADD_THEME, new AddThemeCommand());
//        commands.put(ADD_THEME_PAGE, new AddThemePageCommand());
//        commands.put(ADD_PERMISSION, new AddPermissionCommand());
//        commands.put(ADD_PERMISSION_PAGE, new AddPermissionPageCommand());
//        commands.put(CHANGE_USER_PERMISSION, new ChangeUserPermissionCommand());
//        commands.put(GET_USERS_ON_EVENT, new UsersOnEventCommand());

    }


    private CommandFactory() {
    }


    public static CommandFactory getInstance() {
        if (instance == null) {
            synchronized (CommandFactory.class) {
                if (instance == null) {
                    instance = new CommandFactory();
                }
            }
        }
        return instance;
    }


    public Command getCommand(HttpServletRequest request) throws CommandException {
        String command = request.getParameter(ACTION);
        CommandType commandType = CommandUtil.getCommandType(command);

        if (!commandType.getRequestType().name().equals(request.getMethod())) {
            throw new CommandException("Invalid type of request!");
        }
        return commands.get(commandType);
    }
}
