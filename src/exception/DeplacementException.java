package exception;

public class DeplacementException extends Exception {
    public DeplacementException() {
    }

    public DeplacementException(String message) {
        super(message);
    }

    public DeplacementException(String message, Throwable cause) {
        super(message, cause);
    }

    public DeplacementException(Throwable cause) {
        super(cause);
    }

    public DeplacementException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
