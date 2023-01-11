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
    String EVENTS = DIRECTORY + GUEST_DIRECTORY + "/events.jsp";

    String MY_CABINET = DIRECTORY + ATTENDEE_DIRECTORY + "/cabinet.jsp";
    String PROFILE = DIRECTORY + ATTENDEE_DIRECTORY +  "/profile.jsp";


}
