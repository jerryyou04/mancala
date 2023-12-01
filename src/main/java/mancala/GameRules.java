package mancala;
import java.io.Serializable;
/**
 * Abstract class representing the rules of a Mancala game.
 * KalahRules and AyoRules will subclass this class.
 */
public abstract class GameRules implements Serializable {
    private static final long serialVersionUID = 1L;
    private final MancalaDataStructure gameBoard;
    private int currentPlayer = 1; // Player number (1 or 2)

    /**
     * Constructor to initialize the game board.
     */
    public GameRules() {
        gameBoard = new MancalaDataStructure();
        gameBoard.setUpPits();
    }

    /**
     * Get the number of stones in a pit.
     *
     * @param pitNum The number of the pit.
     * @return The number of stones in the pit.
     */
    public int getNumStones(final int pitNum) {
        return gameBoard.getNumStones(pitNum);
    }

    /**
     * Get the game data structure.
     *
     * @return The MancalaDataStructure.
     */
    public MancalaDataStructure getDataStructure() {
        return gameBoard;
    }

    /**
     * Check if a side (player's pits) is empty.
     *
     * @param pitNum The number of a pit in the side.
     * @return True if the side is empty, false otherwise.
     */
    public boolean isSideEmpty(final int pitNum) {
        final int startIndex = pitNum <= 6 ? 1 : 7;
        final int endIndex = pitNum <= 6 ? 6 : 12;

        for (int i = startIndex; i <= endIndex; i++) {
            if (gameBoard.getNumStones(i) != 0) {
                return false; // Return false as soon as a non-empty pit is found
            }
        }
        return true; // Return true if no non-empty pits are found
    }


    /**
     * Set the current player.
     *
     * @param playerNum The player number (1 or 2).
     */
    public void setPlayer(final int playerNum) {
        currentPlayer = playerNum;
    }

    /**
     * Perform a move and return the number of stones added to the player's store.
     *
     * @param startPit  The starting pit for the move.
     * @param playerNum The player making the move.
     * @return The number of stones added to the player's store.
     * @throws InvalidMoveException If the move is invalid.
     */
    public abstract int moveStones(int startPit, int playerNum) throws InvalidMoveException;

    /**
     * Distribute stones from a pit and return the number distributed.
     *
     * @param startPit The starting pit for distribution.
     * @return The number of stones distributed.
     */
    /* package */abstract int distributeStones(int startPit);

    /**
     * Capture stones from the opponent's pit and return the number captured.
     *
     * @param stoppingPoint The stopping point for capturing stones.
     * @return The number of stones captured.
     */
    /* package */abstract int captureStones(int stoppingPoint);

    /**
     * Register two players and set their stores on the board.
     *
     * @param one The first player.
     * @param two The second player.
     */
    public void registerPlayers(final Player one, final Player two) {
        // this method can be implemented in the abstract class.
         // Create a new store for each player
        final Store storeOne = new Store();
        final Store storeTwo = new Store();

        // Set the owner of each store
        storeOne.setOwner(one);
        storeTwo.setOwner(two);

        one.setStore(storeOne);
        two.setStore(storeTwo);

        // Add the stores to the gameBoard
        gameBoard.setStore(storeOne, 1);
        gameBoard.setStore(storeTwo, 2);

        

        /* make a new store in this method, set the owner
         then use the setStore(store,playerNum) method of the data structure*/
    }

    /**
     * Reset the game board by setting up pits and emptying stores.
     */
    public void resetBoard() { // empty pits code
        gameBoard.setUpPits(); // not working
        gameBoard.emptyStores();
    }

    /**
     * Returns a string representation of the current game state.
     *
     * @return A string indicating the current player.
     */
    @Override
    public String toString() {
        // Implement toString() method logic here.
        return "Current Player :" + currentPlayer;
    }

    /**
     * Collects and returns the number of stones from all pits on one side of the board.
     * This method is typically used at the end of the game to gather remaining stones.
     *
     * @param pitNum The pit number indicating the player's side. Ranges from 1 to 6 for Player 1, and 7 to 12 for Player 2.
     * @return The total number of stones collected from the specified side.
     */
    protected int givePitsAtEnd(final int pitNum){
        int stonesAdded = 0;
        if (pitNum >=1 && pitNum <=6){
            for (int i = 1; i <= 6; i++){
                stonesAdded += gameBoard.removeStones(i);
            }
        }else if (pitNum >=7 && pitNum <=12){
            for (int i = 7; i <= 12; i++){
                stonesAdded += gameBoard.removeStones(i);
            }
        }
        return stonesAdded;
    }
    /**
     * Retrieves the current player's number.
     *
     * @return The number of the current player.
     */
    public int getCurrentPlayerNum(){
        return currentPlayer;
    }
    /**
     * Determines if the current move grants an extra turn based on the game rules.
     * This method must be implemented by subclasses to define specific rules for an extra turn.
     *
     * @return {@code true} if an extra turn is granted, {@code false} otherwise.
     */
    public abstract boolean isExtraTurn();
}
