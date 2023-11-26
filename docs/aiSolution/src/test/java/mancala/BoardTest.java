package mancala;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;
    private Player player;

    @BeforeEach
    public void setUp() {
        board = new Board(12, 4);
        player = new Player("Test Player");
    }

    @Test
    public void testMoveStonesDoesNotThrowException() {
        try {
            board.moveStones(0, player);
        } catch (Exception e) {
            fail("moveStones should not throw any exceptions");
        }
    }

    @Test
    public void testMoveStonesChangesBoardState() {
        String before = board.toString();
        board.moveStones(0, player);
        String after = board.toString();
        assertNotEquals(before, after);
    }

    // ... add more tests as needed ...
}
