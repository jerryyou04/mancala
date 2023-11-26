package mancala;
import java.io.Serializable;

public class UserProfile implements Serializable {
    private String name;
    private int kalahGamesPlayed;
    private int ayoGamesPlayed;
    private int kalahWins;
    private int ayoWins;

    // Constructor
    public UserProfile() {
        this.kalahGamesPlayed = 0;
        this.ayoGamesPlayed = 0;
        this.kalahWins = 0;
        this.ayoWins = 0;
    }

    public UserProfile(String name) {
        this.name = name;
        this.kalahGamesPlayed = 0;
        this.ayoGamesPlayed = 0;
        this.kalahWins = 0;
        this.ayoWins = 0;
    }

    // Getters
    public String getName() {
        return name;
    }

    public int getKalahGamesPlayed() {
        return kalahGamesPlayed;
    }

    public int getAyoGamesPlayed() {
        return ayoGamesPlayed;
    }

    public int getKalahWins() {
        return kalahWins;
    }

    public int getAyoWins() {
        return ayoWins;
    }

    // Setters
    public void setName(String name) {
        this.name = name;
    }

    public void setKalahGamesPlayed(int kalahGamesPlayed) {
        this.kalahGamesPlayed = kalahGamesPlayed;
    }

    public void setAyoGamesPlayed(int ayoGamesPlayed) {
        this.ayoGamesPlayed = ayoGamesPlayed;
    }

    public void setKalahWins(int kalahWins) {
        this.kalahWins = kalahWins;
    }

    public void setAyoWins(int ayoWins) {
        this.ayoWins = ayoWins;
    }

    // Methods to increment game counts and wins
    public void incrementKalahGamesPlayed() {
        this.kalahGamesPlayed++;
    }

    public void incrementAyoGamesPlayed() {
        this.ayoGamesPlayed++;
    }

    public void incrementKalahWins() {
        this.kalahWins++;
    }

    public void incrementAyoWins() {
        this.ayoWins++;
    }

    // toString method for debugging and display purposes
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
