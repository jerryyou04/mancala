package mancala;

public class InvalidMoveException extends Exception {
    private static final long serialVersionUID = 1L;
    public InvalidMoveException() {
        super("Invalid move");
    }

    public InvalidMoveException(final String message) {
        super(message);
    }
}
