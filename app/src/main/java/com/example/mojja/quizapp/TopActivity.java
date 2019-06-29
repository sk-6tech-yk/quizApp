package com.example.mojja.quizapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TopActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_top);
    }

    public void onClick(View v) {
        //インテントの作成
        Intent intent = new Intent(this, MainActivity.class);
        //データをセット
        //EditText editText = (EditText)this.findViewById(R.id.editText);
        //intent.putExtra("sendText",editText.getText().toString());
        //遷移先の画面を起動
        startActivity(intent);
    }
}
