/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #7
 * CSE 214
 * R04 - James Finn / Taylor Ngo
 */

/**
 * ReferenceIDComparator Class
 * Implements Java's built in comparator interface.
 */
public class ReferenceIDComparator
        implements java.util.Comparator<NearEarthObject>{

    /**
     *
     * @param leftSide
     * @param rightSide
     * @return
     *
     * Compares the left to the right for reference ID,
     * and then returns either 0, 1, or -1.
     */
    public int compare
            (NearEarthObject leftSide, NearEarthObject rightSide){

        return Integer.compare(((NearEarthObject) leftSide).getReferenceID(),
                ((NearEarthObject) rightSide).getReferenceID());
    }
}
