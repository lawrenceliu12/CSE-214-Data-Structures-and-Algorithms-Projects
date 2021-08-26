/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #3
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

public class Elevator { //Elevator class
    private int currentFloor; //Current floor
    private int elevatorState;
    //Elevator State -> IDLE / TO_SOURCE / TO_DESTINATION
    private Request request; //Request object

    //makes elevator state into int value.
    private final int IDLE = 0;
    private final int TO_SOURCE = 1;
    private final int TO_DESTINATION = 2;

    /**
     * Default Constructor
     */
    public Elevator(){
        request = null;
        elevatorState = IDLE;
        currentFloor = 1;
    }

    /**
     *
     * @return
     *
     * Getter for current floor
     */
    public int getCurrentFloor() {
        return currentFloor;
    }

    /**
     *
     * @return
     *
     * Getter for elevator state
     */
    public int getElevatorState() {
        return elevatorState;
    }

    /**
     *
     * @return
     *
     * Getter for Request
     */
    public Request getRequest() {
        return request;
    }

    /**
     *
     * @param currentFloor
     *
     * Setter for current floor
     */
    public void setCurrentFloor(int currentFloor) {
        this.currentFloor = currentFloor;
    }

    /**
     *
     * @param elevatorState
     * Takes the elevator state and sets it
     *
     * @throws IllegalArgumentException
     * Throws error if inputted not 0->2, meaning not IDLE or TO_SOURCE or TO_DESTINATION
     *
     * Setter for elevator state
     */
    public void setElevatorState(int elevatorState) throws IllegalArgumentException{
        if (elevatorState != IDLE
                && elevatorState != TO_SOURCE
                && elevatorState != TO_DESTINATION){
            throw new IllegalArgumentException("Elevator State is invalid.");
        }
        this.elevatorState = elevatorState;
    }

    /**
     *
     * @param request
     *
     * Setter for request.
     */
    public void setRequest(Request request) {
        this.request = request;
    }
}
