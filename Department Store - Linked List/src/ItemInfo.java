/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #2
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

public class ItemInfo {
    private String name; //name of item
    private double price; //price of item
    private String rfidTagNumber; //rfid tag number of item
    private String originalLocation; //original location of item
    private String currentLocation; //current location of item

    /**
     * Empty constructor
     */
    public ItemInfo(){
    }

    /**
     *
     * @return
     *
     * returns name
     */
    public String getName(){
        return name;
    }

    /**
     *
     * @return
     *
     * returns price
     */
    public double getPrice(){
        return price;
    }

    /**
     *
     * @return
     *
     * returns RFID Tag Number
     */
    public String getRfidTagNumber(){
        return rfidTagNumber;
    }

    /**
     *
     * @return
     *
     * Returns original location
     */
    public String getOriginalLocation(){
        return originalLocation;
    }

    /**
     *
     * @return
     *
     * Returns current location
     */
    public String getCurrentLocation(){
        return currentLocation;
    }

    /**
     *
     * @param name
     *
     * Sets the name
     */
    public void setName(String name){
        this.name = name;
    }

    /**
     *
     * @param price
     *
     * Sets the price
     */
    public void setPrice(double price){
        this.price = price;
    }

    /**
     *
     * @param rfidTagNumber
     *
     * Sets the Rfid Tag Number
     *
     * Iterates through the entire string and sees if
     * it is a viable rfid tag number.
     * If it is not viable, it throws IllegalArgumentException
     */
    public void setRfidTagNumber(String rfidTagNumber){
        if (rfidTagNumber.length() != 9){
            throw new IllegalArgumentException("\nThe length of the Tag Number must be 9.");
        }
        for (int i = 0; i < rfidTagNumber.length(); i++){
            if (((rfidTagNumber.substring(i, i+1).equalsIgnoreCase("a") ||
                    rfidTagNumber.substring(i, i+1).equalsIgnoreCase("b") ||
                    rfidTagNumber.substring(i, i+1).equalsIgnoreCase("c") ||
                    rfidTagNumber.substring(i, i+1).equalsIgnoreCase("d") ||
                    rfidTagNumber.substring(i, i+1).equalsIgnoreCase("e") ||
                    rfidTagNumber.substring(i, i+1).equalsIgnoreCase("f")) ||
                    (rfidTagNumber.substring(i, i+1).equalsIgnoreCase("0") ||
                            rfidTagNumber.substring(i, i+1).equalsIgnoreCase("1") ||
                            rfidTagNumber.substring(i, i+1).equalsIgnoreCase("2") ||
                            rfidTagNumber.substring(i, i+1).equalsIgnoreCase("3") ||
                            rfidTagNumber.substring(i, i+1).equalsIgnoreCase("4") ||
                            rfidTagNumber.substring(i, i+1).equalsIgnoreCase("5") ||
                            rfidTagNumber.substring(i, i+1).equalsIgnoreCase("6") ||
                            rfidTagNumber.substring(i, i+1).equalsIgnoreCase("7") ||
                            rfidTagNumber.substring(i, i+1).equalsIgnoreCase("8") ||
                            rfidTagNumber.substring(i, i+1).equalsIgnoreCase("9")))){
                this.rfidTagNumber = rfidTagNumber;
            }
            else{
                throw new IllegalArgumentException("\nInvalid format for RFID Tag Number.");
            }
        }
    }

    /**
     *
     * @param originalLocation
     *
     * Sets the original location.
     *
     * Iterates to see if all conditions for
     * original location is met.
     */
    public void setOriginalLocation(String originalLocation){
        int count = 0;
        if (originalLocation.length() == 6 && originalLocation.substring(0, 1).equalsIgnoreCase("s")){
            for (int i = 1; i < originalLocation.length(); i++){
                if ((originalLocation.charAt(i) == '1') ||
                        (originalLocation.charAt(i) == '2') ||
                        (originalLocation.charAt(i) == '3') ||
                        (originalLocation.charAt(i) == '4') ||
                        (originalLocation.charAt(i) == '5') ||
                        (originalLocation.charAt(i) == '6') ||
                        (originalLocation.charAt(i) == '7') ||
                        (originalLocation.charAt(i) == '8') ||
                        (originalLocation.charAt(i) == '9') ||
                        (originalLocation.charAt(i) == '0')){
                    count++;
                    if (count == 5){
                        this.originalLocation = originalLocation;
                    }
                }
                else{
                    throw new IllegalArgumentException("\nInvalid format of original location.");
                }
            }
        }
        else{
            throw new IllegalArgumentException("\nInvalid format of original location.");
        }
    }

    /**
     *
     * @param currentLocation
     *
     * sets current location
     *
     * Iterates through to see if all conditions are met
     * for Current Location.
     */
    public void setCurrentLocation(String currentLocation){
        int count = 0;
        int count1 = 0;
        if (currentLocation.equalsIgnoreCase("out")){
            this.currentLocation = currentLocation;
        }
        else if (((currentLocation.substring(0, 1).equalsIgnoreCase("s") || currentLocation.substring(0, 1).equalsIgnoreCase("c")))
        && ((currentLocation.length() == 6 || currentLocation.length() == 4))){
            if (currentLocation.substring(0, 1).equalsIgnoreCase("c") && currentLocation.length() == 4) {
                for (int i = 1; i < currentLocation.length(); i++) {
                    if ((currentLocation.charAt(i) == '1') ||
                            (currentLocation.charAt(i) == '2') ||
                            (currentLocation.charAt(i) == '3') ||
                            (currentLocation.charAt(i) == '4') ||
                            (currentLocation.charAt(i) == '5') ||
                            (currentLocation.charAt(i) == '6') ||
                            (currentLocation.charAt(i) == '7') ||
                            (currentLocation.charAt(i) == '8') ||
                            (currentLocation.charAt(i) == '9') ||
                            (currentLocation.charAt(i) == '0')) {
                        count++;
                        if (count == 5) {
                            this.currentLocation = currentLocation;
                        }
                    }
                    else{
                        throw new IllegalArgumentException("\nThe format of current location is wrong.");
                    }
                }
            }
            else if (currentLocation.substring(0, 1). equalsIgnoreCase("s") && currentLocation.length() == 6){
                for (int i = 1; i < currentLocation.length(); i++) {
                    if (currentLocation.charAt(i) == '1' ||
                            currentLocation.charAt(i) == '2' ||
                            currentLocation.charAt(i) == '3' ||
                            currentLocation.charAt(i) == '4' ||
                            currentLocation.charAt(i) == '5' ||
                            currentLocation.charAt(i) == '6' ||
                            currentLocation.charAt(i) == '7' ||
                            currentLocation.charAt(i) == '8' ||
                            currentLocation.charAt(i) == '9' ||
                            currentLocation.charAt(i) == '0') {
                        this.currentLocation = currentLocation;
                        count1++;
                        if (count1 == 5) {
                            this.currentLocation = currentLocation;
                        }
                    }
                    else{
                        throw new IllegalArgumentException("\nThe format of current location is wrong.");
                    }
                }
            }
        }
        else{
            throw new IllegalArgumentException("\nThe format of current location is wrong.");
        }
    }

    public void setCurrentLocationForMove(String currentLocation){
        this.currentLocation = currentLocation;
    }
}
