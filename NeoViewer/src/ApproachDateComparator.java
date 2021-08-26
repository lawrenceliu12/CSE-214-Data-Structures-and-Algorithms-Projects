/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #7
 * CSE 214
 * R04 - James Finn / Taylor Ngo
 */

/**
 * ApproachDateComparator Class
 * Implements Java's built in Comparator interface.
 */
public class ApproachDateComparator
        implements java.util.Comparator<NearEarthObject>{

    /**
     *
     * @param leftSide
     * @param rightSide
     * @return
     *
     * compares left and right's date
     */
    public int compare
            (NearEarthObject leftSide, NearEarthObject rightSide){

        return (((NearEarthObject) leftSide).getClosestApproachDate()
                .compareTo(((NearEarthObject) rightSide).getClosestApproachDate()));
    }
}
