package Exception;

public class ZeroQuantityException extends Exception {
    
    public ZeroQuantityException(String eString, Throwable eThrowable) {
        super(eString,eThrowable);
    }
}
