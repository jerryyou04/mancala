package mancala;
public class Player {
    private String name;
    private Store store;

    public Player(String name) {
        this.name = name;
        this.store = new Store();
    }

    public String getName() {
        return this.name;
    }

    public Store getStore() {
        return this.store;
    }

    public void addStonesToStore(int stones) {
        this.store.addStones(stones);
    }

    public int getStonesInStore() {
        return this.store.getStones();
    }

    @Override
    public String toString() {
        return "Player: " + this.name + ", Store: " + this.store;
    }
}
