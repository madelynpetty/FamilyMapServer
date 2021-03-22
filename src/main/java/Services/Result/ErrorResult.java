package Services.Result;

public class ErrorResult extends Throwable {
    private String message;
    private boolean success;

    public ErrorResult(String message) {
        this.message = message;
        success = false;
    }
}
