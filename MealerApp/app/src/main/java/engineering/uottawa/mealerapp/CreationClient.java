package engineering.uottawa.mealerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class CreationClient extends AppCompatActivity {

    Button connectCrCl;
    EditText username;
    EditText password;
    dbHelper DB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_client);

        connectCrCl=(Button)findViewById(R.id.connexionClient);
        username=(EditText)findViewById(R.id.courrielclient);
        password=(EditText)findViewById(R.id.passwordclient);
        DB = new dbHelper(this);

        connectCrCl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String role = "client";

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(CreationClient.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();

                else {
                    Boolean insert = DB.insertData(user, pass);
                    if (insert == true) {
                        Toast.makeText(CreationClient.this, "Registered successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), Client.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(CreationClient.this, "Registration failed", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
