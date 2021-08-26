/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #6
 * CSE 214
 * R04 - James Finn / Taylor Ngo
 */

import java.io.Serializable; //Import serializable

public class Storage implements Serializable { //Storage class
    //Implements the imported java class.
    static long serialVersionID; //static long variable
    private int id; //id variable
    private String client; //client variable
    private String contents; //content variable

    /**
     *
     * @param id
     * @param client
     * @param contents
     *
     * Storage Constructor
     */
    public Storage(int id, String client, String contents){
        this.id = id;
        this.client = client;
        this.contents = contents;
    }

    /**
     *
     * @return
     *
     * returns id value
     */
    public int getId() {
        return id;
    }

    /**
     *
     * @return
     *
     * returns client string
     */
    public String getClient() {
        return client;
    }

    /**
     *
     * @return
     *
     * returns content string
     */
    public String getContents() {
        return contents;
    }

    /**
     *
     * @param id
     *
     * sets ID
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     *
     * @param client
     *
     * sets client string
     */
    public void setClient(String client) {
        this.client = client;
    }

    /**
     *
     * @param contents
     *
     * sets contents string
     */
    public void setContents(String contents) {
        this.contents = contents;
    }
}
