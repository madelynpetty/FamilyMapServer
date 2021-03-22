package Services.Service;
import DataAccess.*;
import Models.Event;
import Models.Person;
import Models.User;
import Services.Result.FillResult;
import Services.Request.FillRequest;
import Utils.RandomDataUtils;
import Utils.StringUtil;

import java.sql.Connection;
import java.util.ArrayList;

/**
 * The FillService makes the request and returns the result
 */
public class FillService {
    private Database database = new Database();
    private ArrayList<Person> people = new ArrayList<>();
    private ArrayList<Event> events = new ArrayList<>();

    /**
     * Returns the result of calling the Fill Service
     * @param request The fill request
     * @return The result of calling the fill service
     */
    public FillResult callFillService(FillRequest request) throws DataAccessException {
        FillResult fillResult;
        Database database = new Database();
        Connection conn = null;
        boolean doCommit = false;

        try {
            conn = database.openConnection();
            UserDAO userDAO = new UserDAO(conn);
            User user = userDAO.find(request.getUsername());

            if (user == null) {
                throw new Exception("Error: cannot find user in the database");
            }

            PersonDAO personDAO = new PersonDAO(conn);
            personDAO.clear(request.getUsername());

            EventDAO eventDAO = new EventDAO(conn);
            eventDAO.clear(request.getUsername());

            //create person for logged in user
            Person person = new Person(user.getPersonID(), user.getUsername(), user.getFirstName(), user.getLastName(), user.getGender(), null, null, null);
            createPerson(person, conn);
            people.add(person);

            int randomBirthYear = RandomDataUtils.getRandomNumber(1950, 2021);
            person.setBirthYear(randomBirthYear);
            person.setGeneration(0);

            createEvents(person, randomBirthYear,false, conn);

            //NEED TO CREATE GENERATIONS AND EVENTS HERE
            Person currPerson = null;
            for (int i = 1; i <= request.getGenerations(); i++) {
                for (int j = 0; j < people.size(); j++) {
                    currPerson = people.get(j);
                    if (currPerson.getGeneration() == i - 1) {
                        Person mom = new Person(StringUtil.getRandomPersonID(conn), user.getUsername(),
                                RandomDataUtils.getInstance().getRandomFemaleName(),
                                RandomDataUtils.getInstance().getRandomSurname(),
                                "f", null, null, null);
                        mom.setBirthYear(RandomDataUtils.getRandomNumber(currPerson.getBirthYear() - 40, currPerson.getBirthYear() - 20));
                        mom.setGeneration(i);

                        Person dad = new Person(StringUtil.getRandomPersonID(conn), user.getUsername(),
                                RandomDataUtils.getInstance().getRandomMaleName(),
                                currPerson.getLastName(), "m", null, null, mom.getPersonID());
                        dad.setBirthYear(RandomDataUtils.getRandomNumber(mom.getBirthYear() - 4, mom.getBirthYear() + 4));
                        dad.setGeneration(i);
                        mom.setSpouseID(dad.getPersonID());

                        createPerson(mom, conn);
                        people.add(mom);

                        createPerson(dad, conn);
                        people.add(dad);

                        createEvents(mom, mom.getBirthYear(), true, conn);
                        createEvents(dad, dad.getBirthYear(), true, conn);

                        createMarriageEvent(mom, dad, conn);

                        currPerson.setMotherID(mom.getPersonID());
                        currPerson.setFatherID(dad.getPersonID());

                        personDAO.update(currPerson);
                    }
                }
            }

            doCommit = true;
            fillResult = new FillResult("Successfully added " + people.size() + " persons and " + events.size() + " events to the database.", true);
        }
        catch (Exception e) {
            doCommit = false;
            fillResult = new FillResult("Error: Could not fill the database", false);
        }
        finally {
            try {
                database.closeConnection(doCommit);
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
        return fillResult;
    }

    private void createPerson(Person person, Connection conn) throws Exception {
        try {
            PersonDAO personDAO = new PersonDAO(conn);
            personDAO.insert(person);
        }
        catch (Exception e) {
            throw new Exception("Error: Failed to create person");
        }
    }

    private void createEvents(Person person, int birthYear, boolean canDie, Connection conn) throws Exception {
        try {
            EventDAO eventDAO = new EventDAO(conn);
            int minDeathDate = birthYear;

            //always create birth event first
            Event birthEvent = new Event(StringUtil.getRandomEventID(conn), person.getAssociatedUsername(), person.getPersonID(),
                    RandomDataUtils.getInstance().getRandomLatitude(), RandomDataUtils.getInstance().getRandomLongitude(),
                    RandomDataUtils.getInstance().getRandomCountry(), RandomDataUtils.getInstance().getRandomCity(),
                    "birth", birthYear);
            eventDAO.insert(birthEvent);
            events.add(birthEvent);

            if (canDie) {
                int baptismYear = birthYear + RandomDataUtils.getRandomNumber(8, 120);
                if (baptismYear > 2021) {
                    baptismYear = 2021;
                }
                if (baptismYear > minDeathDate) {
                    minDeathDate = baptismYear;
                }
                Event baptismEvent = new Event(StringUtil.getRandomEventID(conn), person.getAssociatedUsername(), person.getPersonID(),
                        RandomDataUtils.getInstance().getRandomLatitude(), RandomDataUtils.getInstance().getRandomLongitude(),
                        RandomDataUtils.getInstance().getRandomCountry(), RandomDataUtils.getInstance().getRandomCity(),
                        "baptism", baptismYear);
                eventDAO.insert(baptismEvent);
                events.add(baptismEvent);

                int deathYear = RandomDataUtils.getRandomNumber(minDeathDate, 120 + birthYear);
                if (deathYear > 2021) {
                    deathYear = 2021;
                }
                Event deathEvent = new Event(StringUtil.getRandomEventID(conn), person.getAssociatedUsername(), person.getPersonID(),
                        RandomDataUtils.getInstance().getRandomLatitude(), RandomDataUtils.getInstance().getRandomLongitude(),
                        RandomDataUtils.getInstance().getRandomCountry(), RandomDataUtils.getInstance().getRandomCity(),
                        "death", deathYear);
                eventDAO.insert(deathEvent);
                events.add(deathEvent);
                person.setDeathYear(deathYear);

            }

        }
        catch (Exception e) {
            throw new Exception("Error: could not create birth event.");
        }

    }

    private void createMarriageEvent(Person mom, Person dad, Connection conn) throws Exception {
        int minYear = mom.getBirthYear() > dad.getBirthYear() ? dad.getBirthYear() + 14 : mom.getBirthYear() + 14;
        int maxYear = mom.getBirthYear() > dad.getBirthYear() ? dad.getBirthYear() + 50 : mom.getBirthYear() + 50;

        int marriageYear = RandomDataUtils.getRandomNumber(minYear, maxYear);

        if (marriageYear > mom.getDeathYear()) {
            marriageYear = mom.getDeathYear();
        }
        if (marriageYear > dad.getDeathYear()) {
            marriageYear = dad.getDeathYear();
        }

        if (marriageYear > 2021) {
            marriageYear = 2021;
        }

        EventDAO eventDAO = new EventDAO(conn);
        float latitude = RandomDataUtils.getInstance().getRandomLatitude();
        float longitude = RandomDataUtils.getInstance().getRandomLongitude();
        String country = RandomDataUtils.getInstance().getRandomCountry();
        String city = RandomDataUtils.getInstance().getRandomCity();

        Event marriageEventMom = new Event(StringUtil.getRandomEventID(conn), mom.getAssociatedUsername(), mom.getPersonID(),
                latitude, longitude, country, city, "marriage", marriageYear);
        eventDAO.insert(marriageEventMom);
        events.add(marriageEventMom);

        Event marriageEventDad = new Event(StringUtil.getRandomEventID(conn), dad.getAssociatedUsername(), dad.getPersonID(),
                latitude, longitude, country, city, "marriage", marriageYear);
        eventDAO.insert(marriageEventDad);
        events.add(marriageEventDad);
    }
}
