package com.epicodus.bogglesolitaire;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

import butterknife.Bind;
import butterknife.ButterKnife;


public class ResultsActivity extends AppCompatActivity {
    @Bind(R.id.listView) ListView mListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        ButterKnife.bind(this);
        Bundle bundle = this.getIntent().getExtras();
        ArrayList<String> answerArray = bundle.getStringArrayList("key");
        ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, answerArray);
        mListView.setAdapter(adapter);
    }




}
