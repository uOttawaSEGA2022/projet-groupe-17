package com.example.mealer;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Connexion extends AppCompatActivity {

    Button clientConnexion;
    Button cuisinierConnexion;
    Button adminConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        clientConnexion =  findViewById(R.id.client);

        clientConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConnexionClient.class);
                startActivity(intent);
            }
        });

        cuisinierConnexion =  findViewById(R.id.cuisinier);

        cuisinierConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConnexionCuisinier.class);
                startActivity(intent);
            }
        });

        adminConnexion =  findViewById(R.id.adminButton);

        adminConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConnexionAdmin.class);
                startActivity(intent);
            }
        });
    }
}