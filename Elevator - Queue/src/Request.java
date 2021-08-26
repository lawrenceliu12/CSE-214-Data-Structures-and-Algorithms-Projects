/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #3
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

public class Request { //Request Class
    private int sourceFloor; //Original floor
    private int destinationFloor; //Floor to go to
    private int timeEntered; //Time entered.

    /**
     *
     * @param numFloors
     * User inputs the number of floors,
     * Creates a random number for source floor and destination floor
     */
    public Request(int numFloors){
        sourceFloor = (int) (Math.random() * numFloors) + 1;
        destinationFloor = (int) (Math.random() * numFloors) + 1;
    }

    /**
     *
     * @return
     *
     * returns destination floor
     */
    public int getDestinationFloor() {
        return destinationFloor;
    }

    /**
     *
     * @return
     *
     * returns the source floor
     */
    public int getSourceFloor() {
        return sourceFloor;
    }

    /**
     *
     * @return
     *
     * returns time entered
     */
    public int getTimeEntered() {
        return timeEntered;
    }

    /**
     *
     * @param destinationFloor
     *
     * sets destination floor
     */
    public void setDestinationFloor(int destinationFloor) {
        this.destinationFloor = destinationFloor;
    }

    /**
     *
     * @param sourceFloor
     *
     * sets source floor
     */
    public void setSourceFloor(int sourceFloor) {
        this.sourceFloor = sourceFloor;
    }

    /**
     *
     * @param timeEntered
     *
     * sets time entered.
     */
    public void setTimeEntered(int timeEntered) {
        this.timeEntered = timeEntered;
    }
}
