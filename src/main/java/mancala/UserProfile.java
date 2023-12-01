package mancala;
import java.io.Serializable;

public class UserProfile implements Serializable {
    private static final long serialVersionUID = 1L;
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

    public UserProfile(final String name) {
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
    public void setName(final String name) {
        this.name = name;
    }

    public void setKalahGamesPlayed(final int kalahGamesPlayed) {
        this.kalahGamesPlayed = kalahGamesPlayed;
    }

    public void setAyoGamesPlayed(final int ayoGamesPlayed) {
        this.ayoGamesPlayed = ayoGamesPlayed;
    }

    public void setKalahWins(final int kalahWins) {
        this.kalahWins = kalahWins;
    }

    public void setAyoWins(final int ayoWins) {
        this.ayoWins = ayoWins;
    }

      public double getKalahWinRate() {
        return kalahGamesPlayed > 0 ? (double) kalahWins / kalahGamesPlayed : 0.0;
    }

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
