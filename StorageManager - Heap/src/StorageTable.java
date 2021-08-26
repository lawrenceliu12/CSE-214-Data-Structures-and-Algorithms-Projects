/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #6
 * CSE 214
 * R04 - James Finn / Taylor Ngo
 */

import java.io.Serializable; //imports serializable
import java.util.HashMap; //imports hashmap
//Chose to use hashmap and not hashtable.

public class StorageTable extends HashMap <Integer, Storage> implements Serializable {
    //Extends HashMap so I don't have to create a HashMap in constructor
    //Implements serializable
    static int serialVersionUID; //static int member, but not used.

    /**
     * Storage table constructor.
     *
     * Takes no parameters.
     */
    public StorageTable(){
    }

    /**
     *
     * @param storageId
     * @param storage
     * @throws IllegalArgumentException
     *
     * Adds onto the hashMap.
     *
     * Key: storageID
     * Value: storage
     */
    public void putStorage(int storageId, Storage storage) throws IllegalArgumentException{
        if (storageId < 0){ //StorageId cannot be less than 0.
            throw new IllegalArgumentException("StorageID cannot be less than 0.\n");
        }
        if (storage == null){ //storage cannot be null.
            throw new IllegalArgumentException("Storage cannot be null.\n");
        }
        if (!(this.containsKey(storageId))){ //adds onto hashmap
            this.put(storageId, storage);
        }
        else{
            throw new IllegalArgumentException("Storage ID already contained.\n");
        }
    }

    /**
     *
     * @param storageID
     * @return
     *
     * Get the Storage value from the table having the indicated storageID.
     * If the requested storageID does not exist in the StorageTable, return null.
     */
    public Storage getStorage(int storageID) {
        if (this.containsKey(storageID)) {
            return this.get(storageID);
        } else {
            return null;
        }
    }

    /**
     *
     * @param id
     *
     * uses HashMap methods to remove a value from table.
     */
    public void removeStorage(int id){
        this.remove(id);
    }

    /**
     *
     * @param id
     * @return
     *
     * This checks if the table has a certain ID.
     * returns true if it does, false otherwise.
     */
    public boolean checkContainID(int id){
        return this.containsKey(id);
    }

    /**
     *
     * @param id
     * @return
     *
     * Checks to see if the specified ID exists.
     * If it exists, return true and false otherwise.
     */
    public boolean checkNotExist (int id){
        return this.get(id) == null;
    }

    /**
     *
     * @param client
     * @return
     *
     * This iterates through the entire keySet, initialized to a
     * tempStorage, which then checks if it is equal to the
     * specified client in the parameter.
     */
    public boolean checkContainClient(String client){
        for (int key: keySet()) {
            Storage tempStorage = this.get(key);
            if (tempStorage.getClient().equals(client)) {
                return true;
            }
        }
        return false;
    }

    /**
     *
     * @param id
     *
     * This gets specific values given the ID.
     */
    public void getSpecificValue (int id){
        int box = this.get(id).getId(); //essentially boxID
        String boxString = "Box " + box;
        String contents = this.get(id).getContents(); //Contents
        String client = this.get(id).getClient(); //Clients

        System.out.println(boxString);
        System.out.println("Contents: "  + contents);
        System.out.println("Owner: " + client);
        System.out.println();
    }

    /**
     *
     * @return
     *
     * this generates the top portion of the table, which is the
     * Box#, Contents, Owners, and then dashes underneath.
     */
    public String topTableString(){
        String firstLine = String.format
                ("%-5s%-8s%-10s%-30s%-5s", "Box #", "", "Contents", "", "Owner\n");
        String secondLine =
                "-------------------------------------------" +
                        "-------------------------------------------\n";

        return firstLine + secondLine;
    }

    /**
     *
     * @return
     *
     * toString method. Calls upon the topTableString, and prints out the table.
     * Utilized a bubble sort algorithm to sort all the IDs on the table.
     */
    public String toString(){
        String topTable = topTableString(); //gets topTable and prints
        System.out.print(topTable);

        Object[] keyArray = this.keySet().toArray();
        //Sets the keySet of the hashMap to an array.
        bubbleSort(keyArray); //Bubble sort

        for (Object key: keyArray){
            //Enhanced for loop that iterates a key object through the keys array.
            Storage tempStorage = this.get((int) key);
            //Per iteration, creates a tempStorage that gets the value of the key,
            // and typecasts it to an integer.
            System.out.printf("%-13s%-40s%-33s%n",
                    //Prints out the table content.
                    tempStorage.getId(),
                    tempStorage.getContents(),
                    tempStorage.getClient());
        }
        System.out.println();
        return "";
        //Returns an empty string, but uses the System.out.print functions above.
    }

    /**
     *
     * @param client
     *
     * This prints specifically for Case "C".
     * Uses the same code as toString, but modified.
     * Iterates through, but checks to see if the stored client String
     * is equivalent to the client String parameter. If it does, then it
     * prints the table contents of that specified client String.
     */
    public void twoString(String client){
        String topTable = topTableString();
        System.out.print(topTable);

        Object[] keyArray = this.keySet().toArray();
        //Sets the keySet of the hashMap to an array.
        bubbleSort(keyArray); //Bubble sort

        //Iterates key through keys array and prints if tempStorage is equal to clients.
        for (Object key: keyArray){
            Storage tempStorage = this.get((int) key);
            if (tempStorage.getClient().equals(client)){
                System.out.printf("%-13s%-40s%-33s%n",
                        tempStorage.getId(),
                        tempStorage.getContents(),
                        tempStorage.getClient());
            }
        }
        System.out.println();
    }

    /**
     *
     * @param keys
     *
     * Just a bubble sort algorithm that typecasts Object to int.
     */
    public void bubbleSort(Object[] keys){
        int num = keys.length;
        for (int i = 0; i < num - 1; i++){
            for (int k = 0; k < num - i - 1; k++){
                if ((int) keys[k] > (int) keys[k+1]){
                    int tempHold = (int) keys[k];
                    keys[k] = keys[k + 1];
                    keys[k + 1] = tempHold;
                }
            }
        }
    }
}