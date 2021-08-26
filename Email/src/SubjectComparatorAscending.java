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
// two parameters, which then compares the subject
// value to one another. This goes by ascending order,
// so it goes by alphabetical order.
public class SubjectComparatorAscending
        implements java.util.Comparator<Email>, Serializable {
    public int compare(Email subject1, Email subject2) {
        return ((Email) subject1).getSubject().toLowerCase().compareTo
                (((Email) subject2).getSubject().toLowerCase());
    }
}
