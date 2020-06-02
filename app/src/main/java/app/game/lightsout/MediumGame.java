package app.game.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import app.game.lightsout.R;

public class MediumGame extends AppCompatActivity {
    GameController game;
    ImageButton[][] buttons;
    TextView moves;
    TextView minMoves;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_medium_game);
        buttons = new ImageButton[5][5];
        buttons[0][0] = (ImageButton) findViewById(R.id.imageButton17);
        buttons[0][1] = (ImageButton) findViewById(R.id.imageButton18);
        buttons[0][2] = (ImageButton) findViewById(R.id.imageButton19);
        buttons[0][3] = (ImageButton) findViewById(R.id.imageButton20);
        buttons[0][4] = (ImageButton) findViewById(R.id.imageButton21);
        buttons[1][0] = (ImageButton) findViewById(R.id.imageButton22);
        buttons[1][1] = (ImageButton) findViewById(R.id.imageButton23);
        buttons[1][2] = (ImageButton) findViewById(R.id.imageButton24);
        buttons[1][3] = (ImageButton) findViewById(R.id.imageButton25);
        buttons[1][4] = (ImageButton) findViewById(R.id.imageButton26);
        buttons[2][0] = (ImageButton) findViewById(R.id.imageButton27);
        buttons[2][1] = (ImageButton) findViewById(R.id.imageButton28);
        buttons[2][2] = (ImageButton) findViewById(R.id.imageButton29);
        buttons[2][3] = (ImageButton) findViewById(R.id.imageButton30);
        buttons[2][4] = (ImageButton) findViewById(R.id.imageButton31);
        buttons[3][0] = (ImageButton) findViewById(R.id.imageButton32);
        buttons[3][1] = (ImageButton) findViewById(R.id.imageButton33);
        buttons[3][2] = (ImageButton) findViewById(R.id.imageButton34);
        buttons[3][3] = (ImageButton) findViewById(R.id.imageButton35);
        buttons[3][4] = (ImageButton) findViewById(R.id.imageButton36);
        buttons[4][0] = (ImageButton) findViewById(R.id.imageButton37);
        buttons[4][1] = (ImageButton) findViewById(R.id.imageButton38);
        buttons[4][2] = (ImageButton) findViewById(R.id.imageButton39);
        buttons[4][3] = (ImageButton) findViewById(R.id.imageButton40);
        buttons[4][4] = (ImageButton) findViewById(R.id.imageButton41);
        moves = (TextView) findViewById(R.id.textView11);
        minMoves = (TextView) findViewById(R.id.textView13);
        game = new GameController(buttons, moves, minMoves);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            // We determine which button was selected and insert the current user there.
            case R.id.imageButton17:
                game.click(0, 0);
                break;
            case R.id.imageButton18:
                game.click(0, 1);
                break;
            case R.id.imageButton19:
                game.click(0, 2);
                break;
            case R.id.imageButton20:
                game.click(0, 3);
                break;
            case R.id.imageButton21:
                game.click(0, 4);
                break;
            case R.id.imageButton22:
                game.click(1, 0);
                break;
            case R.id.imageButton23:
                game.click(1, 1);
                break;
            case R.id.imageButton24:
                game.click(1, 2);
                break;
            case R.id.imageButton25:
                game.click(1, 3);
                break;
            case R.id.imageButton26:
                game.click(1, 4);
                break;
            case R.id.imageButton27:
                game.click(2, 0);
                break;
            case R.id.imageButton28:
                game.click(2, 1);
                break;
            case R.id.imageButton29:
                game.click(2, 2);
                break;
            case R.id.imageButton30:
                game.click(2, 3);
                break;
            case R.id.imageButton31: // it was at this moment that i realized i could have used a map
                game.click(2, 4);
                break;
            case R.id.imageButton32:
                game.click(3, 0);
                break;
            case R.id.imageButton33:
                game.click(3, 1);
                break;
            case R.id.imageButton34:
                game.click(3, 2);
                break;
            case R.id.imageButton35:
                game.click(3, 3);
                break;
            case R.id.imageButton36:
                game.click(3, 4);
                break;
            case R.id.imageButton37:
                game.click(4, 0);
                break;
            case R.id.imageButton38:
                game.click(4, 1);
                break;
            case R.id.imageButton39:
                game.click(4, 2);
                break;
            case R.id.imageButton40:
                game.click(4, 3);
                break;
            case R.id.imageButton41:
                game.click(4, 4);
                break;
            case R.id.button6:
                game = new GameController(buttons, moves, minMoves);
                game.updateView();
                break;
        }
        game.updateView();
        if (game.hasWon()){
            Toast.makeText(getApplicationContext(),game.getWinMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
