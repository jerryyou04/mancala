package mancala;
public class Pit {
    private int numStones;

    public Pit(int numStones) {
        this.numStones = numStones;
    }

    public void addStones(int stones) {
        this.numStones += stones;
    }

    public int removeStones() {
        int stones = this.numStones;
        this.numStones = 0;
        return stones;
    }

    public boolean isEmpty() {
        return this.numStones == 0;
    }

    public int numStones() {
        return this.numStones;
    }

    @Override
    public String toString() {
        return Integer.toString(this.numStones);
    }
}
