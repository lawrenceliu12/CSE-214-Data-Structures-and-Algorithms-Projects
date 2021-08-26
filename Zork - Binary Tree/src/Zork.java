/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #5
 * CSE 214
 * R04 - James Finn / Taylor Ngo
 */

//Imported java classes.
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.zip.DataFormatException;

public class Zork { //Zork class
    public static void main (String[] args){ //main method
        Scanner input = new Scanner(System.in);

        String fileName = "";
        String stringInput = "";
        boolean bool = true; //repeats while loop
        int count = 1;

        StoryTree storyTree; //StoryTree object
        System.out.println("Hello and welcome to Zork!\n");
        System.out.print("Please enter the file name: ");
        fileName = input.nextLine();
        System.out.println("\nLoading game from file...");

        try{
            storyTree = StoryTree.readTree(fileName);
            while(bool) { //while loop
                if (count == 1) {
                    System.out.println("\nFile loaded!");
                }
                System.out.println("Would you like to...");
                System.out.println("(E) Edit");
                System.out.println("(P) Play");
                System.out.println("(Q) Quit");
                System.out.println();
                System.out.print("Enter your input: ");
                stringInput = input.nextLine().toUpperCase();
                switch (stringInput) {
                    case "E": //Edits tree
                        editTree(storyTree);
                        break;

                    case "P": //Plays tree
                        playTree(storyTree);
                        break;

                    case "Q": //Quits the loop and saves tree into text file.
                        bool = false;
                        System.out.println("Game being saved to " + fileName + "...");
                        StoryTree.saveTree(fileName, storyTree);
                        System.out.println("\nSave successful!");
                        System.out.println("Program terminating normally...");
            }
            count++; //To stop the first time print.
        }
    }
        catch (DataFormatException e){ //Catches errors.
            System.out.println("Error in formatting data.");
        }
}

