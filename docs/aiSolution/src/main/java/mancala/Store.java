package mancala;
public class Store {
    private int numStones;

    public Store() {
        this.numStones = 0;
    }

    public void addStones(int stones) {
        this.numStones += stones;
    }

    public int getStones() {
        return this.numStones;
    }

    @Override
    public String toString() {
        return Integer.toString(this.numStones);
    }
}
