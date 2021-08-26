/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #5
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

//NOTE: For the main class, I decided to add a 'X'
//which deletes the entire .obj file rather than
//saving it. I still have the 'Q' function, which
//saves the .obj file.

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.GregorianCalendar;
import java.util.Date;

public class Mailbox implements Serializable{ //Mailbox class
    private Folder inbox = new Folder("Inbox"); //Creates a folder object called inbox
    private Folder trash = new Folder("Trash"); //Creates a folder object called trash
    private ArrayList<Folder> folders = new ArrayList<Folder>();
    //Creates an ArrayList of folders
    public static Mailbox mailbox = new Mailbox();
    //Creates a new mailbox. Static reference, can be accessed in main method.

    /**
     * Empty Constructor.
     * Creates a mailbox.
     */
    public Mailbox() {
    }

    /**
     *
     * @return
     *
     * Gets the trash folder
     */
    public Folder getTrash() {
        return trash;
    }

    /**
     *
     * @return
     *
     * Gets the inbox folder
     */
    public Folder getInbox() {
        return inbox;
    }

    /**
     *
     * @return
     *
     * Gets the ArrayList of folders in mailbox.
     */
    public ArrayList<Folder> getFolders() {
        return folders;
    }

    /**
     *
     * @param trash
     * takes in the trash folder
     *
     * sets the trash folder
     */
    public void setTrash(Folder trash) {
        this.trash = trash;
    }

    /**
     *
     * @param inbox
     * takes in the inbox folder
     *
     * sets the inbox folder
     */
    public void setInbox(Folder inbox) {
        this.inbox = inbox;
    }

    /**
     *
     * @param folders
     * takes in the ArrayList of folders
     *
     * sets the ArrayList of folders
     */
    public void setFolders(ArrayList<Folder> folders) {
        this.folders = folders;
    }

    /**
     *
     * @return
     * gets mailbox
     */
    public static Mailbox getMailbox() {
        return mailbox;
    }

    /**
     *
     * @param mailbox
     * Takes in the mailbox
     *
     * Sets the mailbox.
     */
    public static void setMailbox(Mailbox mailbox) {
        Mailbox.mailbox = mailbox;
    }

    /**
     *
     * @param folder
     * Takes in a folder to be added into the folder arraylist
     *
     * Iterates through the ArrayList of folders and if the name
     * of that folder is equal to the folder that wants to be added,
     * then it will not be added.
     *
     * However, if that condition is not fulfilled, then we add a folder
     * onto the ArrayList of folders.
     */
    public void addFolder(Folder folder){
        if (folders.isEmpty()){
            folders.add(folder);
            return;
        }

        for (int i = 0; i < folders.size(); i++){
            if (folders.get(i).getName().equalsIgnoreCase(folder.getName())){
                throw new IllegalArgumentException("Error! Folder name already exists.");
            }
        }
        folders.add(folder);
    }

    /**
     *
     * @param name
     * Gets the name of what folder to delete.
     *
     * Iterates through the entire ArrayList of folders
     * and if the name of that folder in the ArrayList is
     * equal to the name inputted by the user, then it will
     * remove that folder.
     */
    public void deleteFolder (String name){
        boolean flag = true;
        if (folders.isEmpty()){
            throw new IllegalArgumentException("There is nothing to delete.");
        }

        for (int i = 0; i < folders.size(); i++){
            if (folders.get(i).getName().equalsIgnoreCase(name)){
                Folder removeFolder = folders.get(i);
                folders.remove(removeFolder);
                flag = false;
            }
        }
        if (flag){
            throw new IllegalArgumentException("Folder could not be found. Was not deleted.");
        }
    }

    /**
     * Void method. This composes the email by asking
     * the user for to, cc, bcc, subject, and body.
     * Afterwards, it creates a Gregorian calendar object, which
     * all gets added into creating a new Email object with those
     * exact parameters entered by the user. Afterwards, the folder
     * inbox adds that email.
     *
     */
    public void composeEmail(){
        Scanner input = new Scanner(System.in);
        String to, cc, bcc, subjectLine, body;
        System.out.print("Enter recipient (To): ");
        to = input.nextLine();

        System.out.print("Enter carbon copy recipients (CC): ");
        cc = input.nextLine();

        System.out.print("Enter blind carbon copy recipients (BCC): ");
        bcc = input.nextLine();

        System.out.print("Enter subject line: ");
        subjectLine = input.nextLine();

        System.out.print("Enter body: ");
        body = input.nextLine();

        GregorianCalendar calendar = new GregorianCalendar();

        Email newEmail = new Email(to, cc, bcc, subjectLine, body, calendar);

        inbox.addEmail(newEmail);
    }

