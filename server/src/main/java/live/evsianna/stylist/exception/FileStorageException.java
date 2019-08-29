package live.evsianna.stylist.exception;

/**
 * @author Rustam Mamedov
 */

public class FileStorageException extends RuntimeException {

    public FileStorageException(String message) {
        super(message);
    }

    public FileStorageException(String message, Throwable cause) {
        super(message, cause);
    }

}
