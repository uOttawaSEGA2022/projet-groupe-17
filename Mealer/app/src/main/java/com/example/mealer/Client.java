package com.example.mealer;


import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class Client extends AppCompatActivity {

    Button connectClient;
    Button rechercher;
    Button commander;
    Button evaluer;
    String user;
    dbHelper db;
    TextView notification;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_client);
        db=new dbHelper(this);
        notification=findViewById(R.id.notif);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("key");
        }
        if (db.checkNotif(user))
            notification.setVisibility(View.VISIBLE);
        else
            notification.setVisibility(View.INVISIBLE);

        connectClient=findViewById(R.id.deconnexionCl);
        rechercher=findViewById(R.id.rech);
        commander=findViewById(R.id.commande);
        evaluer=findViewById(R.id.eval);

        connectClient.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), RechercheClient.class);
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });

        commander.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), CommandesClient.class);
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });

        evaluer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), EvaluationClient.class);
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });

    }
}
