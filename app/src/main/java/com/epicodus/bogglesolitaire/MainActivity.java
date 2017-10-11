package com.epicodus.bogglesolitaire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.lang.reflect.Array;

import butterknife.Bind;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    @Bind(R.id.puzzleString) TextView mPuzzleString;
    @Bind(R.id.newGameButton) Button mNewGameButton;
    private String[] letterArray = new String[] {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","T","U","V","W","X","Y","Z"};
    private String[] vowelArray = new String[] {"A", "E", "I", "O", "U"};
    private String[] puzzleArray = new String[] {};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ButterKnife.bind(this);
        mNewGameButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v == mNewGameButton) {

        }
    }

    public String getPuzzleString() {
        for(int i=0 ; i < 8 ; i++){
            
        }
    }


}
