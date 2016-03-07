/**
 * Classes to solve word ladders
 * Solves EE422C programming assignment #4
 * @author Tauseef Aziz
 * @author Trevor Eggenberger
 * @version 1.00 2016-03-06
 */


package assignment4;
/**
 * 
 * Exception to specify that a valid word ladder could not be found
 *
 */
public class NoSuchLadderException extends Exception
{
    private static final long serialVersionUID = 1L;

	/**
	 * exception constructor
	 * @param message
	 */
    public NoSuchLadderException(String message)
    {
        super(message);
    }
    
	/**
	 * exception constructor
	 * @param message
	 * @param throwable
	 */
    public NoSuchLadderException(String message, Throwable throwable)
    {
        super(message, throwable);
    }
}
