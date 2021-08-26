/**
 * @author Lawrence Liu
 * 113376858
 * Recitation: R04
 */

public class Cargo {
    private String name; //name
    private double weight; //weight
    private CargoStrength strength; //enum

    /**
     *
     * @param initName
     * @param initWeight
     * @param initStrength
     */
    public Cargo(String initName, double initWeight, CargoStrength initStrength){
        if (initName != null && initWeight > 0) {
            name = initName;
            weight = initWeight;
            strength = initStrength;
        }
        else{
            throw new IllegalArgumentException("initName is null or initWeight <= 0");
        }
    }

    /**
     *
     * @return
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return
     */
    public double getWeight(){
        return weight;
    }

    /**
     *
     * @return
     */
    public CargoStrength getStrength(){
        return strength;
    }
}
