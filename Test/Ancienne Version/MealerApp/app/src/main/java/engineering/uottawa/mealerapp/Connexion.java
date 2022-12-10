package engineering.uottawa.mealerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Connexion extends AppCompatActivity {

    Button clientConnexion;
    Button cuisinierConnexion;
    Button adminConnexion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion);

        clientConnexion = (Button) findViewById(R.id.client);

        clientConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConnexionClient.class);
                startActivityForResult(intent, 0);
            }
        });

        cuisinierConnexion = (Button) findViewById(R.id.cuisinier);

        cuisinierConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConnexionCuisinier.class);
                startActivityForResult(intent, 0);
            }
        });

        adminConnexion = (Button) findViewById(R.id.adminButton);

        adminConnexion.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ConnexionAdmin.class);
                startActivityForResult(intent, 0);
            }
        });
    }
}

