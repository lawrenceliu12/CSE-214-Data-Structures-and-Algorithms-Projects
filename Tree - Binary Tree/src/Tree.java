/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #4
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

import java.io.File; //Imports File
import java.io.FileNotFoundException; //Imports Exception for File
import java.util.InputMismatchException; //Imports Input Mismatch Exception
import java.util.Scanner; //Imports scanner
import java.util.zip.DataFormatException; //Imports Exception for Data Format

public class Tree { //Tree class
    private TreeNode root; //Reference to root node.
    private TreeNode cursor; //Reference to a cursor node.

    /**
     * Default constructor, creates a root node with given parameters.
     */
    public Tree() {
        root = new TreeNode("", "", "");
    }

    /**
     *
     * @param label
     * label of node
     * @param prompt
     * prompt of node
     * @param message
     * message of node
     * @param parentLabel
     * the parent node's label
     * @return
     * returns true or false whether a node was able to be added
     *
     * This method takes gets the node reference of the parent node,
     * and depending on the parent node, this method will add accordingly
     * to the tree as long as the parent node is not null.
     *
     * Important: This method was not implemented for root and children of root.
     * This method is only implemented for working on any node past the root.
     *
     * There are other helper methods that work to add nodes onto the tree
     * for root and the children of the root.
     */
    public boolean addNode
        (String label, String prompt, String message, String parentLabel){
        //Gets the parent node.
        TreeNode parentNode = getNodeReference(parentLabel);
        //Creates a new node to be added onto tree.
        TreeNode treeNode = new TreeNode(label, prompt, message);
        boolean returnValue = false; //return true or false
         if (parentNode.getLeft() == null) { //Adds to left
             parentNode.setLeft(treeNode);
             returnValue = true;
         }
         else if (parentNode.getMiddle() == null
                 && parentNode.getLeft() != null) { //Adds to middle
             parentNode.setMiddle(treeNode);
             returnValue = true;
         }
         else if (parentNode.getRight() == null
                 && parentNode.getMiddle() != null
                 && parentNode.getLeft() != null) { //Adds to right
             parentNode.setRight(treeNode);
             returnValue = true;
        }
        return returnValue;
    }

    /**
     *
     * @param label
     * Gets the node based off label
     * @return
     *
     * This method was inspired by my last binary tree project.
     *
     * This method returns the reference to the TreeNode with
     * the specified label. Returns null if label is not found.
     *
     * Used for the AddNode method.
     *
     * This method works by splitting the label into an array
     * of Strings by splitting by "-". From then, it traverses
     * through the array by a for loop, where if it is equal to
     * "1", it traverses left, "2", it traverses middle, "3",
     * it traverses right.
     */
    public TreeNode getNodeReference(String label) {
        cursor = root;
        if (root.isLeaf()) {
            return null;
        }
        else if (root.getLabel().equalsIgnoreCase(label)) {
            return root;
        }
        String[] split = splitter(label);

        for (String s : split) {
            if (cursor != null) {
                switch (s) {
                    case "1":
                        if (cursor.getLeft() == null) {
                            throw new IllegalArgumentException("Node is not present.");
                        }
                        cursor = cursor.getLeft();
                        break;
                    case "2":
                        if (cursor.getMiddle() == null) {
                            throw new IllegalArgumentException("Node is not present.");
                        }
                        cursor = cursor.getMiddle();
                        break;
                    case "3":
                        if (cursor.getRight() == null) {
                            throw new IllegalArgumentException("Node is not present.");
                        }
                        cursor = cursor.getRight();
                        break;
                    default:
                        return null;
                }
            }
        }
        if (cursor != null) {
            return cursor;
        }
        return null;
    }

    /**
     * Original method for printing out PreOrder.
     * Utilized a helper method by taking in a
     * reference to the cursor, which is at root.
     */
    public void preOrder() {
        resetCursor();
        if (cursor != null) {
            printPreOrder(cursor);
        }
    }

    /**
     *
     * @param treeNode
     * cursor or whatever node you want the preorder traversal on.
     *
     * This method prints out the entire information of the node while
     * traversing by preorder. It is a recursive statement that returns
     * whenever the method reaches the end of the tree or completes
     * the traversal.
     *
     */
    public static void printPreOrder(TreeNode treeNode) {
        if (treeNode == null) {
            return;
        }
        System.out.println("Label: " + treeNode.getLabel()
                + "\nPrompt: " + treeNode.getPrompt()
                + "\nMessage: " + treeNode.getMessage() + "\n");
        printPreOrder(treeNode.getLeft());
        printPreOrder(treeNode.getMiddle());
        printPreOrder(treeNode.getRight());
    }

