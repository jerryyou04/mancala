package mancala;

/**
 * AyoRules class represents the rules for the Ayoayo variant of the Mancala game.
 */
public class AyoRules extends GameRules {
    private static final long serialVersionUID = 1L;
    private final MancalaDataStructure gameBoard = getDataStructure();
    /**
     * Constructor for AyoRules.
     */
    public AyoRules() {
        super(); 
    }

    /**
     * Moves stones according to the rules of Ayoayo.
     *
     * @param startPit The pit from which to start the move.
     * @param playerNum The number of the player making the move.
     * @return The number of stones added to the player's store during this move.
     * @throws InvalidMoveException If the move is invalid.
     */
    @Override
    public int moveStones(final int startPit, final int playerNum) throws InvalidMoveException {
        //final MancalaDataStructure gameBoard = getDataStructure();
        if (gameBoard.getNumStones(startPit) == 0) {
            throw new InvalidMoveException("Starting move cannot start from an empty pit!");
        } else if (!isValidPit(startPit, playerNum)) {
            throw new InvalidMoveException("Invalid pit selection!");
        }
        final int stonesAdded = gameBoard.getStoreCount(playerNum);
        distributeStones(startPit);
        return gameBoard.getStoreCount(playerNum) - stonesAdded;
    }

    /**
     * Distributes stones from a specified pit in Ayoayo style.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    @Override
    /* package */int distributeStones(final int startPit) {
    //final MancalaDataStructure gameBoard = getDataStructure();
    final int playerNum = getCurrentPlayerNum();
    int stones = gameBoard.removeStones(startPit);
    int lastPosition = startPit;
    int stonesDistributed = 0;
    gameBoard.setIterator(lastPosition, playerNum, true); // Set iterator to start at the next pit, skipping the starting pit
    do {
        while (stones > 0) {
            final Countable nextPit = gameBoard.next();
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

    /**
     * Captures stones based on the rules of Ayoayo.
     *
     * @param stoppingPoint The pit where the last stone was placed.
     * @return The number of stones captured.
     */
    @Override
    /* package */int captureStones(final int stoppingPoint) {
        //final MancalaDataStructure gameBoard = getDataStructure();
        final int oppositePit = 12 - stoppingPoint + 1;
        final int stonesCaptured = gameBoard.getNumStones(oppositePit);

        gameBoard.addToStore(getCurrentPlayerNum(), stonesCaptured);
        gameBoard.removeStones(oppositePit);

        return stonesCaptured;
    }

    /**
     * Determines if a pit is valid based on the Ayoayo rules.
     *
     * @param pitNum The pit to check.
     * @param playerNum The number of the player.
     * @return {@code true} if the pit is valid, {@code false} otherwise.
     */
    private boolean isValidPit(final int pitNum, final int playerNum) {
        return playerNum == 1 && pitNum >= 1 && pitNum <= 6 || 
           playerNum == 2 && pitNum >= 7 && pitNum <= 12;
    }

    /**
     * Determines if capturing is required based on the rules of Ayoayo.
     *
     * @param pit The pit to check for capturing.
     * @param playerNum The number of the player.
     * @return {@code true} if capturing is required, {@code false} otherwise.
     */
    private boolean shouldCapture(final int pit, final int playerNum) {
        //final MancalaDataStructure gameBoard = getDataStructure();
        return gameBoard.getNumStones(pit) == 1 && 
            !gameBoard.isInStore() &&
             isValidPit(pit, playerNum);
    }

      /**
     * Determines if an extra turn is awarded to the player in Ayoayo.
     *
     * @return {@code false} as Ayoayo does not provide an extra turn.
     */
     @Override
    public boolean isExtraTurn() {
        return false; // In Ayo, player does not get an extra turn
    }
}
