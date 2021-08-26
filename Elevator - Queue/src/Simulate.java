/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #3
 * CSE 214 Summer
 * R30 - Charlie Clark
 */
import java.util.LinkedList; //Imports linked list.

public class Simulate { //Simulate class.
    /**
     *
     * @param probability
     * Probability value
     *
     * @param numFloors
     * Number of floors
     *
     * @param numElevators
     * Number of elevators
     *
     * @param length
     * Length of entire simulation
     *
     * @throws IllegalArgumentException
     * Throws if inputted wrong value.
     *
     * This method in the class Simulate, acts as a simulator that will
     * be called upon by Analyzer class. It takes in 4 parameters, and
     * checks multiple conditions by utilizing the BooleanSource class,
     * which is essentially a RNG where if the inputted probability is
     * less than RNG, then the program will start working. It simulates
     * an Elevator system where there are people needing to go to specific
     * floors, also randomly generated, by an elevator.
     *
     */
    public static void simulate
    (double probability, int numFloors, int numElevators, int length)
            throws IllegalArgumentException{
        if (probability < 0 || probability > 1){
            throw new IllegalArgumentException
                    ("Probability cannot be less than 0 or greater than 1.");
        }
        if (numFloors <= 1){
            throw new IllegalArgumentException
                    ("Number of floors cannot be 1 or less.");
        }
        if (numElevators <= 0){
            throw new IllegalArgumentException
                    ("Number of elevators cannot be 0 or less.");
        }
        if (length <= 0){
            throw new IllegalArgumentException
                    ("Length of simulation cannot be 0 or less.");
        }
        boolean debug = false; //Used for debug
        int requestCount = 0; //Request count
        int totalWaitTime = 0; //Total time waited
        double averageWaitTime; //Total time waited divided by request count
        int printCount = 0; //Count

        RequestQueue simulateQueue = new RequestQueue(); //Creates a queue

        //Boolean Source object
        BooleanSource probabilityValue = new BooleanSource(probability);

        //Creates a Linked List of Elevators
        LinkedList<Elevator> elevatorList = new LinkedList<>();
        for (int i = 0; i < numElevators; i++){ //Creates links of request objects
            Elevator elevator = new Elevator();
            elevatorList.add(elevator);
        }

        for (int i = 1; i <= length; i++){ //Simulation
            try{
                if (debug) {
                    if (printCount == 0) {
                        System.out.println("Step " + i);
                        printCount++;
                    }
                    else{
                        System.out.println();
                        System.out.println("Step " + i);
                    }
                }

                //This first event checks if the request occurs.
                if (probabilityValue.requestArrived()){
                    Request newRequest = new Request(numFloors);
                    newRequest.setTimeEntered(i);
                    simulateQueue.enqueue(newRequest);
                    if (debug){
                        System.out.println
                                ("Debug. Request from floor #: " + newRequest.getSourceFloor());
                    }
                }

                //This event is after first event where if the elevator state is idle,
                //then it essentially starts moving the elevators by dequeuing from the queue.
                if (simulateQueue.isNotEmpty()){
                    for (int j = 0; j < elevatorList.size(); j++){
                        if (elevatorList.get(j).getElevatorState() == 0){ //IDLE
                            if (simulateQueue.isEmpty()){
                                break;
                            }
                            else if (simulateQueue.isNotEmpty()){
                                elevatorList.get(j).setRequest(simulateQueue.dequeue());
                                elevatorList.get(j).setElevatorState(1);
                                if (debug) {
                                    System.out.println("Debug. Elevator " + (j + 1) + "-> request.");
                                }
                            }
                        }
                    }
                }

                //The next event is when the elevator starts moving to source and to destination.
                //It will add on time whenever the current floor is equal to source floor.
                for (int j = 0; j < elevatorList.size(); j++) {
                    if (elevatorList.get(j).getRequest() != null) {
                        //To Source
                        if (elevatorList.get(j).getElevatorState() == 1) {
                            if (elevatorList.get(j).getCurrentFloor()
                                    < elevatorList.get(j).getRequest().getSourceFloor()) {
                                elevatorList.get(j).setCurrentFloor(elevatorList.get(j).getCurrentFloor() + 1);
                                if (debug) {
                                    System.out.println("Debug. Elevator " + (j + 1)
                                            + " is going up to " + elevatorList.get(j).getCurrentFloor());
                                }
                            }
                            if (elevatorList.get(j).getCurrentFloor()
                                    > elevatorList.get(j).getRequest().getSourceFloor()) {
                                elevatorList.get(j).setCurrentFloor(elevatorList.get(j).getCurrentFloor() - 1);
                                if (debug) {
                                    System.out.println("Debug. Elevator " + (j + 1)
                                            + " is going down to " + elevatorList.get(j).getCurrentFloor());
                                }
                            }
                            if (elevatorList.get(j).getCurrentFloor()
                                    == elevatorList.get(j).getRequest().getSourceFloor()){
                                elevatorList.get(j).setElevatorState(2);
                                requestCount++;
                                totalWaitTime += (i - elevatorList.get(j).getRequest().getTimeEntered());
                                if (debug){
                                    System.out.println("Elevator " + (j + 1)
                                            + " -> source -> to destination (made it to source)");
                                }
                            }
                        }
                        //To Destination
                        else if (elevatorList.get(j).getElevatorState() == 2) {
                            if (elevatorList.get(j).getCurrentFloor()
                                    < elevatorList.get(j).getRequest().getDestinationFloor()) {
                                elevatorList.get(j).setCurrentFloor(elevatorList.get(j).getCurrentFloor() + 1);
                                if (debug) {
                                    System.out.println("Debug. Elevator " + (j + 1)
                                            + " is going up to " + elevatorList.get(j).getCurrentFloor());
                                }
                            }
                            if (elevatorList.get(j).getCurrentFloor()
                                    > elevatorList.get(j).getRequest().getDestinationFloor()) {
                                elevatorList.get(j).setCurrentFloor(elevatorList.get(j).getCurrentFloor() - 1);
                                if (debug) {
                                    System.out.println("Debug. Elevator " + (j + 1)
                                            + " is going down to " + elevatorList.get(j).getCurrentFloor());
                                }
                            }
                            if (elevatorList.get(j).getCurrentFloor()
                                    == elevatorList.get(j).getRequest().getDestinationFloor()) {
                                elevatorList.get(j).setElevatorState(0);
                                if (debug) {
                                    System.out.println("Elevator " + (j + 1)
                                            + " -> destination -> idle (made it to destination)");
                                }
                            }
                        }
                    }
                }

                //This event continues moving the elevator to get from source floor to destination source.
                //This event also checks to see if source is the same as destination, as if that is the case,
                //it just sets the elevator to idle.
                for (int j = 0; j < elevatorList.size(); j++) {
                    if (elevatorList.get(j).getRequest() != null) {
                        if (elevatorList.get(j).getCurrentFloor()
                                == elevatorList.get(j).getRequest().getSourceFloor()) {
                            if (elevatorList.get(j).getCurrentFloor()
                                    != elevatorList.get(j).getRequest().getDestinationFloor()) {
                                if (elevatorList.get(j).getCurrentFloor()
                                        < elevatorList.get(j).getRequest().getDestinationFloor()) {
                                    elevatorList.get(j).setCurrentFloor(elevatorList.get(j).getCurrentFloor() + 1);
                                    if (debug){
                                        System.out.println("Debug. Elevator " + (j + 1)
                                                + " is going up to " + elevatorList.get(j).getCurrentFloor());
                                    }
                                }
                                else if (elevatorList.get(j).getCurrentFloor()
                                        > elevatorList.get(j).getRequest().getDestinationFloor()) {
                                    elevatorList.get(j).setCurrentFloor(elevatorList.get(j).getCurrentFloor() - 1);
                                    if (debug){
                                        System.out.println("Debug. Elevator " + (j + 1)
                                                + " is going down to " + elevatorList.get(j).getCurrentFloor());
                                    }
                                }
                            }
                            else if (elevatorList.get(j).getCurrentFloor()
                                    == elevatorList.get(j).getRequest().getDestinationFloor()) {
                                elevatorList.get(j).setElevatorState(0);
                                if (debug){
                                    System.out.println ("Elevator " + (i + 1)
                                            + " has the same source and destination. -> idle");
                                }
                            }
                        }
                    }
                }
            }
            //Catches any errors.
            catch(IllegalArgumentException e){
                System.out.println(e.getMessage());
            }
        }

        //Prints out the total wait time, request count, and average wait time.
        System.out.println();
        System.out.println("Total wait time: " + totalWaitTime);
        System.out.println("Total request time: " + requestCount);

        averageWaitTime = calculate(totalWaitTime, requestCount);
        String roundedAvgWaitTime = round(averageWaitTime) + " time units.";
        System.out.println("Total average wait time: " + roundedAvgWaitTime);
    }

    /**
     *
     * @param wait
     * Wait time
     * @param request
     * Request count
     * @return
     *
     * Returns calculated average.
     */
    public static double calculate (double wait, double request){
        return wait/request;
    }

    /**
     *
     * @param average
     * Value to be rounded
     * @return
     * returns the rounded average wait time to nearest hundredth.
     */
    public static String round(double average){
        return String.format("%.2f", average);
    }
}