    /**
     *
     * @param email
     * Takes in the email that is deleted.
     *
     * This void method adds the email onto trash,
     * signifying that the email is deleted.
     */
    public void deleteEmail(Email email){
        trash.addEmail(email);
    }

    /**
     * This void method calls upon ArrayList's built in clear
     * function, which deletes everything inside the arraylist.
     */
    public void clearTrash(){
        if (trash.getEmails().isEmpty()){
            System.out.println("Trash is already empty. No need to empty.\n");
        }
        else {
            trash.getEmails().clear();
        }
    }

    /**
     *
     * @param email
     * Email that will be moved
     * @param target
     * Folder that the email moves to
     *
     * It starts with a for loop that iterates through
     * the arraylist of folders until it reaches a point
     * where the instance of the folder's name is equal
     * to the inputted target's name. If it is equal to
     * the target's name, then the arraylist of folders
     * at that iteration will add an email into the folder.
     * Same can be said for trash and inbox, where if it
     * is equal to trash or inbox, then it will be added to
     * its respective folder. However, if the target is not
     * named after a folder that exists, then it will activate
     * the else statement, which just adds it to the inbox. It breaks
     * after it moves because there is no need to continue iterating,
     * and can prevent any future bugs.
     */
    public void moveEmail (Email email, Folder target){
        if (email == null){
            throw new IllegalArgumentException("Email is null. Error.");
        }
        if (target == null) {
            throw new IllegalArgumentException("Folder is null. Error.");
        }
        if (folders.isEmpty()){
            System.out.println
                    ("\nThe target folder was not found, default moving to inbox.\n");
            inbox.addEmail(email);
        }

        String trashName = trash.getName();
        String inboxName = inbox.getName();

        for (int i = 0; i < folders.size(); i++){
            String folderName = folders.get(i).getName();
            if (target.getName().equalsIgnoreCase(folderName)){
                folders.get(i).addEmail(email);
                System.out.println("\n\"" + email.getSubject()
                        + "\" successfully moved to " + target.getName());
                System.out.println();
                break;
            }
            else if (target.getName().equalsIgnoreCase(trashName)){
                deleteEmail(email);
                System.out.println
                        ("\"" + email.getSubject() + "\" successfully moved to " + target.getName());
                break;
            }
            else if (target.getName().equalsIgnoreCase(inboxName)){
                inbox.addEmail(email);
                System.out.println
                        ("\"" + email.getSubject() + "\" successfully moved to " + target.getName());
                break;
            }
            else{ //If the three folders were not the target,
                // then just put the email into inbox.
                inbox.addEmail(email);
                System.out.println
                        ("\nThe target folder was not found, default moving to inbox.\n");
                break;
            }
        }
    }

    /**
     *
     * @param name
     * Name of folder
     * @return
     *
     * Takes in the name of the folder, and when the for loop
     * iterates through the entire arraylist of folders, if the
     * folder at that iteration's name is the same as the inputted
     * name, then the program returns that folder.
     */
    public Folder getFolder(String name){
        Folder returnFolder = null;

        if (name.equalsIgnoreCase("inbox")){
            throw new IllegalArgumentException("Cannot open inbox from here.");
        }
        else if (name.equalsIgnoreCase("trash")){
            throw new IllegalArgumentException("Cannot open trash from here.");
        }
        else {
            for (int i = 0; i < folders.size(); i++) {
                if (folders.get(i).getName().equalsIgnoreCase(name)) {
                    returnFolder = folders.get(i);
                    return returnFolder;
                }
            }
        }
        throw new IllegalArgumentException("Folder does not exist.");
    }

    /**
     * Helper method that just prints out the entire
     * mailbox table, along with any possible folders that
     * the user has created.
     */
    public void printMailboxTable(){
        int i = 0;
        System.out.println("Mailbox: ");
        while (i < 8){
            System.out.print("-");
            i++;
        }
        System.out.println("\nInbox\nTrash");

        if (!folders.isEmpty()) {
            for (int j = 0; j < folders.size(); j++) {
                System.out.println(folders.get(j).getName());
            }
        }
        System.out.println();
    }

