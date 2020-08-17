package com.ogungor.ronaldogame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Random;

public class MainActivity extends AppCompatActivity {
    TextView textTime;
    TextView textScore;
    ImageButton imageButton;
    LinearLayout layoutTime;
    LinearLayout layoutScore;
    int gameScore = 0;
    float randomLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textScore = findViewById(R.id.textScore);
        textTime = findViewById(R.id.textTime);
        imageButton = findViewById(R.id.imageButton);
        layoutTime = findViewById(R.id.layoutTime);
        layoutScore = findViewById(R.id.layoutScore);
        textScore.setText(getString(R.string.score, gameScore));


        gameScore = 0;
        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long l) {
                textTime.setText(getString(R.string.time) + l / 1000);
                imageButton.setX(generateRandomAxis(1));
                imageButton.setY(generateRandomAxis(2));
            }

            @Override
            public void onFinish() {
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle(R.string.restart);
                alert.setMessage(R.string.dialog_question);
                alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        textTime.setText(R.string.timefinish);
                        textScore.setText(getString(R.string.score, gameScore));
                        textScore.setVisibility(View.INVISIBLE);
                        Toast.makeText(MainActivity.this, getString(R.string.gameover) + getString(R.string.score, gameScore), Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
                ;
            }
        }.start();


    }

    ;

    public float generateRandomAxis(int position) {
        int imageWidth = imageButton.getWidth();
        int imageHeight = imageButton.getHeight();
        int randomMin;
        int randomMax;

        if (position == 1) {
            randomMin = 0;
            randomMax = layoutTime.getWidth() - imageWidth;

        } else {
            randomMin = layoutTime.getHeight();
            int windowsHeight = getWindowManager().getDefaultDisplay().getHeight();
            randomMax = (windowsHeight - (layoutScore.getHeight() + imageHeight + layoutTime.getHeight()));
        }
        return randomLocation = generateRandom(randomMax, randomMin);
    }

    public void score(View view) {
        gameScore++;
        textScore.setText(getString(R.string.score, gameScore));
    }

    public float generateRandom(int randomMax, int randomMin) {
        Random rand = new Random();
        randomLocation = rand.nextInt((randomMax - randomMin) + 1) + randomMin;
        return randomLocation;
    }

    ;

}