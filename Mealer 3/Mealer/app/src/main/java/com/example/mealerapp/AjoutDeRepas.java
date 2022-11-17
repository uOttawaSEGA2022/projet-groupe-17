package com.example.mealerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AjoutDeRepas extends AppCompatActivity {

    Button AjoutRepas ;
    EditText NomDuRepas ;
    EditText TypeDeRepas;
    EditText ListDesIngredients;
    EditText TypeDeCuisine ;
    EditText Allergenes;
    EditText Prix;
    EditText Description ;
    private String name;
    private String price;
    private String descrip;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.ajoutrepas);

        AjoutRepas = (Button)findViewById(R.id.ajoutplatbutton);
        NomDuRepas = (EditText) findViewById(R.id.nomRepas);
        Prix = (EditText) findViewById(R.id.prix);
        Description = (EditText) findViewById(R.id.descriptionPlat);

        AjoutRepas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                name = AjoutRepas.getText().toString();
                price = Prix.getText().toString();
                descrip = Description.getText().toString();
        }
        });

        if( name.equals(""))
            Toast.makeText(AjoutDeRepas.this, "Please enter the name", Toast.LENGTH_SHORT).show();
        else if(price.equals(""))
            Toast.makeText(AjoutDeRepas.this, "Please enter the price", Toast.LENGTH_SHORT).show();
        else if(descrip.equals(""))
            Toast.makeText(AjoutDeRepas.this, "Please enter your the description", Toast.LENGTH_SHORT).show();
        else{

        }
    }
}
