### BoardTest Methods

##### Test getNumStones()
| Category | Specific Test Case | Expected Result |
| --- | ----------- |-----------|
| Pit Number Too Small | pitNum = 0 | PitNotFoundException with message "Pit not found" |
| Pit Number Too Big | pitNum = 13 | PitNotFoundException with message "Pit not found" |
| Valid Pit | pitNum = 6 with 6 stones | 6 |
| Empty Pit | pitNum = 12 with 0 stones | 0 |

##### Test resetBoard()
| Category | Specific Test Case | Expected Result |
| --- | ----------- |-----------|
| Pits Modified, Stores Filled | 0 stones in the first pit, 5 stones in the first store | 4 in all pits, 0 in stores |

##### Test isSideEmpty()
| Category | Specific Test Case | Expected Result |
| --- | ----------- |-----------|
| Pit Number Too Small | pitNum = 0 | PitNotFoundException with message "Pit not found" |
| Pit Number Too Big | pitNum = 13 | PitNotFoundException with message "Pit not found" |
| Side Not Empty | Player 1's Side (pitNum = 1) | False |
| Side Empty | Player 1's Side (pitNum = 1) after clearing pits 0-5 | True |

##### Test registerPlayers()
| Category | Specific Test Case | Expected Result |
| --- | ----------- |-----------|
| Two Players | Player 1 and Player 2 | Player 1's store is the first store, Player 2's store is the second store, and their respective stores' owners are themselves |

##### Test captureStones()
| Category | Specific Test Case | Expected Result |
| --- | ----------- |-----------|
| Pit Number Too Small | stoppingPoint = 0 | PitNotFoundException with message "Pit not found" |
| Pit Number Too Big | stoppingPoint = 13 | PitNotFoundException with message "Pit not found" |
| Valid Capture | stoppingPoint = 1 | 4 stones captured |
| Invalid Capture | stoppingPoint = 2 | 0 stones captured |

##### Test distributeStones()
| Category | Specific Test Case | Expected Result |
| --- | ----------- |-----------|
| Pit Number Too Small | startingPoint = 0 | PitNotFoundException with message "Pit not found" |
| Pit Number Too Big | startingPoint = 13 | PitNotFoundException with message "Pit not found" |
| Distribute Into Player 1's Store | startingPoint = 6 | 1 stone in Player 1's store, 5 stones in pits 7-9, 4 stones in pit 10 |
| Distribute Into Player 2's Store | startingPoint = 12 | 1 stone in Player 2's store, 5 stones in pits 1-3, 4 stones in pit 4 |
| Capture by Player 1's Store | startingPoint = 1 | 10 stones moved, 7 stones in Player 1's store |
