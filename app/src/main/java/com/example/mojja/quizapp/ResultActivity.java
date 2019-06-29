package com.example.mojja.quizapp;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.graphics.drawable.LayerDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;


public class ResultActivity extends AppCompatActivity {

    ListView resultListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        TextView resultLabel = findViewById(R.id.resultLabel);
        //TextView totalScoreLabel = findViewById(R.id.totalScoreLabel);

        int score = getIntent().getIntExtra("RIGHT_ANSWER_COUNT", 0);

        SharedPreferences prefs = getSharedPreferences("quizApp", Context.MODE_PRIVATE);
        int totalScore = prefs.getInt("totalScore", 0);

        totalScore += score;

        resultLabel.setText(score + " / 5");
        //totalScoreLabel.setText("トータルスコア : " + totalScore);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putInt("totalScore", totalScore);
        editor.apply();

        //resultList
        setContentView(resultListView);
        ArrayList<String> results = new ArrayList<>();
        results = getIntent().getStringArrayListExtra("RESULT_ARRAY");

        resultListView = (ListView)findViewById(R.id.resultList);
        Toast.makeText(this, results, Toast.LENGTH_LONG).show();

        //ArrayAdapter<String> listAdapter = new ArrayAdapter<String>(this, android.R.layout.list_result_row, results);
        //resultListView.setAdapter(listAdapter);
    }

    public void returnTop(View view){
        Intent intent = new Intent(getApplicationContext(), TopActivity.class);
        startActivity(intent);
    }

}

