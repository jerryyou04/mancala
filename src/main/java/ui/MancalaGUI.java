package ui;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import mancala.GameRules;
import mancala.KalahRules;
import mancala.AyoRules;
import mancala.InvalidMoveException;
import mancala.MancalaGame;
import mancala.Player;
import mancala.Saver;
import mancala.UserProfile;


public class MancalaGUI {

    private JFrame frame;
    private JPanel gameBoardPanel;
    private JLabel statusBar;
    private MancalaGame game;

    public MancalaGUI() {
        initializeUI();
        game = new MancalaGame();
    }

    private void initializeUI() {
        frame = new JFrame("Mancala Game");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        // Setup game board
        gameBoardPanel = new JPanel();
        frame.add(gameBoardPanel, BorderLayout.CENTER);

        // Status bar
        statusBar = new JLabel("Welcome to Mancala!");
        statusBar.setFont(new Font("Calibri", Font.BOLD, 50));
        frame.add(statusBar, BorderLayout.SOUTH);

        // Setup menu
        JMenuBar menuBar = new JMenuBar();
        frame.setJMenuBar(menuBar);

        JPanel northPanel = new JPanel(); // Panel to hold buttons
        northPanel.setLayout(new FlowLayout(FlowLayout.CENTER)); // Set layout

        JButton statsButton = new JButton("Display Stats");
        statsButton.addActionListener(e -> displayStatsScreen());
        northPanel.add(statsButton);

        JButton mainScreenButton = new JButton("Main Screen");
        mainScreenButton.addActionListener(e -> returnToMainScreen());
        northPanel.add(mainScreenButton);

        frame.add(northPanel, BorderLayout.NORTH);



        // Add menu items directly to the menu bar
        JMenuItem newGameItem = new JMenuItem("New Game");
        newGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectGameTypeAndStartNewGame();
            }
        });
        menuBar.add(newGameItem);

        JMenuItem saveGameItem = new JMenuItem("Save Game");
        saveGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGame();
            }
        });
        menuBar.add(saveGameItem);

        JMenuItem loadGameItem = new JMenuItem("Load Game");
        loadGameItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGame();
            }
        });
        menuBar.add(loadGameItem);

        JMenuItem exitItem = new JMenuItem("Exit");
        exitItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        menuBar.add(exitItem);

        frame.pack();
        frame.setVisible(true);
    }

    private void saveGame() {
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setCurrentDirectory(Saver.getAssetsFolderPath().toFile());
    fileChooser.setDialogTitle("Save Game");
    
    int userSelection = fileChooser.showSaveDialog(frame);

    if (userSelection == JFileChooser.APPROVE_OPTION) {
        File fileToSave = fileChooser.getSelectedFile();
        String filename = fileToSave.getAbsolutePath();
        if (!filename.endsWith(".ser")) {
            filename += ".ser";
        }

        try {
            Saver.saveObject(game, filename);
            JOptionPane.showMessageDialog(frame, "Game saved successfully as " + filename, "Game Saved", JOptionPane.INFORMATION_MESSAGE);
        } catch (IOException e) {
            JOptionPane.showMessageDialog(frame, "Error saving game: " + e.getMessage(), "Save Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}

    // Implementing load game functionality
    private void loadGame() {
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(Saver.getAssetsFolderPath().toFile());
        fileChooser.setDialogTitle("Load Game");
        int userSelection = fileChooser.showOpenDialog(frame);

        if (userSelection == JFileChooser.APPROVE_OPTION) {
            File fileToLoad = fileChooser.getSelectedFile();
            String filename = fileToLoad.getAbsolutePath();

            try {
                game = (MancalaGame) Saver.loadObject(filename);
                refreshGameBoard();
                JOptionPane.showMessageDialog(frame, "Game loaded successfully from " + filename, "Game Loaded", JOptionPane.INFORMATION_MESSAGE);
            } catch (IOException | ClassNotFoundException e) {
                JOptionPane.showMessageDialog(frame, "Error loading game: " + e.getMessage(), "Load Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }


private void selectGameTypeAndStartNewGame() {
    String[] options = {"Kalah", "Ayoayo"};
    int choice = JOptionPane.showOptionDialog(frame, 
                                              "Choose the Game Type", 
                                              "Game Type", 
                                              JOptionPane.DEFAULT_OPTION, 
                                              JOptionPane.INFORMATION_MESSAGE, 
                                              null, options, options[0]);

    if (choice == JOptionPane.CLOSED_OPTION) {
        return; // User cancelled or closed the dialog, return to the start
    }

    // Proceed with game initialization
    game.setBoard(choice == 0 ? new KalahRules() : new AyoRules());
    game.startNewGame();

    // Ask for player names
    if (game.getPlayerOne().getName() == "P1" || game.getPlayerTwo().getName() == "P2") { // check if default initialized names
        String playerOneName = askForPlayerName("Player One's name:");
        if (playerOneName == null) {
            return; // User cancelled or closed the dialog, return to the start
        }

        String playerTwoName = askForPlayerName("Player Two's name:");
        if (playerTwoName == null) {
            return; // User cancelled or closed the dialog, return to the start
        }

        // Set player names and initialize players
        Player playerOne = new Player(playerOneName);
        Player playerTwo = new Player(playerTwoName);
        game.setPlayers(playerOne, playerTwo);
    }

    game.setPlayers(game.getPlayerOne(), game.getPlayerTwo()); // Re-register players with the game
    refreshGameBoard();
    statusBar.setText("New " + options[choice] + " game started!");
}

private String askForPlayerName(String prompt) {
    return JOptionPane.showInputDialog(frame, prompt);
}




    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MancalaGUI();
            }
        });
    }

    private void refreshGameBoard() {
    gameBoardPanel.removeAll();
    gameBoardPanel.setLayout(new BorderLayout());

    // Central panel with BoxLayout to stack top and bottom rows
    JPanel centralPanel = new JPanel();
    centralPanel.setLayout(new BoxLayout(centralPanel, BoxLayout.Y_AXIS));

    // Panel for the top row pits (Player 2's side)
    JPanel topRow = new JPanel(new GridLayout(1, 6));
    for (int i = 12; i >= 7; i--) {
        int pitNumber = i;  // effectively final variable for use in lambda
        JButton pit = new JButton("Pit " + pitNumber + ": " + game.getNumStones(pitNumber));
        pit.addActionListener(e -> handlePitClick(pitNumber));
        topRow.add(pit);
    }
    centralPanel.add(topRow);

    // Panel for the bottom row pits (Player 1's side)
    JPanel bottomRow = new JPanel(new GridLayout(1, 6));
    for (int i = 1; i <= 6; i++) {
        int pitNumber = i;  // effectively final variable for use in lambda
        JButton pit = new JButton("Pit " + pitNumber + ": " + game.getNumStones(pitNumber));
        pit.addActionListener(e -> handlePitClick(pitNumber));
        bottomRow.add(pit);
    }
    centralPanel.add(bottomRow);

    // Player 2's store on the left
    JButton playerTwoStore = new JButton("Store 2: " + game.getPlayerTwo().getStoreCount());
    gameBoardPanel.add(playerTwoStore, BorderLayout.WEST);

    // Player 1's store on the right
    JButton playerOneStore = new JButton("Store 1: " + game.getPlayerOne().getStoreCount());
    gameBoardPanel.add(playerOneStore, BorderLayout.EAST);

    // Add the central panel to the game board
    gameBoardPanel.add(centralPanel, BorderLayout.CENTER);

    gameBoardPanel.revalidate();
    gameBoardPanel.repaint();
}



    private void endGame() {
    Player winner;
    winner = game.getWinner();

    String message;
    if (winner == null) {
        message = "The game ended in a tie!";
    } else {
        message = "Game over! Winner: " + winner.getName();
    }
    updateStatsAfterGame(); // updates wins and games played

    statusBar.setText(message);
    refreshGameBoard();
    JOptionPane.showMessageDialog(frame, message, "Game Over", JOptionPane.INFORMATION_MESSAGE);
}

private void updateStatsAfterGame() {
    boolean isKalahGame = game.getBoard() instanceof KalahRules;

    if (isKalahGame) {
        game.getPlayerOne().getUserProfile().incrementKalahGamesPlayed();
        game.getPlayerTwo().getUserProfile().incrementKalahGamesPlayed();
    } else {
        game.getPlayerOne().getUserProfile().incrementAyoGamesPlayed();
        game.getPlayerTwo().getUserProfile().incrementAyoGamesPlayed();
    }

    Player winner = game.getWinner();
    if (winner != null) {
        if (isKalahGame) {
            winner.getUserProfile().incrementKalahWins();
        } else {
            winner.getUserProfile().incrementAyoWins();
        }
    }
}


    private void returnToMainScreen() {
    int confirm = JOptionPane.showConfirmDialog(
            frame,
            "Any unsaved progress will be lost. Do you want to return to the main screen?",
            "Return to Main Screen",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE
    );

    if (confirm == JOptionPane.YES_OPTION) {
        gameBoardPanel.removeAll();
        gameBoardPanel.revalidate();
        gameBoardPanel.repaint();
        statusBar.setText("Welcome to Mancala!"); // Update the status bar
    }
}

    private void displayStatsScreen() {
    JDialog statsDialog = new JDialog(frame, "Player Statistics", true);
    statsDialog.setLayout(new BorderLayout());

    JTextArea statsTextArea = new JTextArea();
    statsTextArea.setEditable(false);
    statsTextArea.setText(getFormattedStats());

    // Add the text area to the dialog
    statsDialog.add(new JScrollPane(statsTextArea), BorderLayout.CENTER);

    // Optionally add a close button
    JButton closeButton = new JButton("Close");
    closeButton.addActionListener(e -> statsDialog.dispose());
    statsDialog.add(closeButton, BorderLayout.SOUTH);

    // Set dialog size and make it visible
    statsDialog.setSize(400, 300); // Adjust size as needed
    statsDialog.setVisible(true);
}

    private String getFormattedStats() {
        // Format the statistics of both players
        StringBuilder statsBuilder = new StringBuilder();
        statsBuilder.append("Player One Stats:\n");
        statsBuilder.append(getPlayerStats(game.getPlayerOne()));
        statsBuilder.append("\nPlayer Two Stats:\n");
        statsBuilder.append(getPlayerStats(game.getPlayerTwo()));

        return statsBuilder.toString();
    }

    private String getPlayerStats(Player player) {
        UserProfile profile = player.getUserProfile();
        return "Name: " + profile.getName() +
            "\nKalah Games Played: " + profile.getKalahGamesPlayed() +
            "\nKalah Wins: " + profile.getKalahWins() +
            "\nAyo Games Played: " + profile.getAyoGamesPlayed() +
            "\nAyo Wins: " + profile.getAyoWins();
    }

    private void handlePitClick(int pitNumber) {
        if (game.isGameOver()) {
        JOptionPane.showMessageDialog(frame, "Game is over. Start a new game to continue playing.", "Game Over", JOptionPane.INFORMATION_MESSAGE);
        // If the game is over, don't respond to clicks
        return;
        }
        try {
            game.move(pitNumber);
            refreshGameBoard(); // Refresh the board after a move
            if (game.isGameOver()) {
                endGame();
            } else {
                statusBar.setText(game.getCurrentPlayer().getName() + "'s turn");
            }
        } catch (InvalidMoveException e) {
            JOptionPane.showMessageDialog(frame, e.getMessage(), "Invalid Move", JOptionPane.ERROR_MESSAGE);
        }
    }
}
