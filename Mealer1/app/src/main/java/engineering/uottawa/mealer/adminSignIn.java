package engineering.uottawa.mealer;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class adminSignIn extends AppCompatActivity {

    Button connect;
    EditText identificateur;
    EditText motdepasse;
    String id, pass;
    TextView erreur;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_sign_in);

        connect=(Button)findViewById(R.id.connexion);
        identificateur=(EditText)findViewById(R.id.username);
        motdepasse=(EditText)findViewById(R.id.password);
        erreur=(TextView)findViewById(R.id.mauvaiseEntree);


        connect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                id=identificateur.getText().toString();
                pass=motdepasse.getText().toString();
                if (id!="Admin1"){
                    if (pass!="Livrable1") {
                        Intent intent = new Intent(getApplicationContext(), adminPage.class);
                        startActivityForResult(intent, 0);
                    }
                }
                else{
                    erreur.setVisibility(View.VISIBLE);

                }
            }
        });
    }
}
