package com.example.mealer;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.RadioButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EvaluationCuisinier extends AppCompatActivity {
    RadioButton R1, R2, R3, R4, R5;
    TextView description;
    Button Retour;
    dbHelper2 DB;
    String user ;
    int choix;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("key");
        }

        DB = new dbHelper2(this);
        Retour = (Button)findViewById(R.id.retour);
        R1 = (RadioButton) findViewById(R.id.MauvaisService);
        R2 = (RadioButton) findViewById(R.id.Passable);
        R3 = (RadioButton) findViewById(R.id.Correct);
        R4 = (RadioButton) findViewById(R.id.Bon);
        R5 = (RadioButton) findViewById(R.id.Excellent);
        description = (TextView) findViewById(R.id.Explication);

      Retour.setOnClickListener(new View.OnClickListener(){

          @Override
          public void onClick(View v) {
              if (R1.callOnClick()){
                  choix = 1;
              }
              if (R2.callOnClick()){
                  choix = 2;
              }
              if (R3.callOnClick()){
                  choix = 3;
              }
              if (R4.callOnClick()){
                  choix = 4;
              }
              if (R5.callOnClick()){
                  choix = 5;
              }

          }
      });


    }

}
