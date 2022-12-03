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
    int pos;
    Button ouiS, nonS, ok, indefinit;
    TextView suspendre;
    ArrayList<String> array;
    EditText limite;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pleintes);
        list=(ListView)findViewById(R.id.listview);
        DB = new dbHelper2(this);
        ouiS=(Button)findViewById(R.id.oui);
        nonS=(Button)findViewById(R.id.non);
        ok=(Button)findViewById(R.id.ok);
        indefinit=(Button)findViewById(R.id.inde);
        suspendre=(TextView)findViewById(R.id.sus);
        limite=(EditText)findViewById(R.id.date);
        final Boolean suspended;
        String cuis1="lila@gmail.com", cuis2="mark@gmail.com", cuis3="George@gmail.com", cuis4="joe@gmail.com",cuis5="sophie@gmail.com", cuis6="amelia@gmail.com";
        DB.insertData(cuis1, "lila");
        DB.insertData(cuis2, "mark");
        DB.insertData(cuis3, "george");
        DB.insertData(cuis4, "joe");
        DB.insertData(cuis5, "sophie");
        DB.insertData(cuis6, "amelia");


        array=display();

        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                suspendre.setVisibility(View.VISIBLE);
                ouiS.setVisibility(View.VISIBLE);
                nonS.setVisibility(View.VISIBLE);
                pos=position;



                ouiS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ok.setVisibility(View.VISIBLE);
                        indefinit.setVisibility(View.VISIBLE);
                        limite.setVisibility(View.VISIBLE);
                        indefinit.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DB.upgradeStatus(array.get(pos));
                                array=display();
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
                                    DB.upgradeStatus(array.get(pos), date);
                                    array=display();
                                }
                            }
                        });
                    }
                });
                nonS.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        DB.upgradeFB(array.get(pos));
                        array=display();
                    }
                });

            }
        });
    }
    public ArrayList<String> display(){
        ouiS=(Button)findViewById(R.id.oui);
        nonS=(Button)findViewById(R.id.non);
        ok=(Button)findViewById(R.id.ok);
        indefinit=(Button)findViewById(R.id.inde);
        suspendre=(TextView)findViewById(R.id.sus);
        limite=(EditText)findViewById(R.id.date);
        suspendre.setVisibility(View.GONE);
        ouiS.setVisibility(View.GONE);
        nonS.setVisibility(View.GONE);
        ok.setVisibility(View.GONE);
        indefinit.setVisibility(View.GONE);
        limite.setVisibility(View.GONE);
        ArrayList<String> arrayList=new ArrayList<>();
        ArrayList<String> names=new ArrayList<>();
        String cuis1="lila@gmail.com", cuis2="mark@gmail.com", cuis3="George@gmail.com", cuis4="joe@gmail.com",cuis5="sophie@gmail.com", cuis6="amelia@gmail.com", cuis7="adam@gmail.com";

        if (!DB.checkFB(cuis1, "lila")){
            arrayList.add("Cuisinier: "+cuis1+"\nPleinte: Le cuisinier m'a donné la mauvaise commande.");
            names.add(cuis1);
        }
        if (!DB.checkFB(cuis2, "mark")){
            arrayList.add("Cuisinier: "+cuis2+"\nPleinte: Le cuisinier a été en retard de 3 heures pour ma commande.");
            names.add(cuis2);
        }
        if (!DB.checkFB(cuis3, "george")){
            arrayList.add("Cuisinier: "+cuis3+"\nPleinte: Très mauvais service. Le cuisinier m'a dit que ma commande était trop grande et que je mange trop.");
            names.add(cuis3);
        }
        if (!DB.checkFB(cuis4, "joe")){
            arrayList.add("Cuisinier: "+cuis4+"\nPleinte: Le cuisinier a mangé ma commande puis m'a envoyé un sac vide.");
            names.add(cuis4);
        }
        if (!DB.checkFB(cuis5, "sophie")){
            arrayList.add("Cuisinier: "+cuis5+"\nPleinte: J'ai reçu le mauvais repas. Losque j'ai contacté le cuisinier il m'a raccroché au nez.");
            names.add(cuis5);
        }
        if (!DB.checkFB(cuis6, "amelia")){
            arrayList.add("Cuisinier: "+cuis6+"\nPleinte: J'ai commandé de la cuisine indienne non piquante et c'étai tellement piquant que je me suis brulée la bouche.");
            names.add(cuis6);
        }



        ArrayAdapter arrayAdapter=new ArrayAdapter(this, android.R.layout.simple_list_item_1, arrayList);
        list.setAdapter(arrayAdapter);
        return names;
    }
}