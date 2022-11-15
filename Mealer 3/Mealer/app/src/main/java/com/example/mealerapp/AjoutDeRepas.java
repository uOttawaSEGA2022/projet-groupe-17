package com.example.mealerapp;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

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
                String name = AjoutRepas.getText().toString();
                String price = Prix.getText().toString();
                String descrip = Description.getText().toString();
        }
        });


    }
}
