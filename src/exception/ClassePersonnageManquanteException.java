package exception;

/**
 * Created by Jean-Baptiste Louvet on 03/12/14.
 * Classe d'exception utilisée dans les ajouts de personnages pour s'assurer de la validité de la classe demandée
 */
public class ClassePersonnageManquanteException extends Exception{
    public ClassePersonnageManquanteException() {
    }

    public ClassePersonnageManquanteException(String message) {
        super(message);
    }

    public ClassePersonnageManquanteException(String message, Throwable cause) {
        super(message, cause);
    }

    public ClassePersonnageManquanteException(Throwable cause) {
        super(cause);
    }

    public ClassePersonnageManquanteException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
