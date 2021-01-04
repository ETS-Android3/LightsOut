package app.game.lightsout;

import java.util.Calendar;
import java.util.Date;

public class Board {
    // Instance variables
    private Cell[][] board; // Representation of the game board, an m * n board of cells.
    private int minMoves; // Minimum number of moves to beat a game.
    private int moves; // Current number of moves completed.

    /**
     * Helper function that initializes an empty board, entirely active and entirely off.
     * @param height - Desired height of the board
     * @param width - Desired width of the board
     */
    private void createEmptyBoard(int height, int width){
        board = new Cell[height][width]; // Creates the height*width table of Cell objects
        minMoves = 0; // Initializes minimum moves to 0, to be incremented when randomizing.

        // Iterates through the board and initializes each cell
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
                board[i][j].setActive(true); // All cells are initially set to be active
                board[i][j].setOn(false); // All cells are initially not illuminated (allows an achievable solution to occur using randomization)
            }
        }
    }

    /**
     * Constructor, initializes and deactivates some lights randomly that are unused in the game.
     * @param height - The height of the board
     * @param width - The width of the board.
     */
    public Board(int height, int width){
        createEmptyBoard(height, width); // We initialize an empty board.

        // We then iterate through the board and decide whether each cell should be active.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // A cell has a somewhat random probability of being active or not.
                double probability = 0.1;
                if (i == 0 || i == board.length - 1 || j == 0 || j == board[i].length - 1) { // The probability is more likely on the edges.
                    probability = probability + 0.2; // 0.2 is arbitrary, but it is a level that seems to work well.
                }
                if (Math.random() < probability) { // Based on the random chance, we then deactivate a cell (i.e it is not used in the game)
                    board[i][j].setActive(false);
                }
            }
        }
    }

    /**
     * Constructor, initializes and deactivates some lights based on the current day (For puzzle of the day activity)
     * @param height - The height of the board
     * @param width - The width of the board
     * @param currentDay - The current date, passed using the Android system.
     */
    public Board(int height, int width, Date currentDay){
        createEmptyBoard(height, width); // We initialize an empty board.

        // We initialize the date instance variables using the Date object.
        int year = currentDay.getYear();
        int month = currentDay.getMonth();
        int day = currentDay.getDay();

        // We use the day value to deactivate random cells (seemingly, to the user)
        switch (day % 5){ // There are five possibilities, each deactivating different cells.
            case 0: // If the result is 0, we turn off the positions (2,2) and (0,3)
                board[2][2].setActive(false);
                board[0][3].setActive(false);
                break;
            case 1: // If the result is 1, we turn off the positions (2,1)
                board[2][1].setActive(false);
                break;
            case 2: // If the result is 2, we turn off the positions (2,3)
                board[2][3].setActive(false);
                break;
            case 3: // If the result is 3, we turn off the positions (1,2)
                board[1][2].setActive(false);
                break;
            case 4: // If the result is 1, we turn off the positions (3,2) and (3,3)
                board[3][2].setActive(false);
                board[3][3].setActive(false);
                break;
        }

        // We then iterate through and again pseudo-randomly deactivate cells on the edge.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((j % 3 == day % 3 && (i == 0 || i == 4)) || ((i % 3 == (day + month) % 3) && (j == 0 || j == 4))){ // Formula that randomly deactivates certain cells.
                    board[i][j].setActive(false);
                }
            }
        }

        // We use this rather than randomizing since it has to be identical for all devices
        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (counter % 2 == (day + month) % 2 && board[i][j].getActive()){ // Formula that chooses which cells to click
                    click(i,j); // Clicking, starting from a winning position, ensures that a winning solution remains available.
                    minMoves = minMoves + 1; // We use this to account for the minimum moves
                }
                counter++;
            }
        }

        // We then initialize the current number of moves and anticipated number of moves to solve.
        moves = 0; // click(int i, int j) increments this, therefore we must reset it.
        minMoves = minMoves + 3;
    }

    /**
     * Copy constructor, using a parameter board to copy it. Used for resetting games.
     * @param parameterBoard - The board to be copied.
     */
    public Board(Board parameterBoard){
        board = new Cell[parameterBoard.getHeight()][parameterBoard.getWidth()]; // Uses the parameter board to initialize a new one
        minMoves = parameterBoard.getMinMoves(); // Initializes minMoves to that of the parameter.

        for (int i = 0; i < board.length; i++) { // We can then iterate through and set each cell to match the parameter
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
                board[i][j].setActive(parameterBoard.getPos(i,j).getActive()); // Copying whether the parameter is active
                board[i][j].setOn(parameterBoard.getPos(i,j).getOn()); // Copying whether the cell is illuminated.
            }
        }
    }

    /**
     * Setter for the number of moves current performed on a board
     * @param moves - Number of moves performed
     */
    public void setMoves(int moves){this.moves = moves;}

    /**
     * Getter for the minimum amount of moves to complete a puzzle.
     * @return - The minimum number of moves needed to complete a puzzle.
     */
    public int getMinMoves(){return minMoves;}

    /**
     * Getter for the number of moves performed on a puzzle
     * @return - The number of moves performed on a puzzle.
     */
    public int getMoves(){return moves;}

    /**
     * Getter for the height of a board
     * @return - The height of the board
     */
    public int getHeight(){return board.length;}

    /**
     * Getter for the width of a board
     * @return - The width of a board
     */
    public int getWidth(){return board[0].length;}

    /**
     * Gets the cell at a given position
     * @param i - The vertical position on the board to be retrieved
     * @param j - The horizontal position on the board to be retrieved
     * @return - The cell requested through the parameters
     */
    public Cell getPos(int i, int j) { return board[i][j];}

    /**
     * Clicks a cell, given the coordinates.
     * @param height - Vertical position on the board to be clicked.
     * @param width - Horizontal position on the board to be clicked.
     */
    public void click(int height, int width){
        // If the height or width exceeds the dimensions, we throw an exception.
        if (height >= board.length || width >= board[0].length || height < 0 || width < 0){
            throw new IllegalArgumentException();
        }

        if (board[height][width].getActive()) { // We ensure that the cell is active before we allow it to be clicked (prevents cheating by clicking empty cells)
            board[height][width].toggleLight(); // Toggles the light that was clicked.
            if (height - 1 >= 0 && board[height - 1][width].getActive()) {// Toggles the light above, if it is on the board and is active.
                board[height - 1][width].toggleLight();
            }
            if (height + 1 < board.length && board[height + 1][width].getActive()) { // Toggles the light below, if it is on the board and is active.
                board[height + 1][width].toggleLight();
            }
            if (width - 1 >= 0 && board[height][width - 1].getActive()) { // Toggles the light to the left, if it is on the board and is active.
                board[height][width - 1].toggleLight();
            }
            if (width + 1 < board[0].length && board[height][width+1].getActive()) { // Toggles the light to the right, if it is on the board and is active.
                board[height][width + 1].toggleLight();
            }
            moves++; // Increments the move counter, as the user has performed a move.
        }
    }

    /**
     * Returns whether a user has won the game. Immediately returns false if an active, off light is found.
     * @return - True if the user has won the game, false otherwise
     */
    public boolean hasWon(){
        for (int i = 0; i < board.length; i++){ // We iterate through the board to check each cell individually.
            for (int j = 0; j < board[i].length; j++) { 
                if (board[i][j].getActive() && board[i][j].getOn()){ // If a cell is found that is active and off, we immediately return false, as the user has not won.
                    return false;
                }
            }
        }
        return true; // If false has not been returned by now, we return that the user won.
    }

    /**
     * Randomizes the board.
     */
    public void randomize(){
        minMoves = 0; // We initialize the minimum moves to 0
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getActive()) { // If a board is active, there is a 55% it gets clicked.
                    double probability = 0.55;
                    if(Math.random() < probability){ // If that chance occurs, we click it and increment the number of moves.
                        click(i,j);
                        minMoves++;
                    }
                }
            }
        }
        moves = 0; // Since the click(i,j) call increments the moves, we reset it.
        minMoves = minMoves + 3;
    }
}
