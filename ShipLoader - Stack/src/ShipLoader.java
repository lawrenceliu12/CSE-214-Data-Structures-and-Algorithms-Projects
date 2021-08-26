/**
 * @author Lawrence Liu
 * 113376858
 * Recititation: R04
 */

import java.util.Scanner; //Scanner imported
import java.util.Stack; //Stack imported

public class ShipLoader {
    public static void main(String[] args){ //Main class
        Scanner input = new Scanner(System.in); //Initializes scanner
        boolean status = true; //true for while loop
        String option; //String input for switch case
        CargoShip cargoShip; //Initializes CargoShip object
        Cargo cargo; //Initializes Cargo object
        Stack<Cargo> cargoStack = new Stack <Cargo>(); //Initializes stack
        int stack; //stack number
        int stackHeight; //stack height
        double cargoWeight; //cargo weight

        System.out.println("Welcome to ShipLoader!");
        System.out.println("Cargo Ship Parameters");
        System.out.println("--------------------------------------------------");
        System.out.print("Number of stacks: ");
        stack = input.nextInt();
        System.out.print("Maximum height of stacks: ");
        stackHeight = input.nextInt();
        System.out.print("Maximum total cargo weight: ");
        cargoWeight = input.nextDouble();

        cargoShip = new CargoShip(stack, stackHeight, cargoWeight); //new CargoShip object

        System.out.println("Cargo ship created.");
        System.out.println("Pulling ship into dock...");
        System.out.println("Cargo ship ready to be loaded.");

        while(status){ //while loop until quit.
            input.nextLine(); //Needed so that switch case doesn't go twice.
            System.out.println();
            System.out.println("Please select an option: \nC) Create new cargo");
            System.out.println("L) Load cargo from dock \nU) Unload cargo from dock");
            System.out.println("M) Move cargo between stacks \nK) Clear Dock");
            System.out.println("P) Print ship stacks \nS) Search for cargo \nQ) Quit");
            System.out.print("\nSelect a menu option: ");
            option = input.nextLine().toUpperCase();

            switch(option){
                case "C": //Creates new cargo
                    String name; //Name of cargo
                    double weight; //Weight of cargo
                    char strength; //char value to get strength of cargo.
                    System.out.print("Enter the name of the cargo: ");
                    name = input.nextLine();
                    System.out.print("Enter the weight of the cargo: ");
                    weight = input.nextDouble();
                    System.out.print("Enter the container strength (F/M/S): ");

                    strength = input.next().charAt(0);

                    if (strength == 'F'){
                        CargoStrength cargoStrength = CargoStrength.FRAGILE;
                        cargo = new Cargo(name, weight, cargoStrength);
                        if (cargoStack.isEmpty() || //If to check for fragile.
                                //Fragile can stack on anything.
                                cargoStack.peek().getStrength() == CargoStrength.MODERATE ||
                                cargoStack.peek().getStrength() == CargoStrength.STURDY ||
                                cargoStack.peek().getStrength() == CargoStrength.FRAGILE) {
                            cargoStack.push(cargo);
                            System.out.println("Cargo '" + cargo.getName() + "' pushed onto the dock.");
                        }
                        else{
                            System.out.println("Fragile cannot be stacked on.");
                        }
                    }
                    else if (strength == 'M'){
                        CargoStrength cargoStrength1 = CargoStrength.MODERATE;
                        cargo = new Cargo(name, weight, cargoStrength1);
                        if (cargoStack.isEmpty() || //If to check for moderate.
                                // Only moderate can stack on moderate and sturdy.
                                cargoStack.peek().getStrength() == CargoStrength.MODERATE ||
                                cargoStack.peek().getStrength() == CargoStrength.STURDY) {
                            cargoStack.push(cargo);
                            System.out.println("Cargo '" + cargo.getName() + "' pushed onto the dock.");
                        }
                        else{
                            System.out.println("Moderate can only be stacked ontop of moderate or sturdy.");
                        }
                    }
                    else if (strength == 'S'){
                        CargoStrength cargoStrength2 = CargoStrength.STURDY;
                        cargo = new Cargo(name, weight, cargoStrength2);
                        if (cargoStack.isEmpty() || //Sturdy can stack on only sturdy or the floor.
                                cargoStack.peek().getStrength() == CargoStrength.STURDY) {
                            cargoStack.push(cargo);
                            System.out.println("Cargo '" + cargo.getName() + "' pushed onto the dock.");
                        }
                        else{
                            System.out.println("Sturdy can only stack on itself or the floor.");
                        }
                    }
                    else {
                        System.out.println("Value entered is not F/M/S.");
                    }

                    cargoShip.displayShip(); //displays ship
                    try{
                        displayDock(cargoStack); //displays dock
                    }
                    catch (CargoStrengthException e) { //catches error.
                        System.out.println("Error.");
                    }
                    break;

                case "L": //Loads dock
                    try {
                        if (cargoStack.isEmpty()){ //If empty, then no stack can be put.
                            throw new EmptyStackException("Dock is empty.");
                        }
                        else { //Only if the dock is not empty.
                            System.out.print("Select the load destination stack at stack index: ");
                            int stackIndex = input.nextInt(); //stack index to load
                            String cargoName = cargoStack.peek().getName(); //cargo name
                            if (cargoShip.getStacks() == null ||
                                    cargoShip.getStacks()[stackIndex - 1].isEmpty()){
                                //If floor is empty, then new stack.
                                cargoShip.pushCargo(cargoStack.pop(), stackIndex); //pushes cargo
                                System.out.println("Cargo '" + cargoName + "' moved from dock to stack " + stackIndex);
                            }
                            else if (cargoStack.peek().getStrength() == CargoStrength.FRAGILE){
                                //Fragile case
                                if (cargoShip.getStacks()[stackIndex - 1].peek().getStrength()
                                        == CargoStrength.FRAGILE
                                        || cargoShip.getStacks()[stackIndex - 1].peek().getStrength()
                                        == CargoStrength.MODERATE
                                        || cargoShip.getStacks()[stackIndex - 1].peek().getStrength()
                                        == CargoStrength.STURDY){
                                    cargoShip.pushCargo(cargoStack.pop(), stackIndex);
                                    System.out.println("Cargo '" + cargoName + "' moved from dock to stack "
                                            + stackIndex);
                                }
                                else{
                                    throw new CargoStrengthException ("Error. Fragile cannot get stacked " +
                                            "unless it is by fragile.");
                                }
                            }
                            else if (cargoStack.peek().getStrength() == CargoStrength.MODERATE){
                                //Moderate case
                                if (cargoShip.getStacks()[stackIndex - 1].peek().getStrength()
                                        == CargoStrength.MODERATE
                                        || cargoShip.getStacks()[stackIndex - 1].peek().getStrength()
                                        == CargoStrength.STURDY){
                                    cargoShip.pushCargo(cargoStack.pop(), stackIndex);
                                    System.out.println("Cargo '" + cargoName + "' moved from dock to stack "
                                            + stackIndex);
                                }
                                else{
                                    throw new CargoStrengthException("Error. " +
                                            "Moderate cannot only be stacked on top of moderate or sturdy cargo.");
                                }
                            }
                            else if (cargoStack.peek().getStrength() == CargoStrength.STURDY){
                                //Sturdy case
                                if (cargoShip.getStacks()[stackIndex - 1].peek().getStrength()
                                        == CargoStrength.STURDY){
                                    cargoShip.pushCargo(cargoStack.pop(), stackIndex);
                                    System.out.println("Cargo '" + cargoName + "' moved from dock to stack "
                                            + stackIndex);
                                }
                                else{
                                    throw new CargoStrengthException("Error. Sturdy can only be stacked above sturdy or ground.");
                                }
                            }
                            cargoShip.displayShip(); //Displays ship and dock
                            displayDock(cargoStack);
                        }
                    }
                    catch (IllegalArgumentException e){ //Catches Exceptions
                        System.out.println("Error.");
                    }
                    catch (FullStackException e) {
                        System.out.println("Error. Full stack.");
                    }
                    catch (CargoStrengthException e) {
                        System.out.println(e.getMessage());
                    }
                    catch (ShipOverweightException e) {
                        System.out.println("Error. Ship is overweight.");
                    } catch (EmptyStackException e) {
                        e.getMessage();
                    }
                    break;

                case "U": //Unloads stacks.
                    try {
                        System.out.print("Select the unload source stack index: ");
                        int unloadStackIndex = input.nextInt(); //Unload stack index.
                        if (cargoStack.isEmpty()) { //Empty, then stack can unload.
                            cargoStack.push(cargoShip.popCargo(unloadStackIndex));
                            System.out.println("Cargo moved from stack " + unloadStackIndex + " to dock.");
                        }
                        else if (cargoShip.cargoStack.peek().getStrength()
                                == CargoStrength.FRAGILE) { //Fragile case
                            if (cargoStack.peek().getStrength() == CargoStrength.FRAGILE
                                    || cargoStack.peek().getStrength() == CargoStrength.MODERATE
                                    || cargoStack.peek().getStrength() == CargoStrength.STURDY) {
                                cargoStack.push(cargoShip.popCargo(unloadStackIndex));
                                System.out.println("Cargo moved from stack " + unloadStackIndex + " to dock.");
                            }
                            else{
                                System.out.println("Fragile cannot support moderate or sturdy.");
                            }
                        }
                        else if (cargoShip.cargoStack.peek().getStrength() == CargoStrength.MODERATE){
                            //Moderate case
                           if (cargoStack.peek().getStrength() == CargoStrength.MODERATE
                                   || cargoStack.peek().getStrength()
                                   == CargoStrength.STURDY){
                               cargoStack.push(cargoShip.popCargo(unloadStackIndex));
                               System.out.println("Cargo moved from stack " + unloadStackIndex + " to dock.");
                           }
                           else{
                               System.out.println("Moderate cannot support sturdy.");
                           }
                        }
                        else if (cargoShip.cargoStack.peek().getStrength()
                                == CargoStrength.STURDY){ //Sturdy case
                            if (cargoStack.peek().getStrength() == CargoStrength.STURDY){
                                cargoStack.push(cargoShip.popCargo(unloadStackIndex));
                                System.out.println("Cargo moved from stack " + unloadStackIndex + " to dock.");
                            }
                            else{
                                System.out.println("Sturdy cannot be on top of anything except itself.");
                            }
                        }
                        cargoShip.displayShip();
                        displayDock(cargoStack);
                    }
                    catch(EmptyStackException e){ //Catches exceptions
                        System.out.println("Error. Can't unload on an empty stack.");
                    }
                    catch (CargoStrengthException e){
                        System.out.println("Error.");
                    }
                    break;

                case "M": //Moves stacks
                    try{
                        System.out.print("Source stack index: ");
                        int sourceStackIndex = input.nextInt(); //First index
                        System.out.print("Destination stack index: ");
                        int toStackIndex = input.nextInt(); //Destination index

                        cargoShip.pushCargo(cargoShip.popCargo(sourceStackIndex), toStackIndex);
                        cargoShip.displayShip();
                        displayDock(cargoStack);
                    }
                    catch (EmptyStackException e){ //Catches errors.
                        System.out.println("Error. Can't move from an empty stack.");
                    }
                    catch (FullStackException e) {
                        System.out.println("Error. Full stack.");
                    }
                    catch (CargoStrengthException e) {
                        System.out.println("Error. Strength value is too high.");
                    }
                    catch (ShipOverweightException e) {
                        System.out.println("Error. Ship is overweight.");
                    }
                    break;

                case "K": //Clears the dock
                    try{
                        if (cargoStack.isEmpty()){ //If dock is empty.
                            System.out.println("Dock is empty.");
                        }
                        else { //While loop until dock is empty.
                            while(!(cargoStack.isEmpty())){
                                cargoStack.pop();
                            }
                            System.out.println("Dock cleared.");
                        }
                        cargoShip.displayShip();
                        displayDock(cargoStack);
                    }
                    catch (IllegalArgumentException e){ //Catches Exceptions
                        System.out.println("Error.");
                    }
                    catch (CargoStrengthException e){
                        System.out.println("Error");
                    }
                    break;

                case "P": //Prints the ship and dock.
                    System.out.println("Input another letter after viewing " +
                            "the printed ship and dock to continue.");
                    cargoShip.displayShip();
                    try{
                        displayDock(cargoStack);
                    }
                    catch (CargoStrengthException e){ //Catches exception
                        System.out.println("Error.");
                    }
                    break;

                case "S": //Searches. This case does not work.
                    System.out.print("Enter the name of the cargo: ");
                    String nameCargo = input.nextLine();

                    System.out.println("\nCargo '" + nameCargo + "'found at the following locations: ");
                    cargoShip.findAndPrint(nameCargo);
                    break;

                case "Q": //Ends the while loop
                    System.out.println("Program terminating normally...");
                    status = false; //Makes status false,
                    // making the while loop false, terminating it.
                    break;

                default: //Default case.
                    System.out.println("Please enter a letter that is given in the menu.");
            }
        }
    }

    public static void displayDock(Stack<Cargo> cargoStack)
            throws CargoStrengthException {
        //Displays the dock
        Stack<Cargo> tempStack = new Stack<>(); //Temp stack
        int numValue = cargoStack.size(); //Size of the stack

        System.out.println();

        for (int i = 0; i < numValue; i++){ //For loop to get
            // the strengths on dock.
            System.out.println("\t\t " + cargoStack.peek().getStrength().toString().substring(0,1));
            tempStack.push(cargoStack.pop());
        }
        for (int i = 0; i < numValue; i++){
            cargoStack.push(tempStack.pop()); //Pushes everything
            // back to how the stacks usually is.
        }

        System.out.println("Dock: |=====|"); //Prints dock.
        System.out.printf("%13s", "|=====|");
        System.out.printf("\n%13s", "|=====|");
    }
}
