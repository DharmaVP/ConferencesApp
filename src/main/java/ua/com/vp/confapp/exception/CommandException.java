package ua.com.vp.confapp.exception;

public class CommandException extends Exception {

    public CommandException() {
        super("No such command exist");
    }

    public CommandException(String message) {
        super(message);
    }


    public CommandException(Throwable cause) {
        super(cause);
    }

    public CommandException(String message, Throwable cause) {
        super(message, cause);
    }
}
