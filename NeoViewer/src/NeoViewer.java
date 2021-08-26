/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #7
 * CSE 214
 * R04 - James Finn / Taylor Ngo
 */

import java.util.Comparator; //Imports Comparator
import java.util.InputMismatchException;
//Imports InputMismatchException for catch case.
import java.util.Scanner; //Imports a scanner for user to input.

/**
 * NeoViewer class
 * Runnable Class
 */
public class NeoViewer {
    public static void main(String[] args){
        NeoDatabase database = new NeoDatabase(); //Creates a NeoDatabase object.
        Scanner input = new Scanner(System.in); //Scanner object
        boolean bool = true; //To create an infinite loop until prompted to quit.
        String userInput; //This is to get user's input for switch case.
        int loadPage; //This is to get the user's desired page to load.
        String builtQueryURL; //This is the queryURL that is built.
        char sortChoice; //User's inputted choice to sort.
        Comparator<NearEarthObject> comparator; //Used for creating a comparator object.

        System.out.println("Welcome to NEO Viewer!");

        while (bool){ //Infinite while loop until user prompts 'Q'
            System.out.println("\nOption Menu:\n" +
                    "    A) Add a page to the database\n" +
                    "    S) Sort the database \n" +
                    "    P) Print the database as a table.\n" +
                    "    Q) Quit");

            System.out.print("\nPlease select a menu option: ");
            userInput = input.nextLine().toUpperCase();
            System.out.println();

            try{
                switch(userInput){ //Switch case
                    case "A": //This is used to load a page.
                        System.out.print("Enter the page to load: ");

                        loadPage = input.nextInt(); //Page number
                        input.nextLine();
                        builtQueryURL = database.buildQueryURL(loadPage);
                        //Builds the QueryURL based off loadPage
                        database.addAll(builtQueryURL);
                        //Then, it adds the values onto the ArrayList.

                        System.out.println("\nPage loaded successfully!");
                        break;

                    case "S": //Sorts based off corresponding user input.
                        System.out.println("Sort options:  \n" +
                                "  R) Sort by referenceID\n" +
                                "  D) Sort by diameter\n" +
                                "  A) Sort by approach date\n" +
                                "  M) Sort by miss distance");

                        System.out.print("\nSelect a menu option: ");
                        sortChoice = input.next().charAt(0);
                        sortChoice = Character.toUpperCase(sortChoice);

                        switch(sortChoice){
                            case 'R': //Sort by referenceID.
                                comparator = new ReferenceIDComparator();

                                database.sort(comparator);

                                System.out.println("\nTable sorted by Reference ID.");
                                input.nextLine();
                                break;

                            case 'D': //Sort by Average Diameter.
                                comparator = new DiameterComparator();

                                database.sort(comparator);

                                System.out.println("\nTable sorted by Diameter.");
                                input.nextLine();
                                break;

                            case 'A': //Sort by Approach Date.
                                comparator = new ApproachDateComparator();

                                database.sort(comparator);

                                System.out.println("\nTable sorted by Approach Date.");
                                input.nextLine();
                                break;

                            case 'M': //Sort by Miss Distance.
                                comparator = new MissDistanceComparator();

                                database.sort(comparator);

                                System.out.println("\nTable sorted by Missed Distance.");
                                input.nextLine();
                                break;

                            default: //If user inputs anything other than specified values,
                                // it terminates switch case and goes back to the beginning.
                                System.out.println("\nPlease input a correct option.");
                                input.nextLine();
                                break;
                        }
                        break;

                    case "P": //Prints the table.
                        database.printTable();
                        System.out.println();
                        break;

                    case "Q": //Terminates the loop.
                        bool = false; //ends loop.
                        System.out.println("Program terminating normally...");
                        break;

                    default:
                        //Default case, which is when the user inputs something that is not specified.
                        System.out.println("Please select a correct option.");
                        break;
                }
            }
            //Catch cases
            catch(IllegalArgumentException e){
                //Prints the error message when there is an Illegal Argument.
                System.out.println(e.getMessage());
            }
            catch(InputMismatchException e){
                //Catches when there is an input mismatch.
                System.out.println("Please input the specified type of option.");
                input.nextLine();
            }
        }
    }
}
