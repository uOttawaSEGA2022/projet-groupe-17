package com.example.mealerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Creation extends AppCompatActivity {
    Button clientCreation;
    Button cuisinierCreation;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creation);

        clientCreation=(Button)findViewById(R.id.CreationClient);

        clientCreation.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(), CreationClient.class);
            startActivity(intent);
        });

        cuisinierCreation=(Button)findViewById(R.id.CreationCuisinier);

        cuisinierCreation.setOnClickListener(v -> {
            Intent intent=new Intent(getApplicationContext(), CreationCuisinier.class);
            startActivity(intent);
        });
    }
}
