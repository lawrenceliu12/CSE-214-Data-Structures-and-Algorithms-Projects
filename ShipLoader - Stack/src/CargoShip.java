/**
 * @author Lawrence Liu
 * 113376858
 * Recititation: R04
 */

import java.util.Stack; //imports java's stack


public class CargoShip {
    /**
     *
     * @return returns stacks
     */
    public CargoStack[] getStacks() {
        return stacks; //getter for stacks
        //Office Hours help
    }

    private CargoStack[] stacks; //Stack array
    private CargoStack[] tempStack; //Temp stack array for storing
    private final int numStacks; //Number of stacks
    private final int maxHeight; //Maximum Height
    private final double maxWeight; //Maximum Weight
    private double CURRENT_CARGO; //Current weight of Cargo
    private CargoStrength cargoStrength; //Enum value
    CargoStack cargoStack = new CargoStack();
    //Initializes CargoStack with new object

    /**
     *
     * @param numStacks
     * @param initMaxHeight
     * @param initMaxWeight
     * Constructor
     */
    public CargoShip(int numStacks, int initMaxHeight, double initMaxWeight){
        if (numStacks > 0 && initMaxHeight > 0 && initMaxWeight > 0){
            this.numStacks = numStacks;
            maxHeight = initMaxHeight;
            maxWeight = initMaxWeight;
            stacks = new CargoStack[numStacks];
            for (int i = 0 ; i < numStacks; i++){
                stacks[i] = new CargoStack();
            }
        }
        else{
            throw new IllegalArgumentException("Parameters are not in appropriate bounds.");
        }
    }

    /**
     *
     * @param cargo
     * @param stack
     * @throws FullStackException
     * @throws ShipOverweightException
     * @throws CargoStrengthException
     * Pushes cargo
     */
    public void pushCargo(Cargo cargo, int stack) throws FullStackException, ShipOverweightException, CargoStrengthException{
        if (cargo == null || stack < 1 || stack > stacks.length){
            throw new IllegalArgumentException("Error");
        }
        if (stacks[stack - 1].size() > maxHeight){
            throw new FullStackException("Full stack error.");
        }
        if (cargo.getWeight() + CURRENT_CARGO > maxWeight){
            throw new ShipOverweightException("Ship overweight error.");
        }
        stacks[stack - 1].push(cargo);
        CURRENT_CARGO += cargo.getWeight();
    }

    /**
     *
     * @param stack
     * @return
     * @throws EmptyStackException
     * Pops cargo
     */
    public Cargo popCargo(int stack) throws EmptyStackException{
        if (stack < 1 || stack > stacks.length){
            throw new IllegalArgumentException();
        }
        if (stacks[stack - 1].isEmpty()){
            throw new EmptyStackException("Empty set.");
        }
        else{
            CURRENT_CARGO -= stacks[stack - 1].peek().getWeight();
            return stacks[stack - 1].pop();
        }
    }


    /**
     *
     * @param name
     * does not work
     */
    public void findAndPrint (String name){
        Stack<Cargo> tempStack = new Stack <Cargo>();
        double weightTotal = 0;
        int count = 0;

        System.out.println("Stack\tDepth\tWeight\tStrength");
        System.out.println("=======+=======+========+==========");

        for (int i = 0; i < numStacks; i++) {
            int num = i + 1;
            int cargoSize = this.stacks[i].size();
            for (int k = 0; k < cargoSize; k++) {
                tempStack.push(this.stacks[i].pop());
            }

            for (Cargo ii : tempStack) {
                try {
                    if (tempStack.pop().getName().equals(name)){
                        this.stacks[i].push(ii);
                        count++;
                        System.out.println(this.stacks[i] + "  |" + this.stacks[i].peek().getName() + "  |" + this.stacks[i].peek().getWeight() + this.stacks[i].peek().getStrength());
                    }
                } catch (CargoStrengthException e) {
                    e.printStackTrace();
                }
            }
            weightTotal += stacks[i].peek().getWeight();
            System.out.println("Total count: " + count);
            System.out.println("Total weight: " + weightTotal);
        }
    }

    /**
     * Displays ship and total weight.
     */
    public void displayShip(){
        int count = 0; //Count
        System.out.println("\n\n");

        tempStack = new CargoStack[stacks.length]; //Temp stack

        for (int i = 0; i < stacks.length; i++) {
            tempStack[i] = new CargoStack();

        }

        int current = stacks[0].size(); //gets current size
        for (int i = 0; i < maxHeight; i++) {
            if (current < stacks[i].size()){
                current = stacks[i].size();
            }
        }

        for (int i = current; i > 0; i--) { //For loop to input the strengths.
            for (int k = 0; k < stacks.length; k++) {
                if (stacks[k].isEmpty() || stacks[k].size() != i) {
                    System.out.printf("%6s", "");
                }
                else {
                    System.out.printf("%6s", stacks[k].peek().getStrength().toString().substring(0,1));
                    try {
                        tempStack[k].push(stacks[k].pop());
                    }
                    catch (CargoStrengthException e) {
                        System.out.println("Error");
                    }
                }
            }
            System.out.println();
        }

        for (int i = 0; i < tempStack.length; i++){ //Pushes everything back
            while(!(tempStack[i].isEmpty())){
                try {
                    stacks[i].push(tempStack[i].pop());
                } catch (CargoStrengthException e) {
                    System.out.println("Error.");
                }
            }
        }

        //Ship building
        System.out.print("\\=|");
        for (int i = 0; i < numStacks; i++) {
            System.out.print("=====|");
        }
        System.out.print("=/");

        System.out.println();
        System.out.print(" \\");
        for (int i = 0; i < numStacks; i++){
            count++;
            System.out.print("   " + count + "  ");
        }

        System.out.print(" /\n  \\");
        for (int i = 0; i < numStacks; i++){
            if (i == numStacks - 1){
                System.out.print("-----");
                break;
            }
            System.out.print("------");
        }
        System.out.print("/");

        System.out.println("\nTotal Weight: " + CURRENT_CARGO + " / " + maxWeight);
        //Gets the current weight / total weight.
    }
}
