package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    Button addnewBtn, delBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        myDb = new DatabaseHelper(this);

        addnewBtn = (Button)findViewById(R.id.addnewBtn);
        delBtn = (Button)findViewById(R.id.delBtn);


        Intent intent1 = new Intent(MainActivity.this, AddNewActivity.class);

        addnewBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        startActivity(intent1);
                    }
                }
        );
    }

}