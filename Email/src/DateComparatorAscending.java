import java.io.Serializable;

/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #5
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

//Comparator class, which utilizes a comparator
// by implementing java's comparator. Takes in
// two parameters, which then compares the timestamp
// value to one another. This goes by ascending order,
// so whatever the newest added email is what will be
// first.
public class DateComparatorAscending
        implements java.util.Comparator<Email>, Serializable {
    public int compare(Email timestamp1, Email timestamp2) {
        return ((Email) timestamp1).getTimestamp().compareTo
                (((Email) timestamp2).getTimestamp());
    }
}