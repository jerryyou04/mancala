# Project Title
Mancala Game (Ayoayo + Kalah Ruleset) GUI

## Description
This Mancala Game project is a graphical user interface (GUI) implementation of the classic board game Mancala. It features both Ayoayo and Kalah rulesets, offering players a choice of gameplay styles. The game is designed for two players and simulates the traditional Mancala experience, allowing players to pick up and distribute stones across the board, aiming to collect the most stones in their store to win.

## Getting Started
The Mancala Game is meant to bring the traditional board game to a digital platform, giving players the opportunity to enjoy this classic game from the comfort of their computers. This project serves as a basic foundation for understanding how the rules and mechanics of Mancala can be translated into a digital format.

### Dependencies

* Describe any prerequisites, libraries, OS version, etc., needed before installing and running your program.

Java Runtime Environment (JRE) and Java Development Kit (JDK) version 8 or higher.
Gradle 8.5 or higher for building and running the project.
JUnit 5.9.2 for running the test cases.

NEEDS TO BE RAN IN A HEADLESS ENVIRONMENT, (i.e, off the linux server or docker).
Java SE 17 was used in combination of Gradle 8.5 in the development of this 

### Executing program

* How to build and run the program
```
# Build the project
gradle build

# Run the application
java -jar build/libs/MancalaGUI.jar

# Alternative way to run
java -cp build/classes/java/main ui.MancalaGUI

```
### Expected output:

The program will launch a GUI where players can choose between Kalah and Ayoayo rulesets, input player names, save and load games, and play Mancala.

## Limitations

- The game is currently designed for two players and lacks an AI component for single-player gameplay.
- Multiplayer functionality over a network is not supported.
- The GUI is basic and may not offer advanced graphical features.

## Author Information
Name: Jerry You
Student ID: 1241911
Email: jyou04@uoguelph.ca

## Development History
Finished on 2023-12-01.

PMD errors as of GP2: 243 on A2

Current PMD errors: 77 on A3


## Acknowledgments
* [awesome-readme](https://github.com/matiassingers/awesome-readme)
* [simple-readme](https://gist.githubusercontent.com/DomPizzie/7a5ff55ffa9081f2de27c315f5018afc/raw/d59043abbb123089ad6602aba571121b71d91d7f/README-Template.md)
* [mancala-rules](https://www.officialgamerules.org/mancala)
* [J-Unit5](https://junit.org/junit5/)