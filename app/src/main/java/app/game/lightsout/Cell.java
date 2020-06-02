package app.game.lightsout;

public class Cell {
    private boolean isActive;
    private boolean isOn;

    // Constructor
    public Cell(boolean active, boolean lit){
        isActive = active;
        isOn = lit;
    }
    public Cell(){
        isActive = false;
        isOn = false;
    }

    // Toggles the light after it (or an adjacent one) is clicked.
    public void toggleLight(){
        if (isOn){
            isOn = false;
        }
        else{
            isOn = true;
        }
    }

    // Getters
    public boolean getActive(){
        return isActive;
    }
    public boolean getOn(){
        return isOn;
    }

    // Setters
    public void setActive(boolean var){
        isActive = var;
    }
    public void setOn(boolean var){
        isOn = var;
    }

    public String toString(){
        String s = "(" + Boolean.toString(isActive) + ", " + Boolean.toString(isOn) + ")";
        return s;
    }
}
