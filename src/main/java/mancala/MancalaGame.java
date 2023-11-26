package mancala;
import java.util.ArrayList;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;

public class MancalaGame implements Serializable{
    private static final Logger LOGGER = Logger.getLogger(MancalaGame.class.getName());
    private GameRules board;
    private Player winner;
    private boolean isGameOver;
    private Player playerOne;
    private Player playerTwo;
    private Player currentPlayer;

    public MancalaGame() {
        playerOne = new Player();
        playerTwo = new Player();
        isGameOver = false;
        currentPlayer = playerOne;
    }

    public GameRules getBoard() {
        return board;
    }

    public Player getCurrentPlayer(){
        if (board.getCurrentPlayerNum() == 1) {
            return playerOne;
        } else if (board.getCurrentPlayerNum() == 2) {
            return playerTwo;
        } else {
            return null;
        }
    }

    public int getNumStones(int pitNum) throws PitNotFoundException {
        if (pitNum < 1 || pitNum > 12){
            throw new PitNotFoundException();
        }
        int totalStones = 0;
        for (int i = 1; i <= 12; i++){
            totalStones += board.getNumStones(i);
        }
        return totalStones;
    }

    public int getStoreCount(Player player) {

        return player.getStoreCount();
    } 

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

    public boolean isGameOver(){
        isGameOver = false;
        try {
            if (board.isSideEmpty(1)) {
                isGameOver = true;
            } else if (board.isSideEmpty(7)){
                isGameOver = true;
            }
        } catch (PitNotFoundException e) {
            return false;
            }
        return isGameOver;
    }

    public int move(int startPit) throws InvalidMoveException {
        if (startPit < 1 || startPit > 12) {
            throw new InvalidMoveException("Pit out of bounds!");
        } 
        if ((startPit <= 6 && currentPlayer.equals(playerTwo)) || (startPit >= 7 && currentPlayer.equals(playerOne))) {
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

    public void setBoard(GameRules theBoard){
        board = theBoard;
    }

    public void setCurrentPlayer(Player player){
        currentPlayer = player;
    }

    public void setPlayers(Player onePlayer, Player twoPlayer){
        playerOne = onePlayer;
        playerTwo = twoPlayer;

        currentPlayer = playerOne; // setting currentPlayer to player 1

        board.registerPlayers(onePlayer, twoPlayer);
    }

    public void startNewGame(){
        board.resetBoard();
    }

    @Override
    public String toString(){
        StringBuilder boardString = new StringBuilder();
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

    private int onSide(int startPit) { // for the useless return statement of move ?
        int onSide = 0;
        int start, end;

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

    public Player getPlayerOne() {
        return playerOne;
    }

    public Player getPlayerTwo() {
        return playerTwo;
    }

    public void setGameRules(int gameType) {
        if (gameType == 1) {
            board = new KalahRules();
        } else {
            board = new KalahRules();
        }
    }

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