package mancala;
/**
 * KalahRules class represents the rules for the Kalah variant of the Mancala game.
 */
public class KalahRules extends GameRules {
    private static final long serialVersionUID = 1L;
    private final MancalaDataStructure gameBoard = getDataStructure();
    /**
     * Constructor for KalahRules.
     */
    public KalahRules() {
        super(); 
    }
    /**
     * Moves stones according to the rules of Kalah.
     *
     * @param startPit The pit from which to start the move.
     * @param playerNum The number of the player making the move.
     * @return The number of stones added to the player's store during this move.
     * @throws InvalidMoveException If the move is invalid.
     */
    @Override
    public  int moveStones(final int startPit, final int playerNum) throws InvalidMoveException{ 
        //final MancalaDataStructure gameBoard = getDataStructure();
        if (gameBoard.getNumStones(startPit) == 0){
            throw new InvalidMoveException("Starting move cannot start from an empty pit!");
        } else if (playerNum == 1 && !(startPit >= 1 && startPit <= 6) || playerNum == 2 && !(startPit >= 7 && startPit <= 12)) {
            throw new InvalidMoveException("Pick your own pit!");
        }
        final int storedInStore = gameBoard.getStoreCount(playerNum); 
        distributeStones(startPit); // finding last position
        
        final int finalPosition = gameBoard.getFinalPos();
         // Check if capturing is appropriate
        if (shouldCapture(finalPosition, playerNum)) {
            captureStones(finalPosition); // adds one extra stone
        }

        return gameBoard.getStoreCount(playerNum) - storedInStore; // If no stones were captured
    }

    /**
     * Determines if capturing is required based on the rules of Kalah.
     *
     * @param pit The pit to check for capturing.
     * @param playerNum The number of the player.
     * @return {@code true} if capturing is required, {@code false} otherwise.
     */
    private boolean shouldCapture(final int pit, final int playerNum) {
        //final MancalaDataStructure gameBoard = getDataStructure();
        if (playerNum == 1 && pit <= 6 || playerNum == 2 && pit >= 7 && pit <= 12) {
            return gameBoard.getNumStones(pit) == 1 && !gameBoard.isInStore();
        }
        return false;
    }
    
    /**
     * Distributes stones from a specified pit.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    @Override
    /* package */  int distributeStones(final int startPit) { 
        //final MancalaDataStructure gameBoard = getDataStructure();
        final int stones = gameBoard.removeStones(startPit);
        final int playerNum = getCurrentPlayerNum();
        gameBoard.setIterator(startPit, playerNum, false); // Set iterator to start at the next pit

        for (int i = 0; i < stones; i++) {
            final Countable nextPit = gameBoard.next();
            nextPit.addStone();
        }

        //final int lastPosition = gameBoard.getCurrentPit();
        // Store the final position in the GameRules class
        gameBoard.setFinalPos(gameBoard.getCurrentPit());

        return stones; // Returns the number of stones distributed
     }

    /**
     * Determines if an extra turn is awarded to the player.
     *
     * @return {true} if the last stone lands in the player's store, {false} otherwise.
     */
     @Override
    /* package */int captureStones(final int stoppingPoint) {
        int stonesCaptured = 0;
        
        //final int oppositePit = 12 - stoppingPoint + 1;

        //final MancalaDataStructure gameBoard = getDataStructure();

        final int stones = gameBoard.getNumStones(stoppingPoint);


        if (stones == 1 && !gameBoard.isInStore()) { // if it's the last stone
            stonesCaptured += gameBoard.removeStones(12 - stoppingPoint + 1);
            stonesCaptured += gameBoard.removeStones(stoppingPoint); // remove one stone
            if (stoppingPoint <= 6) { // if it lands on playerOnes own side
                gameBoard.addToStore(1, stonesCaptured); // add extra stone
            } else {
                gameBoard.addToStore(2, stonesCaptured); // add extra stone
            }
        }

        return stonesCaptured;
    }

    /**
     * Determines if an extra turn is awarded to the player.
     *
     * @return {true} if the last stone lands in the player's store, {false} otherwise.
     */
    @Override
    public boolean isExtraTurn() {
        return gameBoard.isInStore(); // In Kalah, player gets an extra turn if the last stone is in the store
    }
}