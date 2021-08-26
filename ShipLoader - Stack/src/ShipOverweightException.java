/**
 * @author Lawrence Liu
 * 113376858
 * Recititation: R04
 * Exception when ship is too heavy.
 */

public class ShipOverweightException extends Exception{
    public ShipOverweightException (String error){
        super(error);
    }
}
