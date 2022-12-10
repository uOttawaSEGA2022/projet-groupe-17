package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class EvaluationClient extends AppCompatActivity {
    String user;
    Button retourner;
    ListView liste;
    Button soumettre, valider;
    EditText note, pleinte;
    dbMenu db;dbHelper2 db2; dbDemandes db3; dbPleintes dbpleinte;
    int i;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<String> names=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_evaluation_client);
        retourner=findViewById(R.id.retournc);
        liste=findViewById(R.id.offres);
        soumettre=findViewById(R.id.soumettre);
        valider=findViewById(R.id.valider);
        note=findViewById(R.id.note);
        pleinte=findViewById(R.id.pleinte);
        valider.setVisibility(View.GONE);
        pleinte.setVisibility(View.GONE);
        note.setVisibility(View.GONE);
        soumettre.setVisibility(View.GONE);
        db = new dbMenu(this);
        db2=new dbHelper2(this);
        db3=new dbDemandes(this);
        dbpleinte=new dbPleintes(this);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("key");
        }
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
                valider.setVisibility(View.VISIBLE);
                pleinte.setVisibility(View.VISIBLE);
                note.setVisibility(View.VISIBLE);
                soumettre.setVisibility(View.VISIBLE);
                valider.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String not=note.getText().toString();
                        note.setText("");
                        if(!not.equals("1")&&!not.equals("2")&&!not.equals("3")&&!not.equals("4")&&!not.equals("5")){
                            Toast.makeText(EvaluationClient.this, "Veuillez entrer un chiffre de 1 a 5.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            valider.setVisibility(View.GONE);
                            note.setVisibility(View.GONE);
                            db2.addNote(Integer.valueOf(not), names.get(i));
                        }
                    }
                });
                soumettre.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String pleint=pleinte.getText().toString();
                        pleinte.setText("");
                        if(pleint.equals("")){
                            Toast.makeText(EvaluationClient.this, "Veuillez entrer une pleinte.", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            dbpleinte.insertFirst();
                            dbpleinte.insertData(pleint, user, names.get(i));
                            pleinte.setVisibility(View.GONE);
                            soumettre.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });
    }
    public void display(){
        ArrayList<String> newlist=new ArrayList<>();
        ArrayList<String> newnames=new ArrayList<>();
        arrayList=newlist;
        names=newnames;
        Boolean next=true;
        Boolean present=false;
        liste=findViewById(R.id.offres);
        db = new dbMenu(this);
        db2=new dbHelper2(this);
        db3=new dbDemandes(this);
        db3.insertFirst();
        int j=db3.checkNum();
        i=0;

        while (next){
            Cursor cursor =db.getinfo(i);
            present=false;
            if (cursor==null)
                next=false;
            else{
                String cuis=cursor.getString(0);
                j=db3.checkNum();
                while (j>0&&!present){
                    if (db3.isCook(user, cuis, j)){
                        arrayList.add("Cuisinier: "+db2.getPrenom(cuis)+" "+db2.getNom(cuis));
                        names.add(cuis);
                        present=true;
                    }
                    j--;
                }
                i++;
            }

        }
        if(names.size()==0){
            Toast.makeText(EvaluationClient.this, "Aucune de vos commandes a ete validee.", Toast.LENGTH_LONG).show();
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        liste.setAdapter(arrayAdapter);
    }
}