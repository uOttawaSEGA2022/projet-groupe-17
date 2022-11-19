package com.example.mealerapp;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


import androidx.appcompat.app.AppCompatActivity;

public class ConnexionAdmin extends AppCompatActivity {
    Button connectCA;
    EditText username, password;
    dbHelper3 DB;


    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.coadm);

        connectCA=(Button)findViewById(R.id.connexionA);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        DB = new dbHelper3(this);

        connectCA.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DB.insertData("Admin1", "Password1");
                DB.insertData("Admin2", "Password2");
                DB.insertData("Admin3", "Password3");

                String user = username.getText().toString();
                String pass = password.getText().toString();
                String role = "cuisinier";
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(ConnexionAdmin.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(ConnexionAdmin.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), Cuisinier.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ConnexionAdmin.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
