package mancala;
import java.io.Serializable;
/**
 * Represents a player in a Mancala game.
 * Each player has a store to collect stones and a user profile.
 */
public class Player implements Serializable{
    private static final long serialVersionUID = 1L;
    private Store playerStore;
    private final UserProfile userProfile;

    /**
     * Constructs a Player object with a new store and user profile.
     */
    public Player() {
        playerStore = new Store();
        userProfile = new UserProfile();
    }
    /**
     * Constructs a Player object with a specified name.
     * Initializes a new store and user profile with the given name.
     *
     * @param name The name of the player.
     */
     public Player(final String name) {
        userProfile = new UserProfile(name);
        playerStore = new Store();
    }
    /**
     * Returns the name of the player.
     *
     * @return The name of the player.
     */
    public String getName(){
        return userProfile.getName();
    }
    /**
     * Sets the name of the player.
     *
     * @param name The name to set for the player.
     */
    public void setName(final String name) {
        userProfile.setName(name);
    }

    /**
     * Retrieves the UserProfile associated with this player.
     * 
     * The UserProfile contains details such as the player's name,
     * games played, and wins for each type of Mancala game (Kalah and Ayoayo).
     * 
     * @return UserProfile of the player.
     */
    public UserProfile getUserProfile(){
        return userProfile;
    }

    /**
     * Sets the store associated with the player.
     *
     * @param store The store to be associated with the player.
     */
    protected void setStore(final Store store) {
        playerStore = store;
    }

    /**
     * Returns the store belonging to the player.
     *
     * @return The store of the player.
     */
    public Store getStore(){
        return playerStore;
    }

    /**
     * Returns the count of stones in the player's store.
     *
     * @return The number of stones in the player's store.
     */
    public int getStoreCount(){
        return playerStore.getStoneCount();
    }

    /**
     * Returns a string representation of the player, including name and store details.
     *
     * @return String representation of the player.
     */
    @Override
    public String toString() {
        return "Player: " + getName() + ", Store: " + playerStore.toString();
    }
}