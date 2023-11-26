# Project Title
Mancala Game

## Description
This project is a digital version of the classic board game Mancala. It is designed to simulate the traditional Mancala gameplay experience, allowing two players to take turns picking up and distributing stones from the pits, depositing them into their respective stores, and attempting to collect the most stones to win the game.

## Getting Started
The Mancala Game is meant to bring the traditional board game to a digital platform, giving players the opportunity to enjoy this classic game from the comfort of their computers. This project serves as a basic foundation for understanding how the rules and mechanics of Mancala can be translated into a digital format.

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing and running your program.
This project requires Java. The java.util.ArrayList class is used to manage the stones in each pit dynamically.
This class is mandatory for proper functionality of the game.

Some classes are also meant to be implemented in the org.junit.jupiter:junit-jupiter:5.9.2 version.

### Executing program

* How to build and run the program
```
- Run gradle build command
- Run java -jar build/libs/Mancala.jar
OR
- Run gradle build command
- Run java -cp build/classes/java/main ui.TextUI
```
Expected output:

Enter player 1's name:
1
Enter player 2's name:
2
-------------------------------------------------------------------------
| Player 2 Store | Pit 12 | Pit 11 | Pit 10 | Pit 9 | Pit 8 | Pit 7 | Player 1 Store |
|      0      |    4   |    4   |    4   |    4   |    4   |    4   |      0         |
-------------------------------------------------------------------------
|             | Pit 1  | Pit 2  | Pit 3  | Pit 4  | Pit 5  | Pit 6  |                |
|             |   4    |   4    |   4    |   4    |   4    |   4    |                |
-------------------------------------------------------------------------

It is Player 1's turn.

Choose between pits 1 and 6

## Limitations

- The user interface for this project is primarily console-based, which may not provide the most immersive or user-friendly experience for players who are more accustomed to graphical interfaces.
- The game currently lacks an AI opponent or strategic component, limiting the depth of gameplay for single-player modes.
- The game does not currently support multiplayer functionality, which can greatly enhance the gaming experience.

## Author Information

Jerry You
Student ID: 1241911
jyou04@uoguelph.ca

## Development History

# AI code vs my code.
The AI code is very barebones and has incorrect logic on many aspects (e.g) capturing the stones and determining the winner of the game or extra turns.
My game is fully completed and has all aspects of the Mancala game implemented.
ChatGPT has clean, readable code, and while my code can be improved on efficiency, I have attempted to implement better coding practices relating to encapsulation and specific conventions.

In conclusion, the robustness and completeness of my game, coupled with its strong encapsulation, make it a superior implementation of the Mancala game compared to the AI's version.

## Acknowledgments
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme](https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)
* [chat-gpt](https://chat.openai.com/)
* [mancala-rules](https://www.officialgamerules.org/mancala)