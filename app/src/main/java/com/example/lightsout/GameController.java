package com.example.lightsout;

import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class GameController {
    Board board;
    Cell[][] cells;
    ImageButton[][] buttons;

    public GameController(ImageButton[][] buttonsArray){
        buttons = buttonsArray;
        board = new Board(buttons.length, buttons[0].length);
        board.randomize();
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
    }

    public void click(int i, int j){
        board.click(i,j);
        updateView();
    }

    public boolean hasWon(){
        return (board.hasWon());
    }
}
