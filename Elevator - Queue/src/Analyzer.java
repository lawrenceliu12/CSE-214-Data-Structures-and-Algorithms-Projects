/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #3
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

import java.util.InputMismatchException; //Imports input mismatch
import java.util.Scanner; //Imports scanner

public class Analyzer { //Main class / Test class
    public static void main(String[] args){
        Scanner input = new Scanner(System.in); //Scanner object

        double probability; //Probability to be inputted
        int numFloors; //Number of Floors to be inputted
        int numElevators; //Number of Elevators to be inputted
        int time; //Time to be inputted
        String loop = "y"; //Loops simulation until 'n.'

        System.out.println("Welcome to the Elevator Simulator!\n");

        do{ //Do, while loop.
            try{
                System.out.print("Please enter the probability of arrival for requests: ");
                probability = input.nextDouble();

                System.out.print("Please enter the number of floors: ");
                numFloors = input.nextInt();

                System.out.print("Please enter the number of elevators: ");
                numElevators = input.nextInt();

                System.out.print("Enter the length of the simulation (in time units): ");
                time = input.nextInt();
                input.nextLine();

                //Calls upon simulation.
                Simulate.simulate(probability, numFloors, numElevators, time);

                System.out.print("\nWould you like to repeat the simulation? (y/n): ");
                loop = input.nextLine(); //Repeats simulation.

                //Checks if string is not y/n.
                while (!(loop.equalsIgnoreCase("n") || loop.equalsIgnoreCase("y"))){
                    System.out.println("\nInput has to be either Y/y or N/n! Try again. ");
                    System.out.print("\nWould you like to repeat the simulation? (y/n): ");
                    loop = input.nextLine();
                }

                if (loop.equalsIgnoreCase("n")){
                    System.out.println("\nThank you for playing Elevator Simulator!");
                }
            }
            //Catches any errors made in simulation.
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
                System.out.println();
            }
            //Catches wrong inputs.
            catch(InputMismatchException e){
                System.out.println("Input is invalid. Try again.");
                System.out.println();
                input.nextLine();
            }
        }
        //Breaks loop when not inputted y. Continues loop when inputted y.
        while(loop.equalsIgnoreCase("y"));
    }
}
