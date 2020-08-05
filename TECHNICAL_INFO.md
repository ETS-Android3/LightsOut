## Technical Information

This game was developed in Java using Android Studio. The Java source code can be found [here](https://github.com/Sean-Stilwell/LightsOut/tree/master/app/src/main/java/app/game/lightsout).

## Model

There are two core classes that provide the model for this game.

### Cell.java

The most basic element of the game is the [cell class](https://github.com/Sean-Stilwell/LightsOut/blob/master/app/src/main/java/app/game/lightsout/Cell.java). Each cell represents one space on the game board and can have one of three states. Inactive means the cell is not used in that board, OFF means that the cell is used and is in the off position, ON means that the cell is used and is in the on position.

The following methods are used in this class:

* Cell(boolean active, boolean lit) - Constructor method with the status of the cell predetermined. Used when loading a saved game.

* Cell() - Constructor method that defaults to an inactive, off cell.

* void toggleLight() - Switches an active light from the ON position to the OFF position and vice versa.

* boolean getActive(), boolean getOn(), void setActive(boolean var), void setOn(boolean var) - Getter and setter methods for the basic attributes.

* String toString() - For testing, returns the status of a cell (if it is active and if it is illuminated).

### Board.java

The next element of the game is the [board class](https://github.com/Sean-Stilwell/LightsOut/blob/master/app/src/main/java/app/game/lightsout/Board.java). Each board objects contains a matrix of Cell objects, and is able to perform various operations on this board.

The following methods are used in this class:

* Board(int height, int width) - Constructor method that creates a height x width board of active and OFF cells, and then randomly deactivates some.

* Board(int height, int width, Date currentDay) - Constructor method specifically used for Puzzle of the Day challenges, to create pseudo-random puzzles based on the date.

* Board(Board parameterBoard) - Constructor method that copies an existing board.

* void setMoves(int moves), void (getMinMoves), int getMoves(), int getHeight(), int getWidth(), Cell[][] getBoard() - Getter/Setter methods for various attributes.

* Cell getPos(int i, int j) - Returns the cell situated at the i height and j width position.

* void click(int height, int width) - Toggles the light at a given height and width position, as well as those adjacent to it.

* boolean hasWon() - Returns whether the game has won, that is if all active cells are turned off.

* void randomize() - Randomizes a board.

## Controller

The [GameController class](https://github.com/Sean-Stilwell/LightsOut/blob/master/app/src/main/java/app/game/lightsout/GameController.java) interacts with the Board class for a given game, ensuring a Model-View-Controller format for this project.

The following methods are in this class:

* GameController(ImageButton[][] buttonsArray, TextView moves, TextView minMoves) - Constructor method, saves the array of buttons and creates a new board object based on its size, randomizes it, and then sets the moves and minimum moves text spaces.

* GameController(ImageButton[][] buttonsArray, TextView moves, TextView minMoves) - Constructor method, saves the array of buttons and creates a copy of a board object, and then sets the moves and minimum moves text spaces.

* void updateView() - Updates all of the icons to ensure they match the Cell objects in the board.

* void click(int i, int j) - Clicks a position and then updates the view.

* int getMoves() - Getter method for the number of moves performed.

* void retryBoard() - Loads the saved copy of the board created and saves a new copy.

* setMoves(int moves) - Sets the number of moves performed by the user.

* boolean hasWon() - Returns whether the user has won the game.

* String getWinMessage() - Returns a message to give the user based on how well they performed.

## View

There are five different views for this game.

* [MainActivity](https://github.com/Sean-Stilwell/LightsOut/blob/master/app/src/main/java/app/game/lightsout/MediumGame.java) is the home screen of the game. Allows the user to play any of the game modes, or rate the app on Google Play.

* [EasyGame](https://github.com/Sean-Stilwell/LightsOut/blob/master/app/src/main/java/app/game/lightsout/EasyGame.java) is the easiest game, with a 4 by 4 board.

* [MediumGame](https://github.com/Sean-Stilwell/LightsOut/blob/master/app/src/main/java/app/game/lightsout/MediumGame.java) is a medium difficulty game, with a 5 by 5 board.

* [HardGame](https://github.com/Sean-Stilwell/LightsOut/blob/master/app/src/main/java/app/game/lightsout/HardGame.java) is the hardest difficulty, with a 7 by 6 board.

* [PuzzleOfTheDay](https://github.com/Sean-Stilwell/LightsOut/blob/master/app/src/main/java/app/game/lightsout/PuzzleOfTheDay.java) is also a 5 by 5 board, but is set based on a pseudo-random algorithm based on the date.

Each of the game views (the final 4) have the following methods

* void onCreate(Bundle savedInstanceState) - Creates all objects needed, primarily the Game Controller. Creates the matrix of buttons passed to GameController.java.

* void onClick(View v) - Determines what button was clicked and performs the relevant action (toggling a cell, retrying the game, or creating a new game). Updates the view of the game