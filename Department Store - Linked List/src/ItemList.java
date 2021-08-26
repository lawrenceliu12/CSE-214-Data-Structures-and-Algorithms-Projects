/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #2
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

import java.math.BigInteger; //Imports BigInteger for hex values.

public class ItemList {
    private ItemInfoNode head; //head of linked list
    private ItemInfoNode tail; //tail of linked list
    private ItemInfoNode cursor; //cursor of linked list

    /**
     * Empty Constructor
     * Sets head tail and cursor all to null.
     */
    public ItemList(){
        head = null;
        tail = null;
        cursor = null;
    }

    /**
     *
     * @param name
     * name of item
     * @param rfidTag
     * rfidTag of item
     * @param price
     * price of item
     * @param initPosition
     * original position of item
     *
     * This method receives four parameters that are then made
     * into an ItemInfo, named 'info' for me. This info is then
     * stored into the ItemInfoNode, named 'node' for me. After,
     * it starts creating the doubly linked list through converting
     * the RFID Tag to its hex. This will then be sorted by its hex
     * values by using compareTo, adding all of them from ascending
     * order to a linked list (first being the smallest and last being
     * the biggest).
     *
     * This method is O(n), as it takes one while loop. It iterates
     * through the entire linked list as it is constructing a total of
     * n times, hence O(n).
     * In all cases, best, average, and worst, it will be O(n).
     */
    public void insertInfo(String name, String rfidTag, double price, String initPosition) {
        ItemInfo info = new ItemInfo();
        info.setName(name);
        info.setRfidTagNumber(rfidTag);
        info.setPrice(price);
        info.setOriginalLocation(initPosition);
        info.setCurrentLocation(initPosition);

        ItemInfoNode node = new ItemInfoNode();
        node.setInfo(info);

        if (head == null){ //initial condition
            cursor = head = tail = node;
        }
        else {
            cursor = head;
            BigInteger rfidTagValue = new BigInteger ((toHex(rfidTag)), 16);
            while (cursor != null){
                String rfidTagCursor = cursor.getInfo().getRfidTagNumber();
                BigInteger rfidCursor = new BigInteger (toHex(rfidTagCursor), 16);
                if (rfidTagValue.compareTo(rfidCursor) < 0){
                    //if the new rfidtag is less than the already existing one
                    if (cursor != head){ //Not first value in linked list
                        node.setPrev(cursor.getPrev());
                        node.setNext(cursor);
                        cursor.getPrev().setNext(node);
                        cursor.setPrev(node);
                    }
                    else { //First value in linked list
                        cursor.setPrev(node);
                        node.setNext(cursor);
                        head = node;
                    }
                    return;
                }
                else if (rfidTagValue.compareTo(rfidCursor) == 0) { //Checks equality
                    if (cursor == head) {
                        cursor.setPrev(node);
                        node.setNext(cursor);
                        head = node;
                    }
                    else {
                        node.setPrev(cursor.getPrev());
                        node.setNext(cursor);
                        cursor.getPrev().setNext(node);
                        cursor.setPrev(node);
                    }
                    return;
                }
                goNext();
            }

            //appends to tail
            BigInteger rfidTail = new BigInteger
                    (toHex(tail.getInfo().getRfidTagNumber()), 16);
            if (rfidTagValue.compareTo(rfidTail) < 0){
                if (head == tail){ //checks if there is only one node
                    node.setNext(head);
                    head.setPrev(node);
                    head = node;
                }
                else{
                    node.setPrev(tail.getPrev());
                    node.setNext(tail);
                    tail.getPrev().setNext(node);
                    tail.setPrev(node);
                }
            }
            else{ //value is bigger than the tail.
                tail.setNext(node);
                node.setPrev(tail);
                tail = node;
            }
        }
    }

    /**
     * This void method removes all that has their current location
     * to "out." This means that there is a while loop that iterates
     * through the entire linked list until it detects "out," it will
     * remove it from the linked list. There are multiple cases, where
     * the first if is it is any item past the first item on the list.
     * The second is if there is only one item on the list, and the third
     * is when you are removing from the first item on the list but there
     * are still more nodes in the linked list.
     *
     * This entire method is going to be O(n), as it is only travelling through
     * the linked list n times, until it reaches the end of the list when cursor
     * becomes null.
     */
    public void removeAllPurchased(){
        cursor = head;
        printTable();
        System.out.println();
        String fPrint = "%-14s%-7s%-10s%-5s%-10s%-6s%-10s%-3s%-7.2f%n";
        while (cursor != null){
            String current = cursor.getInfo().getCurrentLocation();
            String rfid = cursor.getInfo().getRfidTagNumber();
            String name = cursor.getInfo().getName();
            String original = cursor.getInfo().getOriginalLocation();
            double price = cursor.getInfo().getPrice();
            if (String.valueOf(cursor.getInfo().getCurrentLocation()).toLowerCase().equals("out")){
                System.out.printf(fPrint, name, "", rfid, "", original, "", current, "", price);
                if (cursor != head) { //Any item past the first item on the list
                    cursor.getPrev().setNext(cursor.getNext());
                    cursor.getNext().setPrev(cursor.getPrev());
                }
                else if (head == tail){ //Only one item in the list
                    head = null;
                }
                else { //First item in the list but there are more
                    cursor.setNext(cursor.getNext());
                    cursor.setPrev(null);
                    head = cursor;
                }
            }
            goNext();
        }
    }

