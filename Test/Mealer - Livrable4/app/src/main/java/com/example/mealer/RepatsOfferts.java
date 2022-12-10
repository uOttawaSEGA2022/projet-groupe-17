package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

import android.widget.Toast;
import java.util.List;

public class RepatsOfferts extends AppCompatActivity {

    String user;
    Button retur;
    ListView liste;
    dbMenu db;
    int i;
    TextView name;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> names=new ArrayList<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repats_offerts);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("key");
        }
        display();
        retur =findViewById(R.id.retour);
        db = new dbMenu(this);
        liste=findViewById(R.id.offres);
        name=findViewById(R.id.nom);

        name.setText(user);
        retur.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Cuisinier.class);
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });
    }
    public void display(){
        ArrayList<String> newlist=new ArrayList<>();
        ArrayList<String> newnames=new ArrayList<>();
        arrayList=newlist;
        names=newnames;
        liste=findViewById(R.id.offres);
        Boolean next = true;
        db = new dbMenu(this);
        //db.insertData(user, "testing", "dessert", "mexicain", "sucre", "aucun", 10.3, "cest bon");
        i=0;

        while (next){
            Cursor cursor =db.getinfo(i);
            if (cursor==null)
                next=false;
            else{
                String cuis=cursor.getString(0);
                String nom=cursor.getString(1);
                if (cuis.equals(user) && db.checkOffert(cuis,nom)){
                    String typer=cursor.getString(2);
                    String typec=cursor.getString(3);
                    String ingr=cursor.getString(4);
                    String all=cursor.getString(5);
                    String prix=cursor.getString(6);
                    String desc=cursor.getString(7);
                    arrayList.add("Nom du repas: "+nom+"\n"+"Type de repas: "+typer+"\n"+"Type de cuisine: "+typec+"\n"+"Liste des ingrédients: "+ingr+"\n"+"Allergènes: "+all+"\n"+"Prix: "+prix+"\n"+"Description: "+desc);
                    names.add(nom);
                }
                i++;
            }

        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        liste.setAdapter(arrayAdapter);
    }
}