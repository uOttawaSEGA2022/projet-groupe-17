package com.example.mealerapp;
import java.util.Random;
import java.util.*;
import android.os.Bundle;
import java.io.*;
import android.widget.*;
import android.view.View;
import android.util.Log;
import org.xmlpull.v1.XmlPullParserException;
import androidx.appcompat.app.AppCompatActivity;

public class RetraitDeRepas extends AppCompatActivity {
    xmlToString xml = new xmlToString();
    Random rand = new Random();
    String questions;
    List<String> questionList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RelativeLayout rLayout = findViewById(R.id.rLayout);
        final TextView questionView = findViewById(R.id.questionView);

        try {
            questions = xml.getQuestionFromXML(this);
        } catch (IOException e) {
            e.printStackTrace();
        }
        questionList = new ArrayList<>(Arrays.asList(questions.split("\n")));
        rLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int n = rand.nextInt(questionList.size());
                int i = 0;
                questionView.setText(questionList.get(n));
                questionList.remove(questionList.get(n));

                while (i < questionList.size()) {
                    Log.d("thing = ", questionList.get(i));
                    i++;
                }
            }
        });
    }
}
