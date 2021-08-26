/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #3
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

public class BooleanSource { //Boolean Source class
    private double probability; //Probability
    /**
     *
     * @param initProbability
     * initial probability
     *
     * @throws IllegalArgumentException
     * checks to see if initial probability is inputted wrong.
     *
     * Constructor for BooleanSource.
     */
    public BooleanSource(double initProbability) throws IllegalArgumentException{
        if (initProbability > 1){
            System.out.println("Probability value cannot be more than one.");
        }
        if (initProbability < 0){
            System.out.println("Probability value cannot be less than zero.");
        }

        probability = initProbability;
    }

    /**
     *
     * @return
     *
     * Getter for probability
     */
    public double getProbability() {
        return probability;
    }

    /**
     *
     * @param probability
     *
     * Setter for probability
     */
    public void setProbability(double probability) {
        this.probability = probability;
    }

    /**
     *
     * @return
     *
     * Returns true if a random probability is less than inputted probability.
     * False otherwise.
     */
    public boolean requestArrived(){
        double randomProb = Math.random();
        return (randomProb < probability);
    }
}