    /**
     *
     * @param rfidTag
     * this is the given rfidTag
     * @param source
     * this is the given location
     * @param dest
     * this is the wanted destination
     * @return
     * returns true or false
     * @throws IllegalArgumentException
     * throws IllegalArgumentException
     * where there is a wrong format of input
     *
     * This method will move an item from one location to another.
     * It moves from the tail backwards to head, as shown in the I/O.
     *
     * The time complexity will be O(n), as it is still only traversing
     * through the linked list until it reaches a point where cursor is null.
     * This means that it will move a total of n times, hence, O(n).
     */
    public boolean moveItem (String rfidTag, String source, String dest) throws IllegalArgumentException {
        if (dest.equalsIgnoreCase("out")) {
            throw new IllegalArgumentException("Destination cannot be out.");
        }
        if (source.equalsIgnoreCase("out")) {
            throw new IllegalArgumentException("Source cannot be out.");
        }
        if (dest.substring(0, 1).equalsIgnoreCase("c") || dest.substring(0, 1).equalsIgnoreCase("s")) {
            ItemInfoNode temp;
            int check = 0;

            cursor = tail;
            while (cursor != null) {
                if (cursor.getInfo().getRfidTagNumber().equalsIgnoreCase(rfidTag)) {
                    if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase(source)) {
                        temp = cursor;
                        temp.getInfo().setCurrentLocationForMove(dest);
                        check = 1;
                        break;
                    }
                }
                cursor = cursor.getPrev();
            }
            return check == 1;
        }
        else{
            throw new IllegalArgumentException("Destination is of invalid format for shelf/cart.");
        }
    }

    /**
     * Prints the entire linked list in a table format.
     *
     * Time complexity is O(n) because it traverses through
     * the linked list and prints out their data. This means
     * that it traverses a total of n times until cursor becomes
     * null.
     */
    public void printAll(){
        printTable();
        System.out.println();
        cursor = head;
        while (cursor != null){
            String name = cursor.getInfo().getName();
            String rfid = cursor.getInfo().getRfidTagNumber();
            String original = cursor.getInfo().getOriginalLocation();
            String current = cursor.getInfo().getCurrentLocation();
            double price = cursor.getInfo().getPrice();
            System.out.printf("%-14s%-7s%-10s%-7s%-8s%-8s%-8s%-3s%-1s%-6.2f%n",
                    name, "", rfid, "", original, "", current, "", "$", price);
            goNext();
        }
    }

    /**
     *
     * @param location
     * Gets the location that filters out the table.
     *
     * Like the printAll() method, but instead of
     * printing it all out, it filters by location,
     * so if current location is equal to the inputted
     * location, then it prints out that specific data.
     *
     * This time complexity will be O(n), as it is traversing
     * through the linked list n times until cursor becomes
     * null.
     */
    public void printByLocation(String location){
        printTable();
        System.out.println();
        cursor = head;
        while (cursor != null){
            if (cursor.getInfo().getCurrentLocation().equalsIgnoreCase(location)){
                System.out.printf("%-14s%-7s%-10s%-7s%-8s%-8s%-8s%-3s%-1s%-6.2f%n",
                        cursor.getInfo().getName(), "", cursor.getInfo().getRfidTagNumber(),
                        "", cursor.getInfo().getOriginalLocation(), "",
                        cursor.getInfo().getCurrentLocation(), "", "$", cursor.getInfo().getPrice());
            }
            goNext();
        }
    }

    /**
     * This method cleans the entire store, meaning
     * that those on the wrong current location will
     * be put back into its original location.
     *
     * This method is O(n) because it still traverses
     * through the linked list n times until it fulfills the if condition,
     * setting the original location back, and continuing its
     * traversal until it reaches the end of the linked list, where
     * cursor is null.
     */
    public void cleanStore(){
        printTable();
        System.out.println();
        String fPrint = "%-14s%-7s%-10s%-7s%-8s%-8s%-8s%-3s%-1s%-6.2f%n";
        cursor = head;
        while (cursor != null){
            String current = cursor.getInfo().getCurrentLocation();
            String original = cursor.getInfo().getOriginalLocation();
            if (!current.equalsIgnoreCase(original)){
                if (!current.equalsIgnoreCase("out")){
                    System.out.printf(fPrint, cursor.getInfo().getName(), "",
                            cursor.getInfo().getRfidTagNumber(), "",
                            original, "", current, "", "$", cursor.getInfo().getPrice());
                    cursor.getInfo().setCurrentLocation(original);
                }
            }
            goNext();
        }
    }

    /**
     *
     * @param cartNumber
     * Inputted cart number (c***)
     * @return
     * returns the total
     *
     * This method checks to see if there are any carts,
     * which is indicated by the "c" in the current location.
     * If there is a "c," then it switches that location to out,
     * and prints out all the values that were checked out.
     *
     * This time complexity is O(n), as it travels through the
     * linked list n times until it reaches the end of the list
     * when cursor is null.
     */
    public double checkOut(String cartNumber){
        cursor = head;
        String fPrint = "%-14s%-7s%-10s%-7s%-8s%-8s%-8s%-3s%-1s%-6.2f%n";
        printTable();
        System.out.println();
        double total = 0;
        while (cursor != null){
            String current = cursor.getInfo().getCurrentLocation();
            String rfid = cursor.getInfo().getRfidTagNumber();
            String name = cursor.getInfo().getName();
            String original = cursor.getInfo().getOriginalLocation();
            double price = cursor.getInfo().getPrice();
            if (current.equalsIgnoreCase(cartNumber)){
                System.out.printf(fPrint, name, "", rfid, "", original, "", current, "", "$", price);
                cursor.getInfo().setCurrentLocation("out");
                System.out.println(cursor.getInfo().getCurrentLocation());
                total += price;
            }
            goNext();
        }
        return total;
    }

    /**
     * Extra Credit
     * @param rfidTag
     * This is the user inputted rfidTag to filter the table.
     *
     * Takes in the rfidTag, and compares it all the data node's
     * rfidTag. If there is a match, it filters and prints out those
     * of the same rfidTags.
     *
     * This time complexity is O(n) because like the rest,
     * it travels through the entire linked list n times
     * until it reaches an end of when cursor is finally null.
     */
    public void printByRFIDTagNumber(String rfidTag){
        cursor = head;
        String fPrint = "%-14s%-7s%-10s%-7s%-8s%-8s%-8s%-3s%-1s%-6.2f%n";
        printTable();
        System.out.println();
        while (cursor != null) {
            String rfid = cursor.getInfo().getRfidTagNumber();
            String name = cursor.getInfo().getName();
            String original = cursor.getInfo().getOriginalLocation();
            String current = cursor.getInfo().getCurrentLocation();
            double price = cursor.getInfo().getPrice();

            if (rfid.equalsIgnoreCase(rfidTag)){
                System.out.printf(fPrint, name, "", rfid, "", original, "", current, "", "$", price);
            }
            goNext();
        }
    }

    /**
     *
     * @param arg
     * Takes in string to convert
     * @return
     * Returns a string that becomes a hex.
     *
     * This method takes in a string,
     * and converts it into a hex.
     *
     * Used for RFID Tag.
     *
     * This time complexity is O(1), as it only runs once
     * or at a constant rate.
     */
    public String toHex(String arg){
        return String.format("%040x", new BigInteger(1, arg.getBytes(/*YOUR_CHARSET?*/)));
    }

    /**
     * Void method/Helper method that prints the top of
     * the table for multiple methods that prints a table.
     *
     * Time complexity: O(1), as these for loops are all
     * predetermined in length, and not at a variable rate.
     */
    public void printTable(){
        System.out.printf("%-37s%-8s%-8s%-7s%n", "", "Original", "", "Current");
        System.out.printf("%-14s%-10s%-4s%-9s%-9s%-7s%-8s%-5s%-6s%n",
                "Item Name", "", "RFID", "", "Location", "", "Location", "", "Price");
        for (int i = 0; i < 14; i++){
            System.out.print("-");
        }
        System.out.printf("%-7s", "");
        for (int j = 0; j < 10; j++){
            System.out.print("-");
        }
        System.out.printf("%-5s", "");
        for (int k = 0; k < 10; k++){
            System.out.print("-");
        }
        System.out.printf("%-6s", "");
        for (int l = 0; l < 10; l++){
            System.out.print("-");
        }
        System.out.printf("%-3s", "");
        for (int x = 0; x < 7; x++){
            System.out.print("-");
        }
    }

    /**
     * Helper method that just makes the cursor take in
     * its next value. Essentially moves the cursor next.
     *
     * Time Complexity: O(1), as it is just setting cursor to
     * its next value.
     */
    public void goNext(){
        cursor = cursor.getNext();
    }
}
