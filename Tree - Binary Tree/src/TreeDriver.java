/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #4
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

//EXTRA CREDIT DESCRIPTION:
//TODO: This section is used to explain what my choice of extra credit is.
// In this really expensive world, my choice of extra credit is
// adding a currency option, where after each individual traversals,
// the price will increase according to whether they chose left, right, or middle.
// I was planning on originally altering the homework .txt file to more suitably
// fit the specific prices per node, but I did not want to alter anything excessive
// in case the input/output will be different on your end due to my changes.
// So, I chose the next best option, putting a set price on left right and middle,
// which will give the next best accurate "price-point." I also implemented it
// so that whenever you head back, it will detect that and calculate the price
// as if you never went back.

import java.io.FileNotFoundException;
//Imports exception for File
import java.util.InputMismatchException;
//Imports exception for input mismatches
import java.util.Scanner;
//Imports Scanner to get User's inputs.
import java.util.zip.DataFormatException;
//Imports Exception for Data Formatting.

public class TreeDriver { //Main class.
    public static void main(String[] args){
        Scanner input = new Scanner(System.in); //Creates a scanner object.
        String userInput; //User input, the choice of the user.
        String fileName; //File name.
        Tree playTree = new Tree(); //Creates a tree.
        boolean bool = true; //Keeps the while loop running until terminated.
        int i = 0;
        //Makes sure that the user does not use 'H' or 'T'
        //before a file is read and translated into a tree.

        System.out.println("Welcome to our automated help system!");

        while (bool){ //Continuously running until bool = false.
            try{
                System.out.println("L - Load a Tree.\n" +
                        "H - Begin a Help Session.\n" +
                        "T - Traverse the Tree in preorder.\n" +
                        "Q - Quit");
                System.out.print("\nChoice: ");
                userInput = input.nextLine();

                switch(userInput){
                    case "L": //Loads the .txt file into a tree.
                        System.out.print("Enter the file name: ");
                        fileName = input.nextLine();
                        playTree = Tree.readTree(fileName);
                        System.out.println("File successfully loaded!");
                        i++; //Makes sure that user can use 'H' or 'T' now.
                        break;

                    case "H": //Begins the session after loading the text file.
                        if (i > 0){
                            playTree.beginSession();
                        }
                        else{
                            System.out.println("You did not enter a file.");
                        }
                        break;

                    case "T": //Traverses the tree in preorder and prints it out.
                        if (i > 0){
                            System.out.println("Traversing the tree in preorder: ");
                            playTree.preOrder();
                        }
                        else{
                            System.out.println("No file read.");
                        }
                        break;

                    case "Q": //Terminates the program by switching bool = false.
                        System.out.println("Thank you for using our automated help services!");
                        bool = false;
                        break;

                    default:
                        //Default case, in which user does not input any of the specified letters above.
                        System.out.println("Please enter a letter specified from the menu.");
                        break;
                }
            }
            catch(IllegalArgumentException
                    | FileNotFoundException
                    | DataFormatException e){ //Catches exceptions and prints them.
                System.out.println(e.getMessage() + "\n");
            }
            catch(InputMismatchException e){
                //Catches when user inputs something of the wrong format.
                System.out.println("Please enter the right format of option. \n");
            }
        }
    }
}
