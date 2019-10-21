package com.example.braintrainer;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    Button goButton;
    Button option1;
    Button option2;
    Button option3;
    Button option4;
    Button playAgain;

    TextView result;
    TextView question;
    TextView timertextView;
    int location_of_correct_answer;
    int score=0;
    int noq=0;
    TextView scoretextView;
    ArrayList<Integer> answers =  new ArrayList<Integer>();

    public void checkAnswer(View view){

        Log.i("Tag", (String) view.getTag());

        if(String.valueOf(view.getTag()).equals(Integer.toString(location_of_correct_answer))){
            result.setText("Correct!");
            score++;
        }else{
            result.setText("Wrong ://");
        }
        noq++;
        scoretextView.setText(Integer.toString(score)+"/"+Integer.toString(noq));
        newQuestion();
    }

    public void newQuestion(){

        Random rand=new Random();

        int a = rand.nextInt(100);
        int b= rand.nextInt(100);

        question.setText(Integer.toString(a)+"+"+Integer.toString(b));

        location_of_correct_answer = rand.nextInt(4);

        answers.clear();

        for(int i=0;i<4;i++){

            if(i==location_of_correct_answer){
                answers.add(a+b);
            }else{
                int wrong_answer=rand.nextInt(100);
                while(wrong_answer==a+b){
                    wrong_answer=rand.nextInt(100);
                }
                answers.add(wrong_answer);
            }

        }

        option1.setText(Integer.toString(answers.get(0)));
        option2.setText(Integer.toString(answers.get(1)));
        option3.setText(Integer.toString(answers.get(2)));
        option4.setText(Integer.toString(answers.get(3)));

    }

    public void start(View view){

        goButton.setVisibility(View.INVISIBLE);

    }

    public  void playAgain(View view){

        score=0;
        noq=0;
        scoretextView.setText(Integer.toString(score)+"/"+Integer.toString(noq));
        timertextView.setText("30s");
        newQuestion();
        option1.setEnabled(true);
        option2.setEnabled(true);
        option3.setEnabled(true);
        option4.setEnabled(true);

        playAgain.setVisibility(View.INVISIBLE);

        new CountDownTimer(30100,1000){

            @Override
            public void onTick(long l) {
                timertextView.setText(String.valueOf(l/1000)+"s");
            }

            @Override
            public void onFinish() {
                result.setText("TimeUp");
                option1.setEnabled(false);
                option2.setEnabled(false);
                option3.setEnabled(false);
                option4.setEnabled(false);

                playAgain.setVisibility(View.VISIBLE);
            }
        }.start();

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        question = findViewById(R.id.question);
        goButton = findViewById(R.id.goButton);
        option1=findViewById(R.id.opt1);
        option2=findViewById(R.id.opt2);
        option3=findViewById(R.id.opt3);
        option4=findViewById(R.id.opt4);
        result= findViewById(R.id.result);
        scoretextView = findViewById(R.id.scoretextView);
        timertextView = findViewById(R.id.timer);
        playAgain = findViewById(R.id.playAgainButton);

        playAgain(findViewById(R.id.timer));
    }
}
