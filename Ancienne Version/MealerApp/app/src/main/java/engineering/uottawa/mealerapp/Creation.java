package engineering.uottawa.mealerapp;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Creation extends AppCompatActivity {

    Button clientCreation;
    Button cuisinierCreation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation);

        clientCreation=(Button)findViewById(R.id.clientCreate);

        clientCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), CreationClient.class);
                startActivityForResult(intent,0);
            }
        });

        cuisinierCreation=(Button)findViewById(R.id.cuisinierCreate);

        cuisinierCreation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(getApplicationContext(), CreationCuisinier.class);
                startActivityForResult(intent,0);
            }
        });
    }
}
