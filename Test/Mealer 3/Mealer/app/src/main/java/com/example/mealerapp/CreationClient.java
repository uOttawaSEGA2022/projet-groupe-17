package com.example.mealerapp;

import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CreationClient extends AppCompatActivity {

    Button connectCrCl;
    EditText username;
    EditText password;
    EditText prenom;
    EditText nom;
    EditText adresse;
    EditText carte;
    dbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.creationclient);

        connectCrCl=(Button)findViewById(R.id.connexionClient);
        username=(EditText)findViewById(R.id.courrielclien);
        password=(EditText)findViewById(R.id.passwordclient);
        DB = new dbHelper(this);
        prenom=(EditText)findViewById(R.id.prenomclient);
        nom=(EditText)findViewById(R.id.nomclient);
        adresse=(EditText)findViewById(R.id.adresseclient);
        carte=(EditText)findViewById(R.id.carte);

        connectCrCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String prnom = prenom.getText().toString();
                String nomm = nom.getText().toString();
                String adress = adresse.getText().toString();
                String cart = carte.getText().toString();

                if(prnom.equals(""))
                    Toast.makeText(CreationClient.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                else if(nomm.equals(""))
                    Toast.makeText(CreationClient.this, "Please enter your last name", Toast.LENGTH_SHORT).show();
                else if(user.equals(""))
                    Toast.makeText(CreationClient.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                else if(pass.equals(""))
                    Toast.makeText(CreationClient.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                else if(adress.equals(""))
                    Toast.makeText(CreationClient.this, "Please enter an address", Toast.LENGTH_SHORT).show();
                else if(cart.equals(""))
                    Toast.makeText(CreationClient.this, "Please enter your card number", Toast.LENGTH_SHORT).show();

                else {
                    Boolean insert = DB.insertData(user, pass);
                    if (insert == true) {
                        Toast.makeText(CreationClient.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Client.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(CreationClient.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}

