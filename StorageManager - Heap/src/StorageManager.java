/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #6
 * CSE 214
 * R04 - James Finn / Taylor Ngo
 */

//Excessive imported classes.
//java.io.* are the imported FileWriters.
import java.io.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class StorageManager{ //Main class or test class.
    private static StorageTable storageTable = new StorageTable();
    //Creates a storageTable
    public static void main(String[] args){
        String fileName = "storage.obj";
        //filename that gets stored and saved.

        try { //Based off homework document.
            // When ran again, it reads the file of the saved file.
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream inStream = new ObjectInputStream(file);

            storageTable = (StorageTable) inStream.readObject();
            inStream.close();
        }
        //Catch cases
        catch (FileNotFoundException e){
            try { //If file is not found, then it creates a new file.
                //Based off homework document.
                FileOutputStream file = new FileOutputStream(fileName);
                ObjectOutputStream outStream = new ObjectOutputStream(file);

                outStream.writeObject(storageTable);
                outStream.close();
            }
            catch (FileNotFoundException fileNotFoundException) {
                System.out.println("File not found exception.\n");
            }
            catch (IOException ioException) {
                System.out.println("I/O error.\n");
            }
        }
        catch (IOException e){
            System.out.println("Input/Output error.\n");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class not found error.\n");
        }

        Scanner input = new Scanner(System.in); //Scanner object
        String userInput; //input for switch case.
        boolean bool = true; //Infinite while loop until stopped.
        int storageID; //StorageID value
        int removeID; //RemoveID value
        String client; //Client String
        String clientContent; //Content String

        System.out.println("Hello, and welcome to Rocky Stream Storage Manager.");
        System.out.println("\nP - Print all storage boxes\n" +
                "A - Insert into storage box\n" +
                "R - Remove contents from a storage box\n" +
                "C - Select all boxes owned by a particular client\n" +
                "F - Find a box by ID and display its owner and contents\n" +
                "Q - Quit and save workspace\n" +
                "X - Quit and delete workspace\n");

        while(bool) { //Infinite while loop until stopped by Q or X.
            System.out.print("Please enter an option: ");
            userInput = input.nextLine().toUpperCase();
            //Prevents errors when user inputs 'a' rather than 'A'.
            System.out.println();

            try { //Tries the switch case, else catches exceptions.
                switch (userInput) {
                    //Switch case based off what the user input.
                    case "P": //Prints the table by toString.
                        storageTable.toString();
                        break;

                    case "A": //Adds a new storage onto the table.
                        System.out.print("Enter storage ID: ");
                        storageID = input.nextInt();
                        while (storageID == 0){ //ID cannot be 0,
                            //so it prompts the user to reenter a value if storageID is 0.
                            System.out.println("Enter a value that is not 0. ");
                            System.out.print("Enter storage ID: ");
                            storageID = input.nextInt();
                        }
                        input.nextLine();
                        System.out.print("Please enter client: " );
                        client = input.nextLine(); //Asks for client string
                        System.out.print("Please enter contents: ");
                        clientContent = input.nextLine(); //Asks for content string

                        //Creates a new storage
                        Storage storageInput = new Storage(storageID, client, clientContent);
                        storageTable.putStorage(storageID, storageInput); //Adds onto hashMap.

                        System.out.println("\nStorage " + storageID + " set!\n");
                        break;

                    case "R": //Removes value from table.
                        System.out.print("Please enter ID: ");
                        removeID = input.nextInt(); //value of the ID that will get removed.

                        if (storageTable.checkNotExist(removeID)){
                            //Checks whether the ID has a box that exists.
                            System.out.println("This box does not exist. Enter a real box value.");
                        }
                        else if (storageTable.checkContainID(removeID)) {
                            //If it contains the ID, then it removes the storage.
                            storageTable.removeStorage(removeID);
                            System.out.println("Box " + removeID + " is now removed.\n");
                        }
                        else{
                            System.out.println("Box is deleted already.");
                        }

                        input.nextLine();
                        break;

                    case "C": //Select all boxes owned by a particular client
                        System.out.print("Please enter the name of the client: ");
                        client = input.nextLine();

                        //If it contains client, then it calls upon the specified toString
                        //for the values that equal the inputted client string.
                        if (storageTable.checkContainClient(client)) {
                            storageTable.twoString(client);
                        }

                        else{
                            System.out.println("Inputted client does not equal any values on the table.\n");
                        }

                        break;

                    case "F": //Gets an ID and gives the Box#, Content, and Clients of it.
                        System.out.print("Please enter ID: ");
                        storageID = input.nextInt();

                        if (storageTable.checkNotExist(storageID)){
                            //Checks whether the storageID has an existing box.
                            System.out.println("This ID does not correlate to a box.\n");
                        }
                        else if (storageTable.checkContainID(storageID)){
                            //Checks to see if the table contains an ID from the inputted ID.
                            storageTable.getSpecificValue(storageID);
                            //Calls upon helper method that gets the specific values based off ID.
                        }
                        else{
                            System.out.println("Box does not exist.");
                        }

                        input.nextLine();
                        break;

                    case "Q": //Quits and saves.
                        System.out.println("Storage manager is quitting.");

                        //This is based off the homework doc.
                        // Writes all onto the "storage.obj" for future use.
                        FileOutputStream file = new FileOutputStream(fileName);
                        ObjectOutputStream outStream = new ObjectOutputStream(file);

                        outStream.writeObject(storageTable);
                        outStream.close();

                        System.out.println("Current storage is saved for next session.");
                        bool = false; //Breaks the infinite while loop.
                        break;

                    case "X": //Deletes and quits the simulation.
                        System.out.println("Storage manager is quitting.");

                        /*
                        FileOutputStream file1 = new FileOutputStream(fileName);
                        ObjectOutputStream outStream1 = new ObjectOutputStream(file1);
                        outStream1.close();
                        */

                        //Creates a new file of the same name, and deletes it when exited.
                        File newFile = new File(fileName);
                        //Since the program gets terminated here, it deletes upon exit.
                        newFile.deleteOnExit();

                        System.out.println("All data is being erased.");
                        bool = false; //breaks while loop
                        break;

                    default:
                        System.out.println("Please enter a valid option.");
                        break;
                }
            }
            //Catch cases.
            catch (IllegalArgumentException e){
                System.out.println("Error. Storage ID already exists.\n");
            }
            catch (NullPointerException e){
                System.out.println("Value is null.\n");
            }
            catch (FileNotFoundException e){
                System.out.println("File not found.\n");
            }
            catch (IOException e){
                System.out.println("IO error.\n");
            }
            catch (InputMismatchException e){
                System.out.println("Put the right value.\n");
                input.nextLine();
            }
        }
    }
}
