/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #2
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

import java.util.InputMismatchException;
//Imports InputMisMatchException
import java.util.Scanner;
//Imports Scanner for user Input

public class DepartmentStore {
    public static void main (String[] args){ //Main class, test class.
        Scanner input = new Scanner(System.in); //Creates scanner object
        String userInput; //User's input
        String name; //Name of item
        String rfid; //rfid Tag of item
        String original; //original location of item
        String current; //current location of item
        double price; //price of item
        ItemList shoppingList = new ItemList(); //Creates an ItemList
        boolean loop = true; //loops while loop until false: 'Q'

        System.out.println("Welcome to the Department Store!");
        while (loop){ //While loop until false
            try{
                System.out.println("    C - Clean store \n" +
                        "    I - Insert an item into the list \n" +
                        "    L - List by location \n" +
                        "    M - Move an item in the store \n" +
                        "    O - Checkout  \n" +
                        "    P - Print all items in store \n" +
                        "    R - Print by RFID tag number\n" +
                        "    U - Update inventory system   \n" +
                        "    Q - Exit the program. \n");
                System.out.print("Please select an option: ");
                userInput = input.nextLine().toUpperCase();
                System.out.println();

                switch(userInput){
                    case "I": //Inserts item
                        System.out.print("Enter the name: ");
                        name = input.nextLine();
                        System.out.print("Enter the RFID: ");
                        rfid = input.nextLine().toUpperCase();
                        System.out.print("Enter the original location: ");
                        original = input.nextLine();
                        System.out.print("Enter the price: ");
                        price = input.nextDouble();

                        shoppingList.insertInfo(name, rfid, price, original);
                        System.out.println();
                        input.nextLine();
                        break;

                    case "C": //Cleans the store of all "outs"
                        System.out.println("The following item(s) have been" +
                                " moved back to their original positions:\n ");
                        shoppingList.cleanStore();
                        System.out.println();
                        break;

                    case "L": //Prints by Location
                        System.out.print("Enter the location: ");
                        current = input.nextLine();
                        System.out.println();
                        shoppingList.printByLocation(current);
                        System.out.println();
                        break;

                    case "M": //Moves the item from one location to another
                        System.out.print("Enter the RFID: ");
                        rfid = input.nextLine();
                        System.out.print("Enter the original location: ");
                        original = input.nextLine();
                        System.out.print("Enter the new location: ");
                        current = input.nextLine();

                        boolean status = shoppingList.moveItem(rfid, original, current);
                        if (status){
                            System.out.println(rfid + " moved from " + original + " to " + current);
                        }
                        else{
                            System.out.println("Failed to move.");
                        }
                        System.out.println();
                        break;

                    case "O": //Checks out the list from cart.
                        System.out.print("Please enter the cart number: ");
                        current = input.nextLine();
                        double total = shoppingList.checkOut(current);
                        String roundedTotal = roundFormat(total);
                        System.out.println("\nThe total cost for all merchandise in cart "
                                + current.substring(1, 4) + " is: $" + (roundedTotal));
                        System.out.println();
                        break;

                    case "P": //Prints out everything.
                        shoppingList.printAll();
                        System.out.println();
                        break;

                    case "R": //Prints by RFID
                        System.out.print("Enter the RFID Tag Number to " +
                                "find all items with the same RFID Tag Number:");
                        rfid = input.nextLine();
                        shoppingList.printByRFIDTagNumber(rfid);
                        System.out.println();
                        break;

                    case "U": //Updates system
                        System.out.println("The following item(s) have been removed from the system: \n");
                        shoppingList.removeAllPurchased();
                        System.out.println();
                        break;

                    case "Q": //Terminates program
                        System.out.println("Goodbye! Thank you for shopping with us.");
                        loop = false;
                        break;

                    default: //Default case
                        System.out.println("Please enter a right input. Try again.");
                        break;
                }
            }
            catch(IllegalArgumentException e){ //Catches exception
                System.out.println(e.getMessage() + "\n");
            }
            catch (InputMismatchException e){ //Catches wrong input
                System.out.println("Invalid format of input. Try again.\n");
                input.nextLine();
            }
        }
    }

    /**
     * Helper method to round the value to two decimal places.
     */
    public static String roundFormat(double value){
        return String.format("%.2f", value);
    }
}
