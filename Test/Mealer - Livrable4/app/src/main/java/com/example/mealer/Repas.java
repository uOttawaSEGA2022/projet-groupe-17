package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Repas extends AppCompatActivity {

    String user;
    Button ajouter;
    dbMenu db;
    EditText nom, type, cuisine, ing, all, prix, desc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repas);

        db = new dbMenu(this);

        nom=findViewById(R.id.nom);
        type=findViewById(R.id.type);
        cuisine=findViewById(R.id.cuisine);
        ing=findViewById(R.id.ingrédients);
        all=findViewById(R.id.allergenes);
        prix=findViewById(R.id.prix);
        desc=findViewById(R.id.description);
        ajouter=findViewById(R.id.ajouter);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("key");
        }

        ajouter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String nomm = nom.getText().toString();
                String typeR = type.getText().toString();
                String typeC = cuisine.getText().toString();
                String ingr = ing.getText().toString();
                String alle = all.getText().toString();
                String prx = prix.getText().toString();
                String descr = desc.getText().toString();

                if(nomm.equals(""))
                    Toast.makeText(Repas.this, "Veuillez entrer le nom du repas", Toast.LENGTH_SHORT).show();
                else if(typeR.equals(""))
                    Toast.makeText(Repas.this, "Veuillez entrer le type du repas", Toast.LENGTH_SHORT).show();
                else if(typeC.equals(""))
                    Toast.makeText(Repas.this, "Veuillez entrer le type de cuisine", Toast.LENGTH_SHORT).show();
                else if(ingr.equals(""))
                    Toast.makeText(Repas.this, "Veuillez donner la liste des ingredients", Toast.LENGTH_SHORT).show();
                else if(alle.equals(""))
                    Toast.makeText(Repas.this, "Veuillez indiquer les allergenes", Toast.LENGTH_SHORT).show();
                else if(prx.equals(""))
                    Toast.makeText(Repas.this, "Veuillez entrer le cout du plat", Toast.LENGTH_SHORT).show();
                else if(descr.equals(""))
                    Toast.makeText(Repas.this, "Veuillez entrer une description du repas", Toast.LENGTH_SHORT).show();

                else {
                    Boolean insert = db.insertData(user, nomm, typeR, typeC, ingr, alle, Double.valueOf(prx), descr);
                    if (insert == true) {
                        Toast.makeText(Repas.this, "Plat ajouté", Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent(getApplicationContext(), MenuCuisinier.class);
                        intent.putExtra("key",user);
                        startActivity(intent);
                    } else {
                        Toast.makeText(Repas.this, "Le plat existe déjà", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}