    /**
     *
     * @param folder
     * Takes in the folder to print out its details.
     *
     * This helper method is called upon for the second half
     * of the program, which is the email itself. It creates a
     * table, and after the table, it prints the contents of the
     * emails at each iteration from a for loop.
     */
    public void printFolderTable(Folder folder){
        System.out.printf("%-1s%-6s%-1s%-10s%-4s%-10s%-1s%-5s%-7s%-5s%n",
                "", "Index", "|", "", "Time", "", "|", "", "Subject", "");
        int count = 0;
        while (count < 50){
            System.out.print("-");
            count++;
        }

        SimpleDateFormat format = new SimpleDateFormat("hh:mm:ss a MM/dd/yyyy");

        System.out.println();
        for (int i = 0; i < folder.getEmails().size(); i++){
            Date timestamp = folder.getEmails().get(i).getDateTime();
            String formattedDateTime = format.format(timestamp);

            System.out.printf("%-1s%-6d%-2s%-23s%-2s%-16s%n",
                    "", i + 1, "| ", formattedDateTime,
                    "| ", folder.getEmails().get(i).getSubject());
        }
    }


    public static void main(String[] args){ //Test class
        String fileName = "mailbox.obj";
        //Creates an .obj to be read and written for saving.


        try { //Based off homework document.
            // When ran again, it reads the file of the saved file.
            FileInputStream file = new FileInputStream(fileName);
            ObjectInputStream inStream = new ObjectInputStream(file);

            mailbox = (Mailbox) inStream.readObject();
            System.out.println("Welcome back! Resuming previous activities.");
            inStream.close();
        }
        //Catch cases
        catch (FileNotFoundException e){
            try { //If file is not found, then it creates a new file.
                //Based off homework document.
                FileOutputStream file = new FileOutputStream(fileName);
                ObjectOutputStream outStream = new ObjectOutputStream(file);

                System.out.println
                        ("Previous save not found. Starting with an empty mailbox.\n");
                outStream.writeObject(new Mailbox());
                outStream.close();
            }
            catch (FileNotFoundException fileNotFoundException) {
                System.out.println("File not found exception.\n");
            }
            catch (IOException ioException) {
                e.getStackTrace();
            }
        }
        catch (IOException e){
            System.out.println("Input/Output error.\n");
        }
        catch (ClassNotFoundException e) {
            System.out.println("Class not found error.\n");
        }

        Scanner input = new Scanner(System.in); //Scanner object
        String userInput; //User's input
        boolean bool = true; //Loops until false
        String folderName; //Folder's name

        while (bool) { //Infinite loop until 'Q'
            try {
                mailbox.printMailboxTable(); //helper method to print mailbox
                System.out.println("A – Add folder\n" +
                        "R – Remove folder\n" +
                        "C – Compose email\n" +
                        "F – Open folder\n" +
                        "I – Open Inbox\n" +
                        "T – Open Trash\n" +
                        "E - Clear Trash\n" +
                        "Q – Quit and save\n"+
                        "X - Quit and delete");
                System.out.print("Enter a user option: ");
                userInput = input.nextLine().toUpperCase();
                System.out.println();

                switch (userInput) {
                    case "A": //Adds a folder
                        System.out.print("Enter folder name: ");
                        folderName = input.nextLine();
                        Folder folder = new Folder(folderName); //Creates a new folder
                        folder.setName(folderName); //Sets the folder name
                        mailbox.addFolder(folder); //Adds the folder to the mailbox
                        break;

                    case "R": //Removes a folder
                        System.out.print("Enter the folder name to remove: ");
                        folderName = input.nextLine();
                        mailbox.deleteFolder(folderName);
                        System.out.println("\nFolder deleted.\n");
                        break;

                    case "C": //Composes an email
                        mailbox.composeEmail();
                        System.out.println("\nEmail successfully added to Inbox.\n");
                        break;

                    case "F": //Gets that folder and displays menu of editing options.
                        System.out.print("Enter folder name: ");
                        folderName = input.nextLine();

                        folder = mailbox.getFolder(folderName);
                        if (folder != null) {
                            System.out.println("\n" + folderName + ":\n");
                            mailbox.folderSubMenu(folder);
                        }
                        else{
                            throw new IllegalArgumentException
                                    ("Folder name: " + folderName + " does not exist.");
                        }

                        break;

                    case "I": //Displays inbox and displays menu of editing options.
                        System.out.println("Inbox:\n");
                        folder = mailbox.getInbox();
                        mailbox.folderSubMenu(folder);
                        break;

                    case "T": //Displays trash and displays menu of editing options
                        System.out.println("Trash:\n");
                        folder = mailbox.getTrash();
                        mailbox.folderSubMenu(folder);
                        break;

                    case "E": //Empties the trash
                        int sizeOfTrash = mailbox.getTrash().getEmails().size();
                        mailbox.clearTrash();
                        System.out.println("Trash has been emptied! "
                                + sizeOfTrash + " item(s) deleted.\n");
                        break;

                    case "Q": //Quits the mailbox manager and saves for future use.
                        FileOutputStream file = new FileOutputStream(fileName);
                        System.out.println("does it get here? check 1");
                        ObjectOutputStream outStream = new ObjectOutputStream(file);

                        System.out.println("does it get here? check 2");
                        outStream.writeObject(mailbox);
                        System.out.println("does it get here? check 3");
                        outStream.close();
                        System.out.println("does it get here? check 4");

                        System.out.println("Program successfully exited and mailbox saved.");

                        bool = false;
                        break;

                    case "X": //Quits the mailbox manager but deletes the .obj file
                        //Personally added this.
                        System.out.println("Closing...");
                        File newFile = new File(fileName);
                        newFile.deleteOnExit();
                        System.out.println("All data has been deleted.");
                        bool = false;
                        break;

                    default: //Default case
                        System.out.println("Please enter a viable choice. Try again.\n ");
                        break;
                }
            }
            //Catch cases for exceptions
            catch (IllegalArgumentException e){
                System.out.println(e.getMessage() + "\n");
            }
            catch (InputMismatchException e){
                System.out.println("Put the right formatted value. Try again.\n");
            }
            catch (FileNotFoundException e) {
                System.out.println("File cannot be found.\n");
            }
            catch (IOException e) {
                e.getCause();
//                System.out.println("IO Error.\n");
            }
        }
    }

