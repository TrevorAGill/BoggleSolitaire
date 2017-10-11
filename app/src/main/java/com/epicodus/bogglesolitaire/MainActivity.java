package com.epicodus.bogglesolitaire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.puzzleString) TextView mPuzzleString;
    @Bind(R.id.newGameButton) Button mNewGameButton;
    private int vowelCount;
    private List<String> letterArray = Arrays.asList("A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z");
    private List<String> vowelArray = Arrays.asList("A", "E", "I", "O", "U");
    private ArrayList<String> puzzleArray = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mNewGameButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mNewGameButton)
            emptyValues();
            getPuzzleString();
    }

    public void emptyValues() {
        puzzleArray.clear();
        vowelCount = 0;
    }

    public List getPuzzleString() {
        for(int i=0 ; i < 8 ; i++){
            if(puzzleArray.size()>5 && vowelCount <2){
                String randomLetter = getRandomVowel();
                puzzleArray.add(randomLetter);
                Log.i("MainActivity","IFvowel count is: " + vowelCount);
            } else {
                String randomLetter = getRandomLetter();
                puzzleArray.add(randomLetter);
                Log.i("MainActivity","puzzleArray.size()= " + puzzleArray.size());
                Log.i("MainActivity","ELSEvowel count is: " + vowelCount);
            }
        }
        Log.i("MainActivity", "Puzzle Array equals" + puzzleArray);
        return puzzleArray;
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
