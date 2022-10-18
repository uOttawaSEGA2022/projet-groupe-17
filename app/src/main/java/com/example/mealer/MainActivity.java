package com.example.mealer;



import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import com.example.mealer.Firebase.DBHelperF;
import com.example.mealer.Sql.DBHelper;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;




public class MainActivity extends AppCompatActivity {
    EditText FirstName;
    EditText LastName;
    EditText Address;
    EditText Email;
    EditText password;
    Button Register;
    Button login;
    DBHelperF dbHelper;
    private DBHelperF dbHelperFS;
    private DatabaseReference myRef;
    private FirebaseDatabase database;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FirstName = findViewById(R.id.firstName);
        LastName = findViewById(R.id.lastName);
        Address = findViewById(R.id.address);
        Email = findViewById(R.id.email);
        password = findViewById(R.id.Password);

        Button Register = findViewById(R.id.register);
        dbHelper = new DBHelper(this);
        return Register.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                String userName1 = FirstName.getText().toString();
                String address1 = Address.getText().toString();
                String email1 = Email.getText().toString();
                String pass1 = password.getText().toString();
                long password = Long.parseLong(pass1);
                database = FirebaseDatabase.getInstance();
                myRef = database.getReference("User");
                myRef.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        if (checkUser(snapshot, userName1)) {dbHelperFS = new DBHelperF(userName1, address1, email1, password);
                            myRef.child(userName1.toString()).setValue(dbHelperFS);
                            Email.setText("");
                            Address.setText("");
                            password.setText("");
                            Toast.makeText(MainActivity.this, "Data inserted", Toast.LENGTH_SHORT).show();
                            Intent i = new Intent(MainActivity.this, LoginActivity.class);
                            startActivity(i);
                        } else {
                            Toast.makeText(MainActivity.this, "User Name Already Exists", Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                    }
                });

                login = findViewById(R.id.idBtnLogin);
                login.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Intent i = new Intent(MainActivity.this, LoginActivity.class);
                        startActivity(i);
                    }
                });
            }
            private boolean checkUser(DataSnapshot snapshot, String user) {
                String user1;
                for (DataSnapshot ds : snapshot.getChildren()) {
                    user1 = ds.child("userName").getValue(String.class);
                    if (user.equals(user1)) {
                        return false;
                    }
                }
                return true;
            }
        }
    }
}

/*
    boolean isEmail(EditText text) {
        CharSequence editTextTextEmailAddress = text.getText().toString();
        return (!TextUtils.isEmpty(editTextTextEmailAddress) && Patterns.EMAIL_ADDRESS.matcher(editTextTextEmailAddress).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(FirstName)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(LastName)) {
            LastName.setError("Last name is required!");
        }
        if (isEmail(Email)==false){
            Email.setError("Enter valid email!");
        }
    }
    */

