package Exception;

public class InventoryFullException extends Exception {

    public InventoryFullException(String eString, Throwable eThrowable) {
        super(eString,eThrowable);
    }
}
