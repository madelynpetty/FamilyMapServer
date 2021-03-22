package Services.Result;

import Services.Request.FillRequest;

/**
 * The response based on the success of the service
 */
public class FillResult {

    private String message;
    private boolean success = false;

    public FillResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof FillResult) {
            FillResult fillResult = (FillResult) o;
            return fillResult.message.equals(message) &&
                    fillResult.success == (success);
        } else {
            return false;
        }
    }
}
