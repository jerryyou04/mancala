// package ui;
// import java.util.Scanner;
// import mancala.*;

// public class TextUI {
//     private static MancalaGame game;
//     private static Scanner scanner;

//     public TextUI() {
//         game = new MancalaGame(); // Assumes this initializes the game
//         scanner = new Scanner(System.in);
//     }

//     private static void initializePlayers() {
//         System.out.print("Enter name for Player One: ");
//         String playerOneName = scanner.nextLine();

//         System.out.print("Enter name for Player Two: ");
//         String playerTwoName = scanner.nextLine();

//         Player playerOne = new Player(playerOneName);
//         Player playerTwo = new Player(playerTwoName);
//         game.setPlayers(playerOne, playerTwo);
//     }

//     public static void main(String[] args) {
//         TextUI tui = new TextUI();

//         initializePlayers();
//         game.startNewGame(); // Start a new game

//         while (!game.isGameOver()) { // Main game loop
//             System.out.println(game.toString()); // Display the current board state

//             Player currentPlayer = game.getCurrentPlayer();
//             System.out.println("Current player: " + currentPlayer.getName());
//             System.out.print("Enter the number of the pit to move stones from: ");
//             int pitNumber = scanner.nextInt();

//             try {
//                 game.move(pitNumber); // Attempt to make the move
//             } catch (InvalidMoveException e) {
//                 System.out.println("Invalid move: " + e.getMessage());
//                 // The loop will continue, prompting the player for a valid move
//             } catch (Exception e) {
//                 System.out.println("An error occurred: " + e.getMessage());
//                 // Handle other unexpected exceptions
//             }
//         }

//         // Game is over, announce the winner
      
//          Player winner = game.getWinner();
//         System.out.println("Game over! The winner is " + winner.getName());
        
//     }
// }

package ui;

import mancala.MancalaGame;
import mancala.Player;
import mancala.InvalidMoveException;
import java.util.Scanner;
import java.io.IOException;
import mancala.Saver;

public class TextUI{

    private MancalaGame game;
    private Scanner scanner;
    private boolean newGame = false;
    private Player currPlay;

    public TextUI(){
        game = new MancalaGame();
        scanner = new Scanner(System.in);
    }

    private void startGame(){
        System.out.println("Is game over " + game.isGameOver());

        do{
            while(!game.isGameOver()){
                System.out.println("Position: " + game.getBoard().getDataStructure().getFinalPos());
                System.out.println("Is in store: " + game.getBoard().getDataStructure().isInStore());
                displayBoard();

                currPlay = game.getCurrentPlayer();
                System.out.println(currPlay.getName() + "'s turn.");

                 System.out.println("Enter a pit number to make a move, 'S' to save, or 'L' to load a previous game:");
                String input = scanner.next().toLowerCase();

                if (input.equals("s")) {
                    saveGame();
                    continue; // Skip the rest of the loop and prompt again
                } else if (input.equals("l")) {
                    loadGame();
                    continue; // Skip the rest of the loop and prompt again
                }

                try{
                    int pitNumber = Integer.parseInt(input);
                    game.move(pitNumber);
                }catch(InvalidMoveException e){
                    System.out.println(e.getMessage());
                }catch(RuntimeException ex){
                    System.out.println("\n" + ex.getMessage() + "Please enter a valid number.");             
                }
            }

             
            
            System.out.println(game.getWinner().getName() + " wins!");
            displayBoard();

            System.out.println("Would you like to play again? (y/n)");
            String input = scanner.next().toLowerCase();
            while(!input.equals("y") && !input.equals("n")){
                System.out.println("Invalid response. Please try again. (y/n)");
                input = scanner.next().toLowerCase();
            }

            if (input.equals("y")) {
                newGame = true;
                game.startNewGame();
            }else{
                newGame = false;
            }            

        } while (newGame);

    }

    private void setUpGame(){

        Player playerOne = new Player();
        Player playerTwo = new Player();
        newGame = false;

        System.out.println("Please enter Player One's Name: ");
        playerOne.setName(scanner.nextLine());
        System.out.println("Please enter Player Two's name: ");
        playerTwo.setName(scanner.nextLine());

        System.out.println("Please enter GameType (1) KalahRules (2) AyoRules");
        int gameType = scanner.nextInt();
        game.setGameRules(gameType);

        game.setPlayers(playerOne, playerTwo);

    }

    private void displayBoard() {
        System.out.println(game.toString());
    }

    public static void main(String[] args) {
        
        TextUI textUI = new TextUI();

        System.out.println("Let's play mancala!");
        textUI.setUpGame();
        textUI.startGame();

        System.out.println("Good game!");
    }

    private void saveGame() {
    System.out.println("Enter a name for your save file (without extension): ");
    String filename = scanner.next(); 
    filename += ".ser"; // Append .ser extension to the filename

        try {
            Saver.saveObject(game, filename);
            System.out.println("Game saved successfully as " + filename);
        } catch (IOException e) {
            System.out.println("Error saving game: " + e.getMessage());
        }
    }

    private void loadGame() {
        System.out.println("Enter the name of the save file to load (without extension): ");
        String filename = scanner.next(); 
        filename += ".ser"; // Append .ser extension to the filename

        try {
            game = (MancalaGame) Saver.loadObject(filename);
            System.out.println("Game loaded successfully from " + filename);
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading game: " + e.getMessage());
        }
    }

}