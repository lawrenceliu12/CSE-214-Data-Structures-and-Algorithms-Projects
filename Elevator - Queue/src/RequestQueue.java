/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #3
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

import java.util.Queue; //Implements java's queue.
import java.util.LinkedList; //Implements java's linked list.

public class RequestQueue { //Request Queue Class
    Queue<Request> queue; //Creates a Queue of requests

    /**
     * Default Constructor.
     *
     * Iterates the queue as Linked List of Requests.
     */
    public RequestQueue(){
        queue = new LinkedList<Request>();
    }

    /**
     *
     * @param request
     *
     * Takes request and adds it to the end of linked list queue.
     */
    public void enqueue(Request request){
        queue.add(request);
    }

    /**
     *
     * @return
     *
     * returns the first value and removes it from the linked list.
     */
    public Request dequeue(){
        return queue.poll();
    }

    /**
     *
     * @return
     *
     * gets size of queue.
     */
    public int size(){
        return queue.size();
    }

    /**
     *
     * @return
     *
     * Checks if the queue is empty.
     */
    public boolean isEmpty(){
        return queue.isEmpty();
    }

    /**
     *
     * @return
     *
     * Checks if the queue is not empty. Helper method.
     */
    public boolean isNotEmpty(){
        return !queue.isEmpty();
    }

    /**
     *
     * @return
     *
     * Helper method. Checks the first value of the queue.
     */
    public Request peek(){
        return queue.peek();
    }
}
