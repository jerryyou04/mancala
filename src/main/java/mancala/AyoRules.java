package mancala;

public class AyoRules extends GameRules {
    private static final long serialVersionUID = 1L;
    public AyoRules() {
        super(); 
    }

    @Override
    public int moveStones(int startPit, int playerNum) throws InvalidMoveException {
        MancalaDataStructure gameBoard = getDataStructure();
        if (gameBoard.getNumStones(startPit) == 0) {
            throw new InvalidMoveException("Starting move cannot start from an empty pit!");
        } else if (!isValidPit(startPit, playerNum)) {
            throw new InvalidMoveException("Invalid pit selection!");
        }
        int stonesAdded = gameBoard.getStoreCount(playerNum);
        distributeStones(startPit);
        return gameBoard.getStoreCount(playerNum) - stonesAdded;
    }

    @Override
    /* package */int distributeStones(int startPit) {
    MancalaDataStructure gameBoard = getDataStructure();
    int playerNum = getCurrentPlayerNum();
    int stones = gameBoard.removeStones(startPit);
    int lastPosition = startPit;
    int stonesDistributed = 0;
    gameBoard.setIterator(lastPosition, playerNum, true); // Set iterator to start at the next pit, skipping the starting pit
    do {
        while (stones > 0) {
            Countable nextPit = gameBoard.next();
            nextPit.addStone();
            stonesDistributed++;
            stones--;
            if (!gameBoard.isInStore()){
                lastPosition = gameBoard.getCurrentPit();
            }
            if (stones == 0 && gameBoard.getNumStones(lastPosition) > 1 && !gameBoard.isInStore()) {
                // If last stone lands in a non-empty pit, pick up all stones from that pit for next distribution
                stones = gameBoard.removeStones(lastPosition);
            }
        }

    } while (stones > 0); // Continue until all stones are distributed

    if (shouldCapture(lastPosition, playerNum)) {
        stonesDistributed += captureStones(lastPosition);
        }
        return stonesDistributed;
    }

    @Override
    /* package */int captureStones(int stoppingPoint) {
        MancalaDataStructure gameBoard = getDataStructure();
        int oppositePit = 12 - stoppingPoint + 1;
        int stonesCaptured = gameBoard.getNumStones(oppositePit);

        gameBoard.addToStore(getCurrentPlayerNum(), stonesCaptured);
        gameBoard.removeStones(oppositePit);

        return stonesCaptured;
    }

    private boolean isValidPit(final int pitNum, final int playerNum) {
        return playerNum == 1 && pitNum >= 1 && pitNum <= 6 || 
           playerNum == 2 && pitNum >= 7 && pitNum <= 12;
    }

    private boolean shouldCapture(int pit, int playerNum) {
        MancalaDataStructure gameBoard = getDataStructure();
        return gameBoard.getNumStones(pit) == 1 && 
            !gameBoard.isInStore() &&
             isValidPit(pit, playerNum);
    }

     @Override
    public boolean isExtraTurn() {
        return false; // In Ayo, player does not get an extra turn
    }
}
