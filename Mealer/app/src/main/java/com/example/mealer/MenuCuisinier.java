package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class MenuCuisinier extends AppCompatActivity {

    Button ajouter;
    Button offrir;
    Button cesser;
    Button supp;
    String user;
    ListView liste;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> names=new ArrayList<>();
    int i;
    dbMenu db;

    //@SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cuisinier);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("key");
        }
        display();
        db = new dbMenu(this);

        cesser=findViewById(R.id.cesser);
        ajouter=(Button)findViewById(R.id.ajouter);
        supp=findViewById(R.id.enlever);
        offrir=(Button)findViewById(R.id.offrir);
        liste=findViewById(R.id.listview);
        offrir.setVisibility(View.GONE);
        cesser.setVisibility(View.GONE);
        supp.setVisibility(View.GONE);

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Repas.class);
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });

        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                offrir.setVisibility(View.VISIBLE);
                cesser.setVisibility(View.VISIBLE);
                supp.setVisibility(View.VISIBLE);
                supp.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (db.checkOffert(user, names.get(i)))
                            Toast.makeText(MenuCuisinier.this, "Veuillez supprimer le repas de la liste de plats offerts avant de le supprimer de votre menu", Toast.LENGTH_LONG).show();
                        else{
                            db.delete(names.get(i));
                            display();
                            supp.setVisibility(View.GONE);
                            offrir.setVisibility(View.GONE);
                            cesser.setVisibility(View.GONE);
                        }
                    }
                });
                offrir.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.offrir(user, names.get(i));
                        Toast.makeText(MenuCuisinier.this, "Le repas a été ajouté à la liste de repas proposés", Toast.LENGTH_LONG).show();
                        supp.setVisibility(View.GONE);
                        offrir.setVisibility(View.GONE);
                        cesser.setVisibility(View.GONE);                    }
                });
                cesser.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db.enlever(user, names.get(i));
                        supp.setVisibility(View.GONE);
                        offrir.setVisibility(View.GONE);
                        cesser.setVisibility(View.GONE);                    }
                });

            }
        });

    }

    public void display(){
        ArrayList<String> newlist=new ArrayList<>();
        ArrayList<String> newnames=new ArrayList<>();
        arrayList=newlist;
        names=newnames;
        liste=findViewById(R.id.listview);
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
                if (cuis.equals(user)){
                    String nom=cursor.getString(1);
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