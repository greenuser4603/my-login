package com.example.tapgame;

import android.content.Intent;
import android.view.animation.AnimationUtils;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private int score= 0;
    private int timeLeft = 10;
    private int highestscore = 0;
    private TextView scoreTextView, timeTextView, LeaderBoard;
    private Button tapButton, tapRestart;
    private MediaPlayer mediaPlayer;


    private Handler handler = new Handler(Looper.getMainLooper());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        scoreTextView = findViewById(R.id.scoreTextView);
        timeTextView = findViewById(R.id.timeTextView);
        tapButton = findViewById(R.id.tapButton);
        tapRestart = findViewById(R.id.tapRestart);
        LeaderBoard = findViewById(R.id.leaderboard);
        mediaPlayer = MediaPlayer.create(this, R.raw.tap);

        tapButton.setOnClickListener(v -> {
            if (timeLeft == 10) {
                startGameTimer();
                tapButton.setText("Tap to Start");
            }
            if (timeLeft > 0) {
                score++;
                scoreTextView.setText("Score: " + score);
                mediaPlayer.start();
                tapButton.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.enlarge_button));
                tapButton.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        tapButton.startAnimation(AnimationUtils.loadAnimation(MainActivity.this, R.anim.shrink_button));
                    }
                }, 200);
            }
        });
        tapRestart.setOnClickListener(v -> {
            tapButton.setEnabled(true);
            tapButton.setText("Tap to Start");
            tapRestart.setVisibility(View.GONE);
            score = 0;
            timeLeft = 10;
            scoreTextView.setText("Score: " + score);
            timeTextView.setText("Time Left: " + timeLeft + " s");
        });
    }

    private void startGameTimer(){
        handler.post(new Runnable() {
            @Override
            public void run() {
                if(timeLeft > 0){
                    timeLeft--;
                    timeTextView.setText("Time Left: " + timeLeft + " s");
                    handler.postDelayed(this, 1000);
                    tapButton.setText("Tap Me!");
                }
                else {
                    tapRestart.setVisibility(View.VISIBLE);
                    tapButton.setEnabled(false);
                    tapButton.setText("Game Over");

                    if(highestscore > score){
                        LeaderBoard.setText("Highest Score: " + highestscore);
                    }
                    else {
                        LeaderBoard.setText("Highest Score: " + score);
                        highestscore = score;
                    }
                }
            }
        });
    }

}