package ua.com.vp.confapp.command.constants;

public interface WebPages {
    String DIRECTORY = "/WEB-INF";

    String GUEST_DIRECTORY = "/guest";
    String ATTENDEE_DIRECTORY = "/attendee";
    String SPEAKER_DIRECTORY = "/speaker";
    String MODERATOR_DIRECTORY = "/moderator";
    String ADMIN_DIRECTORY = "/admin";

    String HOME = "/index.jsp";
    String ERROR_PAGE = "/error.jsp";

    String SIGN_UP_PAGE = DIRECTORY + GUEST_DIRECTORY + "/sign_up.jsp";
    String SIGN_IN_PAGE = DIRECTORY + GUEST_DIRECTORY + "/sign_in.jsp";
    String ALL_EVENTS_PAGE = DIRECTORY + GUEST_DIRECTORY + "/events.jsp";

    String MY_CABINET = DIRECTORY + ATTENDEE_DIRECTORY + "/cabinet.jsp";
    String PROFILE = DIRECTORY + ATTENDEE_DIRECTORY +  "/profile.jsp";
    String EVENT_PAGE = DIRECTORY + ATTENDEE_DIRECTORY +  "/event.jsp";
    String USER_EVENTS_PAGE = DIRECTORY + ATTENDEE_DIRECTORY +  "/user_events.jsp";



    String EVENTS_MANAGEMENT = DIRECTORY + MODERATOR_DIRECTORY + "/events_management.jsp";
    String ADD_EVENT_PAGE = DIRECTORY + MODERATOR_DIRECTORY + "/add_event.jsp";
    String EDIT_EVENT_PAGE = DIRECTORY + MODERATOR_DIRECTORY + "/edit_event.jsp";
    String USERS_MANAGEMENT = DIRECTORY + MODERATOR_DIRECTORY + "/users_management.jsp";
    String VIEW_USER = DIRECTORY + MODERATOR_DIRECTORY + "/user_info.jsp";



}
