package Services.Result;

import Models.Event;

import java.util.ArrayList;

/**
 * The response based on the success of the service
 */
public class EventListResult {
    private String message;
    private ArrayList<Event> events = new ArrayList<>();
    private boolean success = false;

    public EventListResult(String message, boolean success) {
        this.message = message;
        this.success = success;
    }

    public EventListResult(ArrayList<Event> events, boolean success) {
        this.events = events;
        this.success = success;
    }

    public String getMessage() {
        return message;
    }


    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof EventListResult) {
            EventListResult eventListResult = (EventListResult) o;
            return eventListResult.events.equals(events) &&
                    eventListResult.success == (success);
        } else {
            return false;
        }
    }
}