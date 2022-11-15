package com.example.mealerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Cuisinier extends AppCompatActivity {
    Button connectCuisinier;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cuisinier);

        connectCuisinier=(Button)findViewById(R.id.deconnexionC);

        connectCuisinier.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
        });

    }
}
