/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #7
 * CSE 214
 * R04 - James Finn / Taylor Ngo
 */

import java.text.SimpleDateFormat; //Imports SimpleDateFormat for MM-dd-yyyy
import java.util.ArrayList; //Imports ArrayList to hold object values.
import big.data.DataSource;
//Utilizes bigdata.jar and imports DataSource
//to extract NASA's API and take their values.
import java.util.Collections; //Imports Collections to add values.
import java.util.Comparator; //Imports Comparator for comparison of values.

/**
 * NeoDatabase Class
 */
public class NeoDatabase{
    public static final String API_KEY =
            "lXNA9KrKhS1XNgknkde4BDiEzpmQgeHUBKU0YpLb"; //This is the API_KEY
    //https://api.nasa.gov/planetary/apod?api_key=
    //lXNA9KrKhS1XNgknkde4BDiEzpmQgeHUBKU0YpLb
    public static final String API_ROOT =
            "https://api.nasa.gov/neo/rest/v1/neo/browse?";
    //This is the website to extract their data from, or the API_ROOT.

    private final ArrayList<NearEarthObject> neoList;
    //Creates an ArrayList of type NearEarthObject.

    /**
     * NeoDatabase's constructor.
     * Empty Constructor
     */
    public NeoDatabase(){
        neoList = new ArrayList<>(); //builds ArrayList.
    }

    /**
     *
     * @param pageNumber integer value
     * @return a Query URL.
     * @throws IllegalArgumentException
     *
     * Builds a query URL given a page number.
     */
    public String buildQueryURL(int pageNumber) throws IllegalArgumentException{
        if (pageNumber > 715 || pageNumber < 0){ //Throws error if out of range.
            throw new IllegalArgumentException("The page number is not in valid range.");
        }

        return (API_ROOT + "page=" + pageNumber + "&api_key=" + API_KEY); //query URL
    }

    /**
     *
     * @param queryURL String
     * @throws IllegalArgumentException
     *
     * Opens a connection to the data from the queryURL
     * Adds all the NearEarthObjects found into the dataset.
     * In this case, it adds all onto the ArrayList.
     */
    public void addAll(String queryURL) throws IllegalArgumentException{
        if (queryURL == null){ //If the URL is null, it throws an error.
            throw new IllegalArgumentException("QueryURL is null.");
        }

        DataSource ds = DataSource.connectJSON(queryURL);
        //Connects DataSource object to JSON file given URL.
        ds.load(); //Loads the DataSource object.

        //This entire array basically fetches and constructs
        //instances of a class given these strings, and
        //a list of identifiers that are used in the constructor.
        NearEarthObject[] neoArray = ds.fetchArray("NearEarthObject",
                "near_earth_objects/neo_reference_id",
                "near_earth_objects/name", "near_earth_objects/absolute_magnitude_h",
                "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_min",
                "near_earth_objects/estimated_diameter/kilometers/estimated_diameter_max",
                "near_earth_objects/is_potentially_hazardous_asteroid",
                "near_earth_objects/close_approach_data/epoch_date_close_approach",
                "near_earth_objects/close_approach_data/miss_distance/kilometers",
                "near_earth_objects/close_approach_data/orbiting_body");

        //Collections adds all of the data extracted onto the ArrayList.
        Collections.addAll(neoList, neoArray);
    }

    /**
     *
     * @param comp Comparator Object
     * @throws IllegalArgumentException
     *
     * Sorts the ArrayList based off the which comparator class is used
     * (ReferenceID, Diameter, ApproachDate, MissDistance)
     */
    public void sort (Comparator<NearEarthObject> comp)
            throws IllegalArgumentException{
        if (comp == null){
            //If there is no comparator object or null, it throws an error.
            throw new IllegalArgumentException("Comp is null.");
        }
        //Uses Java's own implementation of list sort on the ArrayList.
        neoList.sort(comp);
    }

    /**
     * No parameter method
     * Prints the Table.
     */
    public void printTable(){
        int i = 0, k = 0; //For the while loop later.
        String booleanValue; //Holds "True" or "False"

        //This print function creates the top portion of the table.
        System.out.printf("%-4s%-5s%-16s%-19s%-3s%-6s" +
                        "%-4s%-11s%-2s%-7s%-3s%-12s" +
                        "%-5s%-14s%-2s%-7s%-1s%n",
                "|", "ID", "|", "Name", "|", "Mag.",
                "|", "Diameter", "|", "Danger", "|",
                "Close Date", "|", "Miss Dist.", "|",
                "Orbits", "|");

        //This while loop prints the "=" right under the top portion of table.
        while (i < 121){
            System.out.print("=");
            i++;
        }
        System.out.println();

        //Iterates through the entire ArrayList and prints out their function.
        for (NearEarthObject object: neoList) {
            if (object.isDangerous()){ //Converts "true" from boolean to "True" because OCD.
                booleanValue = "True";
            }
            else{ //Converts "false" to "False" on table.
                booleanValue = "False";
            }

            //Using the imported SimpleDateFormat, it changes the default date format
            //to MM-dd-yyyy as seen on the I/O.
            //A string then takes the value of the simplified date,
            // which is then printed in the print function.
            SimpleDateFormat dateFormat = new SimpleDateFormat("MM-dd-yyyy");
            String simpleDateString = dateFormat.format(object.getClosestApproachDate());

            //Prints the values of the extracted data.
            System.out.printf("%-1s%-8d%-1s%-34s" +
                            "%-1s%-8.2f%-1s" +
                            "%-14.10f%-1s%-8s%-1s%-14s" +
                            "%-1s%-18.6f%-1s%-9s",
                    "", object.getReferenceID(), "", object.getName(),
                    "", object.getAbsoluteMagnitude(), "",
                    object.getAverageDiameter(), "", booleanValue, "", simpleDateString,
                    "", object.getMissDistance(), "", object.getOrbitingBody());
            System.out.println();
        }

        //Closes the table off with "-" on the bottom.
        while(k < 121){
            System.out.print("-");
            k++;
        }
    }
}
