package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class CommandesClient extends AppCompatActivity {

    String user;
    Button retourner;
    ListView liste;
    dbMenu db;dbHelper2 db2; dbDemandes db3; dbHelper dbclient;
    int i;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<Integer> names=new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commandes_client);
        retourner=findViewById(R.id.retour);
        liste=findViewById(R.id.offres);
        db = new dbMenu(this);
        db2=new dbHelper2(this);
        db3=new dbDemandes(this);
        dbclient=new dbHelper(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("key");
        }
        dbclient.setNotif(user, 0);
        retourner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent  = new Intent(getApplicationContext(), Client.class);
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });
        display();
        liste.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                int j=db3.checkstatus(names.get(i));
                if (j==0)
                    Toast.makeText(CommandesClient.this, "Votre commande est en attente.", Toast.LENGTH_SHORT).show();
                else if(j==1)
                    Toast.makeText(CommandesClient.this, "Votre commande a été approuvée", Toast.LENGTH_SHORT).show();
                else if(j==2)
                    Toast.makeText(CommandesClient.this, "Votre commande a été rejetée", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void display(){
        ArrayList<String> newlist=new ArrayList<>();
        ArrayList<Integer> newnames=new ArrayList<>();
        arrayList=newlist;
        names=newnames;
        liste=findViewById(R.id.offres);
        db = new dbMenu(this);
        db2=new dbHelper2(this);
        db3=new dbDemandes(this);
        db3.insertFirst();
        int j=db3.checkNum();
        while (j>0){
            if (db3.isClient(user, j)){
                String name=db3.getMeal(j);
                arrayList.add("Repas commandé: "+name);
                names.add(j);
            }
            j--;
        }
        if(names.size()==0){
            Toast.makeText(CommandesClient.this, "Vous n'avez fait aucune commande.", Toast.LENGTH_LONG).show();
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        liste.setAdapter(arrayAdapter);
    }
}