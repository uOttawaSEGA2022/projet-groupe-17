package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class ProfilCuisinier extends AppCompatActivity {
    String user;
    dbHelper2 DB;
    TextView prenom;
    TextView nom;
    TextView couriel;
    TextView adresse;
    TextView note;
    TextView desc;
    Button retour;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profil_cuisinier);
        prenom=findViewById(R.id.PrenomPc);
        nom=findViewById(R.id.nomPc);
        couriel=findViewById(R.id.courielPc);
        adresse=findViewById(R.id.AdressePc);
        note=findViewById(R.id.notepc);
        desc=findViewById(R.id.descPc);
        retour=findViewById(R.id.retourp);

        DB = new dbHelper2(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("key");
        }
        prenom.setText(DB.getPrenom(user));
        nom.setText(DB.getNom(user));
        couriel.setText(user);
        adresse.setText(DB.getAdresse(user));
        note.setText(DB.getNote(user)+"/5");
        desc.setText(DB.getDesc(user));

        retour.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), Cuisinier.class);
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });
    }
}