package com.example.twinkle.drquery;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

import com.example.twinkle.drquery.vaibhav.MachineLearning.ScoreCalculator;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
//    Button b1;
    private ProgressBar spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        b1=(Button)findViewById(R.id.button);
        //spinner=(ProgressBar)findViewById(R.id.progressBar);
       // spinner.setVisibility(View.GONE);
        //startSpinner();
        try {
            new ScoreCalculator(this);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

//        b1.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                spinner.setVisibility(View.VISIBLE);
//            }
//        });
    }
    /*private void startSpinner()
    {
        spinner.setVisibility(View.VISIBLE);
    }*/
}
