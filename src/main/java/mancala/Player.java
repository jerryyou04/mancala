package mancala;
import java.io.Serializable;

public class Player implements Serializable{
    private static final long serialVersionUID = 1L;
    private Store playerStore;
    private final UserProfile userProfile;


    public Player() {
        playerStore = new Store();
        userProfile = new UserProfile();
    }

     public Player(final String name) {
        userProfile = new UserProfile(name);
        playerStore = new Store();
    }

    public String getName(){
        return userProfile.getName();
    }

    public void setName(final String name) {
        userProfile.setName(name);
    }

        
    protected void setStore(final Store store) {
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
        return "Player: " + getName() + ", Store: " + playerStore.toString();
    }
}