    /**
     *
     * @param folder
     * Takes in folder for the submenu
     *
     * This helper method is used in the main method,
     * which is everytime the user attempts to work on
     * the folder, this pops up, where it gives additional
     * options for the user to edit on the folder itself.
     * It runs off an infinite while loop, as it should until
     * the user prompts 'R', which is return, ending the entire
     * void method entirely and going back to the main method, or
     * the mailbox.
     */
    public void folderSubMenu(Folder folder){
        Scanner input = new Scanner(System.in);
        String userInput;
        boolean bool = true; //loops
        int index;
        String folderName;
        Email email;
        Folder target = new Folder("");
        String view;

        while(bool){ //Loops until return or 'R'
            try{
                if (folder.getEmails().isEmpty()){
                    System.out.println(folder.getName() + " is empty!");
                }
                else {
                    mailbox.printFolderTable(folder);
                }
                System.out.println("\nM - Move email\n"
                        +"D - Delete email\n"
                        +"V - View email contents\n"
                        +"SA - Sort by subject ascending\n"
                        +"SD - Sort by subject descending\n"
                        +"DA - Sort by date ascending\n"
                        +"DD - Sort by date descending\n"
                        +"R - Return to main menu\n");
                System.out.print("Enter a choice: ");
                userInput = input.nextLine().toUpperCase();
                System.out.println();

                switch(userInput){
                    case "M": //Moves email
                        System.out.print("Enter the index of the email to move: ");
                        index = input.nextInt();
                        System.out.println();
                        input.nextLine();

                        mailbox.printMailboxTable();

                        email = folder.removeEmail(index);
                        System.out.print
                                ("Select a folder to move \"" + email.getSubject() + "\" to: ");

                        folderName = input.nextLine();
                        target.setName(folderName);

                        mailbox.moveEmail(email, target);
                        break;

                    case "D": //Deletes email
                        System.out.print("Enter the email index to delete: ");
                        index = input.nextInt();
                        input.nextLine();

                        email = folder.removeEmail(index);
                        System.out.println
                                ("\n\"" + email.getSubject() +
                                        "\" has successfully been moved to the trash.\n");

                        mailbox.deleteEmail(email);
                        break;

                    case "V": //Views email content
                        System.out.print("Enter email index to view: ");
                        index = input.nextInt();
                        input.nextLine();

                        if (index > folder.getEmails().size()){
                            throw new IllegalArgumentException("There is no email at that index.");
                        }

                        System.out.println("\n" + folder.getName() + ": \n");

                        view = folder.getEmails().get(index - 1).view();

                        System.out.println (view + "\n");
                        break;

                    case "SA": //Sort by Ascending Subject Order
                        folder.sortBySubjectAscending();
                        break;

                    case "SD": //Sorts by Descending Subject Order
                        folder.sortBySubjectDescending();
                        break;

                    case "DA": //Sorts by Ascending Date Order
                        folder.sortByDateAscending();
                        break;

                    case "DD": //Sorts by Descending Date Order
                        folder.sortByDateDescending();
                        break;

                    case "R": //Exits out of this helper method and goes back to main
                        return;

                    default: //Default case
                        System.out.println("Please enter a viable option. Try again.\n");
                        break;
                }
            }
            //Catch cases for exceptions
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage() + "\n");
            }
        }
    }
}
