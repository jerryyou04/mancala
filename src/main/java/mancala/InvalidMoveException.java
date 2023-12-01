package mancala;
/**
 * Custom exception class for handling invalid moves in the Mancala game.
 */
public class InvalidMoveException extends Exception {
    private static final long serialVersionUID = 1L;
    /**
     * Default constructor that uses a default error message.
     */
    public InvalidMoveException() {
        super("Invalid move");
    }

    /**
     * Constructor that accepts a custom error message.
     * 
     * @param message The error message to be displayed when this exception is thrown.
     */
    public InvalidMoveException(final String message) {
        super(message);
    }
}
