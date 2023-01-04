package ua.com.vp.confapp.exception;

public class NoSuchEntityException extends DAOException {
    private static final long serialVersionUID = 1L;

    public NoSuchEntityException(){
        super("no entity");
    };
    /**
     * Constructs a DAOException with the given detail message.
     * @param message The detail message of the DAOException.
     */
    public NoSuchEntityException(String message) {
        super(message);
    }

    /**
     * Constructs a DAOException with the given root cause.
     * @param cause The root cause of the DAOException.
     */
    public NoSuchEntityException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructs a DAOException with the given detail message and root cause.
     * @param message The detail message of the DAOException.
     * @param cause The root cause of the DAOException.
     */
    public NoSuchEntityException(String message, Throwable cause) {
        super(message, cause);
    }
}
