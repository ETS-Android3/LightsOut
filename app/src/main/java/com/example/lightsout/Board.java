package com.example.lightsout;

public class Board {
    private Cell[][] board;

    // Constructor
    public Board(int height, int width){
        board = new Cell[height][width];

        // We then iterate through the board and decide whether each cell should be active.
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++){
                board[i][j].setOn(true);
                // A cell has a somewhat random probability of being active or not.
                double probability = 0.05;
                if (i == 0 || i == board.length - 1 || j == 0 || j == board[i].length){
                    probability = probability + 0.2;
                }
                if (Math.random() < probability){
                    board[i][j].setActive(false);
                }
            }
        }
    }

    // If a button is clicked, we must toggle the adjacent ones too.
    public void click(int height, int width){
        if (height >= board.length || width >= board[0].length){
            throw new IllegalArgumentException();
        }
        board[height][width].toggleLight();
        if (height - 1 >= 0){
            board[height - 1][width].toggleLight();
        }
        if (height + 1 < board.length){
            board[height + 1][width].toggleLight();
        }
        if (width - 1 >= 0){
            board[height][width - 1].toggleLight();
        }
        if (width + 1 < board[0].length){
            board[height][width + 1].toggleLight();
        }
    }

    // If any active light is on, we immediately return false. Otherwise, the user has won.
    public boolean hasWon(){
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getActive() && board[i][j].getOn()){
                    return false;
                }
            }
        }
        return true;
    }
}
