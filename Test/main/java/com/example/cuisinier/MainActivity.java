package com.example.cuisinier;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Patterns;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

 class cuisinier extends AppCompatActivity {
    EditText firstName;
    EditText lastName;
    EditText email;
    EditText address;
    ImageView cheque;
    Button register;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        firstName = findViewById(R.id.firstName);
        lastName = findViewById(R.id.lastName);
        address = findViewById(R.id.address);
        email = findViewById(R.id.email);
        register = findViewById(R.id.register);

        register.setOnClickListener(view -> checkDataEntered());
    }
    boolean isEmail(EditText text) {
        CharSequence editTextTextEmailAddress = text.getText().toString();
        return (!TextUtils.isEmpty(editTextTextEmailAddress) && Patterns.EMAIL_ADDRESS.matcher(editTextTextEmailAddress).matches());
    }

    boolean isEmpty(EditText text) {
        CharSequence str = text.getText().toString();
        return TextUtils.isEmpty(str);
    }

    void checkDataEntered() {
        if (isEmpty(firstName)) {
            Toast t = Toast.makeText(this, "You must enter first name to register!", Toast.LENGTH_SHORT);
            t.show();
        }
        if (isEmpty(lastName)) {
            lastName.setError("Last name is required!");
        }
        if (!isEmail(email)) {
            email.setError("Enter valid email!");
        }
    }
}