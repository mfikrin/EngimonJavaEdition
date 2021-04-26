package Exception;

public class MovementException extends Exception {
    
    public MovementException(String errorString, Throwable eThrowable) {
        super(errorString, eThrowable);
    }
}
