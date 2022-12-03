package com.example.mealer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class Cuisinier extends AppCompatActivity {

    Button connectCuisinier;
    Button menuCuisinier;
    Button offertCuisinier;
    TextView susp;
    TextView limite;
    String user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cuisinier);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            user = extras.getString("key");
        }

        connectCuisinier=(Button)findViewById(R.id.deconnexionC);
        menuCuisinier=(Button)findViewById(R.id.menu);
        offertCuisinier=(Button)findViewById(R.id.offert);
        susp=findViewById(R.id.suspendu);
        limite=findViewById(R.id.date);

        if (user.startsWith("jusqu'au")||user.startsWith("indéfiniment")){
            menuCuisinier.setVisibility(View.GONE);
            offertCuisinier.setVisibility(View.GONE);
            susp.setVisibility(View.VISIBLE);
            limite.setText(user);
            limite.setVisibility(View.VISIBLE);

        }

        connectCuisinier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        menuCuisinier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), MenuCuisinier.class);
                intent.putExtra("key",user);
                startActivity(intent);
            }
        });

        offertCuisinier.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), RepatsOfferts.class);
                intent.putExtra("key",user);
                startActivity(intent);

            }
        });

    }
}
