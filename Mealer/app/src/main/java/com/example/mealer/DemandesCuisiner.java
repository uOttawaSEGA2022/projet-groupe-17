package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class DemandesCuisiner extends AppCompatActivity {
    String user;
    Button retourner, approuver, rejeter;
    ListView liste;
    dbMenu db;dbHelper2 db2; dbDemandes db3; dbHelper clientdb;
    int i;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<Integer> names=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demandes_cuisiner);
        retourner=findViewById(R.id.retour);
        approuver=findViewById(R.id.offrir);
        rejeter=findViewById(R.id.cesser);
        approuver.setVisibility(View.GONE);
        rejeter.setVisibility(View.GONE);
        liste=findViewById(R.id.listview);
        db = new dbMenu(this);
        db2=new dbHelper2(this);
        db3=new dbDemandes(this);
        clientdb=new dbHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("key");
        }
        retourner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), Cuisinier.class);
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });
        display();
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                approuver.setVisibility(View.VISIBLE);
                rejeter.setVisibility(View.VISIBLE);
                approuver.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db3.setstatus(names.get(i), 1);
                        approuver.setVisibility(View.GONE);
                        rejeter.setVisibility(View.GONE);
                        String client=db3.getClient(names.get(i));
                        clientdb.setNotif(client, 1);
                        display();
                    }
                });
                rejeter.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        db3.setstatus(names.get(i), 2);
                        approuver.setVisibility(View.GONE);
                        rejeter.setVisibility(View.GONE);
                        String client=db3.getClient(names.get(i));
                        clientdb.setNotif(client, 1);
                        display();
                    }
                });
            }
        });
    }
    public void display(){
        ArrayList<String> newlist=new ArrayList<>();
        ArrayList<Integer> newnames=new ArrayList<>();
        arrayList=newlist;
        names=newnames;
        liste=findViewById(R.id.listview);
        db = new dbMenu(this);
        db2=new dbHelper2(this);
        db3=new dbDemandes(this);
        db3.insertFirst();
        int j=db3.checkNum();
        while (j>0){
            if (db3.showCook(user, j)){
                String name=db3.getMeal(j);
                String client=db3.getClient(j);
                arrayList.add("Repas: "+name+"\nCommendé par: "+client);
                names.add(j);
            }
            j--;
        }
        if(names.size()==0){
            Toast.makeText(DemandesCuisiner.this, "Vous n'avez aucune commande.", Toast.LENGTH_LONG).show();
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        liste.setAdapter(arrayAdapter);
    }
}