    /**
     *
     * @param tree
     *
     * Edit tree
     */
    public static void editTree(StoryTree tree){
        Scanner input = new Scanner(System.in);
        String strInput = "";
        boolean bool = true;
        int count = 0;

        System.out.println("Zork Editor: ");
        System.out.println("\tV: View the cursor's position, option and message.");
        System.out.println("\tS: Select a child of this cursor " +
                "(options are 1, 2, and 3).");
        System.out.println("\tO: Set the option of the cursor.");
        System.out.println("\tM: Set the message of the cursor.");
        System.out.println("\tA: Add a child StoryNode to the cursor.");
        System.out.println
                ("\tD: Delete one of the cursor's children and all its descendants.");
        System.out.println("\tR: Move the cursor to the root of the tree.");
        System.out.println("\tQ: Quit editing and return to main menu.");

        System.out.println();

        try {
            tree.selectChild("1"); //Starts the tree at 1 rather than root.
        }
        catch (InvalidArgumentException e){
            System.out.println(e.getMessage());
        }
        catch (NodeNotPresentException e){
            System.out.println("There is no node.");
        }

        while (bool) { //While loop, loops until Q.
            if (count > 0){
                System.out.println();
            }
            System.out.print("Please select an option: ");
            strInput = input.next().toUpperCase();
            try {
                switch (strInput) {
                    case "V": //Gets position, option, message of cursor.
                        System.out.println("Position: " + tree.getCursorPosition());
                        System.out.println("Option: " + tree.getCursorOptions());
                        System.out.println("Message: " + tree.getCursorMessage());
                        break;

                    case "S": //Selects children
                        try {
                            String select = "";
                            int childrenCheck = tree.getOptions().length;
                            if (childrenCheck == 1) {
                                System.out.print("Please select a child [1]: ");
                                //tempSelect = cursor.getLeftChild();
                                select = input.next();
                                while (!(select.equals("1"))) {
                                    System.out.print("Enter a valid number (1): ");
                                    select = input.next();
                                }
                                tree.selectChild(select);
                            }
                            else if (childrenCheck == 2) {
                                System.out.print("Please select a child [1, 2]: ");
                                select = input.next();
                                while (!(select.equals("1")) && !(select.equals("2"))) {
                                    System.out.print("Enter a valid number (1, 2): ");
                                    select = input.next();
                                }
                                tree.selectChild(select);
                            }
                            else if (childrenCheck == 3) {
                                System.out.print("Please select a child [1, 2, 3]: ");
                                select = input.next();
                                while ((!(select.equals("1")) && !(select.equals("2")) && !(select.equals("3")))) {
                                    System.out.print("Enter a valid number (1, 2, 3): ");
                                    select = input.next();
                                }
                                tree.selectChild(select);
                            }
                            else {
                                System.out.println("Error. No child for current node.");
                            }
                            /*
                            int numInput;
                            System.out.print("Please select a child[1,2,3]: ");
                            numInput = input.nextInt();

                            if (numInput == 1){
                                tempSelect = cursor.getLeftChild();
                                cursor.setLeftChild(tempSelect);
                            }
                            else if (numInput == 2){
                                tempSelect = cursor.getMiddleChild();
                                cursor.setMiddleChild(tempSelect);
                            }
                            else if (numInput == 3){
                                tempSelect = cursor.getRightChild();
                                cursor.setMiddleChild(tempSelect);
                            }
                            else{
                                System.out.println("Error. No child " + numInput + " for the current node.");
                            }

                             */
                        }
                        //Catches exceptions.
                        catch (IllegalArgumentException e) {
                            System.out.println("Error.");
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Error in inputting data.");
                        }
                        catch (NodeNotPresentException e){
                            System.out.println("No child.");
                        }
                        catch (InvalidArgumentException e){
                            System.out.println("Invalid. Error occurred.");
                        }
                        break;

                    case "O": //Sets options
                        try{
                            input.nextLine();
                            System.out.print("Please enter a new option: ");
                            String optionInput = input.nextLine();
                            tree.setCursorOption(optionInput);
                            System.out.println("\nOption set.");
                        }
                        //Catches exceptions
                        catch (IllegalArgumentException e){
                            System.out.println("Error.");
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Error in inputting data.");
                        }
                        break;

                    case "M": //Sets message
                        try{
                            input.nextLine();
                            System.out.print("Please enter a new message: ");
                            String messageInput = input.nextLine();
                            tree.setCursorMessage(messageInput);
                            System.out.println("\nMessage set.");
                        }
                        //catches exceptions
                        catch (IllegalArgumentException e){
                            System.out.println("Error.");
                        }
                        catch (InputMismatchException e) {
                            System.out.println("Error in inputting data.");
                        }
                        break;

                    case "A": //Adds children
                        try {
                            input.nextLine();
                            System.out.print("Enter an option: ");
                            String optionInput = input.nextLine();
                            System.out.print("Enter a message: ");
                            String messageInput = input.nextLine();

                            tree.addChild(optionInput, messageInput);
                            System.out.println("\nChild added.");
                        }
                        //catches exceptions
                        catch (TreeFullException e){
                            System.out.println("Tree is full.");
                        }
                        catch (InvalidArgumentException e){
                            System.out.println("Error.");
                        }
                        catch (InputMismatchException e){
                            System.out.println("Error in inputting data.");
                        }
                        break;

                    case "D": //Deletes children and subtrees
                        try {
                            //StoryTreeNode children = new StoryTreeNode
                            //       (tree.getCursorPosition(), tree.getCursorOptions(), tree.getCursorMessage());
                            int childrenCheck = tree.getOptions().length;
                            String removeInput;
                            if (childrenCheck == 1) {
                                System.out.print("Please select a child [1]: ");
                                removeInput = input.next();
                                while (!(removeInput.equals("1"))) {
                                    System.out.print("Enter a valid number (1): ");
                                    removeInput = input.next();
                                }
                                tree.removeChild(removeInput);
                                System.out.println("\nSubtree deleted.");
                            }
                            else if (childrenCheck == 2) {
                                System.out.print("Please select a child [1, 2]: ");
                                removeInput = input.next();
                                while (!(removeInput.equals("1")) && !(removeInput.equals("2"))) {
                                    System.out.print("Enter a valid number (1, 2): ");
                                    removeInput = input.next();
                                }
                                tree.removeChild(removeInput);
                                System.out.println("\nSubtree deleted.");
                            }
                            else if (childrenCheck == 3) {
                                System.out.print("Please select a child [1, 2, 3]: ");
                                removeInput = input.next();
                                while (!(removeInput.equals("1")) && !(removeInput.equals("2")) && !(removeInput.equals("3"))) {
                                    System.out.print("Enter a valid number (1): ");
                                    removeInput = input.next();
                                }
                                tree.removeChild(removeInput);
                                System.out.println("\nSubtree deleted.");
                            }
                            else {
                                System.out.println("There are no children.");
                            }
                        }
                        //catches exceptions
                        catch (NodeNotPresentException e){
                            System.out.println("There is no node.\n");
                        }
                        catch (InputMismatchException e){
                            System.out.println("Inputted data is not correct.\n");
                        }
                        catch(IllegalArgumentException e){
                            System.out.println("Error.\n");
                        }
                        break;

                    case "R": //Resets cursor to "1"
                        tree.resetCursorToTree();
                        System.out.println("Cursor has moved to root.");
                        break;

                    case "Q": //Quits the while loop.
                        bool = false;
                        System.out.println("Quitting the editing menu...");
                        System.out.println("\nReturning to the main menu...");
                        break;

                    default: //default
                        System.out.println("Please enter a valid letter provided in the menu.");
                        break;
                }
            }//Catches exceptions
            catch (InputMismatchException e){
                System.out.println("Please input the necessary data.");
            }
            catch (IllegalArgumentException e){
                System.out.println("Error.");
            }
            count++;
        }
    }

