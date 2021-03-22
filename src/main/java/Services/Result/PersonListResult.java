package Services.Result;

import Models.Person;

import java.util.ArrayList;

public class PersonListResult {
    private ArrayList<Person> data = new ArrayList<>();
    private boolean success = false;

    /**
     * Constructor to use for /person success
     * @param data
     */
    public PersonListResult(ArrayList<Person> data) {
        this.data = data;
        success = true;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null)
            return false;
        if (o instanceof PersonListResult) {
            PersonListResult personListResult = (PersonListResult) o;
            return personListResult.data.equals(data) &&
                    personListResult.success == (success);
        } else {
            return false;
        }
    }
}
