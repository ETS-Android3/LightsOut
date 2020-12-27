package app.game.lightsout;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import app.game.lightsout.R;

public class GameController {
    // Instance variables
    Board board; // Representation of the board.
    Board copyOfBoard; // An identical copy of the original version of the board.
    Cell[][] cells; // Contains the cells appearing on the board (to display them)
    ImageButton[][] buttons; // Table of the buttons used in the game.
    TextView moves; // Display of the number of moves completed
    TextView minMoves; // Display of the minimum number of moves to win.

    /**
     * Constructor of a game object
     * @param buttonsArray - Array of buttons used in the game
     * @param moves - Display for the number of moves performed
     * @param minMoves - Display for the minimum number of moves to win
     */
    public GameController(ImageButton[][] buttonsArray, TextView moves, TextView minMoves){
        buttons = buttonsArray; // Initializes the buttons array.
        board = new Board(buttons.length, buttons[0].length); // Creates a board object of the desired size (matching the buttons table)
        board.randomize(); // Randomizes the given board
        copyOfBoard = new Board(board); // Creates a copy of the board in case the user wishes to reset.
        this.moves = moves; // Sets the location to display the number of moves.
        this.minMoves = minMoves; // Sets the location to display the minimum number of moves.
        updateView(); // Updates the view so the cells match the board.
    }

    /**
     * Constructor of a game object
     * @param buttonsArray - Array of buttons used in the game
     * @param moves - Display for the number of moves performed
     * @param minMoves - Display for the minimum number of moves to win
     * @param board - A board that gets displayed on the board.
     */
    public GameController(ImageButton[][] buttonsArray, TextView moves, TextView minMoves, Board board){
        buttons = buttonsArray; // Initializes the buttons array.
        this.board = board; // Assigns the board to match that in the paramter.
        copyOfBoard = new Board(board); // Creates a copy of the board in case the user wishes to reset.
        this.moves = moves; // Sets the location to display the number of moves.
        this.minMoves = minMoves; // Sets the location to display the minimum number of moves.
        updateView(); // Updates the view so the cells match the board.
    }

    /**
     * Updates the game view to display the board.
     */
    public void updateView(){
        for (int i = 0; i < buttons.length; i++){
            for (int j = 0; j < buttons[i].length; j++) {
                // If the cells are on and active
                if (board.getPos(i,j).getActive() && board.getPos(i,j).getOn()){
                    buttons[i][j].setImageResource(R.drawable.lighton2); // Image of the button being on is displayed
                    buttons[i][j].setVisibility(View.VISIBLE); // The button is made visible.
                }
                // If the cells are off and active
                else if (board.getPos(i,j).getActive() && !board.getPos(i,j).getOn()){
                    buttons[i][j].setImageResource(R.drawable.lightoff2); // Image of the button being off is displayed
                    buttons[i][j].setVisibility(View.VISIBLE); // The button is made visible
                }
                // If the cells are inactive
                else if (!board.getPos(i,j).getActive()){
                    buttons[i][j].setImageResource(R.drawable.nolight2); // An image of no button is used (failsafe)
                    buttons[i][j].setVisibility(View.INVISIBLE); // The button does not appear.
                }
            }
        }
        moves.setText(Integer.toString(board.getMoves())); // Displays the current number of moves
        minMoves.setText(Integer.toString(board.getMinMoves())); // Displays the minimum number of moves.
    }

    /**
     * Clicks a position on a board
     * @param i - The height value to click
     * @param j - The width value to click
     */
    public void click(int i, int j){
        board.click(i,j); // Clicks the position on the board
        updateView(); // Updates the view to reflect the change.
    }

    /**
     * Getter for the current number of moves on the board
     * @return - The number of moves performed
     */
    public int getMoves(){ return board.getMoves(); }

    /**
     * Resets the board to the copied board, allowing the user to retry.
     */
    public void retryBoard(){
        board = copyOfBoard;
        copyOfBoard = new Board(copyOfBoard); // Uses the copy constructor to create a new board.
    }

    /**
     * Setter for the number of moves performed
     * @param moves - Number of moves performed.
     */
    public void setMoves(int moves){ board.setMoves(moves); }

    /**
     * Getter for whether the user has won the game
     * @return - True if the user has won, False if not.
     */
    public boolean hasWon(){ return (board.hasWon()); }

    /**
     * Returns a message to be displayed based on how the user performed
     * @return - A message to be displayed
     */
    public String getWinMessage(){
        // If the user beat the move goal, we congratulate them.
        if (board.getMoves() < board.getMinMoves() && board.getMoves() > board.getMinMoves() - 3){
            return "You beat it within the target number of moves. Great work!";
        }
        // If the user beat the absolute minimum number of moves, we send a sarcastic message :)
        else if (board.getMoves() <= board.getMinMoves()-3){
            return "You beat it very quickly. You must be gifted. Or a cheater. Or a gifted cheater";
        }
        // If the user got double the number of moves, we bully them a bit.
        else if (board.getMoves() == board.getMinMoves() * 2){
            return "You took twice the number of moves it takes. Let's see if you can get that down!";
        }
        // If the user got triple the number of moves, we encourage them to keep playing
        else if (board.getMoves() == board.getMinMoves() * 3){
            return "You took triple the number of moves it takes. Surely you can do it faster!";
        }
        // IF the user gets less than 5 more than the min, we encourage them to keep trying (replay?)
        else if (board.getMoves() <= board.getMinMoves() + 5){
            return "Very well played. Can you get the minimum, though?";
        }
        // IF the user gets less than 10 more than the min, we challenge them to beat it faster
        else if (board.getMoves() <= board.getMinMoves() + 10){
            return "Good work. Can you beat it faster?";
        }
        // If the user gets a large number of moves, we try to congratulate them.
        else if (board.getMoves() <= board.getMinMoves() * 4){
            return "Hey, at least you beat it. Why not give it another go?";
        }
        // If the user gets far more than the requirement, we encourage them to replay to learn the game.
        else{
            return "Keep trying! You'll get better as you play.";
        }
    }
}