    /**
     *
     * @param tree
     * For playing the tree or zork game.
     */
    public static void playTree (StoryTree tree){
        Scanner input = new Scanner(System.in);
        boolean bool = true; //While loop
        String strInput = "";

        tree.resetCursorToTree(); //Resets to "1"
        System.out.println("\n" + tree.getCursor().getOption());
        try{
            while(bool){ //Infinite loop
                System.out.println(tree.getCursorMessage());
                for (int i = 0; i < tree.getOptions().length; i++){ //Iterates through the length
                    String playTreeString = "";
                    if (tree.getOptions()[i][0] == null || tree.getOptions()[i][1] == null){
                        break;
                    }
                    else{
                        int num = tree.getOptions()[i][0].length();
                        playTreeString += tree.getOptions()[i][0].substring(num - 1, num) + ") ";
                        playTreeString += tree.getOptions()[i][1];
                        System.out.println(playTreeString);
                    }
                }

                System.out.print("\nPlease make a choice: ");
                try {
                    strInput = input.nextLine();
                    tree.selectChild(strInput);
                } //catches errors
                catch (NodeNotPresentException e){
                    System.out.println("Invalid input.");
                }
                catch (InvalidArgumentException e){
                    System.out.println("Error.");
                }
                //if string.equals(winning_message or losing_message) -> bool = false;
                if (tree.getCursor().isLosingNode() || tree.getCursor().isWinningNode()){ //Ends game.
                    System.out.println(tree.getCursorMessage());
                    System.out.println("Thank you for playing!\n\n");
                    bool = false;
                }
            }
        }
        catch(InputMismatchException e){
            System.out.println("Input is wrong.");
        }
        catch(IllegalArgumentException e){
            System.out.println("Error.");
        }
    }

    /**
     *
     * @param child
     * @return
     *
     * Not used, but returns number of Children.
     */
    public static int childCount(StoryTreeNode child){
        int count;
        if (child.getLeftChild() != null
                && child.getMiddleChild() != null
                && child.getRightChild() != null){
            count = 3;
            return count;
        }
        else if (child.getLeftChild() != null
                && child.getMiddleChild() != null
                && child.getRightChild() == null){
            count = 2;
            return count;
        }
        else if (child.getLeftChild() != null
                && child.getMiddleChild() == null
                && child.getRightChild() != null){
            count = 2;
            return count;
        }
        else if (child.getLeftChild() == null
                && child.getMiddleChild() != null
                && child.getRightChild() != null){
            count = 2;
            return count;
        }
        else if (child.getLeftChild() != null
                && child.getMiddleChild() == null
                && child.getRightChild() == null){
            count = 1;
            return count;
        }
        else if (child.getLeftChild() == null
                && child.getMiddleChild() != null
                && child.getRightChild() == null){
            count = 1;
            return count;
        }
        else if (child.getLeftChild() == null
                && child.getMiddleChild() == null
                && child.getRightChild() != null){
            count = 1;
            return count;
        }
        else{
            count = 0;
            return count;
        }
    }
}
