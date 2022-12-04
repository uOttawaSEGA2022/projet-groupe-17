package com.example.mealer;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Client extends AppCompatActivity {

    Button connectClient;
    Button searchMeal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);

        connectClient=findViewById(R.id.deconnexionCl);
        searchMeal = findViewById(R.id.rechercherRepas);

        connectClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        searchMeal.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), RechercherRepas.class);
                startActivity(intent);
            }


        });

    }
}
