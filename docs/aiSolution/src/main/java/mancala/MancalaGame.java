package mancala;
public class MancalaGame {
    private Player player1;
    private Player player2;
    private Board board;
    private Player currentPlayer;

    public MancalaGame(String player1Name, String player2Name, int numPits, int stonesPerPit) {
        this.player1 = new Player(player1Name);
        this.player2 = new Player(player2Name);
        this.board = new Board(numPits, stonesPerPit);
        this.currentPlayer = this.player1;
    }

    public Player getCurrentPlayer() {
        return this.currentPlayer;
    }

    public void switchPlayer() {
        this.currentPlayer = (this.currentPlayer == this.player1) ? this.player2 : this.player1;
    }

    public void makeMove(int pitIndex) {
        this.board.moveStones(pitIndex, this.currentPlayer);
        this.switchPlayer();
    }

    public Player getWinner() {
        int stonesPlayer1 = this.player1.getStonesInStore();
        int stonesPlayer2 = this.player2.getStonesInStore();
        if (stonesPlayer1 > stonesPlayer2) {
            return this.player1;
        } else if (stonesPlayer2 > stonesPlayer1) {
            return this.player2;
        } else {
            return null;  // It's a draw
        }
    }

    @Override
    public String toString() {
        return "Player 1: " + this.player1 + "\nPlayer 2: " + this.player2 + "\nBoard: " + this.board;
    }
}
