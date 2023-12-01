package mancala;
import java.io.Serializable;
/**
 * Represents a pit in a Mancala game.
 * Each pit can hold a certain number of stones.
 */
public class Pit implements Serializable, Countable {
    private static final long serialVersionUID = 1L;
    private int stones;

    /**
     * Constructs a Pit object with an initial stone count of zero.
     */
    public Pit() {
        stones = 0;
    }

    /**
     * Adds a single stone to this pit.
     */
    @Override
    public void addStone(){
        stones += 1;
    }

    /**
     * Returns the current number of stones in this pit.
     *
     * @return The number of stones.
     */
    @Override
    public int getStoneCount(){
        return stones;
    }

    /**
     * Adds a specified number of stones to this pit.
     *
     * @param numToAdd The number of stones to add.
     */
     @Override
    public void addStones(final int numToAdd) {
        stones += numToAdd;
    }

    /**
     * Removes all stones from this pit and returns the number removed.
     *
     * @return The number of stones removed.
     */
    @Override
    public int removeStones(){
        final int originalStones = stones;
        stones = 0;
        return originalStones;
    }
}
