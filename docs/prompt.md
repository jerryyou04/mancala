Create a mancalaGame in Java, step by step. First, start with creating the exceptions. You are to create 4 exceptions:
GameNotOverException
InvalidMoveException
NoSuchPlayerException
PitNotFoundException

Thank you! Your next task is to create a class named Pit. Here are the instructions to create it to set up the pits for the mancala.
Pits class should be a helper class that helps with the functionality of pits (e.g) adding and removing stones.

We will write the main later. The next step is to create a class store. This keeps track of the stores for each player and the methods should help with functionality like accessing it, adding stones to it, etc.

Next, we will write a player class, that connects them to their respective stores, and other functionality.

Next, we will write two classes: One is class Board and another is class MancalaGame.
Board deals with setting up the board, resetting it, it moving the stones itself, and capturing.
MancalaGame deals with the players, the winners and making sure the game is working properly. These are the last classes before writing main. Make sure to use the previous classes you have written.

Awesome. Your next and final task is to create a class named TextUI. Inside is the main that properly displays and lets the user play the game.

Running all your code gave me these two errors
/home/undergrad/1/jyou04/CIS2430/GP2/docs/src/main/java/mancala/Board.java:18: error: cannot find symbol
            pit.setStones(stonesPerPit);
               ^
  symbol:   method setStones(int)
  location: variable pit of type Pit
/home/undergrad/1/jyou04/CIS2430/GP2/docs/src/main/java/mancala/Board.java:36: error: cannot find symbol
            if (currentPit.getStones() == 1)
                          ^
  symbol:   method getStones()
  location: variable currentPit of type Pit
2 errors

Also, TextUI must be in a package ui and all the other classes must be in package mancala;

I need you to physically print out the game so i can see what's going on