package app.game.lightsout;

import junit.framework.TestCase;

public class BoardTest extends TestCase {

    private Board board;

    public void testSetMoves() {
        board = new Board(3,3);
        board.setMoves(5);
        assertEquals(5, board.getMoves());
    }

    public void testGetMinMoves() {
        board = new Board(2,2);
        boolean test = board.getMinMoves() <= 4; // Every game on a 2x2 board should have less than 4
        assertEquals(true, test);
    }

    public void testGetMoves() {
        board = new Board(4,4);
        int clicksPerformed = 0;
        // Attempts to click several different positions and increments clicks each time
        if (board.getPos(0,3).getActive()){
            board.click(0,3);
            ++clicksPerformed;
        }
        if (board.getPos(3,1).getActive()){
            board.click(3,1);
            ++clicksPerformed;
        }
        if (board.getPos(2,2).getActive()){
            board.click(2,2);
            ++clicksPerformed;
        }
        // We then check that our clicks counter equals the move instance variable.
        assertEquals(clicksPerformed, board.getMoves());
    }

    public void testGetHeight() {
        board = new Board(3,2);
        assertEquals(3,board.getHeight());
    }

    public void testGetWidth() {
        board = new Board(3,2);
        assertEquals(2,board.getWidth());
    }

    public void testGetPos() {
        board = new Board(3,4);
        assertNotNull(board.getPos(2,3));
    }

    public void testClick() {
        board = new Board(3,3);
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board.getPos(i,j).setActive(true);
                board.getPos(i,j).setOn(false);
            }
        }
        board.click(1,1);
        boolean condition = board.getPos(0,1).getOn() && board.getPos(1,1).getOn() &&board.getPos(1,2).getOn() &&board.getPos(2,1).getOn() &&board.getPos(1,0).getOn();
        assertTrue(condition);
    }

    public void testHasWon() {
        board = new Board(3,3);
        for (int i = 0; i < 3; i++){
            for (int j = 0; j < 3; j++){
                board.getPos(i,j).setActive(true);
                board.getPos(i,j).setOn(false);
            }
        }
        assertTrue(board.hasWon());
    }

    public void testRandomize() {
        board = new Board(3,3);
        board.randomize();
        assertFalse(board.hasWon());
    }
}