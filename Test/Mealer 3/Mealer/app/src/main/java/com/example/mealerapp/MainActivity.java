package com.example.mealerapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    Button Connexion = (Button)findViewById(R.id.co);
    Button Inscription= (Button)findViewById(R.id.inscription);


    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Connexion.setOnClickListener(v ->{
            Intent intent = new Intent(getApplicationContext(), Connexion.class);
            startActivity(intent);});


        Inscription.setOnClickListener(v -> {
            Intent intent = new Intent(getApplicationContext(), Creation.class);
            startActivity(intent);});


    }
}