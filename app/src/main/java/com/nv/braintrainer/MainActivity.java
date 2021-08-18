package com.nv.braintrainer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

public class MainActivity extends AppCompatActivity {
    Button startButton;
    ArrayList<Integer> answer = new ArrayList<Integer>();
    int locationOfCorrectAnswer;
    TextView resultTextView;
    int score = 0;
    int numberOfQuestions = 0;
    TextView scoreTextView;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    TextView sumTextView;
    TextView timerTextView;
    Button playAgainButton;
    ConstraintLayout gameLayout;

    public void playAgain(View view){
       score = 0;
       numberOfQuestions = 0;
       timerTextView.setText("30s");
        scoreTextView.setText(Integer.toString(score) + "/" +Integer.toString(numberOfQuestions));
        newQuestion();
        playAgainButton.setVisibility(View.INVISIBLE);
        resultTextView.setText("");

        new CountDownTimer(30100,1000){
            @Override
            public void onTick(long l) {
                timerTextView.setText(String.valueOf(l / 1000) + "s");
            }
            @Override
            public void onFinish() {
                resultTextView.setText("DONE!");
                playAgainButton.setVisibility(View.VISIBLE);
                MediaPlayer mplayer = MediaPlayer.create(getApplicationContext(), R.raw.horn);
                mplayer.start();
            }
        }.start();
    }

    public void chooseAnswer(View view){
        if (Integer.toString(locationOfCorrectAnswer).equals(view.getTag().toString())){
            resultTextView.setText("Correct!");
            score++;
        }else{
            resultTextView.setText("Wrong :(");
        }
        numberOfQuestions++;
        scoreTextView.setText(Integer.toString(score) + "/" +Integer.toString(numberOfQuestions));
        newQuestion();
    }

    public void start(View view){
        startButton.setVisibility(View.INVISIBLE);
        gameLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.timerTextView));
    }
    public void newQuestion(){
        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);
        sumTextView.setText(Integer.toString(a) + "+" + Integer.toString(b));

        locationOfCorrectAnswer = rand.nextInt(4);
        answer.clear();

        for (int i = 0; i < 4; i++) {
            if (i == locationOfCorrectAnswer){
                answer.add(a + b);
            }else {
                int wrongAnswer = rand.nextInt(41);
                while (wrongAnswer == a + b) {
                    wrongAnswer = rand.nextInt(41);
                }
                answer.add(wrongAnswer);
            }
        }
        button0.setText(Integer.toString(answer.get(0)));
        button1.setText(Integer.toString(answer.get(1)));
        button2.setText(Integer.toString(answer.get(2)));
        button3.setText(Integer.toString(answer.get(3)));


    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sumTextView = findViewById(R.id.sumTextView);
        button0 = findViewById(R.id.button0);
        button1 = findViewById(R.id.button1);
        button2 = findViewById(R.id.button2);
        button3 = findViewById(R.id.button3);
        resultTextView = findViewById(R.id.resultTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        timerTextView = findViewById(R.id.timerTextView);
        playAgainButton= findViewById(R.id.playAgainButton);
        gameLayout =findViewById(R.id.gameLayout);
        startButton = findViewById(R.id.startButton);
        startButton.setVisibility(View.VISIBLE);
        gameLayout.setVisibility(View.INVISIBLE);






    }
}