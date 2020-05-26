package com.example.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.Toast;

public class EasyGame extends AppCompatActivity {
    GameController game;
    ImageButton[][] buttons;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_easy_game);

        buttons = new ImageButton[4][4];
        buttons[0][0] = (ImageButton) findViewById(R.id.imageButton);
        buttons[0][1] = (ImageButton) findViewById(R.id.imageButton2);
        buttons[0][2] = (ImageButton) findViewById(R.id.imageButton3);
        buttons[0][3] = (ImageButton) findViewById(R.id.imageButton4);
        buttons[1][0] = (ImageButton) findViewById(R.id.imageButton5);
        buttons[1][1] = (ImageButton) findViewById(R.id.imageButton6);
        buttons[1][2] = (ImageButton) findViewById(R.id.imageButton7);
        buttons[1][3] = (ImageButton) findViewById(R.id.imageButton8);
        buttons[2][0] = (ImageButton) findViewById(R.id.imageButton9);
        buttons[2][1] = (ImageButton) findViewById(R.id.imageButton10);
        buttons[2][2] = (ImageButton) findViewById(R.id.imageButton11);
        buttons[2][3] = (ImageButton) findViewById(R.id.imageButton12);
        buttons[3][0] = (ImageButton) findViewById(R.id.imageButton13);
        buttons[3][1] = (ImageButton) findViewById(R.id.imageButton14);
        buttons[3][2] = (ImageButton) findViewById(R.id.imageButton15);
        buttons[3][3] = (ImageButton) findViewById(R.id.imageButton16);

        game = new GameController(buttons);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            // We determine which button was selected and insert the current user there.
            case R.id.imageButton:
                game.click(0,0);
                break;
            case R.id.imageButton2:
                game.click(0,1);
                break;
            case R.id.imageButton3:
                game.click(0,2);
                break;
            case R.id.imageButton4:
                game.click(0,3);
                break;
            case R.id.imageButton5:
                game.click(1,0);
                break;
            case R.id.imageButton6:
                game.click(1,1);
                break;
            case R.id.imageButton7:
                game.click(1,2);
                break;
            case R.id.imageButton8:
                game.click(1,3);
                break;
            case R.id.imageButton9:
                game.click(2,0);
                break;
            case R.id.imageButton10:
                game.click(2,1);
                break;
            case R.id.imageButton11:
                game.click(2,2);
                break;
            case R.id.imageButton12:
                game.click(2,3);
                break;
            case R.id.imageButton13:
                game.click(3,0);
                break;
            case R.id.imageButton14:
                game.click(3,1);
                break;
            case R.id.imageButton15:
                game.click(3,2);
                break;
            case R.id.imageButton16:
                game.click(3,3);
                break;
            case R.id.button4:
                game = new GameController(buttons);
                game.updateView();
                break;
        }
        game.updateView();
    }
}
