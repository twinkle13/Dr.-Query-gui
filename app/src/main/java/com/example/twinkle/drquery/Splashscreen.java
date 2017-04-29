package com.example.twinkle.drquery;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ProgressBar;

/**
 * Created by Twinkle on 27-Apr-17.
 */

public class Splashscreen extends AppCompatActivity {
    private ProgressBar spinner;
    private static int SPLASH_SCREEN_TIME = 3000;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splashscreen_activity);
        spinner=(ProgressBar)findViewById(R.id.progressBar);
        spinner.setVisibility(View.VISIBLE);

        // Create object of Handler class and call method postDelayed to make
        // Splash Screen visible for SPLASH_SCREEN_TIME
        new Handler().postDelayed(new Runnable() {

            public void run() {
                // This is method will be executed when SPLASH_SCREEN_TIME is
                // over, Now you can call your Home Screen
                Intent iHomeScreen = new Intent(getApplicationContext(),
                        MainActivity.class);
                startActivity(iHomeScreen);

                // Finish Current Splash Screen, as it should be visible only
                // once when application start
                finish();
            }

        }, SPLASH_SCREEN_TIME);
    }

}
