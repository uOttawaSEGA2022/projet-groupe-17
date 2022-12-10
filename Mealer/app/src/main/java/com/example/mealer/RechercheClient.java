package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class RechercheClient extends AppCompatActivity {
    String user;
    Button rechercher;
    EditText plat;
    ListView liste;
    Button commande;
    dbMenu db;dbHelper2 db2; dbDemandes db3;
    int i;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> names=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recherche_client);
        rechercher=findViewById(R.id.cherche);
        commande=findViewById(R.id.commander);
        commande.setVisibility(View.GONE);
        plat=findViewById(R.id.nom);
        db = new dbMenu(this);
        db2=new dbHelper2(this);
        db3=new dbDemandes(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("key");
        }

        rechercher.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name=plat.getText().toString();
                plat.setText("");
                if(name.equals(""))
                    Toast.makeText(RechercheClient.this, "Veuillez entrer une recherche", Toast.LENGTH_LONG).show();
                else{
                    display(name);
                    liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                            commande.setVisibility(View.VISIBLE);
                            commande.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View v) {
                                    db3.insertFirst();
                                    String name=names.get(i);
                                    String cook=db.getCuis(names.get(i));
                                    db3.insertData(name, user, cook);
                                    Toast.makeText(RechercheClient.this, "Le repas a ete ajoute dans votre de commandes", Toast.LENGTH_LONG).show();
                                    commande.setVisibility(View.GONE);
                                }
                            });

                        }
                    });
                }
            }
        });


    }
    public void display(String name){
        ArrayList<String> newlist=new ArrayList<>();
        ArrayList<String> newnames=new ArrayList<>();
        arrayList=newlist;
        names=newnames;
        liste=findViewById(R.id.result);
        Boolean next = true;
        db = new dbMenu(this);
        db2=new dbHelper2(this);
        //db.insertData(user, "testing", "dessert", "mexicain", "sucre", "aucun", 10.3, "cest bon");
        i=0;

        while (next){
            Cursor cursor =db.getinfo(i);
            if (cursor==null)
                next=false;
            else{
                String usern=cursor.getString(0);
                String type=cursor.getString(2);
                String nom=cursor.getString(1);
                String cuis=cursor.getString(3);
                if (db.checkOffert(usern, nom)&&!db2.checksuspended(usern)&&(cuis.equals(name) || type.equals(name)||nom.equals(name))){
                    String typer=cursor.getString(2);
                    String typec=cursor.getString(3);
                    String ingr=cursor.getString(4);
                    String all=cursor.getString(5);
                    String prix=cursor.getString(6);
                    String desc=cursor.getString(7);
                    arrayList.add("Nom du repas: "+nom+"\n"+"Type de repas: "+typer+"\n"+"Type de cuisine: "+typec+"\n"+"Liste des ingrédients: "+ingr+"\n"+"Allergènes: "+all+"\n"+"Prix: "+prix+"\n"+"Description: "+desc
                    +"\nCuisinier: "+db2.getPrenom(usern)+" "+db2.getNom(usern)+"\nCourriel: "+usern+"\nAdresse: "+db2.getAdresse(usern)+"\nNote: "+db2.getNote(usern)+"\nDescription: "+db2.getDesc(usern));
                    names.add(nom);
                }
                i++;
            }

        }
        if(names.size()==0){
            Toast.makeText(RechercheClient.this, "Aucun resultat ne correspond a votre recherche. Veuillez reessayer.", Toast.LENGTH_LONG).show();
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        liste.setAdapter(arrayAdapter);
    }
}
