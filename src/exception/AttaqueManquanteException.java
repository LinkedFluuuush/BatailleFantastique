package exception;

public class AttaqueManquanteException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 929163054229792837L;

    public AttaqueManquanteException() {
    }

    public AttaqueManquanteException(String message) {
        super(message);
    }

    public AttaqueManquanteException(String message, Throwable cause) {
        super(message, cause);
    }

    public AttaqueManquanteException(Throwable cause) {
        super(cause);
    }

    public AttaqueManquanteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
