package ua.com.vp.confapp.command.constants;

public interface Parameters {
    //Names
    String CONTROLLER = "controller";
    String ACTION = "action";
    String EMAIL = "email";
    String PASSWORD = "password";
    String CONFIRM_PASSWORD = "confirm_password";
    String ERROR = "error";
    String INVALID_EMAIL = "invalid_email";
    String INVALID_PASSWORD = "invalid_password";
    String NO_MATCH = "no_match";
    String MESSAGE = "message";
    String SUCCEED = "succeed";
    String SESSION_USER = "user";
    String USER_ID = "user_id";
    String EVENT_ID = "event_id";


    //EventDTO
    String EVENT_NAME = "name";
    String DESCRIPTION = "description";
    String DATE_TIME = "date_time";
    String VISITORS = "no_of_visitors";

    String PLACE_ID = "place_id";
    String BUILDING = "building";
    String FLOOR = "floor";
    String STREET_NUMBER = "street_number";
    String STREET_NAME = "street_name";
    String CITY = "city";
    String POSTAL_CODE = "postal_code";
    String COUNTRY = "country";

    String ROLE_GUEST = "guest";
    String ROLE_ATTENDEE = "attendee";
    String ROLE_SPEAKER = "speaker";
    String ROLE_MODERATOR = "moderator";
    String ROLE_ADMIN = "admin";

    //Values
    String SUCCEED_REGISTER = "succeed.registered";
    String FINISH_REGISTRATION = "finish.registration";


}
