package com.narenph.simpleadd;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button startButton;
    RelativeLayout gameRelativeLayout;
    TextView timerTextView;
    TextView textView;
    TextView questionText;
    TextView resultText;
    Button button0;
    Button button1;
    Button button2;
    Button button3;
    Button playAgainButton;
    int score;
    int totalQues;
    int locationOfAnswer;
    ArrayList<Integer> answers= new ArrayList<Integer>();

    public void playAgain(View view){


        playAgainButton.setVisibility(View.INVISIBLE);
        score=0;
        totalQues=0;

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        timerTextView.setText("30s");
        textView.setText("0/0");
        resultText.setText("");

        newQuestion();

        CountDownTimer countDownTimer = new CountDownTimer(30100,1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                timerTextView.setText((int)millisUntilFinished/1000+"s");

            }

            @Override
            public void onFinish() {

                resultText.setText("Done! Your Score is: "+score+"/"+totalQues);
                timerTextView.setText("0s");
                playAgainButton.setVisibility(View.VISIBLE);
                button0.setEnabled(false);
                button1.setEnabled(false);
                button2.setEnabled(false);
                button3.setEnabled(false);


            }
        }.start();

    }

    public void newQuestion(){


        Random rand = new Random();

        int a = rand.nextInt(21);
        int b = rand.nextInt(21);

        questionText.setText(Integer.toString(a) +" + "+Integer.toString(b));

        int answer =a+b;
        int incorrectAnswer;
        locationOfAnswer=rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){

            if(i==locationOfAnswer){

                answers.add(answer);
            }
            else{

                incorrectAnswer=rand.nextInt(41);
                while (incorrectAnswer == answer){

                    incorrectAnswer=rand.nextInt(41);

                }
                answers.add(incorrectAnswer);

            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

    }

    public void checkAnswer(View view){



        if(Integer.toString(locationOfAnswer).equals(view.getTag().toString())){

            score++;
            resultText.setText("CORRECT!");
        }

        else{

            resultText.setText("WRONG!");

        }

        totalQues++;
        textView.setText(Integer.toString(score)+"/"+Integer.toString(totalQues));
        newQuestion();



    }

    public void startGame(View view){

        startButton.setVisibility(view.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        playAgain(findViewById(R.id.startButton));

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        startButton=(Button)findViewById(R.id.startButton);
        gameRelativeLayout=(RelativeLayout)findViewById(R.id.gameRelativeLayout);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        textView = (TextView)findViewById(R.id.textView);
        questionText = (TextView)findViewById(R.id.questionText);
        resultText = (TextView)findViewById(R.id.resultText);
        button0 = (Button)findViewById(R.id.button1);
        button1 = (Button)findViewById(R.id.button2);
        button2 = (Button)findViewById(R.id.button3);
        button3 = (Button)findViewById(R.id.button4);
        playAgainButton = (Button)findViewById(R.id.playAgainButton);



    }
}
