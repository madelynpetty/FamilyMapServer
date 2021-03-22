package Utils;

import Models.Event;
import Models.Person;
import Models.User;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.io.FileReader;
import java.util.ArrayList;

public class LoadDataUtils {
    private static ArrayList<User> users = new ArrayList<>(); //2
    private static ArrayList<Person> persons = new ArrayList<>(); //11
    private static ArrayList<Event> events = new ArrayList<>(); //19

    public static ArrayList<User> getUserArray() {
        return users;
    }
    public static ArrayList<Person> getPersonArray() {
        return persons;
    }
    public static ArrayList<Event> getEventArray() {
        return events;
    }

    public static ArrayList<User> getMockUserArray() throws Exception {
        ArrayList<User> returnArrayList = new ArrayList<>();
        Object obj = new JsonParser().parse(new FileReader("/Users/maddie/IdeaProjects/FamilyMap/passoffFiles/LoadData.json"));
        try {
            JsonObject jo = (JsonObject) obj;
            JsonArray usersIterator = jo.get("users").getAsJsonArray();
            for (int i = 0; i < usersIterator.size(); i++) {
                JsonObject u = (JsonObject) usersIterator.get(i);
                String username = u.get("username").getAsString();
                String password = u.get("password").getAsString();
                String email = u.get("email").getAsString();
                String firstName = u.get("firstName").getAsString();
                String lastName = u.get("lastName").getAsString();
                String gender = u.get("gender").getAsString();
                String personID = u.get("personID").getAsString();

                User user = new User(username, password, email, firstName, lastName, gender, personID);
                returnArrayList.add(user);
            }
        }
        catch (Exception e) {
            throw new Exception("Error: Could not populate user array");
        }
        return returnArrayList;
    }

    public static ArrayList<Person> getMockPersonArray() throws Exception {
        ArrayList<Person> returnArrayList = new ArrayList<>();
        Object obj = new JsonParser().parse(new FileReader("/Users/maddie/IdeaProjects/FamilyMap/passoffFiles/LoadData.json"));
//        Object obj = new JsonParser().parse(json);
        try {
            JsonObject jo = (JsonObject) obj;
            JsonArray personIterator = jo.get("persons").getAsJsonArray();
            for (int i = 0; i < personIterator.size(); i++) {
                JsonObject p = (JsonObject) personIterator.get(i);
                String firstName = p.get("firstName").getAsString();
                String lastName = p.get("lastName").getAsString();
                String gender = p.get("gender").getAsString();
                String personID = p.get("personID").getAsString();
                String spouseID = p.get("spouseID").getAsString();
                String fatherID = p.get("fatherID").getAsString();
                String motherID = p.get("motherID").getAsString();
                String associatedUsername = p.get("associatedUsername").getAsString();

                Person person = new Person(personID, associatedUsername, firstName, lastName, gender, fatherID, motherID, spouseID);
                returnArrayList.add(person);
            }
        }
        catch (Exception e) {
            throw new Exception("Error: Could not populate person array");
        }
        return returnArrayList;
    }

    public static ArrayList<Event> getMockEventArray() throws Exception {
        ArrayList<Event> returnArrayList = new ArrayList<>();
        Object obj = new JsonParser().parse(new FileReader("/Users/maddie/IdeaProjects/FamilyMap/passoffFiles/LoadData.json"));
//        Object obj = new JsonParser().parse(json);
        try {
            JsonObject jo = (JsonObject) obj;
            JsonArray eventIterator = jo.get("events").getAsJsonArray();
            for (int i = 0; i < eventIterator.size(); i++) {
                JsonObject e = (JsonObject) eventIterator.get(i);

                String eventType = e.get("eventType").getAsString();
                String personID = e.get("personID").getAsString();
                String city = e.get("city").getAsString();
                String country = e.get("country").getAsString();
                float latitude = e.get("latitude").getAsFloat();
                float longitude = e.get("longitude").getAsFloat();
                int year = e.get("year").getAsInt();
                String eventID = e.get("eventID").getAsString();
                String associatedUsername = e.get("associatedUsername").getAsString();

                Event event = new Event(eventID, associatedUsername, personID, latitude, longitude, country, city, eventType, year);
                returnArrayList.add(event);
            }
        }
        catch (Exception e) {
            throw new Exception("Error: Could not populate event array");
        }
        return returnArrayList;
    }
}
