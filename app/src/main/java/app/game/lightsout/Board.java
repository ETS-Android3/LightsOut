package app.game.lightsout;

import java.util.Calendar;
import java.util.Date;

public class Board {
    private Cell[][] board;
    private int minMoves;
    private int moves;

    // Constructor
    public Board(int height, int width){
        board = new Cell[height][width];
        minMoves = 0;

        // We then iterate through the board and decide whether each cell should be active.
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
                board[i][j].setActive(true);
                board[i][j].setOn(false);
                // A cell has a somewhat random probability of being active or not.
                double probability = 0.1;
                if (i == 0 || i == board.length - 1 || j == 0 || j == board[i].length - 1) {
                    probability = probability + 0.2;
                }
                if (Math.random() < probability) {
                    board[i][j].setActive(false);
                }
            }
        }
    }

    public Board(int height, int width, Date currentDay){
        board = new Cell[height][width];
        minMoves = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                board[i][j] = new Cell();
                board[i][j].setActive(true);
                board[i][j].setOn(false);
            }
        }

        int year = currentDay.getYear();
        int month = currentDay.getMonth();
        int day = currentDay.getDay();
        switch (day % 5){
            case 0:
                board[2][2].setActive(false);
                board[0][3].setActive(false);
                break;
            case 1:
                board[2][1].setActive(false);
                break;
            case 2:
                board[2][3].setActive(false);
                break;
            case 3:
                board[1][2].setActive(false);
                break;
            case 4:
                board[3][2].setActive(false);
                board[3][3].setActive(false);
                break;
        }

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if ((j % 3 == day % 3 && (i == 0 || i == 4)) || ((i % 3 == (day + month) % 3) && (j == 0 || j == 4))){
                    board[i][j].setActive(false);
                }
            }
        }

        int counter = 0;
        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                if (counter % 2 == (day + month) % 2 && board[i][j].getActive()){
                    click(i,j);
                    minMoves = minMoves + 1;
                }
                counter++;
            }
        }

        moves = 0;
        minMoves = minMoves + 3;
    }

    public Board(Board parameterBoard){
        board = new Cell[parameterBoard.getHeight()][parameterBoard.getWidth()];
        minMoves = parameterBoard.getMinMoves();

        for (int i = 0; i < board.length; i++) {
            for (int j = 0; j < board[i].length; j++) {
                // We copy the parameter board values
                board[i][j] = new Cell();
                board[i][j].setActive(parameterBoard.getPos(i,j).getActive());
                board[i][j].setOn(parameterBoard.getPos(i,j).getOn());
            }
        }
    }

    public void setMoves(int moves){this.moves = moves;}

    public int getMinMoves(){return minMoves;}

    public int getMoves(){return moves;}

    public int getHeight(){return board.length;}

    public int getWidth(){return board[0].length;}

    public Cell[][] getBoard(){
        return board;
    }

    public Cell getPos(int i, int j) { return board[i][j];}

    // If a button is clicked, we must toggle the adjacent ones too.
    public void click(int height, int width){
        if (height >= board.length || width >= board[0].length){
            throw new IllegalArgumentException();
        }
        if (board[height][width].getActive()) {
            board[height][width].toggleLight();
            if (height - 1 >= 0 && board[height - 1][width].getActive()) {
                board[height - 1][width].toggleLight();
            }
            if (height + 1 < board.length && board[height + 1][width].getActive()) {
                board[height + 1][width].toggleLight();
            }
            if (width - 1 >= 0 && board[height][width - 1].getActive()) {
                board[height][width - 1].toggleLight();
            }
            if (width + 1 < board[0].length && board[height][width+1].getActive()) {
                board[height][width + 1].toggleLight();
            }
            moves++;
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

    // Randomly toggles lights
    public void randomize(){
        minMoves = 0;
        for (int i = 0; i < board.length; i++){
            for (int j = 0; j < board[i].length; j++) {
                if (board[i][j].getActive()) {
                    double probability = 0.55;
                    if(Math.random() < probability){
                        click(i,j);
                        minMoves++;
                    }
                }
            }
        }
        moves = 0;
        minMoves = minMoves + 3;
    }
}
