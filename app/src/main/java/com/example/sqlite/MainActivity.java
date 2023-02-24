package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    DatabaseHelper myDb;
    EditText editName, editSurname, editMarks, editId;
    Button addDataBtn, viewAllBtn, updateBtn, delBtn;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myDb = new DatabaseHelper(this);

        addDataBtn = (Button)findViewById(R.id.addDataBtn);
        viewAllBtn = (Button)findViewById(R.id.viewAllBtn);
        updateBtn = (Button)findViewById(R.id.updateBtn);
        delBtn = (Button)findViewById(R.id.delBtn);
        editName = (EditText)findViewById(R.id.editText_name);
        editSurname = (EditText)findViewById(R.id.editText_surname);
        editId = (EditText)findViewById(R.id.editText_Id);
        editMarks = (EditText)findViewById(R.id.editText_marks);
        addData();
        viewAll();
        updateData();
        deleteData();
    }

    public void updateData() {
        updateBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        boolean isUpdate = myDb.updateData(
                                editId.getText().toString(),
                                editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString());
                        if(isUpdate == true){
                            Toast.makeText(MainActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    public void addData() {
        addDataBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertData(
                                editName.getText().toString(),
                                editSurname.getText().toString(),
                                editMarks.getText().toString()
                        );
                        if(isInserted == true){
                            Toast.makeText(MainActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                        }
            }
        });
    }

    public void viewAll() {
        viewAllBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = myDb.getAllData();
                if(res.getCount() == 0){
                    // show message
                    showMessage("Error", "Nothing found");
                    return;
                }
                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id: "+res.getString(0)+"\n");
                    buffer.append("Name: "+res.getString(1)+"\n");
                    buffer.append("Surname: "+res.getString(2)+"\n");
                    buffer.append("Marks: "+res.getString(3)+"\n");
                }

                // show all data
                showMessage("Data", buffer.toString());
            }
        });
    }

    public void deleteData() {
        delBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Integer deletedData = myDb.deleteData(editId.getText().toString());
                        if(deletedData > 0){
                            Toast.makeText(MainActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(MainActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }

    public void showMessage(String title, String Message){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}