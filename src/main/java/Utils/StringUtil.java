package Utils;

import DataAccess.AuthTokenDAO;
import DataAccess.Database;
import DataAccess.EventDAO;
import DataAccess.PersonDAO;
import Models.AuthToken;
import Models.Event;
import Models.Person;

import java.io.*;
import java.sql.Connection;
import java.util.Random;

public class StringUtil {
    //converts input stream into a string
    public static String getStringFromInputStream(InputStream is) throws IOException {
        StringBuilder sbuf = new StringBuilder();
        InputStreamReader sr = new InputStreamReader(is);
        char[] buf = new char[1024];
        int len;
        while ((len = sr.read(buf)) > 0) {
            sbuf.append(buf, 0, len);
        }
        return sbuf.toString();
    }

    public static String getRandomEventID(Connection conn) throws Exception {
        String id = null;

        try {
            EventDAO eventDAO = new EventDAO(conn);
            Event event;
            do {
                id = getRandomID();
                event = eventDAO.find(id);
            }
            while (event != null);
        }
        catch (Exception e) {
            throw new Exception("Error: cannot generate new eventID");
        }

        return id;
    }

    public static String getRandomPersonID(Connection conn) throws Exception {
        String id = null;

        try {
            PersonDAO personDAO = new PersonDAO(conn);
            Person person;
            do {
                id = getRandomID();
                person = personDAO.find(id);
            }
            while (person != null);
        }
        catch (Exception e) {
            throw new Exception("Error: cannot generate new personID");
        }
        return id;
    }

    public static String getRandomAuthToken(Connection conn) throws Exception {
        String authTokenID = null;

        try {
            AuthTokenDAO authTokenDAO = new AuthTokenDAO(conn);
            AuthToken authToken;
            do {
                authTokenID = getRandomID();
                authToken = authTokenDAO.find(authTokenID);
            }
            while (authToken != null);
        }
        catch (Exception e) {
            throw new Exception("Error: cannot generate new authToken");
        }
        return authTokenID;
    }

    private static String getRandomID() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890abcdefghijklmnopqrstuvwxyz";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 8) { // length of the random string.
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }

    public static void writeStringToStream(String resp, OutputStream os) throws IOException {
        OutputStreamWriter sw = new OutputStreamWriter(os);
        sw.write(resp);
        sw.flush();
    }
}
