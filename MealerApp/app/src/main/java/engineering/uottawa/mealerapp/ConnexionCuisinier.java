package engineering.uottawa.mealerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
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

        connectCC=(Button)findViewById(R.id.connexionC);
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        DB = new dbHelper2(this);

        connectCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = username.getText().toString();
                String pass = password.getText().toString();
                String role = "cuisinier";
                if(user.equals("")||pass.equals(""))
                    Toast.makeText(ConnexionCuisinier.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    Boolean checkuserpass = DB.checkusernamepassword(user, pass);
                    if(checkuserpass==true){
                        Toast.makeText(ConnexionCuisinier.this, "Sign in successfull", Toast.LENGTH_SHORT).show();
                        Intent intent  = new Intent(getApplicationContext(), Cuisinier.class);
                        startActivity(intent);
                    }else{
                        Toast.makeText(ConnexionCuisinier.this, "Invalid Credentials", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

    }
}
