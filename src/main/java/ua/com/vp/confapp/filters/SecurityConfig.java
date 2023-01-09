package ua.com.vp.confapp.filters;

import java.util.*;

public class SecurityConfig {

    public static final String ROLE_GUEST = "guest";
    public static final String ROLE_ATTENDEE = "attendee";
    public static final String ROLE_SPEAKER = "speaker";
    public static final String ROLE_MODERATOR = "moderator";
    public static final String ROLE_ADMIN = "admin";

    // String: Role
    // List<String>: urlPatterns.
    private static final Map<String, List<String>> mapConfig = new HashMap<>();

    static {
        init();
    }

    private static void init() {

        // Configuration of "ATTENDEE".
        List<String> attendeeCommands = new ArrayList<String>();

        attendeeCommands.add("/userInfo");
        attendeeCommands.add("/employeeTask");

        mapConfig.put(ROLE_ATTENDEE, attendeeCommands);

        // Configuration of "ADMIN".
        List<String> moderatorCommands = new ArrayList<String>();

        moderatorCommands.add("/userInfo");
        moderatorCommands.add("/managerTask");

        mapConfig.put(ROLE_MODERATOR, moderatorCommands);
    }

    public static Set<String> getAllAppRoles() {
        return mapConfig.keySet();
    }

    public static List<String> getUrlPatternsForRole(String role) {
        return mapConfig.get(role);
    }
}
