package ua.com.vp.confapp.command.constants;

public interface WebPages {
    String DIRECTORY = "/WEB-INF";

    String GUEST_DIRECTORY = "/guest";
    String USER_DIRECTORY = "/user";
    String SPEAKER_DIRECTORY = "/speaker";
    String MODERATOR_DIRECTORY = "/moderator";
    String ADMIN_DIRECTORY = "/admin";

    String HOME = "/index.jsp";
    String ERROR_PAGE = "/error.jsp";

    String SIGN_UP_PAGE = DIRECTORY + GUEST_DIRECTORY + "/sign_up.jsp";
    String SIGN_IN_PAGE = DIRECTORY + GUEST_DIRECTORY + "/sign_in.jsp";
    String EVENTS = DIRECTORY + GUEST_DIRECTORY + "/events.jsp";

    String MY_CABINET = DIRECTORY + USER_DIRECTORY + "/cabinet.jsp";
    String PROFILE = DIRECTORY + USER_DIRECTORY +  "/profile.jsp";


}
