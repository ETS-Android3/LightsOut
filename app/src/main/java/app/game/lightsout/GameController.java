package app.game.lightsout;

import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import app.game.lightsout.R;

public class GameController {
    Board board;
    Cell[][] cells;
    ImageButton[][] buttons;
    TextView moves;
    TextView minMoves;

    public GameController(ImageButton[][] buttonsArray, TextView moves, TextView minMoves){
        buttons = buttonsArray;
        board = new Board(buttons.length, buttons[0].length);
        board.randomize();
        this.moves = moves;
        this.minMoves = minMoves;
        updateView();
    }

    public void updateView(){
        for (int i = 0; i < buttons.length; i++){
            for (int j = 0; j < buttons[i].length; j++) {
                // If the cells are on and active
                if (board.getPos(i,j).getActive() && board.getPos(i,j).getOn()){
                    buttons[i][j].setImageResource(R.drawable.lighton2);
                    buttons[i][j].setVisibility(View.VISIBLE);
                }
                // If the cells are off and active
                else if (board.getPos(i,j).getActive() && !board.getPos(i,j).getOn()){
                    buttons[i][j].setImageResource(R.drawable.lightoff2);
                    buttons[i][j].setVisibility(View.VISIBLE);
                }
                // If the cells are inactive
                else if (!board.getPos(i,j).getActive()){
                    buttons[i][j].setImageResource(R.drawable.nolight2);
                    buttons[i][j].setVisibility(View.INVISIBLE);
                }
            }
        }
        moves.setText(Integer.toString(board.getMoves()));
        minMoves.setText(Integer.toString(board.getMinMoves()));
    }

    public void click(int i, int j){
        board.click(i,j);
        updateView();
    }

    public boolean hasWon(){
        return (board.hasWon());
    }

    public String getWinMessage(){
        if (board.getMoves() < board.getMinMoves() && board.getMoves() > board.getMinMoves() - 3){
            return "You beat it within the target number of moves. Great work!";
        }
        else if (board.getMoves() <= board.getMinMoves()-3){
            return "You beat it very quickly. You must be gifted. Or a cheater. Or a gifted cheater";
        }
        else if (board.getMoves() == board.getMinMoves() * 2){
            return "You took twice the number of moves it takes. Let's see if you can get that down!";
        }
        else if (board.getMoves() == board.getMinMoves() * 3){
            return "You took triple the number of moves it takes. Surely you can do it faster!";
        }
        else if (board.getMoves() <= board.getMinMoves() + 5){
            return "Very well played. Can you get the minimum, though?";
        }
        else if (board.getMoves() <= board.getMinMoves() + 10){
            return "Good work. Can you beat it faster?";
        }
        else if (board.getMoves() <= board.getMinMoves() * 4){
            return "Hey, at least you beat it. Why not give it another go?";
        }
        else{
            return "Keep trying! You'll get better as you play.";
        }
    }
}
