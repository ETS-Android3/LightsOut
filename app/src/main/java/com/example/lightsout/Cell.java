package com.example.lightsout;

public class Cell {
    private boolean isActive;
    private boolean isOn;

    // Constructor
    public Cell(boolean active, boolean lit){
        isActive = active;
        isOn = lit;
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
}
