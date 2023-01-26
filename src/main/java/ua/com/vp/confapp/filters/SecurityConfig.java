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
                REGISTER_FOR_EVENT,
                VIEW_EVENT,
                USER_EVENTS
        );

        mapConfig.put(ROLE_ATTENDEE, attendeeCommands);
    }

    static {
        speakerCommands = EnumSet.of(
                ADD_REPORT
        );
        speakerCommands.addAll(attendeeCommands);

        mapConfig.put(ROLE_SPEAKER, speakerCommands);
    }

    static {
        moderatorCommands = EnumSet.of(
                ADD_EVENT,
                REMOVE_EVENT,
                EDIT_EVENT,
                GET_ALL_USERS
        );
        moderatorCommands.addAll(speakerCommands);

        mapConfig.put(ROLE_MODERATOR, moderatorCommands);
    }

    static {
       adminCommands = EnumSet.of(
                EDIT_USER_PHOTO
        );
        adminCommands.addAll(moderatorCommands);

        mapConfig.put(ROLE_ADMIN, adminCommands);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static Set<CommandType> getCommandsForRole(String role) {
          return mapConfig.get(role);
     }
}
