package Exception;

public class InsufficientLevelException extends Exception {

    public InsufficientLevelException(String esString, Throwable eThrowable) {
        super(esString, eThrowable);
    }
}
