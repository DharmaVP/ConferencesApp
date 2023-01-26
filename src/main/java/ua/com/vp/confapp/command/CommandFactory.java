package ua.com.vp.confapp.command;


import jakarta.servlet.http.HttpServletRequest;
import ua.com.vp.confapp.command.action.*;
import ua.com.vp.confapp.command.action.guest.*;
import ua.com.vp.confapp.command.action.attendee.*;
import ua.com.vp.confapp.command.action.moderator.*;
import ua.com.vp.confapp.command.action.speaker.*;
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

        commands.put(GET_EVENT_TO_EDIT, new GetEventToEditCommand());
        commands.put(ADD_REPORT_PAGE, new AddReportPageCommand());
        commands.put(ADD_REPORT, new AddReportCommand());

        commands.put(REMOVE_EVENT, new RemoveEventCommand());
        commands.put(EDIT_EVENT, new EditEventCommand());
        commands.put(SET_VISITORS, new SetVisitorsCommand());

        commands.put(GET_ALL_REPORTS, new GetAllReportsCommand());
        commands.put(APPROVE_SPEAKER, new ApproveSpeakerCommand());
        commands.put(DELETE_REPORT, new DeleteReportCommand());
        commands.put(EDIT_REPORT, new EditReportPageCommand());
        commands.put(CHANGE_SPEAKER, new ChangeSpeakerCommand());
        commands.put(VIEW_SPEAKER_REPORTS, new ViewSpeakerReportsCommand());
        commands.put(ACCEPT_REPORT, new AcceptReportCommand());
        commands.put(PROPOSE_SPEAKER, new ProposeSpeakerCommand());
        commands.put(DROP_REPORT, new DropProposedReportCommand());
        commands.put(PROPOSE_REPORT, new ProposeReportCommand());
        commands.put(VIEW_EVENTS_TO_SPEAK, new ViewEventsToSpeakCommand());
        commands.put(VIEW_EVENT_TO_SPEAK, new ViewEventToSpeakCommand());
        commands.put(VIEW_REPORT, new ViewReportCommand());


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
