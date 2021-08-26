/**
 * @author Lawrence Liu
 * 113376858
 * lawrence.liu.1@stonybrook.edu
 * Assignment #7
 * CSE 214
 * R04 - James Finn / Taylor Ngo
 */

/**
 * DiameterComparator Class
 * Implements Java's built in Comparator interface.
 */
public class DiameterComparator
        implements java.util.Comparator<NearEarthObject>{

    /**
     *
     * @param leftSide
     * @param rightSide
     * @return
     *
     * Compares left to right for the diameter,
     * and returns either 0, -1, or 1.
     */
    public int compare
            (NearEarthObject leftSide, NearEarthObject rightSide){

        return Double.compare(((NearEarthObject) leftSide).getAverageDiameter(),
                ((NearEarthObject) rightSide).getAverageDiameter());
    }
}