    /**
     * This method is used for TreeDriver's "H" function, which
     * prints the helping session for the user to find out and
     * solve their problem.
     * It uses a while loop that is initiated to true until
     * the loop is false, which is when the cursor is moved till it
     * is a leaf. However, the user can choose to end the session anytime
     * by inputting 0.
     *
     * For Extra Credit: I utilized a "B" and "C", where "B" backtracks to
     * the past node. I do this by initializing a temp node as cursor before
     * the cursor traverses. Afterwards, if the user inputs B, they have the
     * cursor's previous location's information stored, so I just call upon
     * cursor = temp cursor. On the other hand, "C" is a way of checking currency,
     * which I will be more in depth in TreeDriver class per the HW Doc's instructions.
     */
    public void beginSession() {
        Scanner input = new Scanner(System.in);
        boolean bool = true;
        String userInput = "";
        boolean checker = false;
        resetCursor();
        TreeNode temp = null;
        double leftCurrency = 1.5;
        double middleCurrency = 2;
        double rightCurrency = 2.5;
        double currency = 10.00;
        int count = 0;
        String tempInput = "";

        System.out.println("Help session starting... \n");


        try {
            while (bool) {
                System.out.println(cursor.getMessage());
                if (cursor.isLeaf()) {
                    String roundedCurrency = round(currency);
                    System.out.println
                            ("Your estimated subtotal for our service " +
                                    "and maintenance fees/supplies is: $" + roundedCurrency);
                    System.out.println("\nThank you for using our automated help services!\n");
                    break;
                }

                else {
                    if (cursor.getLeft() != null) {
                        System.out.println("1) " + cursor.getLeft().getPrompt());
                    }
                    if (cursor.getMiddle() != null) {
                        System.out.println("2) " + cursor.getMiddle().getPrompt());
                    }
                    if (cursor.getRight() != null) {
                        System.out.println("3) " + cursor.getRight().getPrompt());
                    }
                    System.out.println("B) Go backwards. ");
                    System.out.println("C) Check current currency. ");
                    System.out.println("0) Exit session.");
                }

                System.out.print("Choice: ");
                tempInput = userInput;
                userInput = input.nextLine().toUpperCase();
                System.out.println();

                if (cursor.getLeft() != null) {
                    checker = true;

                }
                if (cursor.getMiddle() != null) {
                    checker = true;
                }
                if (cursor.getRight() != null) {
                    checker = true;
                }

                if (userInput.equals("0")){
                    System.out.println("Ending session...\n");
                    bool = false;
                }

                switch (userInput + "-" + checker) {
                    case "1-true":
                        temp = cursor;
                        cursor = cursor.getLeft();
                        if (count == 0){
                            currency *= 1;
                            count++;
                        }
                        else {
                            currency *= leftCurrency;
                        }
                        break;
                    case "2-true":
                        temp = cursor;
                        cursor = cursor.getMiddle();
                        if (count == 0){
                            currency *= 1;
                            count++;
                        }
                        else {
                            currency *= middleCurrency;
                        }
                        break;
                    case "3-true":
                        temp = cursor;
                        cursor = cursor.getRight();
                        if (count == 0){
                            currency *= 1;
                            count++;
                        }
                        else {
                            currency *= rightCurrency;
                        }
                        break;
                    case "B-true":
                        if (temp == null){
                            System.out.println("No backtracking!\n");
                        }

                        switch (tempInput) {
                            case "1":
                                currency /= leftCurrency;
                                break;
                            case "2":
                                currency /= middleCurrency;
                                break;
                            case "3":
                                currency /= rightCurrency;
                                break;
                        }
                        cursor = temp;
                        break;
                    case "C-true":
                        if (currency == 10.00){
                            System.out.println("You have not made any choices yet. \n");
                        }
                        else {
                            System.out.println("The current currency is: $" + round(currency));
                        }
                        break;
                    default:
                        if (userInput.equals("0")){
                            continue;
                        }
                        System.out.println("Please enter a viable option...\n");
                        break;
                }
            }
        } catch (IllegalArgumentException e) { //Catches IllegalArgumentException
            System.out.println(e.getMessage());
        } catch (InputMismatchException e) { //Catches InputMismatchException
            System.out.println("Please enter the right format of option.");
        }
    }

