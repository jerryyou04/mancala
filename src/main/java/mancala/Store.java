package mancala;
import java.io.Serializable;
/**
 * Represents a store in a Mancala game.
 * A store is used to collect and hold stones that are captured by a player.
 */
public class Store implements Serializable, Countable {
    private static final long serialVersionUID = 1L;
    private int stones;
    private Player owner;

    /**
     * Constructs a Store object with an initial stone count of zero.
     */
    public Store() {
        stones = 0;
    }
    /**
     * Returns the owner of this store.
     *
     * @return The player who owns this store.
     */
    public Player getOwner() {
        return owner;
    }

    /**
     * Sets the owner of this store.
     *
     * @param player The player to set as the owner of this store.
     */
    protected void setOwner(final Player player) {
        owner = player;
    }

    /**
     * Returns the total number of stones currently in the store.
     *
     * @return The number of stones in the store.
     */
    @Override
    public int getStoneCount() {
        return stones;
    }


    /**
     * Adds a specified number of stones to the store.
     *
     * @param amount The number of stones to add.
     */
    @Override
    public  void addStones(final int amount) {
        if (amount > 0) {
            stones += amount;
        }
    }

    /**
     * Adds a single stone to the store.
     */
    @Override
    public void addStone(){
        stones += 1;
    }

    /**
     * Empties the store of all stones and returns the number that were in it.
     *
     * @return The number of stones removed from the store.
     */
    @Override
    public int removeStones() {
        final int originalStones = stones;
        stones = 0;
        return originalStones;
    }

    /**
     * Returns a string representation of the store.
     *
     * @return String representation of the store.
     */
    @Override
    public String toString() {
        return "Store with " + stones + " stones. Owner: " + owner;
    }
}
