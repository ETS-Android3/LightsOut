package app.game.lightsout;

public class Cell {
    // Instance variables
    private boolean isActive; // Indicates that a cell is used in a game.
    private boolean isOn; // Indicates whether the cell is on/off in the current game.

    /**
     * Constructor with two parameters
     * @param active - Whether a cell appears in a game, if not then it will be invisible
     * @param lit - Whether a cell is illuminated in a game.
    */
    public Cell(boolean active, boolean lit){
        isActive = active;
        isOn = lit;
    }

    /**
     * Default constructor without any parameters. Sets both instance variables to false by default
     */
    public Cell(){
        isActive = false;
        isOn = false;
    }

    /**
     * Toggles a light on or off.
     */
    public void toggleLight(){
        if (isOn){ // If a light is on, it gets turned off.
            setOn(false);
        }
        else{ // Inversely, if a light is off, it gets turned on.
            setOn(true);
        }
    }

    /**
     * Getter for whether a cell appears on a board.
     * @return - If the cell appears on the board.
     */
    public boolean getActive(){
        return isActive;
    }

    /**
     * Getter for whether a value is illuminated on a board
     * @return - If the cell is illuminated.
     */
    public boolean getOn(){
        return isOn;
    }

    /**
     * Setter for whether a cell appears on a board.
     * @param var - Value to assign, true = appears, false = invisible.
     */
    public void setActive(boolean var){
        isActive = var;
    }

    /**
     * Setter for whether a cell is illuminated
     * @param var - Value to assign, true = on, false = off
     */
    public void setOn(boolean var){
        isOn = var;
    }

    /**
     * Returns a string representation of a cell
     * @return - A string representation of the cell, in the format (active, illuminated), e.g (true, false) for an active but off light.
     */
    public String toString(){
        String s = "(" + Boolean.toString(isActive) + ", " + Boolean.toString(isOn) + ")";
        return s;
    }
}
