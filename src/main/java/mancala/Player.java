package mancala;
import java.io.Serializable;

public class Player implements Serializable{
    private String playerName;
    private Store playerStore;
    private UserProfile userProfile;


    public Player() {
        playerStore = new Store();
        userProfile = new UserProfile();
    }

     public Player(String name) {
        userProfile = new UserProfile();
        playerName = name;
        playerStore = new Store();
    }

    public String getName(){
        return playerName;
    }

    public void setName(String name) {
        playerName = name;
    }

        // Setter method for store
    /* package */    void setStore(Store store) {
        playerStore = store;
    }

    public Store getStore(){
        return playerStore;
    }

    public int getStoreCount(){
        return playerStore.getStoneCount();
    }

    // toString method
    @Override
    public String toString() {
        return "Player: " + playerName + ", Store: " + playerStore.toString();
    }
}