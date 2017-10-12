package com.epicodus.bogglesolitaire;

import android.content.Intent;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.attr.key;
import static android.R.id.list;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.puzzleString) TextView mPuzzleString;
    @Bind(R.id.newGameButton) Button mNewGameButton;
    @Bind(R.id.submitAnswerButton) Button mSubmitAnswerButton;
    @Bind(R.id.answer) EditText mAnswer;
    @Bind(R.id.timer) TextView mTimer;
    private int vowelCount;
    private List<String> letterArray = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
    private List<String> vowelArray = Arrays.asList("A", "E", "I", "O", "U");
    private ArrayList<String> puzzleArray = new ArrayList<>();
    private ArrayList<String> correctAnswers = new ArrayList<>();
    private ArrayList<String> puzzleArrayReset = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mNewGameButton.setOnClickListener(this);
        mSubmitAnswerButton.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        if(v == mNewGameButton) {
            emptyValues();
            String puzzle = getPuzzleString();
            mPuzzleString.setText(puzzle);
            startTimer();
        } else if(v == mSubmitAnswerButton) {
            puzzleArray = puzzleArrayReset;
            String answer = mAnswer.getText().toString().trim().toUpperCase();
            evaluateAnswerValidity(answer);
        }
    }

    public void emptyValues() {
        puzzleArray.clear();
        vowelCount = 0;
    }

    public void startTimer() {
        new CountDownTimer(10000, 1000) {

            public void onTick(long millisUntilFinished) {
                mTimer.setText("seconds remaining: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                mTimer.setText("done!");
                Bundle bundle = makeBundle(correctAnswers);
                Intent intent = new Intent(MainActivity.this, ResultsActivity.class);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }.start();
    }

    public Bundle makeBundle(ArrayList correctAnswers){
        Bundle bundle = new Bundle();
        bundle.putStringArrayList("key", correctAnswers);
        return bundle;
    }

    public void evaluateAnswerValidity(String answer) {
        Log.i("MainActivity","thing: " + puzzleArray);
        puzzleArray = puzzleArrayReset;
        int letterMatchCount = 0;
        String[] answerArray = answer.split("");
        if(answer.length() < 3) {
            Toast.makeText(MainActivity.this, "USE AT LEAST 3 LETTERS", Toast.LENGTH_LONG).show();
            mAnswer.setText("");
        } else {
            for (int i = 1; i < answer.length() + 1 ; i++) {
                if (puzzleArray.contains(answerArray[i]))
                    letterMatchCount += 1;
                    puzzleArray.remove(answerArray[i]);
            }
//            puzzleArray.add(answerArray)
            if(letterMatchCount > 2 && letterMatchCount == answer.length()){
                Toast.makeText(MainActivity.this, "GOOD ANSWER!", Toast.LENGTH_LONG).show();
                correctAnswers.add(answer);
                mAnswer.setText("");
            } else {
                Toast.makeText(MainActivity.this, "LETTERS DON'T MATCH PUZZLE", Toast.LENGTH_LONG).show();
                mAnswer.setText("");
            }
        }
    }

    public String getPuzzleString() {
        for(int i=0 ; i < 8 ; i++){
            if(puzzleArray.size()>5 && vowelCount <2){
                String randomLetter = getRandomVowel();
                puzzleArray.add(randomLetter);
                puzzleArrayReset.add(randomLetter);
            } else {
                String randomLetter = getRandomLetter();
                puzzleArray.add(randomLetter);
                puzzleArrayReset.add(randomLetter);
            }
        }
        String joined = TextUtils.join(" ", puzzleArray);
        return joined;
    }

    public String getRandomLetter() {
        String randomLetter = letterArray.get(new Random().nextInt(26));
        if(randomLetter.equals("A") || randomLetter.equals("E") || randomLetter.equals("I") || randomLetter.equals("O") || randomLetter.equals("U"))
            vowelCount += 1;
        return randomLetter;
    }

    public String getRandomVowel() {
        String randomLetter = vowelArray.get(new Random().nextInt(5));
        vowelCount += 1;
        return randomLetter;
    }

}
