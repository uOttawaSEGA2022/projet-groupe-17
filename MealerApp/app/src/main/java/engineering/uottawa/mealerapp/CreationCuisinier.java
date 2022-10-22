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
    dbHelper2 DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_cuisinier);

        connectCrC=(Button)findViewById(R.id.connexionCuisinier);
        username=(EditText)findViewById(R.id.courrielcuisinier);
        password=(EditText)findViewById(R.id.passwordcuisinier);
        DB = new dbHelper2(this);

        connectCrC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String role = "cuisinier";

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(CreationCuisinier.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

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