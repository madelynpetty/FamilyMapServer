package Services.Result;

/**
 * The response based on the success of the service
 */
public class LoadResult {
    private String message;
    private boolean success = false;

    public LoadResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }
}