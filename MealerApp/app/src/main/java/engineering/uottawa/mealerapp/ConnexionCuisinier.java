package engineering.uottawa.mealerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class ConnexionCuisinier extends AppCompatActivity {

    Button connectCC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_connexion_cuisinier);

        connectCC=(Button)findViewById(R.id.connexionC);

        connectCC.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), Cuisinier.class);
                startActivityForResult(intent,0);
            }
        });
    }
}
