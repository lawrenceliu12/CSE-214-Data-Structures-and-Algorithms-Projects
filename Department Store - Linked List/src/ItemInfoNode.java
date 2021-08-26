/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #2
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

public class ItemInfoNode {
    private ItemInfo infoData; //Creates an object of ItemInfo to store
    private ItemInfoNode next;  //Goes next
    private ItemInfoNode prev; //Goes previous

    /**
     * Empty Constructor
     */
    public ItemInfoNode(){
    }

    /**
     *
     * @param info
     *
     * Sets the info into the nodes
     */
    public void setInfo (ItemInfo info){
        if (info == null){
            throw new IllegalArgumentException("Info cannot be null.");
        }
        infoData = info;
    }

    /**
     *
     * @return
     *
     * Gets info
     */
    public ItemInfo getInfo(){
        return infoData;
    }

    /**
     *
     * @param node
     *
     * goes next
     */
    public void setNext(ItemInfoNode node){
        next = node;
    }

    /**
     *
     * @param node
     *
     * goes previous
     */
    public void setPrev(ItemInfoNode node){
        prev = node;
    }

    /**
     *
     * @return
     *
     * gets the next value
     */
    public ItemInfoNode getNext(){
        return next;
    }

    /**
     *
     * @return
     *
     * gets the previous value
     */
    public ItemInfoNode getPrev(){
        return prev;
    }
}
