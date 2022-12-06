package com.example.mealer;

import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class EvaluationCuisinier extends AppCompatActivity {
    ListView list;
    dbHelper2 DB;
    int pos;
    Button ouiS, nonS, ok, indefinit;
    TextView suspendre;
    ArrayList<String> array;
    EditText limite;
}
