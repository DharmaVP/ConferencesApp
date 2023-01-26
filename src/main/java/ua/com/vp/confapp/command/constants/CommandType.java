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

    REMOVE_EVENT("remove_event", POST),

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

    ADD_REPORT("add_report", POST),

    VIEW_EVENT("view_event", GET),

    VIEW_USER("view_user", GET),

    CHANGE_ROLE("change_role", POST),

    MANAGE_EVENTS("manage_events", GET),

    GET_EVENT_TO_EDIT("get_event_to_edit", GET),

    ADD_REPORT_PAGE("add_report_page", GET),

    SET_VISITORS("set_visitors", POST),

    GET_ALL_REPORTS("get_all_reports", GET),

    APPROVE_SPEAKER("approve_speaker", POST),

    DELETE_REPORT("delete_report", POST),

    EDIT_REPORT("edit_report_page", GET),

    CHANGE_SPEAKER("change_speaker", POST),

    VIEW_SPEAKER_REPORTS("view_speaker_reports", GET),

    ACCEPT_REPORT("accept_to_speak", POST),

    PROPOSE_SPEAKER("propose_speaker", POST),

    DROP_REPORT("drop_proposed_report", POST),

    PROPOSE_REPORT("propose_report", POST),

    VIEW_EVENTS_TO_SPEAK("view_events_to_speak", GET),

    VIEW_EVENT_TO_SPEAK("view_event_to_speak", GET),

    VIEW_REPORT("view_report", GET);


    private final String commandName;

    private final RequestType requestType;

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
