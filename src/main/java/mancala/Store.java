package mancala;
import java.io.Serializable;
public class Store implements Serializable, Countable {
    private int stones;
    private Player owner;

    // Constructor
    public Store() {
        stones = 0;
    }

    public Player getOwner() {
        return owner;
    }

    /* package */    void setOwner(Player player) {
        owner = player;
    }

    // Getter method for the total number of stones in the store
    public int getStoneCount() {
        return stones;
    }


    // Method to add stones to the store
    public  void addStones(int amount) {
        if (amount > 0) {
            stones += amount;
        }
    }

    public void addStone(){
        stones += 1;
    }

    // Method to empty the store and return the number of stones that were in it
    public int removeStones() {
        int originalStones = stones;
        stones = 0;
        return originalStones;
    }

    // toString method
    @Override
    public String toString() {
        return "Store with " + stones + " stones. Owner: " + owner;
    }
}
