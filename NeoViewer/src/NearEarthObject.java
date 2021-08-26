/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #7
 * CSE 214
 * R04 - James Finn / Taylor Ngo
 */

import java.util.Date; //Imports date

/**
 * NearEarthObject Class
 */
public class NearEarthObject {
    private int referenceID; //To get reference ID
    private String name; //To get name
    private double absoluteMagnitude; //To get magnitude
    private double averageDiameter; //(min + max) / 2. Gets average Diameter.
    private boolean isDangerous; //Checks to see if dangerous
    private Date closestApproachDate; //Gets approach date
    private double missDistance; //Gets the miss distance
    private String orbitingBody; //Gets the orbiting body.

    /**
     *
     * @param referenceID
     * @param name
     * @param absoluteMagnitude
     * @param minDiameter
     * @param maxDiameter
     * @param isDangerous
     * @param closestDateTimestamp
     * @param missDistance
     * @param orbitingBody
     *
     * NearEarthObject Constructor
     */
    public NearEarthObject(int referenceID,
                           String name,
                           double absoluteMagnitude,
                           double minDiameter,
                           double maxDiameter,
                           boolean isDangerous,
                           long closestDateTimestamp,
                           double missDistance,
                           String orbitingBody){
        this.referenceID = referenceID;
        this.name = name;
        this.absoluteMagnitude = absoluteMagnitude;
        this.averageDiameter = ((minDiameter + maxDiameter) / 2);
        this.isDangerous = isDangerous;
        this.closestApproachDate = new Date(closestDateTimestamp);
        this.missDistance = missDistance;
        this.orbitingBody = orbitingBody;
    }

    /**
     *
     * @return
     *
     * Getter for ReferenceID
     */
    public int getReferenceID(){
        return referenceID;
    }

    /**
     *
     * @return
     *
     * Getter for Name
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return
     *
     * Getter for the Absolute Magnitude
     */
    public double getAbsoluteMagnitude(){
        return absoluteMagnitude;
    }

    /**
     *
     * @return
     *
     * Getter for the Average Diameter
     */
    public double getAverageDiameter(){
        return averageDiameter;
    }

    /**
     *
     * @return
     *
     * Getter for whether it is dangerous or not.
     */
    public boolean isDangerous(){
        return isDangerous;
    }

    /**
     *
     * @return
     *
     * Getter for the Closest Approach Date
     */
    public Date getClosestApproachDate() {
        return closestApproachDate;
    }

    /**
     *
     * @return
     *
     * Getter for the Miss Distance
     */
    public double getMissDistance(){
        return missDistance;
    }

    /**
     *
     * @return
     *
     * Getter for the Orbiting Body
     */
    public String getOrbitingBody(){
        return orbitingBody;
    }

    /**
     *
     * @param referenceID
     *
     * Setter for Reference ID
     */
    public void setReferenceID(int referenceID) {
        this.referenceID = referenceID;
    }

    /**
     *
     * @param name
     *
     * Setter for Name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param absoluteMagnitude
     *
     * Setter for Absolute Magnitude
     */
    public void setAbsoluteMagnitude(double absoluteMagnitude) {
        this.absoluteMagnitude = absoluteMagnitude;
    }

    /**
     *
     * @param averageDiameter
     *
     * Setter for Average Diameter
     */
    public void setAverageDiameter(double averageDiameter) {
        this.averageDiameter = averageDiameter;
    }

    /**
     *
     * @param dangerous
     *
     * Setter for dangerous check.
     */
    public void setDangerous(boolean dangerous) {
        isDangerous = dangerous;
    }

    /**
     *
     * @param closestApproachDate
     *
     * Setter for the Closest Approach Date.
     */
    public void setClosestApproachDate(Date closestApproachDate) {
        this.closestApproachDate = closestApproachDate;
    }

    /**
     *
     * @param missDistance
     *
     * Setter for the Miss Distance
     */
    public void setMissDistance(double missDistance) {
        this.missDistance = missDistance;
    }

    /**
     *
     * @param orbitingBody
     *
     * Setter for the Orbiting Body.
     */
    public void setOrbitingBody(String orbitingBody) {
        this.orbitingBody = orbitingBody;
    }
}
