package Services.Result;

import Models.Person;

import java.util.ArrayList;

public class PersonListResult {
    private ArrayList<Person> persons = new ArrayList<>();
    private boolean success = false;
    private String message;

    /**
     * Constructor to use for /person success
     * @param persons
     */
    public PersonListResult(ArrayList<Person> persons) {
        this.persons = persons;
        success = true;
    }

    /**
     * Constructor to use for /person/[personID] error and
     * @param message
     */
    public PersonListResult(String message) {
        this.message = message;
        success = false;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof PersonListResult) {
            PersonListResult personListResult = (PersonListResult) o;
            return personListResult.persons.equals(persons) &&
                    personListResult.success == (success) &&
                    personListResult.message.equals(message);
        } else {
            return false;
        }
    }
}
