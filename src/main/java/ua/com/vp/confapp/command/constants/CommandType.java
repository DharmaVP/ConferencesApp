package ua.com.vp.confapp.command.constants;

import ua.com.vp.confapp.exception.CommandException;

import java.util.stream.Stream;

import static ua.com.vp.confapp.command.constants.RequestType.*;

public enum CommandType {

    GET_SIGN_IN_PAGE("get_sign_in_page", GET),

    SIGN_IN("sign-in", POST),

    GET_SIGN_UP_PAGE("get_sign_up_page", GET),

    SIGN_UP("sign-up", POST),

    SIGN_OUT("sign-out", GET),

    GET_ALL_EVENTS("get_all_events", GET),

    ENTER_CABINET("enter_cabinet", GET),

    GET_PROFILE("get_profile", GET),

    REGISTER_FOR_EVENT("register_for_event", POST),

    ADD_EVENT("add_event", POST),

    ADD_EVENT_PAGE("add_event_page", GET),

    REMOVE_EVENT("remove_event", GET),

    EDIT_EVENT("edit_event", POST),

    EDIT_EVENT_PAGE("edit_event_page", GET),

    EDIT_USER("edit_user", POST),

    EDIT_USER_INFO("edit_user_info", POST),

    EDIT_USER_PHOTO("edit_user_photo", POST),

    DELETE_USER("delete_user", POST),

    CANCEL_REGISTRATION("cancel_registration", POST),

    USER_EVENTS("user_events", GET),

    GET_USERS_ON_EVENT("get_users_on_event", GET),

    GET_ALL_USERS("get_all_users", GET),

    ADD_REPORT("add_theme", POST),

    VIEW_EVENT("view_event", GET),

    VIEW_USER("view_user", GET),

    CHANGE_ROLE("change_role", POST),

    MANAGE_EVENTS("manage_events", GET);



    private String commandName;

    private RequestType requestType;

    CommandType(String commandName, RequestType requestType) {
        this.commandName = commandName;
        this.requestType = requestType;
    }


    public String getCommandName() {
        return commandName;
    }

    /**
     * Get request type request type.
     *
     * @return the request type
     */
    public RequestType getRequestType() {
        return requestType;
    }


}
