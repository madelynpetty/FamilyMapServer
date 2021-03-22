package Services.Result;

import Models.Event;

import java.util.ArrayList;

/**
 * The response based on the success of the service
 */
public class EventListResult {
    private ArrayList<Event> data = new ArrayList<>();
    private boolean success = false;

    public EventListResult(ArrayList<Event> data, boolean success) {
        this.data = data;
        this.success = success;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof EventListResult) {
            EventListResult eventListResult = (EventListResult) o;
            return eventListResult.data.equals(data) &&
                    eventListResult.success == (success);
        } else {
            return false;
        }
    }
}