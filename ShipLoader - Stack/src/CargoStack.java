/**
 * @author Lawrence Liu
 * 113376858
 * Recitation: R04
 */

import java.util.Stack; // imports stack

public class CargoStack {
    private final Stack<Cargo> cargoStack; //Java's stack
    private double CURRENT_CARGO = 0; //cargo value.
    private int counter = 0; //Counter for size

    /**
     * Constructor
     */
    public CargoStack(){
        this.cargoStack = new Stack<>(); //Initializes stack
    }

    /**
     *
     * @return
     * checks if stack is empty.
     */
    public boolean isEmpty(){
        return (cargoStack.isEmpty());
    }

    /**
     *
     * @param cargo
     * @throws CargoStrengthException
     * pushes the stack
     */
    public void push(Cargo cargo) throws CargoStrengthException{
        if (cargo.getStrength() == CargoStrength.FRAGILE) {
            cargoStack.push(cargo);
            counter++;
            CURRENT_CARGO += cargo.getWeight();
        }
        else if (cargo.getStrength() == CargoStrength.STURDY){
            if (cargoStack.isEmpty() || cargoStack.peek().getStrength() == CargoStrength.STURDY) {
                cargoStack.push(cargo);
                counter++;
                CURRENT_CARGO += cargo.getWeight();
            }
        }
        else if (cargo.getStrength() == CargoStrength.MODERATE){
            if (cargo.getStrength() == CargoStrength.MODERATE || cargo.getStrength() != CargoStrength.STURDY){
                cargoStack.push(cargo);
                counter++;
                CURRENT_CARGO += cargo.getWeight();
            }
        }
        else{
            throw new CargoStrengthException("Error in stacking the Cargo.");
        }
    }

    /**
     *
     * @return
     * pops the stack
     */
    public Cargo pop(){
        counter--;
        return cargoStack.pop();
    }

    /**
     *
     * @return
     * peek top of stack
     */
    public Cargo peek() {
        return cargoStack.peek();
    }

    /**
     *
     * @return
     * returns size
     */
    public int size(){
        return counter;
    }
}
