package mancala;

import java.util.ArrayList;

public class KalahRules extends GameRules {

    // Constructor
    public KalahRules() {
        super(); 
    }

    public  int moveStones(int startPit, int playerNum) throws InvalidMoveException{ 
        MancalaDataStructure gameBoard = getDataStructure();
        if (gameBoard.getNumStones(startPit) == 0){
            throw new InvalidMoveException("Starting move cannot start from an empty pit!");
        } else if (playerNum == 1 && !(startPit >= 1 && startPit <= 6) || playerNum == 2 && !(startPit >= 7 && startPit <= 12)) {
            throw new InvalidMoveException("Pick your own pit!");
        }
        int storedInStore = 0; 
        int stonesDistributed = distributeStones(startPit); // finding last position
        
        int finalPosition = gameBoard.getFinalPos();
         // Check if capturing is appropriate
        if (shouldCapture(finalPosition, playerNum)) {
            storedInStore = captureStones(finalPosition) + 1; // adds one extra stone
            gameBoard.removeStones(finalPosition); // remove one stone
        }

        return storedInStore; // If no stones were captured
    }

    private boolean shouldCapture(int pit, int playerNum) {
        MancalaDataStructure gameBoard = getDataStructure();
        if (playerNum == 1 && pit <= 6 || playerNum == 2 && pit >= 7 && pit <= 12) {
            return gameBoard.getNumStones(pit) == 1 && !gameBoard.isInStore();
        }
        return false;
    }
    
    @Override
      int distributeStones(int startPit) { 
        MancalaDataStructure gameBoard = getDataStructure();
        int stones = gameBoard.removeStones(startPit);
        int playerNum = getCurrentPlayerNum();
        gameBoard.setIterator(startPit, playerNum, false); // Set iterator to start at the next pit

        for (int i = 0; i < stones; i++) {
            Countable nextPit = gameBoard.next();
            nextPit.addStone();
        }

        int lastPosition = gameBoard.getCurrentPit();
        // Store the final position in the GameRules class
        gameBoard.setFinalPos(lastPosition);

        return stones; // Returns the number of stones distributed
     }

     @Override
    int captureStones(int stoppingPoint) {
        int stonesCaptured = 0;
        
        int oppositePit = 12 - stoppingPoint + 1;

        MancalaDataStructure gameBoard = getDataStructure();

        int stones = gameBoard.getNumStones(stoppingPoint);


        if (stones == 1 && !gameBoard.isInStore()) { // if it's the last stone
            stonesCaptured = gameBoard.removeStones(oppositePit);
            if (stoppingPoint <= 6) { // if it lands on playerOnes own side
                gameBoard.addToStore(1, stonesCaptured + 1); // add extra stone
            } else {
                gameBoard.addToStore(2, stonesCaptured + 1); // add extra stone
            }
            stonesCaptured += 1;// to account for extra stone
        }

        return stonesCaptured;
    }

    @Override
    public boolean isExtraTurn() {
        return getDataStructure().isInStore(); // In Kalah, player gets an extra turn if the last stone is in the store
    }
}