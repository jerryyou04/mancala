package mancala;

public class NoSuchPlayerException extends Exception {
    public NoSuchPlayerException() {
        super("No such player");
    }

    public NoSuchPlayerException(String message) {
        super(message);
    }
}
