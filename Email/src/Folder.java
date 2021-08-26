/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #5
 * CSE 214 Summer
 * R30 - Charlie Clark
 */

import java.io.Serializable; //Imports serializable
import java.util.ArrayList; //Imports an ArrayList as data structure
import java.util.Comparator; //Imports comparator to compare.

public class Folder implements Serializable { //Folder class
    private ArrayList<Email> emails;
    //Creates ArrayList of emails. This holds all the array, like a folder.
    private String name; //Name of the folder.
    private String currentSortingMethod; //Current way of sorting the folder.
    Comparator <Email> comparator; //Comparator object to compare between two objects.

    /**
     *
     * @param name
     * Gets the name of a folder.
     *
     * Constructs the Folder class, which has a name as its parameter.
     */
    public Folder(String name){
        this.name = name;
        currentSortingMethod = "sortByDateDescending"; //Default sorting
        emails = new ArrayList<Email>();
    }

    /**
     *
     * @return
     * getter for arraylist of emails.
     */
    public ArrayList<Email> getEmails() {
        return emails;
    }

    /**
     *
     * @return
     * getter for folder name
     */
    public String getName() {
        return name;
    }

    /**
     *
     * @return
     * getter for its current sorting method
     */
    public String getCurrentSortingMethod() {
        return currentSortingMethod;
    }

    /**
     *
     * @param name
     * takes in name of folder
     *
     * sets the name of folder.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @param emails
     * takes in an arraylist of emails
     *
     * sets the arraylist of emails
     */
    public void setEmails(ArrayList<Email> emails) {
        this.emails = emails;
    }

    /**
     *
     * @param currentSortingMethod
     * gets the current sorting method
     *
     * sets a sorting method.
     */
    public void setCurrentSortingMethod(String currentSortingMethod) {
        this.currentSortingMethod = currentSortingMethod;
    }

    /**
     *
     * @param email
     * Gets an email
     *
     * Adds the email by adding it into the ArrayList.
     * Goes by individual folder's choice of sort, so
     * whenever the email is added to the ArrayList,
     * depending on the sort method, it will sort the new
     * email added in according to the current choice of sort.
     */
    public void addEmail(Email email) {
        if (emails.isEmpty()) {
            emails.add(email);
            return;
        }

        if (currentSortingMethod.equals("sortByDateDescending")) {
            emails.add(1, email);
            sortByDateDescending();
        } else if (currentSortingMethod.equals("sortByDateAscending")) {
            emails.add(1, email);
            sortByDateAscending();
        } else if (currentSortingMethod.equals("sortBySubjectDescending")) {
            emails.add(1, email);
            sortBySubjectDescending();
        } else if (currentSortingMethod.equals("sortBySubjectAscending")) {
            emails.add(1, email);
            sortBySubjectAscending();
        }
    }

    /**
     *
     * @param index
     * Gets index of removing email.
     * @return
     *
     * Uses the index - 1 and removes the email from the ArrayList.
     */
    public Email removeEmail (int index){
        if (index < 0){
            throw new IllegalArgumentException("Index cannot be less than 0.");
        }
        if (index > emails.size()){
            throw new IllegalArgumentException("This index does not exist.");
        }
        if (emails.isEmpty()){
            throw new IllegalArgumentException("There is no email to remove.");
        }
        return emails.remove(index - 1);
    }

    /**
     * Void method. Sorts by the subject ascending, which is the reverse of ascend.
     * This means that it sorts the emails by the emails subjects/names, in which
     * this sort makes it so that it sorts the subjects in alphabetical order.
     *
     * I.E -> a to z. letters starting from a to z will be sorted first until the list
     * reaches z.
     */
    public void sortBySubjectAscending(){
        comparator = new SubjectComparatorAscending();

        if (comparator == null){
            throw new IllegalArgumentException
                    ("Comparator must not be null. Null in subject ascending.");
        }

        emails.sort(comparator);

        currentSortingMethod = "sortBySubjectAscending";
    }

    /**
     * Void method. Sorts by the subject descending, which is the reverse of ascend.
     * This means that it sorts the emails by the emails subjects/names, in which
     * this sort makes it so that it sorts the subjects in reverse alphabetical order.
     *
     * I.E -> z to a. letters starting from z to a will be sorted first until the list
     * reaches a.
     */
    public void sortBySubjectDescending(){
        comparator = new SubjectComparatorDescending();

        if (comparator == null){
            throw new IllegalArgumentException
                    ("Comparator must not be null. Null in subject descending.");
        }

        emails.sort(comparator);

        currentSortingMethod = "sortBySubjectDescending";
    }

    /**
     * Void method. Sorts by date ascending, which is the reverse of descend.
     * This means that it sorts the emails by whatever emails were added
     * last to first.
     */
    public void sortByDateAscending(){
        comparator = new DateComparatorAscending();

        if (comparator == null){
            throw new IllegalArgumentException
                    ("Comparator must not be null. Null in date ascending.");
        }

        emails.sort(comparator);

        currentSortingMethod = "sortByDateAscending";
    }

    /**
     * Void method. Sorts by date descending, which is the default sort.
     * This means that it sorts the emails by whatever emails were added
     * first to last.
     */
    public void sortByDateDescending(){
        comparator = new DateComparatorDescending();

        if (comparator == null){
            throw new IllegalArgumentException
                    ("Comparator must not be null. Null in date descending.");
        }

        emails.sort(comparator);

        currentSortingMethod = "sortByDateDescending";
    }
}
