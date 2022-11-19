package com.example.projet;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ConnexionCuisinier extends AppCompatActivity {

    Button connectCC;
    EditText username, password;
    dbHelper2 DB;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_cuisinier);

        connectCC=(Button)findViewById(R.id.connexionCuisinier);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        DB = new dbHelper2(this);

        connectCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String cuis1="lila@gmail.com", cuis2="mark@gmail.com", cuis3="George@gmail.com", cuis4="joe@gmail.com",cuis5="sophie@gmail.com", cuis6="amelia@gmail.com", cuis7="adam@gmail.com";
                DB.insertData(cuis1, "lila");
                DB.insertData(cuis2, "mark");
                DB.insertData(cuis3, "george");
                DB.insertData(cuis4, "joe");
                DB.insertData(cuis5, "sophie");
                DB.insertData(cuis6, "amelia");
                DB.insertData(cuis7, "adamou");
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String role = "cuisinier";
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(ConnexionCuisinier.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    Boolean checksuspend = DB.checksuspended(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(ConnexionCuisinier.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), Cuisinier.class);
                        startActivity(intent);
                    }else if(checksuspend==true){
                        Cursor res=DB.getdate(user);
                        if (res.getCount()!=0){
                            if(res.moveToNext()){
                                String date=res.getString(3);
                                if (date.equals(""))
                                    Toast.makeText(ConnexionCuisinier.this, "Vous avez été suspendu indefiniment", Toast.LENGTH_LONG).show();
                                else{
                                    Toast.makeText(ConnexionCuisinier.this, "Vous avez été suspendu juasqu'au "+date, Toast.LENGTH_LONG).show();
                                }
                            }
                        }
                    }
                    else {
                        Toast.makeText(ConnexionCuisinier.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
