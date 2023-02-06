package ua.com.vp.confapp.filters;

import ua.com.vp.confapp.command.constants.CommandType;

import java.util.*;

import static ua.com.vp.confapp.command.constants.CommandType.*;
import static ua.com.vp.confapp.command.constants.Parameters.*;

public class SecurityConfig {

    private static final Map<String, EnumSet<CommandType>> mapConfig = new HashMap<>();
    private static final EnumSet<CommandType> attendeeCommands;
    private static final EnumSet<CommandType> speakerCommands;
    private static final EnumSet<CommandType> moderatorCommands;
    private static final EnumSet<CommandType> adminCommands;


    static {
        attendeeCommands = EnumSet.of(
                ENTER_CABINET,
                GET_PROFILE,
                EDIT_USER,
                VIEW_EVENT,
                REGISTER_FOR_EVENT,
                CANCEL_REGISTRATION,
                USER_EVENTS,
                SIGN_OUT
        );

        mapConfig.put(ROLE_ATTENDEE, attendeeCommands);
    }

    static {
        speakerCommands = EnumSet.of(
                VIEW_EVENTS_TO_SPEAK,
                VIEW_EVENT_TO_SPEAK,
                PROPOSE_REPORT,
                PROPOSE_SPEAKER,
                ACCEPT_REPORT,
                DROP_REPORT,
                VIEW_REPORT,
                VIEW_SPEAKER_REPORTS
        );
        speakerCommands.addAll(attendeeCommands);

        mapConfig.put(ROLE_SPEAKER, speakerCommands);
    }

    static {
        moderatorCommands = EnumSet.of(
                MANAGE_EVENTS,
                ADD_EVENT,
                ADD_EVENT_PAGE,
                REMOVE_EVENT,
                GET_EVENT_TO_EDIT,
                EDIT_EVENT_PAGE,
                EDIT_EVENT,
                SET_VISITORS,

                ADD_REPORT_PAGE,
                ADD_REPORT,
                EDIT_REPORT,
                DELETE_REPORT,
                GET_ALL_REPORTS,
                APPROVE_SPEAKER,
                CHANGE_SPEAKER,

                GET_ALL_USERS,
                VIEW_USER,
                CHANGE_ROLE
        );
        moderatorCommands.addAll(attendeeCommands);

        mapConfig.put(ROLE_MODERATOR, moderatorCommands);
    }

    static {
        adminCommands = EnumSet.copyOf(moderatorCommands);
        mapConfig.put(ROLE_ADMIN, adminCommands);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static Set<CommandType> getCommandsForRole(String role) {
          return mapConfig.get(role);
     }
}
