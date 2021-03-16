import com.google.gson.*;

import java.io.*;
import java.lang.reflect.Array;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Main method for the project
 */
public class FamilyMapServer {

    /**
     * parses JSONObjects to call methods and parse the names into individual arrays.
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception{
        ArrayList<String> femaleNameArray = parseFemaleFile();
        ArrayList<String> maleNameArray = parseMaleFile();
        ArrayList<String> surnameArray = parseSurnameFile();

        ArrayList<String> locations = parseLocationsFile();
        ArrayList<String> countryArray = getCountryArray(locations);
        ArrayList<String> cityArray = getCityArray(locations);
        ArrayList<Float> latitudeArray = getLatitudeArray(locations);
        ArrayList<Float> longitudeArray = getLongitudeArray(locations);
    }

    /**
     * Parses the json in "fnames.json" into an array of Strings of the female names
     * @return An arraylist of female name strings
     */
    private static ArrayList<String> parseFemaleFile() throws Exception {
        ArrayList<String> femaleNameArray = new ArrayList<>();
        Object obj = new JsonParser().parse(new FileReader("/Users/maddie/IdeaProjects/FamilyMap/json/fnames.json"));
        try {
            JsonObject jo = (JsonObject) obj;
            JsonArray femaleNames = (JsonArray) jo.get("data");
            Iterator<JsonElement> iterator = femaleNames.iterator();
            while (iterator.hasNext()) {
                femaleNameArray.add(iterator.next().toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return femaleNameArray;
    }

    /**
     * Parses the json in "mnames.json" into an array of Strings of the male names
     * @return An arraylist of male name strings
     */
    private static ArrayList<String> parseMaleFile() throws Exception {
        ArrayList<String> maleNameArray = new ArrayList<>();
        Object obj = new JsonParser().parse(new FileReader("/Users/maddie/IdeaProjects/FamilyMap/json/mnames.json"));
        try {
            JsonObject jo = (JsonObject) obj;
            JsonArray maleNames = (JsonArray) jo.get("data");
            Iterator<JsonElement> iterator = maleNames.iterator();
            while (iterator.hasNext()) {
                maleNameArray.add(iterator.next().toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return maleNameArray;
    }

    /**
     * Parses the json in "snames.json" into an array of Strings of the surname names
     * @return An arraylist of surname name strings
     */
    private static ArrayList<String> parseSurnameFile() throws Exception {
        ArrayList<String> surnameArray = new ArrayList<>();
        Object obj = new JsonParser().parse(new FileReader("/Users/maddie/IdeaProjects/FamilyMap/json/snames.json"));
        try {
            JsonObject jo = (JsonObject) obj;
            JsonArray surnameNames = (JsonArray) jo.get("data");
            Iterator<JsonElement> iterator = surnameNames.iterator();
            while (iterator.hasNext()) {
                surnameArray.add(iterator.next().toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return surnameArray;
    }

    /**
     * Parses the locations into an ArrayList of Strings in which there are countries, cities, latitudes, and longitudes.
     * @return ArrayList<String>
     * @throws Exception
     */
    private static ArrayList<String> parseLocationsFile() throws Exception {
        ArrayList<String> locationsArray = new ArrayList<>();
        Object obj = new JsonParser().parse(new FileReader("/Users/maddie/IdeaProjects/FamilyMap/json/locations.json"));
        try {
            JsonObject jo = (JsonObject) obj;
            JsonArray locations = (JsonArray) jo.get("data");
            Iterator<JsonElement> iterator = locations.iterator();
            while (iterator.hasNext()) {
                locationsArray.add(iterator.next().toString());
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        return locationsArray;
    }

    private static ArrayList<String> getCountryArray(ArrayList<String> locations) throws Exception {
//        ArrayList<String> countries = new ArrayList<>();
//        InputStream is = new FileInputStream("/Users/maddie/IdeaProjects/FamilyMap/json/locations.json");
//        Reader r = new InputStreamReader(is, "UTF-8");
//        JsonStreamParser p = new JsonStreamParser(r);
//
//        while (p.hasNext()) {
//            JsonElement e = p.next();
//            if (e.isJsonObject()) {
//                countries.add(e.toString());
//            }
//        }
//        return countries;

        ArrayList<String> countries = new ArrayList<>();
        return countries;
    }

    private static ArrayList<String> getCityArray(ArrayList<String> locations) {
        return null;
    }

    private static ArrayList<Float> getLatitudeArray(ArrayList<String> locations) {
        return null;
    }

    private static ArrayList<Float> getLongitudeArray(ArrayList<String> locations) {
        return null;
    }
}
