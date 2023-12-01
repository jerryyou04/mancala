package mancala;
import java.io.Serializable;
/**
 * Represents a user's profile in the Mancala game, keeping track of their game statistics.
 */
public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;
    private String name;
    private int kalahGamesPlayed;
    private int ayoGamesPlayed;
    private int kalahWins;
    private int ayoWins;

    /**
     * Default constructor. Initializes game counts and wins to zero.
     */
    public UserProfile() {
        this.kalahGamesPlayed = 0;
        this.ayoGamesPlayed = 0;
        this.kalahWins = 0;
        this.ayoWins = 0;
    }

    /**
     * Constructor with user name. Initializes game counts and wins to zero.
     * 
     * @param name The name of the user.
     */

    public UserProfile(final String name) {
        this.name = name;
        this.kalahGamesPlayed = 0;
        this.ayoGamesPlayed = 0;
        this.kalahWins = 0;
        this.ayoWins = 0;
    }

    /**
     * Retrieves the user's name.
     * 
     * @return The name of the user.
     */
    public String getName() {
        return name;
    }

    /**
     * Retrieves the total number of Kalah games played by the user.
     * 
     * @return The number of Kalah games played.
     */
    public int getKalahGamesPlayed() {
        return kalahGamesPlayed;
    }

    /**
     * Retrieves the total number of Ayoayo games played by the user.
     * 
     * @return The number of Ayoayo games played.
     */
    public int getAyoGamesPlayed() {
        return ayoGamesPlayed;
    }

    /**
     * Retrieves the total number of Kalah wins by the user.
     * 
     * @return The number of Kalah wins.
     */
    public int getKalahWins() {
        return kalahWins;
    }

    /**
     * Retrieves the total number of Ayoayo wins by the user.
     * 
     * @return The number of Ayoayo wins.
     */
    public int getAyoWins() {
        return ayoWins;
    }

    /**
     * Sets the user's name.
     * 
     * @param name The new name for the user.
     */
    public void setName(final String name) {
        this.name = name;
    }

    /**
     * Sets the number of Kalah games played.
     * 
     * @param kalahGamesPlayed The new total of Kalah games played.
     */
    public void setKalahGamesPlayed(final int kalahGamesPlayed) {
        this.kalahGamesPlayed = kalahGamesPlayed;
    }

    /**
     * Sets the number of Ayoayo games played.
     * 
     * @param ayoGamesPlayed The new total of Ayoayo games played.
     */
    public void setAyoGamesPlayed(final int ayoGamesPlayed) {
        this.ayoGamesPlayed = ayoGamesPlayed;
    }

    /**
     * Sets the number of Kalah wins.
     * 
     * @param kalahWins The new total of Kalah wins.
     */
    public void setKalahWins(final int kalahWins) {
        this.kalahWins = kalahWins;
    }

    /**
     * Sets the number of Ayoayo wins.
     * 
     * @param ayoWins The new total of Ayoayo wins.
     */
    public void setAyoWins(final int ayoWins) {
        this.ayoWins = ayoWins;
    }

    /**
     * Calculates the win rate for Kalah games.
     * 
     * @return The win rate for Kalah games as a decimal.
     */
      public double getKalahWinRate() {
        return kalahGamesPlayed > 0 ? (double) kalahWins / kalahGamesPlayed : 0.0;
    }

    /**
     * Calculates the win rate for Ayoayo games.
     * 
     * @return The win rate for Ayoayo games as a decimal.
     */
    public double getAyoWinRate() {
        return ayoGamesPlayed > 0 ? (double) ayoWins / ayoGamesPlayed : 0.0;
    }

    // Methods to increment game counts and wins
    // public void incrementKalahGamesPlayed() {
    //     this.kalahGamesPlayed++;
    // }

    // public void incrementAyoGamesPlayed() {
    //     this.ayoGamesPlayed++;
    // }

    // public void incrementKalahWins() {
    //     this.kalahWins++;
    // }

    // public void incrementAyoWins() {
    //     this.ayoWins++;
    // }

    /**
     * Provides a string representation of the user profile for debugging and display purposes.
     * 
     * @return A string representing the user profile.
     */
    @Override
    public String toString() {
        return "UserProfile{" +
                "name='" + name + '\'' +
                ", kalahGamesPlayed=" + kalahGamesPlayed +
                ", ayoGamesPlayed=" + ayoGamesPlayed +
                ", kalahWins=" + kalahWins +
                ", ayoWins=" + ayoWins +
                '}';
    }
}
