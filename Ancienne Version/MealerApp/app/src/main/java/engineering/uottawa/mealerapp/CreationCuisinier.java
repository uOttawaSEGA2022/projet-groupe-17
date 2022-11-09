package engineering.uottawa.mealerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreationCuisinier extends AppCompatActivity {

    Button connectCrC;
    EditText username;
    EditText password;
    EditText prenom;
    EditText nom;
    EditText adresse;
    EditText desc;
    dbHelper2 DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_cuisinier);

        connectCrC=(Button)findViewById(R.id.connexionCuisinier);
        username=(EditText)findViewById(R.id.courrielcuisinier);
        password=(EditText)findViewById(R.id.passwordcuisinier);
        DB = new dbHelper2(this);
        prenom=(EditText)findViewById(R.id.prenomcuisinier);
        nom=(EditText)findViewById(R.id.nomcuisinier);
        adresse=(EditText)findViewById(R.id.adressecuisinier);
        desc=(EditText)findViewById(R.id.description);

        connectCrC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String prnom = prenom.getText().toString();
                String nomm = nom.getText().toString();
                String adress = adresse.getText().toString();
                String description = desc.getText().toString();

                if(prnom.equals(""))
                    Toast.makeText(CreationCuisinier.this, "Please enter your name", Toast.LENGTH_SHORT).show();
                else if(nomm.equals(""))
                    Toast.makeText(CreationCuisinier.this, "Please enter your last name", Toast.LENGTH_SHORT).show();
                else if(user.equals(""))
                    Toast.makeText(CreationCuisinier.this, "Please enter your email", Toast.LENGTH_SHORT).show();
                else if(pass.equals(""))
                    Toast.makeText(CreationCuisinier.this, "Please enter a password", Toast.LENGTH_SHORT).show();
                else if(adress.equals(""))
                    Toast.makeText(CreationCuisinier.this, "Please enter an address", Toast.LENGTH_SHORT).show();
                else if(description.equals(""))
                    Toast.makeText(CreationCuisinier.this, "Please enter a description", Toast.LENGTH_SHORT).show();

                else {
                    Boolean insert = DB.insertData(user, pass);
                    if (insert == true) {
                        Toast.makeText(CreationCuisinier.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Cuisinier.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(CreationCuisinier.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }
}