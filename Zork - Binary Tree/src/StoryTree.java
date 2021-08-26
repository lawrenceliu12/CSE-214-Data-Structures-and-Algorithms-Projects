/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #5
 * CSE 214
 * R04 - James Finn / Taylor Ngo
 */

import java.io.PrintWriter; //Imported printWriter
import java.util.zip.DataFormatException; //Imported Data Format Exception
import java.io.File; // Imported File
import java.util.Scanner; //Imported Scanner
import java.io.FileNotFoundException; //Imported File Not Found Exception

//StoryTree Class
public class StoryTree {
    private StoryTreeNode root; //Root node
    private StoryTreeNode cursor; //Cursor node
    private GameState state; //game state, calls upon enum class

    /**
     * Constructor
     * Sets root as a new StoryTreeNode object
     * Sets cursor to root
     * Sets state to GAME_NOT_OVER from enum class.
     */
    public StoryTree(){
        root = new StoryTreeNode("root", "root", "Hello, welcome to Zork!");
        cursor = root;
        state = GameState.GAME_NOT_OVER;
    }

    /**
     *
     * @param filename
     * @return
     * @throws IllegalArgumentException
     * @throws DataFormatException
     *
     * Reads the tree/traverses the tree
     *
     */
    public static StoryTree readTree(String filename) throws IllegalArgumentException, DataFormatException {
        if (filename == null || filename.isEmpty()){ //Checks if file name is null or empty
            throw new IllegalArgumentException("File is either empty or null.");
        }

        if (!(filename.contains(".txt"))){ //Has to contain .txt
            throw new DataFormatException("Needs to be in .txt format.");
        }


        StoryTree storyTree = new StoryTree(); //Creates a new StoryTree object
        StoryTreeNode tempTreeNode; //Creates a temp StoryTreeNode object to traverse.

        try {
            File file = new File(filename); //Creates file object
            Scanner zorkFile = new Scanner(file); //Creates Scanner object
            String[] readFile; //String array of the read file/

            while (zorkFile.hasNextLine()) { //Loops until end of file.
                String str = zorkFile.nextLine(); //Reads the next line.
                readFile = str.split("\\|"); //Splits the |,
                // splitting to position | option | message


                if (readFile.length != 3) { //Has to be three | or else not right format.
                    throw new DataFormatException("Invalid. Need 3 \"|\"");
                }

                /* Tried using recursion for reading file,
                but does not implement correctly/work correctly.

                if (readFile[0].equals("1")){
                    tempTreeNode = storyTree.root;
                    tempTreeNode.setLeftChild(new StoryTreeNode(readFile[0], readFile[1], readFile[2]));
                    traverseReadTree(tempTreeNode, filename);
                }
                */

                String[] newReadFile = readFile[0].trim().split("-");
                //Trims the white space and splits - into newReadFile string array.
                //Basically the position.
                StoryTreeNode tempCursor = storyTree.root; //Sets the temp cursor to root.
                String position = readFile[0].trim(); //Trims the original position string.
                String option = readFile[1].trim(); //Trims the original option string
                String message = readFile[2].trim(); //Trims the original message string.

                tempTreeNode = new StoryTreeNode(position, option, message);
                //Creates a temp Tree Node as a new StoryTreeNode object using above strings.

                for (int i = 0; i < newReadFile.length - 1; i++) {
                    //Traverses through the position string.
                    if (newReadFile[i].equals("1")) { //If one, then go left.
                        tempCursor = tempCursor.getLeftChild();
                    } else if (newReadFile[i].equals("2")) { //If two, then go middle.
                        tempCursor = tempCursor.getMiddleChild();
                    } else if (newReadFile[i].equals("3")) { //If three, then go right.
                        tempCursor = tempCursor.getRightChild();
                    } else { //Else, throw an exception.
                        throw new DataFormatException("Error in formatting data.");
                    }
                }

                if (newReadFile[newReadFile.length - 1].contains("1")) {
                    //Checks if it contains 1 in the end, and sets the tree at cursor.
                    tempCursor.setLeftChild(tempTreeNode);
                }
                else if (newReadFile[newReadFile.length - 1].contains("2")) {
                    //Checks if it contains 2 in the end, and sets the tree at cursor.
                    tempCursor.setMiddleChild(tempTreeNode);
                }
                else if (newReadFile[newReadFile.length - 1].contains("3")) {
                    //Checks if it contains 3 in the end, and sets the tree at cursor.
                    tempCursor.setRightChild(tempTreeNode);
                }
                else {
                    //Else, throw an error.
                    throw new DataFormatException("Error.");
                }
            }
            storyTree.resetCursor(); //Resets cursor to root.
            zorkFile.close(); //Closes the text file.
            return storyTree; //Returns storyTree.

        }
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
        }
        catch (DataFormatException e) {
            System.out.println("Data format error.");
        }
        /*
        Additional catch methods if I used a different way.
        catch(InvalidArgumentException e){
            System.out.println("Error.");
        }
        catch(TreeFullException e){
            System.out.println("Tree full.");
        }
        catch(NodeNotPresentException e){
            System.out.println("Node is not present.");
        }

         */
        return storyTree; //Return outside of catch
    }

    /**
     *
     * @param treeNode
     * @param fileName
     * NOT USED. Tried to apply recursive traversal through tree to read the tree.
     *
     */
    public static void traverseReadTree(StoryTreeNode treeNode, String fileName){
        try{
            if (treeNode == null){
                return;
            }
            String next = "";
            String[] nextStr;
            File zorkFile = new File(fileName);
            Scanner file = new Scanner(zorkFile);
            next = file.nextLine();
            nextStr = next.trim().split("\\|");

            while(file.hasNextLine()) {
                next = file.nextLine();
                nextStr = next.trim().split("\\|");
                if ((treeNode.getPosition() + "-1").equals(nextStr[0])) {
                    treeNode.setLeftChild(new StoryTreeNode(treeNode.getPosition(), treeNode.getOption(), treeNode.getMessage()));
                    traverseReadTree(treeNode.getLeftChild(), fileName);
                }
                else if ((treeNode.getPosition() + "-2").equals(nextStr[0])) {
                    treeNode.setMiddleChild(new StoryTreeNode(treeNode.getPosition(), treeNode.getOption(), treeNode.getMessage()));
                    traverseReadTree(treeNode.getMiddleChild(), fileName);
                }
                else if ((treeNode.getPosition() + "-3").equals(nextStr[0])) {
                    treeNode.setRightChild(new StoryTreeNode(treeNode.getPosition(), treeNode.getOption(), treeNode.getMessage()));
                    traverseReadTree(treeNode.getRightChild(), fileName);
                }
            }
        }
        catch (FileNotFoundException e) {
            System.out.println("File error.");
        }
    }

    /**
     *
     * @param filename
     * @param tree
     * @throws IllegalArgumentException
     *
     * Uses two other helper methods that saves the tree after user is done.
     */
    public static void saveTree(String filename, StoryTree tree) throws IllegalArgumentException{
        if (filename == null || filename.isEmpty()){ //Checks if the filename is empty or null.
            throw new IllegalArgumentException("File is either empty or null.");
        }
        if (tree == null){ //Checks if tree is node.
            throw new IllegalArgumentException("Tree is null.");
        }
        try {
            File saveTreeFile = new File(filename); //Creates file object
            PrintWriter outputFile = createPrintWriter(saveTreeFile);
            //Creates PrintWriter object
            traversePreOrderTree(tree.root.getLeftChild(), filename, outputFile);
            //Traverses the PreOrderTree to print out text file.
            outputFile.close(); //Closes the printWriter.
        }
        catch(FileNotFoundException e){ //Catch exception.
            System.out.println("File is not found.");
        }
    }

    /**
     *
     * @param file
     * @return
     * @throws FileNotFoundException
     *
     * Helper method to create PrintWriter.
     */
    public static PrintWriter createPrintWriter(File file) throws FileNotFoundException {
        return new PrintWriter(file);
    }

    /**
     *
     * @param treeNode
     * @param filename
     * @param writer
     *
     * Recursive method to traverse and print the text file.
     */
    public static void traversePreOrderTree (StoryTreeNode treeNode, String filename, PrintWriter writer){
        if (treeNode == null) {
            return;
        }

        String words = treeNode.getPosition()
                + " | " + treeNode.getOption()
                + " | " + treeNode.getMessage();
        writer.write(words + "\n");

        traversePreOrderTree(treeNode.getLeftChild(), filename, writer);
        traversePreOrderTree(treeNode.getMiddleChild(), filename, writer);
        traversePreOrderTree(treeNode.getRightChild(), filename, writer);

    }

    //GETTERS / SETTERS
    /**
     *
     * @return
     * @throws IllegalArgumentException
     */
    public GameState getState() throws IllegalArgumentException{
        if (state == null) {
            throw new IllegalArgumentException("State is null");
        }
        return state;
    }

    /**
     *
     * @return
     */
    public String getCursorPosition(){
        return cursor.getPosition();
    }

    /**
     *
     * @return
     */
    public String getCursorMessage(){
        return cursor.getMessage();
    }

    /**
     *
     * @return
     */
    public String getCursorOptions(){
        return cursor.getOption();
    }

    /**
     *
     * @return
     */
    public StoryTreeNode getCursor(){
        return cursor;
    }

    /**
     *
     * @return
     * Gets a double array of position and their options.
     */
    public String[][] getOptions() {
        String[][] option = new String[childrenCount(cursor)][2];

        for (int i = 0; i < option.length; i++) {
            for (int k = 0; k < option[i].length; k++) {
                if (i == 0) {
                    switch (k) {
                        case 0:
                            option[i][k] = cursor.getLeftChild().getPosition();
                            break;
                        case 1:
                            option[i][k] = cursor.getLeftChild().getOption();
                            break;
                        default:
                            break;
                    }
                }
                else if (i == 1) {
                    switch (k) {
                        case 0:
                            option[i][k] = cursor.getMiddleChild().getPosition();
                            break;
                        case 1:
                            option[i][k] = cursor.getMiddleChild().getOption();
                            break;
                        default:
                            break;
                    }
                } else {
                    switch (k) {
                        case 0:
                            option[i][k] = cursor.getRightChild().getPosition();
                            break;
                        case 1:
                            option[i][k] = cursor.getRightChild().getOption();
                            break;
                        default:
                            break;
                    }
                }
            }
        }
        return option;
    }

    /**
     *
     * @param message
     */
    public void setCursorMessage(String message){
        cursor.setMessage(message);
    }

    /**
     *
     * @param option
     */
    public void setCursorOption(String option){
        cursor.setOption(option);
    }

    //END OF GETTERS / SETTERS
    /**
     * Resets cursor to root.
     */
    public void resetCursor(){
        cursor = root;
    }

    /**
     *
     */
    public void resetCursorToTree(){
        cursor = root.getLeftChild();
    }

    /**
     *
     * @param children
     * @return
     *
     * returns number of children.
     */
    public static int childrenCount(StoryTreeNode children){
        int count;
        if (children.getLeftChild() != null
                && children.getMiddleChild() != null
                && children.getRightChild() != null){
            count = 3;
            return count;
        }
        else if (children.getLeftChild() != null
                && children.getMiddleChild() != null
                && children.getRightChild() == null){
            count = 2;
            return count;
        }
        else if (children.getLeftChild() != null
                && children.getMiddleChild() == null
                && children.getRightChild() != null){
            count = 2;
            return count;
        }
        else if (children.getLeftChild() == null
                && children.getMiddleChild() != null
                && children.getRightChild() != null){
            count = 2;
            return count;
        }
        else if (children.getLeftChild() != null
                && children.getMiddleChild() == null
                && children.getRightChild() == null){
            count = 1;
            return count;
        }
        else if (children.getLeftChild() == null
                && children.getMiddleChild() != null
                && children.getRightChild() == null){
            count = 1;
            return count;
        }
        else if (children.getLeftChild() == null
                && children.getMiddleChild() == null
                && children.getRightChild() != null){
            count = 1;
            return count;
        }
        else{
            count = 0;
            return count;
        }
    }

    /**
     *
     * @param position
     * @throws InvalidArgumentException
     * @throws NodeNotPresentException
     *
     * Selects the child based off position string.
     */
    public void selectChild(String position)
            throws InvalidArgumentException, NodeNotPresentException{
        if (position == null || position.isEmpty()){
            throw new InvalidArgumentException("Position either empty or null");
        }
        StoryTreeNode tempCursor = cursor;
            if (position.equals("1")) {
                if (tempCursor.getLeftChild() == null) {
                    throw new NodeNotPresentException("Node is not present.");
                }
                cursor = tempCursor.getLeftChild();
            } else if (position.equals("2")) {
                if (tempCursor.getMiddleChild() == null) {
                    throw new NodeNotPresentException("Node is not present.");
                }
                cursor = tempCursor.getMiddleChild();
            } else if (position.equals("3")) {
                if (tempCursor.getRightChild() == null) {
                    throw new NodeNotPresentException("Node is not present.");
                }
                cursor = tempCursor.getRightChild();
            }

            /*
            if (cursor.isWinningNode()){
                state = GameState.GAME_OVER_WIN;
            }
            if (cursor.isLosingNode()){
                state = GameState.GAME_OVER_LOSE;

             */
    }

    /**
     *
     * @param option
     * @param message
     * @throws InvalidArgumentException
     * @throws TreeFullException
     *
     * adds child and follows left shift.
     */
    public void addChild(String option, String message)
            throws InvalidArgumentException, TreeFullException{
        if (option == null || option.isEmpty()){
            throw new InvalidArgumentException("String is either null or empty.");
        }
        if (cursor.getLeftChild() != null && cursor.getRightChild() != null &&
        cursor.getRightChild() != null){
            throw new TreeFullException("Tree is full.");
        }

        StoryTreeNode treeNode;
        StoryTreeNode treeNode1;
        StoryTreeNode treeNode2;
        if (cursor.getLeftChild() == null){
            treeNode = new StoryTreeNode(cursor.getPosition() + "-1", option, message);
            cursor.setLeftChild(treeNode);
        }
        else if (cursor.getMiddleChild() == null){
            treeNode1 = new StoryTreeNode(cursor.getPosition() + "-2", option, message);
            cursor.setMiddleChild(treeNode1);
        }
        else if (cursor.getRightChild() == null){
            treeNode2 = new StoryTreeNode(cursor.getPosition() + "-3", option, message);
            cursor.setRightChild(treeNode2);
        }
        else{
            throw new TreeFullException("Tree is full.");
        }
    }

    /**
     *
     * @param position
     * @return
     * @throws NodeNotPresentException
     *
     * removes child and follows a left shift.
     */
    public StoryTreeNode removeChild(String position) throws NodeNotPresentException {
        StoryTreeNode tempCursor = cursor;

        switch(position){
            case "1":
                if (tempCursor.getLeftChild() != null){
                    if (tempCursor.getLeftChild() == null){
                        throw new NodeNotPresentException("No left node.");
                    }
                    else{
                        tempCursor = tempCursor.getLeftChild();
                        StoryTreeNode newNode = cursor.getMiddleChild();
                        if (newNode != null) {
                            newNode.setPosition(cursor.getPosition() + "-1");
                        }
                        cursor.setLeftChild(newNode);
                        StoryTreeNode newNode1 = cursor.getRightChild();
                        if (newNode1 != null) {
                            newNode1.setPosition(cursor.getPosition() + "-2");
                        }
                        cursor.setMiddleChild(newNode1);
                        cursor.setRightChild(null);
                    }
                }
                else{
                    cursor.setLeftChild(null);
                    cursor.setMiddleChild(null);
                    cursor.setRightChild(null);
                }
                break;

            case "2":
                if (tempCursor.getLeftChild() != null && tempCursor.getMiddleChild() != null){
                    if (tempCursor.getMiddleChild() == null){
                        throw new NodeNotPresentException("No middle node.");
                    }
                    else{
                        tempCursor = tempCursor.getMiddleChild();
                        StoryTreeNode newNode = cursor.getRightChild();
                        if (newNode != null) {
                            newNode.setPosition(cursor.getPosition() + "-2");
                        }
                        cursor.setMiddleChild(newNode);
                        cursor.setRightChild(null);
                    }
                }
                else{
                    cursor.setMiddleChild(null);
                    cursor.setRightChild(null);
                }
                break;

            case "3":
                if(tempCursor.getLeftChild() != null && tempCursor.getMiddleChild() != null && tempCursor.getRightChild() != null) {
                    if (tempCursor.getRightChild() == null) {
                        throw new NodeNotPresentException("No right node.");
                    } else {
                        tempCursor = tempCursor.getRightChild();
                        cursor.setRightChild(null);
                    }
                }
            }
        return tempCursor;
    }

    //left = middle -> middle = right -> right = null (position == 1 && middle != null && right != null)
    //position == 2 -> middle = right and then right = null (right != null)
    //position == 3 -> right = null

    /**
     *
     * @param storyTreeNode
     *
     * helper method to print out traversal of tree in case of bugs.
     */
    public static void printPreOrder(StoryTreeNode storyTreeNode){
        if (storyTreeNode == null){
            return;
        }

        System.out.println(storyTreeNode.getPosition() + " " +storyTreeNode.getOption() + " " + storyTreeNode.getMessage());
        printPreOrder(storyTreeNode.getLeftChild());
        printPreOrder(storyTreeNode.getMiddleChild());
        printPreOrder(storyTreeNode.getRightChild());
    }
}