package Services.Request;

import Models.Person;
import Models.User;
import Models.Event;
import Utils.LoadDataUtils;

import java.util.ArrayList;

/**
 * Takes the request and turns it into a Java object
 */
public class LoadRequest {
    private ArrayList<User> users = LoadDataUtils.getUserArray();
    private ArrayList<Person> persons = LoadDataUtils.getPersonArray();
    private ArrayList<Event> events = LoadDataUtils.getEventArray();

    /**
     * @return Returns user array from LoadData.json
     */
    public ArrayList<User> getUsers() {
        return users;
    }

    /**
     * Sets User Array
     * @param users
     */
    public void setUsers(ArrayList<User> users) {
        this.users = users;
    }

    /**
     * Returns the person array from LoadData.json
     * @return
     */
    public ArrayList<Person> getPersons() {
        return persons;
    }

    /**
     * Sets Person Array
     * @param persons
     */
    public void setPersons(ArrayList<Person> persons) {
        this.persons = persons;
    }

    /**
     * Returns the Events array from LoadData.json
     * @return
     */
    public ArrayList<Event> getEvents() {
        return events;
    }

    /**
     * Sets the events Array
     * @param events
     * @return
     */
    public void setEvents(ArrayList<Event> events) {
        this.events = events;
    }
}
