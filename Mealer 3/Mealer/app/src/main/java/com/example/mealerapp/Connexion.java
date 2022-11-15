package com.example.mealerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Connexion extends AppCompatActivity {
    Button clientConnexion;
    Button cuisinierConnexion;
    Button adminConnexion;
    Button Retour;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.connexion);

        clientConnexion = (Button) findViewById(R.id.client);

        clientConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConnexionClient.class);
                startActivity(intent);
            }
        });

        cuisinierConnexion = (Button) findViewById(R.id.cui);

        cuisinierConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConnexionCuisinier.class);
                startActivity(intent);
            }
        });

            adminConnexion = (Button) findViewById(R.id.adm);

        adminConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConnexionAdmin.class);
                startActivity(intent);
            }
        });
        Retour = (Button)findViewById(R.id.retour);
        Retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