    /**
     *
     * @param fileName
     * File Name to be read.
     * @return
     * Returns a tree that has been established by this method.
     * @throws FileNotFoundException
     * If the file is not of .txt.
     * @throws IllegalArgumentException
     * If File is either null, empty, or too big.
     * @throws DataFormatException
     * When there is trouble adding the node to the tree.
     *
     * This method calls upon a filename, which utilizes Java's
     * imported FileReader and Scanner. By using FileReader and
     * Scanner, I scan the file, where I am accessed to their
     * nextLine. From there, I am able to separate the text file
     * into specific subsections and add them separately to the tree.
     * Comments will specify. By using a for loop, we will always be
     * able to get the next place of sectioning, which is the
     * Parent childCount (x y) in the text document. By taking x,
     * we get the parentLabel, and taking y, we get the number of
     * children for the next for loop.
     */
    public static Tree readTree(String fileName)
            throws FileNotFoundException, IllegalArgumentException, DataFormatException{
        if (fileName == null || fileName.isEmpty()){
            throw new IllegalArgumentException("File is either null or empty.");
        }
        if (!fileName.contains(".txt")){
            throw new FileNotFoundException("Needs to be in .txt format.");
        }
        if (fileName.length() > 20){
            throw new IllegalArgumentException("File is too big.");
        }

        Tree tree = new Tree();

        try{
            File file = new File(fileName);
            Scanner treeFile = new Scanner(file);
            String label;
            String message;
            String prompt;
            String parentLabel;

            label = treeFile.nextLine().trim();
            prompt = treeFile.nextLine().trim();
            message = treeFile.nextLine().trim();
            tree.addNodeToRoot(label, prompt, message, label); //This adds to root.
            while (treeFile.hasNextLine()){
                parentLabel = treeFile.next().trim(); //Parent name
//                System.out.println("Parent Label!: " + parentLabel);
                int parse = treeFile.nextInt(); //Number of children
//                System.out.println("Count!: " + parse);
                treeFile.nextLine();
                //Todo: Anytime I add a node that is not the root,
                // I get a null pointer exception.
                // Basically, after reading the tree,
                // it only adds the root and nothing else. **Fixed**
                for (int i = 0; i < parse; i++){
                    label = treeFile.nextLine().trim();
                    prompt = treeFile.nextLine().trim();
                    message = treeFile.nextLine().trim();
                    boolean b = ((label.equals("1") ||
                            label.equals("2") ||
                            label.equals("3"))
                            && parentLabel.contains("root"));
                    if (b){ //Adds to the children of the root if conditions are met.
//                        System.out.println("P: " + parentLabel);
                        tree.addNodePastRoot(label, prompt, message, parentLabel);
                    }
                    else if (!b){
                        //Other than the root and its children, this adds the rest of the
                        //nodes onto the tree.
                        System.out.println("P: " + parentLabel);
                        tree.addNode(label, prompt, message, parentLabel);
                    }
                    else{
                        throw new DataFormatException("Error in formatting data.");
                    }
                }
            }
            tree.resetCursor(); //Goes back to root.
            treeFile.close(); //Closes the file.
        }
        catch (FileNotFoundException
                | IllegalArgumentException
                | DataFormatException e){ //Catches exception
            System.out.println(e.getMessage());
        }
        return tree; //Returns a tree from the text file.
    }

    /**
     *
     * @param label
     * Label of root node
     * @param prompt
     * Prompt of root node
     * @param message
     * Message of root node
     * @param parentLabel
     * Parent Label of root node, which is root or null.
     *
     * This helper method is called upon by ReadTree, another
     * helper method, in which this one changes or sets the label,
     * prompt, and message of the root. This kickstarts the entire
     * 3-ary tree.
     */
    public void addNodeToRoot
            (String label, String prompt, String message, String parentLabel){
        resetCursor();
        if (cursor.isLeaf()) {
            cursor.setLabel(label);
            cursor.setPrompt(prompt);
            cursor.setMessage(message);
        }
    }

    /**
     *
     * @param label
     * Label of node
     * @param prompt
     * Prompt of node
     * @param message
     * Message of node
     * @param parentLabel
     * Parent label of node.
     *
     * This helper method is the second method called upon in
     * ReadTree method. This method is after the root node was
     * set to its values, in which this creates children of the
     * root node under specific circumstances. The reason for this
     * helper method was because when sectioning the text file to
     * specific nodes, the root being a parent class was giving off
     * a problem in the AddNode method, so I decided to create this
     * helper method to just set everything in place for the rest
     * of the tree.
     */
    public void addNodePastRoot
            (String label, String prompt, String message, String parentLabel){
        TreeNode node = new TreeNode(label, prompt, message);
        resetCursor();
        if (parentLabel.contains("root")){
            if (cursor.getLeft() == null) {
                if (label.equals("1")) {
                    cursor.setLeft(node);
                }
            }
            else if (cursor.getMiddle() == null
                    && cursor.getLeft() != null) {
                if (label.equals("2")) {
                    cursor.setMiddle(node);
                }
            }
            else if (cursor.getRight() == null
                    && cursor.getMiddle() != null
                    && cursor.getLeft() != null) {
                if (label.equals("3")) {
                    cursor.setRight(node);
                }
            }
        }
    }

    /**
     *
     * @param str
     * String that gets split
     * @return
     * Returns an array of the String that was split.
     *
     * Helper method that makes it easier to split a String
     * into array by "-".
     */
    public String[] splitter(String str){
        return str.split("-");
    }

    /**
     * This helper method is just an easier way of realizing
     * that I have reset the cursor back to root.
     */
    public void resetCursor(){
        cursor = root;
    }

    /**
     *
     * @param val
     * Double value
     * @return
     * Returns a rounded value from the double to the
     * nearest hundredth. This is used for my extra credit.
     */
    public String round(double val){
        return String.format("%.2f", val);
    }

}
