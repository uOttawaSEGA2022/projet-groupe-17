package com.example.testingas;

import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {

    Button connectMain;
    Button createMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        connectMain=findViewById(R.id.connection1);

        connectMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Connexion.class);
                startActivityForResult(intent,0);
            }
        });

        createMain=findViewById(R.id.creatAccount);

        createMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Creation.class);
                startActivityForResult(intent,0);
            }
        });
    }
}