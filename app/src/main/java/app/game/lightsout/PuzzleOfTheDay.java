package app.game.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class PuzzleOfTheDay extends AppCompatActivity {
    GameController game;
    ImageButton[][] buttons;
    TextView moves;
    TextView minMoves;
    MediaPlayer mpWin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_of_the_day);
        buttons = new ImageButton[5][5];
        buttons[0][0] = (ImageButton) findViewById(R.id.imageButton84);
        buttons[0][1] = (ImageButton) findViewById(R.id.imageButton85);
        buttons[0][2] = (ImageButton) findViewById(R.id.imageButton86);
        buttons[0][3] = (ImageButton) findViewById(R.id.imageButton87);
        buttons[0][4] = (ImageButton) findViewById(R.id.imageButton104);
        buttons[1][0] = (ImageButton) findViewById(R.id.imageButton88);
        buttons[1][1] = (ImageButton) findViewById(R.id.imageButton89);
        buttons[1][2] = (ImageButton) findViewById(R.id.imageButton90);
        buttons[1][3] = (ImageButton) findViewById(R.id.imageButton91);
        buttons[1][4] = (ImageButton) findViewById(R.id.imageButton105);
        buttons[2][0] = (ImageButton) findViewById(R.id.imageButton92);
        buttons[2][1] = (ImageButton) findViewById(R.id.imageButton93);
        buttons[2][2] = (ImageButton) findViewById(R.id.imageButton94);
        buttons[2][3] = (ImageButton) findViewById(R.id.imageButton95);
        buttons[2][4] = (ImageButton) findViewById(R.id.imageButton106);
        buttons[3][0] = (ImageButton) findViewById(R.id.imageButton96);
        buttons[3][1] = (ImageButton) findViewById(R.id.imageButton97);
        buttons[3][2] = (ImageButton) findViewById(R.id.imageButton98);
        buttons[3][3] = (ImageButton) findViewById(R.id.imageButton99);
        buttons[3][4] = (ImageButton) findViewById(R.id.imageButton107);
        buttons[4][0] = (ImageButton) findViewById(R.id.imageButton100);
        buttons[4][1] = (ImageButton) findViewById(R.id.imageButton101);
        buttons[4][2] = (ImageButton) findViewById(R.id.imageButton102);
        buttons[4][3] = (ImageButton) findViewById(R.id.imageButton103);
        buttons[4][4] = (ImageButton) findViewById(R.id.imageButton108);
        moves = (TextView) findViewById(R.id.textView11);
        minMoves = (TextView) findViewById(R.id.textView13);
        mpWin = MediaPlayer.create(this, R.raw.tada);

        Board board = new Board(5, 5, Calendar.getInstance().getTime());
        game = new GameController(buttons, moves, minMoves, board);
    }

    public void onClick(View v) {
        switch (v.getId()) {
            // We determine which button was selected and insert the current user there.
            case R.id.imageButton84:
                game.click(0, 0);
                break;
            case R.id.imageButton85:
                game.click(0, 1);
                break;
            case R.id.imageButton86:
                game.click(0, 2);
                break;
            case R.id.imageButton87:
                game.click(0, 3);
                break;
            case R.id.imageButton104:
                game.click(0, 4);
                break;
            case R.id.imageButton88:
                game.click(1, 0);
                break;
            case R.id.imageButton89:
                game.click(1, 1);
                break;
            case R.id.imageButton90:
                game.click(1, 2);
                break;
            case R.id.imageButton91:
                game.click(1, 3);
                break;
            case R.id.imageButton105:
                game.click(1, 4);
                break;
            case R.id.imageButton92:
                game.click(2, 0);
                break;
            case R.id.imageButton93:
                game.click(2, 1);
                break;
            case R.id.imageButton94:
                game.click(2, 2);
                break;
            case R.id.imageButton95:
                game.click(2, 3);
                break;
            case R.id.imageButton106:
                game.click(2, 4);
                break;
            case R.id.imageButton96:
                game.click(3, 0);
                break;
            case R.id.imageButton97:
                game.click(3, 1);
                break;
            case R.id.imageButton98:
                game.click(3, 2);
                break;
            case R.id.imageButton99:
                game.click(3, 3);
                break;
            case R.id.imageButton107:
                game.click(3, 4);
                break;
            case R.id.imageButton100:
                game.click(4, 0);
                break;
            case R.id.imageButton101:
                game.click(4, 1);
                break;
            case R.id.imageButton102:
                game.click(4, 2);
                break;
            case R.id.imageButton103:
                game.click(4, 3);
                break;
            case R.id.imageButton108:
                game.click(4, 4);
                break;
            case R.id.button10:
                int moves = game.getMoves();
                game.retryBoard();
                game.updateView();
                break;
        }
        game.updateView();
        if (game.hasWon()){
            mpWin.start();
            Toast.makeText(getApplicationContext(),game.getWinMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
