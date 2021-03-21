package Utils;

import DataAccess.AuthTokenDAO;
import DataAccess.Database;
import DataAccess.EventDAO;
import DataAccess.PersonDAO;
import Models.AuthToken;
import Models.Event;
import Models.Person;

import java.io.*;
import java.nio.charset.Charset;
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

    public static String getRandomEventID() throws Exception {
        String id = null;
        Database database = new Database();
        Connection conn = null;

        try {
            conn = database.getConnection();

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
        finally {
            if (conn != null) {
                conn.close();
            }
        }

        return id;
    }

    public static String getRandomPersonID() throws Exception {
        String id = null;
        Database database = new Database();
        Connection conn = null;

        try {
            conn = database.getConnection();
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
        finally {
            if (conn != null) {
                conn.close();
            }
        }
        return id;
    }

    public static String getRandomAuthToken() throws Exception {
        String authTokenID = null;
        Database database = new Database();
        Connection conn = null;

        try {
            conn = database.getConnection();

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
        finally {
            if (conn != null) {
                conn.close();
            }
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
