package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

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

public class Pleintes extends AppCompatActivity {

    ListView list;
    dbHelper2 DB;
    dbPleintes db;
    Button ouiS, nonS, ok, indefinit;
    TextView suspendre;
    int i;
    ArrayList<String> arrayList=new ArrayList<>();
    ArrayList<Integer> names=new ArrayList<>();
    EditText limite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pleintes);
        list=(ListView)findViewById(R.id.listview);
        DB = new dbHelper2(this);
        db=new dbPleintes(this);
        ouiS=(Button)findViewById(R.id.oui);
        nonS=(Button)findViewById(R.id.non);
        ok=(Button)findViewById(R.id.ok);
        indefinit=(Button)findViewById(R.id.inde);
        suspendre=(TextView)findViewById(R.id.sus);
        limite=(EditText)findViewById(R.id.date);
        final Boolean suspended;
        display();


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                suspendre.setVisibility(View.VISIBLE);
                ouiS.setVisibility(View.VISIBLE);
                nonS.setVisibility(View.VISIBLE);
                ouiS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        ok.setVisibility(View.VISIBLE);
                        indefinit.setVisibility(View.VISIBLE);
                        limite.setVisibility(View.VISIBLE);
                        indefinit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DB.upgradeStatus(db.getCook(names.get(i)));
                                suspendre.setVisibility(View.GONE);
                                ouiS.setVisibility(View.GONE);
                                nonS.setVisibility(View.GONE);
                                ok.setVisibility(View.GONE);
                                indefinit.setVisibility(View.GONE);
                                limite.setVisibility(View.GONE);
                                db.settraite(names.get(i));
                                display();
                            }
                        });
                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                String date=limite.getText().toString().trim();
                                if (date.length()!=8 || date.charAt(2)!='/'||date.charAt(2)!='/')
                                    Toast.makeText(Pleintes.this, "Veuillez suivre le format (JJ/MM/AA)", Toast.LENGTH_LONG).show();
                                else{
                                    Toast.makeText(Pleintes.this, "Pleinte traitée", Toast.LENGTH_LONG).show();
                                    DB.upgradeStatus(db.getCook(names.get(i)), date);
                                    db.settraite(names.get(i));
                                    suspendre.setVisibility(View.GONE);
                                    ouiS.setVisibility(View.GONE);
                                    nonS.setVisibility(View.GONE);
                                    ok.setVisibility(View.GONE);
                                    indefinit.setVisibility(View.GONE);
                                    limite.setVisibility(View.GONE);
                                    display();
                                }
                            }
                        });
                    }
                });
                nonS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DB.upgradeFB(db.getCook(names.get(i)));
                        db.settraite(names.get(i));
                        suspendre.setVisibility(View.GONE);
                        ouiS.setVisibility(View.GONE);
                        nonS.setVisibility(View.GONE);
                        ok.setVisibility(View.GONE);
                        indefinit.setVisibility(View.GONE);
                        limite.setVisibility(View.GONE);
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
        list=findViewById(R.id.listview);
        db=new dbPleintes(this);
        DB=new dbHelper2(this);
        db.insertFirst();
        int j=db.checkNum();
        while (j>0){
            if (!db.isTraite(j)&&!DB.checksuspended(db.getCook(j))){
                String cuis=db.getCook(j);
                String client=db.getClient(j);
                String pleinte=db.getPleinte(j);
                arrayList.add("Cuisinier: "+cuis+" Client: "+client+"\nPleinte: "+pleinte);
                names.add(j);
            }
            j--;
        }
        if(names.size()==0){
            Toast.makeText(Pleintes.this, "Aucune pleinte.", Toast.LENGTH_LONG).show();
        }
        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
    }

}

