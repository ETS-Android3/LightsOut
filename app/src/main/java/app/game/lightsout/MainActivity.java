package app.game.lightsout;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

import app.game.lightsout.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Occurs when any button is clicked. Determines what button was chosen and performs a related action.
     * @param v
     */
    public void onClick(View v) {
        switch (v.getId()) {
            // We determine which button was selected and insert the current user there.
            case R.id.button: // Starts an easy game and switches to that screen
                Intent intent = new Intent(MainActivity.this, EasyGame.class);
                startActivity(intent);
                break;
            case R.id.button2: // Starts a medium game and switches to that screen
                Intent intent2 = new Intent(MainActivity.this, MediumGame.class);
                startActivity(intent2);
                break;
            case R.id.button3: // Starts a hard game and switches to that screen
                Intent intent3 = new Intent(MainActivity.this, HardGame.class);
                startActivity(intent3);
                break;
            case R.id.button8: // Opens the puzzle of the day activity
                Intent intent4 = new Intent(MainActivity.this, PuzzleOfTheDay.class);
                startActivity(intent4);
                break;
            case R.id.button5: // Opens the review page for the game
                final String myappPackageName = getPackageName(); // getPackageName() from Context or Activity object
                try {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + myappPackageName)));
                } catch (android.content.ActivityNotFoundException anfe) {
                    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + myappPackageName)));
                }
                break;
        }
    }
}
