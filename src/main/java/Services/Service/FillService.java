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

        try {
            conn = database.getConnection();
            UserDAO userDAO = new UserDAO(conn);
            User user = userDAO.find(request.getUsername());

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

            createEvents(person, randomBirthYear, 0,false, conn);

            //NEED TO CREATE GENERATIONS AND EVENTS HERE
            Person currPerson = null;
            for (int i = 1; i <= request.getGenerations(); i++) {
                for (int j = 0; j < people.size(); j++) {
                    currPerson = people.get(j);
                    if (currPerson.getGeneration() == i - 1) {
                        Person mom = new Person(StringUtil.getRandomPersonID(), user.getUsername(),
                                RandomDataUtils.getInstance().getRandomFemaleName(),
                                RandomDataUtils.getInstance().getRandomSurname(),
                                "f", null, null, null);
                        mom.setBirthYear(RandomDataUtils.getRandomNumber(currPerson.getBirthYear() - 50, currPerson.getBirthYear() - 13));
                        mom.setGeneration(i);

                        Person dad = new Person(StringUtil.getRandomPersonID(), user.getUsername(),
                                RandomDataUtils.getInstance().getRandomMaleName(),
                                currPerson.getLastName(), "m", null, null, mom.getPersonID());
                        dad.setBirthYear(RandomDataUtils.getRandomNumber(currPerson.getBirthYear() - 50, currPerson.getBirthYear() - 13));
                        dad.setGeneration(i);
                        mom.setSpouseID(dad.getPersonID());

                        int marriage = mom.getBirthYear() + RandomDataUtils.getRandomNumber(13, 50);
                        if (mom.getBirthYear() > dad.getBirthYear()) {
                            marriage = dad.getBirthYear() + RandomDataUtils.getRandomNumber(13, 50);
                        }

                        createPerson(mom, conn);
                        people.add(mom);

                        createPerson(dad, conn);
                        people.add(dad);

                        createEvents(mom, mom.getBirthYear(), marriage, true, conn);
                        createEvents(dad, dad.getBirthYear(), marriage, true, conn);

                        currPerson.setMotherID(mom.getPersonID());
                        currPerson.setFatherID(dad.getPersonID());

                        personDAO.update(currPerson);
                    }
                }
            }

            fillResult = new FillResult("Successfully added " + people.size() + " persons and " + events.size() + " events to the database.", true);
        }
        catch (Exception e) {
            fillResult = new FillResult("Error: Could not fill the database", false);
        }
        finally {
            try {
                if (conn != null) {
                    database.closeConnection(true);
                }
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

    private void createEvents(Person person, int birthYear, int marriageYear, boolean canDie, Connection conn) throws Exception {
        try {
            EventDAO eventDAO = new EventDAO(conn);
            int minDeathDate = birthYear;

            //always create birth event first
            Event birthEvent = new Event(StringUtil.getRandomEventID(), person.getAssociatedUsername(), person.getPersonID(),
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
                Event baptismEvent = new Event(StringUtil.getRandomEventID(), person.getAssociatedUsername(), person.getPersonID(),
                        RandomDataUtils.getInstance().getRandomLatitude(), RandomDataUtils.getInstance().getRandomLongitude(),
                        RandomDataUtils.getInstance().getRandomCountry(), RandomDataUtils.getInstance().getRandomCity(),
                        "baptism", baptismYear);
                eventDAO.insert(baptismEvent);
                events.add(baptismEvent);

                if (marriageYear > 2021) {
                    marriageYear = 2021;
                }
                if (marriageYear > minDeathDate) {
                    minDeathDate = marriageYear;
                }
                Event marriageEvent = new Event(StringUtil.getRandomEventID(), person.getAssociatedUsername(), person.getPersonID(),
                        RandomDataUtils.getInstance().getRandomLatitude(), RandomDataUtils.getInstance().getRandomLongitude(),
                        RandomDataUtils.getInstance().getRandomCountry(), RandomDataUtils.getInstance().getRandomCity(),
                        "marriage", marriageYear);
                eventDAO.insert(marriageEvent);
                events.add(marriageEvent);

                int deathYear = RandomDataUtils.getRandomNumber(minDeathDate, 120 + birthYear);
                if (deathYear > 2021) {
                    deathYear = 2021;
                }
                if (birthYear > 2020 - 120 || RandomDataUtils.getRandomNumber(0, 10) > 7) {
                    Event deathEvent = new Event(StringUtil.getRandomEventID(), person.getAssociatedUsername(), person.getPersonID(),
                            RandomDataUtils.getInstance().getRandomLatitude(), RandomDataUtils.getInstance().getRandomLongitude(),
                            RandomDataUtils.getInstance().getRandomCountry(), RandomDataUtils.getInstance().getRandomCity(),
                            "death", deathYear);
                    eventDAO.insert(deathEvent);
                    events.add(deathEvent);
                }
            }

        }
        catch (Exception e) {
            throw new Exception("Error: could not create birth event.");
        }

    }
}
