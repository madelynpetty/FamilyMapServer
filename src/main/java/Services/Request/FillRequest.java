package Services.Request;

/**
 * Takes the request and turns it into a Java object
 */
public class FillRequest {
    private int generations = 4; //non negative integer
    private String username;
    private boolean success = false;

    public FillRequest(String username, int generations) {
        this.username = username;
        this.generations = generations;
    }

    public int getGenerations() {
        return generations;
    }

    public void setGenerations(int generations) {
        this.generations = generations;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
