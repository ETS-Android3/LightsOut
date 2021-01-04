package app.game.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;

public class PuzzleOfTheDay extends AppCompatActivity {
    GameController game;
    ImageButton[][] buttons;
    TextView moves;
    TextView minMoves;
    MediaPlayer mpWin;
    TextView points;
    int score;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_puzzle_of_the_day);

        // Saves the current score in a variable to be displayed
        SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
        score = prefs.getInt("points", 0); // Retrieves the user's current score

        // Initializes the button array
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
        points = (TextView) findViewById(R.id.textView14);
        mpWin = MediaPlayer.create(this, R.raw.tada);

        Board board = new Board(5, 5, Calendar.getInstance().getTime());
        game = new GameController(buttons, moves, minMoves, points, score);
    }

    /**
     * Occurs when any button is clicked. Determines what button was chosen and performs a related action.
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            // We determine which button was selected and insert the current user there.
            // The first set is buttons on the game board, which clicks their corresponding button
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
            case R.id.button10:// Button to create a new puzzle (giving up)
                int moves = game.getMoves();
                game.retryBoard();
                game.updateView();
                break;
        }

        // When a user wins the game, this code is run.
        if (game.hasWon()) {
            mpWin.start(); // Plays a sound.

            // Rerieves the user's current total points
            SharedPreferences prefs = this.getSharedPreferences("prefsKey", Context.MODE_PRIVATE);
            int score = prefs.getInt("points", 0);

            // Retrieves the date the user last did a Puzzle of the Day
            int month = prefs.getInt("month", 0);
            int day = prefs.getInt("day", 0);
            Date today = Calendar.getInstance().getTime(); // Retrieve's the current date
            SharedPreferences.Editor editor = prefs.edit(); // Allows modification of the values we retrieved

            if (isNextDay(month, day, today)) {
                score = score + 35 + game.getBonusPoints(); // 10 points are awarded by default, with 5 extra for beating under the minimum number of moves
                Toast.makeText(getApplicationContext(), "You've completed the daily puzzle on multiple days in a row to earn 10 bonus points, your new score is: " + score + " points.", Toast.LENGTH_LONG).show();
            } else if (month == today.getMonth() && day == today.getDay()){
                Toast.makeText(getApplicationContext(), "Good work, but you can only get points for a daily puzzle once per day!.", Toast.LENGTH_LONG).show();
            }
            else {
                score = score + 25 + game.getBonusPoints(); // 10 points are awarded by default, with 5 extra for beating under the minimum number of moves
                Toast.makeText(getApplicationContext(), "You've completed the daily puzzle, do it once per day for bonus points! Your new score is: " + score + " points.", Toast.LENGTH_LONG).show();
            }

            game.updatePoints(score);
            this.score = score;

            // Updates the date
            editor.putInt("month", today.getMonth());
            editor.putInt("day", today.getDay());

            editor.putInt("points", score); // Replaces the score with the increased amount.
            editor.commit();
        }
        game.updateView();
    }

    /**
    * Determines if two dates are sequential
     * @param month - the month to be compared
     * @param day - the day to be compared
     * @param today - current date from the Android device.
    */
    private boolean isNextDay(int month, int day, Date today){
        int todayMonth = today.getMonth();
        int todayDay = today.getDay();

        // We first check if the months are the same.
        if (todayMonth == month){
            // If so, it's a simple matter of checking that the dates are 1 after each other.
            if (todayDay == day + 1){ return true; }
            else { return false; }
        } else { // Otherwise, we make sure it's not the new month starting
            if (todayDay >= 30 || (month == 2 && day >= 28 && todayMonth == 3)){
                // If so, as long as it's the first day we return true.
                if (todayDay == 1){ return true; }
                else { return false; }
            }
        }
        return false;
    }
}
