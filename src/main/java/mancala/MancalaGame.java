package mancala;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 * Represents a game of Mancala, handling game state and player actions.
 */
public class MancalaGame implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(MancalaGame.class.getName());
    private GameRules board;
    private Player winner;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    /**
     * Constructs a new Mancala game with two players.
     */
    public MancalaGame() {
        playerOne = new Player();
        playerTwo = new Player();
        currentPlayer = playerOne;
    }

       /**
     * Gets the current game board with its rules.
     *
     * @return The current game board.
     */
    public GameRules getBoard() {
        return board;
    }

    /**
     * Retrieves the current player.
     *
     * @return The player whose turn it is.
     */
    public Player getCurrentPlayer(){
        return board.getCurrentPlayerNum() == 1 ? playerOne : playerTwo;
    }

    /**
     * Calculates the total number of stones in a specific pit.
     *
     * @param pitNum The pit number to calculate stones for.
     * @return The total number of stones in the specified pit.
     */
    public int getNumStones(final int pitNum) {
        int totalStones = 0;
        for (int i = 1; i <= 12; i++){
            totalStones += board.getNumStones(i);
        }
        return totalStones;
    }

    /**
     * Gets the number of stones in a player's store.
     *
     * @param player The player whose store count is to be retrieved.
     * @return The number of stones in the player's store.
     */
    public int getStoreCount(final Player player) {

        return player.getStoreCount();
    } 

     /**
     * Determines the winner of the game, if the game is over.
     *
     * @return The player who has won the game, or null in case of a tie.
     */
    public Player getWinner() {
        // gives stuff at end
        playerOne.getStore().addStones(board.givePitsAtEnd(1));
        playerTwo.getStore().addStones(board.givePitsAtEnd(12));

        final int totalPOne = playerOne.getStoreCount();
        final int totalPTwo = playerTwo.getStoreCount();

        if (totalPOne == totalPTwo){
            winner = null;
        } else if (totalPOne > totalPTwo) {
            winner = playerOne;
        } else if (totalPOne < totalPTwo) {
            winner = playerTwo;
        }
        
        return winner;
    }

     /**
     * Checks if the game is over.
     *
     * @return {@code true} if the game is over, {@code false} otherwise.
     */
    public boolean isGameOver(){
        return board.isSideEmpty(1) || board.isSideEmpty(7);
    }

    /**
     * Attempts to make a move for the current player.
     *
     * @param startPit The starting pit for the move.
     * @return The number of stones on the side of the board after the move.
     * @throws InvalidMoveException If the move is invalid.
     */
    public int move(final int startPit) throws InvalidMoveException {
        if (startPit < 1 || startPit > 12) {
            throw new InvalidMoveException("Pit out of bounds!");
        } 
        if (startPit <= 6 && currentPlayer.equals(playerTwo) || startPit >= 7 && currentPlayer.equals(playerOne)) {
            throw new InvalidMoveException("Access your own pits, " + currentPlayer.getName() + "!");
        }
        if (board.getNumStones(startPit) == 0) {
            throw new InvalidMoveException("Pit is empty");
        }
        try {
            board.moveStones(startPit, board.getCurrentPlayerNum());
        } catch(InvalidMoveException e) { 
            LOGGER.log(Level.SEVERE, "An exception occurred: Invalid Move", e);
            }
        if (!board.isExtraTurn()) {
            switchPlayer();
        }
        return onSide(startPit);
         
    }

    /**
     * Sets the GameRules object.
     * 
     * @param theBoard the GameRules object to be set.
     */
    public void setBoard(final GameRules theBoard){
        board = theBoard;
    }
    /**
     * Sets the current player.
     * 
     * @param player the player to be set as the current player.
     */
    public void setCurrentPlayer(final Player player){
        currentPlayer = player;
    }

    /**
     * Sets the players of the game.
     * 
     * @param onePlayer The first player.
     * @param twoPlayer The second player.
     */
    public void setPlayers(final Player onePlayer, final Player twoPlayer){
        playerOne = onePlayer;
        playerTwo = twoPlayer;

        currentPlayer = playerOne; // setting currentPlayer to player 1

        board.registerPlayers(onePlayer, twoPlayer);
    }

    /**
     * Starts a new game by resetting the board.
     */
    public void startNewGame(){
        board.resetBoard();
    }

    /**
     * Returns a string representation of the game's current state.
     * 
     * @return The string representation of the game.
     */
    @Override
    public String toString(){
        final StringBuilder boardString = new StringBuilder();
        boardString.append("-------------------------------------------------------------------------\n");
        boardString.append("| Player 2 Store ");
        for (int i = 12; i >= 7; i--) {
            boardString.append("| Pit ").append(i).append(" ");
        }
        boardString.append("| Player 1 Store |\n");
        boardString.append("|      ").append(playerTwo.getStoreCount()).append("      ");
        for (int i = 12; i >= 7; i--) {
            boardString.append("|    ").append(board.getNumStones(i)).append("   ");
        }
        boardString.append("|      ").append(playerOne.getStoreCount()).append("         |\n");
        boardString.append("-------------------------------------------------------------------------\n");
        boardString.append("|             ");
        for (int i = 1; i <= 6; i++) {
            boardString.append("| Pit ").append(i).append("  ");
        }
        boardString.append("|                |\n");
        boardString.append("|          ").append("   ");
        for (int i = 1; i <= 6; i++) {
            boardString.append("|   ").append(board.getNumStones(i)).append("    ");
        }
        boardString.append("|       ").append("         |\n");
        boardString.append("-------------------------------------------------------------------------\n");
        return boardString.toString();
    }

    /**
     * Calculates the number of stones on one side of the board.
     * 
     * @param startPit The starting pit to calculate from.
     * @return The total number of stones on one side of the board.
     */
    private int onSide(final int startPit) { // for the useless return statement of move ?
        int onSide = 0;
        int start;
        int end;

        if (startPit >= 1 && startPit <= 6) {
            start = 1;
            end = 7;
        } else {
            start = 7;
            end = 13;
        }

        for (int i = start; i < end; i++) {
            onSide += board.getNumStones(i);
        }

        return onSide; // Returns the total number of stones on the player's side
    }

    /**
     * Retrieves player one of the game.
     * 
     * @return Player one.
     */
    public Player getPlayerOne() {
        return playerOne;
    }

    /**
     * Retrieves player two of the game.
     * 
     * @return Player two.
     */
    public Player getPlayerTwo() {
        return playerTwo;
    }


    /**
     * Sets the game rules based on the specified game type.
     *
     * @param gameType The game type to set (0 for Kalah, 1 for Ayo).
     */
    public void setGameRules(final int gameType) {
        if (gameType == 0) {
            board = new KalahRules();
        } else {
            board = new AyoRules();
        }
    }

    /**
     * Switches the current player to the next player.
     */
    private void switchPlayer() {
        if (currentPlayer.equals(playerOne)) {
            currentPlayer = playerTwo;
            board.setPlayer(2);
        } else {
            currentPlayer = playerOne;
            board.setPlayer(1);
        }
    }
}