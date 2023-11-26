package ui;

import mancala.*;
import java.util.Scanner;

public class TextUI {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter player 1 name: ");
        String player1Name = scanner.nextLine();
        System.out.print("Enter player 2 name: ");
        String player2Name = scanner.nextLine();
        int numPits = 12;
        int stonesPerPit = 4;
        MancalaGame game = new MancalaGame(player1Name, player2Name, numPits, stonesPerPit);

        while (true) {
            System.out.println(game);
            Player currentPlayer = game.getCurrentPlayer();
            System.out.println(currentPlayer.getName() + "'s turn");
            System.out.print("Enter pit index to make a move: ");
            int pitIndex = scanner.nextInt();
            game.makeMove(pitIndex);

            Player winner = game.getWinner();
            if (winner != null) {
                System.out.println("The winner is: " + winner.getName());
                break;
            }
        }
        scanner.close();
    }
}
