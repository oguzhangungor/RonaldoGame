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
    int gameScore;
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
        gameScore = 0;
        new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long l) {
                textTime.setText("Time: " + l / 1000);
                imageButton.setX(Random(1));
                imageButton.setY(Random(2));
            }

            @Override
            public void onFinish() {
                textTime.setText("Time Finish!");
                AlertDialog.Builder alert = new AlertDialog.Builder(MainActivity.this);
                alert.setTitle("Restart ?");
                alert.setMessage("Are you sure restart game ? ");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Intent intent = getIntent();
                        finish();
                        startActivity(intent);
                    }
                });
                alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        Toast.makeText(MainActivity.this, "Game Over", Toast.LENGTH_LONG).show();
                    }
                });
                alert.show();
                ;
            }
        }.start();


    }

    ;

    public float Random(int position) {
        int imageWidth = imageButton.getWidth();
        int imageHeight = imageButton.getHeight();

        if (position == 1) {
            int randomMin = 0;
            int randomMax = layoutTime.getWidth() - imageWidth;
            Random rand = new Random();

            int a = randomMax - imageWidth;
            randomLocation = rand.nextInt((randomMax - randomMin) + 1) + randomMin;

        } else {
            int randomMin = layoutTime.getHeight();
            int windowsHeight = getWindowManager().getDefaultDisplay().getHeight();

            int randomMax = (windowsHeight - (layoutScore.getHeight() + imageHeight + layoutTime.getHeight()));
            Random rand = new Random();
            randomLocation = rand.nextInt((randomMax - randomMin) + 1) + randomMin;
        }
        return randomLocation;
    }

    public void score(View view) {
        gameScore++;
        textScore.setText("Score: " + gameScore);
    }
}