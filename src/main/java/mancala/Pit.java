package mancala;
import java.io.Serializable;
public class Pit implements Serializable, Countable {
    private static final long serialVersionUID = 1L;
    private int stones;

    public Pit() {
        stones = 0;
    }

    @Override
    public void addStone(){
        stones += 1;
    }

    @Override
    public int getStoneCount(){
        return stones;
    }

     @Override
    public void addStones(final int numToAdd) {
        stones += numToAdd;
    }

    @Override
    public int removeStones(){
        final int originalStones = stones;
        stones = 0;
        return originalStones;
    }
}
