import com.google.gson.*;

import java.io.*;
import java.util.ArrayList;
import java.util.Iterator;

/**
 * Main method for the project
 */
public class FamilyMapServer {
    public static ArrayList<String> femaleNameArray;
    public static ArrayList<String> maleNameArray;
    public static ArrayList<String> surnameArray;
    public static ArrayList<String> countryArray;
    public static ArrayList<String> cityArray;
    public static ArrayList<Float> latitudeArray;
    public static ArrayList<Float> longitudeArray;

    /**
     * parses JSONObjects to call methods and parse the names into individual arrays.
     * @param args
     * @throws Exception
     */

    public static void main(String[] args) throws Exception{
        femaleNameArray = parseFemaleFile();
        maleNameArray = parseMaleFile();
        surnameArray = parseSurnameFile();

        countryArray = setCountryArray();
        cityArray = setCityArray();
        latitudeArray = setLatitudeArray();
        longitudeArray = setLongitudeArray();
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

    private static ArrayList<String> setCountryArray() throws Exception {
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


//        JsonObject obj;
//        String fileName = "/Users/maddie/IdeaProjects/FamilyMap/json/locations.json";
//        String line = null;
//
//        try {
//            FileReader fileReader = new FileReader(fileName);
//            BufferedReader bufferedReader = new BufferedReader(fileReader);
//
//            while((line = bufferedReader.readLine()) != null) {
//                obj = (JsonObject) new JsonParser().parse(line);
//                countries.add(obj.get("country").toString());
//            }
//        }
//        catch (Exception e) {
//            e.printStackTrace();
//        }
        ArrayList<String> countries = new ArrayList<>();
        Object obj = new JsonParser().parse(new FileReader("/Users/maddie/IdeaProjects/FamilyMap/json/locations.json"));
        try {
            JsonObject jo = (JsonObject) obj;
            JsonArray countryJSON = jo.get("data").getAsJsonArray();
            Iterator<JsonElement> iterator = countryJSON.iterator();
            for (int i = 0; i < countryJSON.size(); i++) {
                JsonObject location = (JsonObject) countryJSON.get(i);
                String country = location.get("country").toString();
                countries.add(country);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return countries;
    }

    private static ArrayList<String> setCityArray() throws Exception {
        ArrayList<String> cities = new ArrayList<>();
        Object obj = new JsonParser().parse(new FileReader("/Users/maddie/IdeaProjects/FamilyMap/json/locations.json"));
        try {
            JsonObject jo = (JsonObject) obj;
            JsonArray cityJSON = jo.get("data").getAsJsonArray();
            Iterator<JsonElement> iterator = cityJSON.iterator();
            for (int i = 0; i < cityJSON.size(); i++) {
                JsonObject location = (JsonObject) cityJSON.get(i);
                String country = location.get("city").toString();
                cities.add(country);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return cities;
    }

    private static ArrayList<Float> setLatitudeArray() throws Exception {
        ArrayList<Float> latitudes = new ArrayList<>();
        Object obj = new JsonParser().parse(new FileReader("/Users/maddie/IdeaProjects/FamilyMap/json/locations.json"));
        try {
            JsonObject jo = (JsonObject) obj;
            JsonArray longitudeJSON = jo.get("data").getAsJsonArray();
            Iterator<JsonElement> iterator = longitudeJSON.iterator();
            for (int i = 0; i < longitudeJSON.size(); i++) {
                JsonObject location = (JsonObject) longitudeJSON.get(i);
                Float country = location.get("latitude").getAsFloat();
                latitudes.add(country);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return latitudes;
    }

    private static ArrayList<Float> setLongitudeArray() throws Exception {
        ArrayList<Float> longitudes = new ArrayList<>();
        Object obj = new JsonParser().parse(new FileReader("/Users/maddie/IdeaProjects/FamilyMap/json/locations.json"));
        try {
            JsonObject jo = (JsonObject) obj;
            JsonArray latitudeJSON = jo.get("data").getAsJsonArray();
            Iterator<JsonElement> iterator = latitudeJSON.iterator();
            for (int i = 0; i < latitudeJSON.size(); i++) {
                JsonObject location = (JsonObject) latitudeJSON.get(i);
                Float country = location.get("latitude").getAsFloat();
                longitudes.add(country);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }

        return longitudes;
    }
}
