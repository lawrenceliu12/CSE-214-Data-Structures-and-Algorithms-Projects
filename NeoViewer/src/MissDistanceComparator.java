/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #7
 * CSE 214
 * R04 - James Finn / Taylor Ngo
 */

/**
 * MissDistanceComparator Class
 * Implements Java's comparator interface.
 */
public class MissDistanceComparator
        implements java.util.Comparator<NearEarthObject>{

    /**
     *
     * @param leftSide
     * @param rightSide
     * @return
     *
     * Compares left and right's miss distance,
     * and returns 0, -1, or 1.
     */
    public int compare
            (NearEarthObject leftSide, NearEarthObject rightSide){
        return Double.compare(((NearEarthObject) leftSide).getMissDistance(),
                ((NearEarthObject) rightSide).getMissDistance());
    }
}
