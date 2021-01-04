package app.game.lightsout;

import junit.framework.TestCase;

public class CellTest extends TestCase {

    private Cell cell;

    public void testToggleLightOff() {
        cell = new Cell(true, true);
        cell.toggleLight();
        assertFalse(cell.getOn());
    }

    public void testToggleLightOn() {
        cell = new Cell(true, false);
        cell.toggleLight();
        assertTrue(cell.getOn());
    }

    public void testGetActive() {
        cell = new Cell(true, true);
        assertTrue(cell.getActive());
    }

    public void testGetOn() {
        cell = new Cell(false, false);
        assertFalse(cell.getOn());
    }

    public void testSetActive() {
        cell = new Cell(false, false);
        cell.setActive(true);
        assertTrue(cell.getActive());
    }

    public void testSetOn() {
        cell = new Cell(false, false);
        cell.setOn(true);
        assertTrue(cell.getOn());
    }

    public void testTestToString() {
        String test = "(" + true + ", " + true + ")";
        cell = new Cell(true,true);
        assertEquals(test, cell.toString());
    }
}