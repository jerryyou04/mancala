package mancala;
import java.util.List;
import java.util.ArrayList;

public class Board {
    private List<Pit> pits;
    private int pitsPerPlayer;

    public Board(int numPits, int stonesPerPit) {
        this.pits = new ArrayList<>();
        this.pitsPerPlayer = numPits / 2;
        for (int i = 0; i < numPits; i++) {
            Pit pit = new Pit(stonesPerPit);
            this.pits.add(pit);
        }
    }

    public void resetBoard(int stonesPerPit) {
        for (Pit pit : this.pits) {
            pit.removeStones();
            pit.addStones(stonesPerPit);
        }
    }

    public void moveStones(int startIndex, Player player) {
        int stones = this.pits.get(startIndex).removeStones();
        int currentIndex = startIndex;
        while (stones > 0) {
            currentIndex = (currentIndex + 1) % this.pits.size();
            if (currentIndex != startIndex) {
                this.pits.get(currentIndex).addStones(1);
                stones--;
            }
        }

        // Capture
        if (currentIndex < this.pitsPerPlayer) {
            Pit currentPit = this.pits.get(currentIndex);
            if (currentPit.numStones() == 1) {
                int oppositeIndex = this.pits.size() - 1 - currentIndex;
                Pit oppositePit = this.pits.get(oppositeIndex);
                int capturedStones = oppositePit.removeStones();
                player.addStonesToStore(capturedStones + 1);
                currentPit.removeStones();
            }
        }
    }

    public String toString() {
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < pits.size(); i++) {
                sb.append("Pit ").append(i).append(": ").append(pits.get(i)).append("\n");
            }
            return sb.toString();
    }
}
