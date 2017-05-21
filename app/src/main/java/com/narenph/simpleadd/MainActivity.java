package com.narenph.simpleadd;

import android.graphics.Outline;
import android.os.CountDownTimer;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    FloatingActionButton startButton;
    RelativeLayout gameRelativeLayout;
    LinearLayout difGrid;
    TextView timerTextView,textView,questionText,resultText,startText;
    Button button0,button1,button2,button3;
    Button playAgainButton;
    Button dif1,dif2,dif3;
    int a,b,score,answer,incorrectAnswer;
    int totalQues;
    int locationOfAnswer;
    boolean checker=true;

    ArrayList<Integer> answers= new ArrayList<Integer>();
    public int difLevel;

    public void playAgain(View view, int difLevel){


        playAgainButton.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.VISIBLE);
        score=0;
        totalQues=0;

        button0.setEnabled(true);
        button1.setEnabled(true);
        button2.setEnabled(true);
        button3.setEnabled(true);

        timerTextView.setText("30s");
        textView.setText("0/0");
        resultText.setText("");

        Log.i("WAAAASUUUUP",Integer.toString(difLevel));
        newQuestion(view, difLevel);

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

    public void newQuestion(View view, int difLevel){


        Random rand = new Random();

        Log.i("difLevel",Integer.toString(difLevel));

        a = rand.nextInt(difLevel);
        b = rand.nextInt(difLevel);
        int oper;
        oper = rand.nextInt(4);

            if(oper==0){
                questionText.setText(Integer.toString(a) +" + "+Integer.toString(b));
                answer=a+b;
            }
            if(oper==1){
                questionText.setText(Integer.toString(a) +" - "+Integer.toString(b));
                answer=a-b;

            }
            if(oper==2){
                questionText.setText(Integer.toString(a) +" X "+Integer.toString(b));
                answer=a*b;
            }
            if(oper==3){

                while(checker){
                    if((a>=b) && (a%b==0)) {
                        Log.i("Printing out A and B", Integer.toString(a) + "  " + Integer.toString(b));
                        answer = a / b;
                        break;
                    }

                    else{
                        a = rand.nextInt(difLevel);
                        b = rand.nextInt(difLevel);
                    }


                }
                questionText.setText(Integer.toString(a) +" / "+Integer.toString(b));

            }




        locationOfAnswer=rand.nextInt(4);
        answers.clear();
        for(int i=0;i<4;i++){

            if(i==locationOfAnswer){

                answers.add(answer);
            }
            else{

                incorrectAnswer=rand.nextInt((2*difLevel)-1);
                while (incorrectAnswer == answer){

                    incorrectAnswer=rand.nextInt((2*difLevel)-1);

                }
                answers.add(incorrectAnswer);

            }
        }

        button0.setText(Integer.toString(answers.get(0)));
        button1.setText(Integer.toString(answers.get(1)));
        button2.setText(Integer.toString(answers.get(2)));
        button3.setText(Integer.toString(answers.get(3)));

        Log.i("WOOHOOOO",Integer.toString(difLevel));
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
        Log.i("HELOOOOOO",Integer.toString(difLevel));
        newQuestion(view, difLevel);

    }

    public void setDiff1(View view){

        difLevel=21;
        gameRelativeLayout.setVisibility(View.VISIBLE);
        difGrid.setVisibility(View.INVISIBLE);
        playAgain(view, difLevel);

    }

    public void setDiff2(View view){

        difLevel=41;
        gameRelativeLayout.setVisibility(View.VISIBLE);
        difGrid.setVisibility(View.INVISIBLE);
        playAgain(view, difLevel);

    }

    public void setDiff3(View view){

        difLevel=61;
        gameRelativeLayout.setVisibility(View.VISIBLE);
        difGrid.setVisibility(View.INVISIBLE);
        playAgain(view, difLevel);

    }

    public void startGame(View view){

        startButton.setVisibility(view.INVISIBLE);
        startText.setVisibility(View.INVISIBLE);
        gameRelativeLayout.setVisibility(View.INVISIBLE);
        difGrid.setVisibility(View.VISIBLE);

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        gameRelativeLayout=(RelativeLayout)findViewById(R.id.gameRelativeLayout);
        difGrid= (LinearLayout)findViewById(R.id.difGrid);
        timerTextView = (TextView)findViewById(R.id.timerTextView);
        startText = (TextView)findViewById(R.id.startText);
        textView = (TextView)findViewById(R.id.textView);
        questionText = (TextView)findViewById(R.id.questionText);
        resultText = (TextView)findViewById(R.id.resultText);
        button0 = (Button)findViewById(R.id.button1);
        button1 = (Button)findViewById(R.id.button2);
        button2 = (Button)findViewById(R.id.button3);
        button3 = (Button)findViewById(R.id.button4);
        dif1 = (Button)findViewById(R.id.dif1);
        dif2 = (Button)findViewById(R.id.dif2);
        dif3 = (Button)findViewById(R.id.dif3);

        playAgainButton = (Button)findViewById(R.id.playAgainButton);

        startButton = (FloatingActionButton) findViewById(R.id.startButton);
        startButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                startGame(view);
            }
        });


    }
}
