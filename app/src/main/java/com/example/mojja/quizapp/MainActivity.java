package com.example.mojja.quizapp;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView countLabel;
    private TextView questionLabel;
    private Button answerBtn1;
    private Button answerBtn2;
    private Button answerBtn3;
    private Button answerBtn4;

    private String rightAnswer;
    private int rightAnswerCount = 0;
    private int quizCount = 1;
    static final private int QUIZ_COUNT = 3; //24

    //問題番号
    private String quizNum;
    //問題文
    private String quizText;
    //答え
    private String quizAnswer;
    //回答(正解の場合空白)
    private String playerAnswer;

    ArrayList<ArrayList<String>> resultArray = new ArrayList<>();

    ArrayList<ArrayList<String>> quizArray = new ArrayList<>();

    String quizData[][] = {
        // {"問題", "正解", "選択肢１", "選択肢２", "選択肢３"}
        {"机", "desk", "chair", "vase", "stepladder"},
        {"犬", "dog", "cat", "cow", "tiger"},
        {"腕時計", "watch", "clock", "promise", "engine"},
        {"梨", "pear", "apple", "Footbridge", "persimmon"},
        {"手袋", "gloves", "catcher", "hand", "tomato"},
        {"ろうそく", "candle", "light", "flame", "fire"},
        {"転職", "Career change", "recruitnavi", "raise", "demotion"},
        {"銀行", "bank", "classroom", "money station", "post office"},
        {"賄賂", "bribe", "back money", "jail", "robbery"},
        {"名刺", "business card", "career card", "admission certificate", "career license"},
        {"受験", "exam", "school test", "war", "study test"},
        {"蜘蛛", "spider", "tarantula", "galaxy", "reindeer"},
        {"喪服", "mourning", "silent suits", "cry", "mofuku"},
        {"グーグル", "Play", "ios", "yahoo", "Bing"},
        {"気象", "weather", "labor and Welfare", "Metropolitan", "amida lottery"},
        {"眼鏡", "glasses", "stroll", "whistle", "nose"},
        {"コーヒー", "coffee", "coffe", "cofee", "cuffee"},
        {"レモネード", "lemonade", "lemonadu", "lemonado", "lemonede"},
        {"恋人", "sweetheart", "wife", "cheating partner", "affair"},
        {"カレー", "curry", "carry", "ccury", "curryr"},
        {"シチュー", "stew", "beef", "fruit", "cook"},
        {"余罪", "aftergone", "over sin", "fiend", "justice"},
        {"呼ぶ", "call", "tell", "exclaim", "angry"},
        {"地下鉄", "subway", "tokaido", "Kentucky", "parking Lot"},
    };


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        countLabel = findViewById(R.id.countLabel);
        questionLabel = findViewById(R.id.questionLabel);
        answerBtn1 = findViewById(R.id.answerBtn1);
        answerBtn2 = findViewById(R.id.answerBtn2);
        answerBtn3 = findViewById(R.id.answerBtn3);
        answerBtn4 = findViewById(R.id.answerBtn4);

        for(int i = 0; i < quizData.length; i++){

            //新しいArrayListを準備
            ArrayList<String> tmpArray = new ArrayList<>();

            tmpArray.add(quizData[i][0]);
            tmpArray.add(quizData[i][1]);
            tmpArray.add(quizData[i][2]);
            tmpArray.add(quizData[i][3]);
            tmpArray.add(quizData[i][4]);

            quizArray.add(tmpArray);
        }

        showNextQuiz();
    }

    public void showNextQuiz(){

        //問題番号 for result
        quizNum = "Q"+quizCount;
        countLabel.setText(quizNum);

        Random random = new Random();
        int randomNum = random.nextInt(quizArray.size());

        ArrayList<String> quiz = quizArray.get(randomNum);

        //問題文
        quizText = quiz.get(0);
        questionLabel.setText(quizText);

        //答え
        quizAnswer = quiz.get(1);
        rightAnswer = quizAnswer;

        quiz.remove(0);

        Collections.shuffle(quiz);

        answerBtn1.setText(quiz.get(0));
        answerBtn2.setText(quiz.get(1));
        answerBtn3.setText(quiz.get(2));
        answerBtn4.setText(quiz.get(3));

        quizArray.remove(randomNum);
    }

    public void checkAnswer(View view){

        //どの回答ボタンが押されたか
        Button answerBtn = findViewById(view.getId());
        String btnText = answerBtn.getText().toString();

        //答え
        playerAnswer = btnText;

        //tempArrayと同じノリでこいつらをresultArrayに入れていく
        //そして最後にresult画面に渡してlistViewで展開させて表示
        ArrayList<String> tmpResultArray = new ArrayList<>();

        tmpResultArray.add(quizNum);
        tmpResultArray.add(quizText);
        tmpResultArray.add(quizAnswer);
        tmpResultArray.add(playerAnswer);

        resultArray.add(tmpResultArray);

        String alertTitle;
        if(btnText.equals(rightAnswer)){
            alertTitle = "正解!";
            rightAnswerCount++;
        } else {
            alertTitle = "不正解...";
        }

        //ダイアログを作成
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(alertTitle);
        builder.setMessage("答え : " + rightAnswer);
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialogInterface, int i){
                if(quizCount == QUIZ_COUNT){
                    //結果画面へ移動
                    Intent intent = new Intent(getApplicationContext(), ResultActivity.class);
                    intent.putExtra("RIGHT_ANSWER_COUNT", rightAnswerCount);
                    intent.putStringArrayListExtra("RESULT_ARRAY", resultArray);
                    startActivity(intent);
                }else{
                    quizCount++;
                    showNextQuiz();
                }
            }
        });

        builder.setCancelable(false);
        builder.show();
    }
}
