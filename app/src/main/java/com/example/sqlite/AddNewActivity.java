package com.example.sqlite;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNewActivity extends AppCompatActivity {

    EditText editName, editPhone, editStreet, editEmail, editCity;
    Button saveBtn, updateBtn, delBtn, viewContactsBtn;

    DatabaseHelper myDb;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myDb = new DatabaseHelper(this);

        saveBtn = (Button)findViewById(R.id.saveBtn);
        viewContactsBtn = (Button)findViewById(R.id.viewContactsBtn);
        updateBtn = (Button)findViewById(R.id.updateBtn);
        delBtn = (Button)findViewById(R.id.delBtn);

        editName = (EditText)findViewById(R.id.editText_name);
        editPhone = (EditText)findViewById(R.id.editText_phone);
        editStreet = (EditText)findViewById(R.id.editText_street);
        editEmail = (EditText)findViewById(R.id.editText_email);
        editCity = (EditText)findViewById(R.id.editText_city);

//        Intent intent2 = new Intent(AddNewActivity.this, MainActivity.class);
//        viewContactsBtn.setOnClickListener(
//                new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(intent2);
//                    }
//                }
//        );

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
                                editName.getText().toString(),
                                editPhone.getText().toString(),
                                editStreet.getText().toString(),
                                editEmail.getText().toString(),
                                editCity.getText().toString());
                        if(isUpdate == true){
                            Toast.makeText(AddNewActivity.this, "Data Updated", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(AddNewActivity.this, "Data Not Updated", Toast.LENGTH_LONG).show();
                        }
                    }
                }
        );
    }
    public void addData() {
        saveBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        boolean isInserted = myDb.insertData(
                                editName.getText().toString(),
                                editPhone.getText().toString(),
                                editStreet.getText().toString(),
                                editEmail.getText().toString(),
                                editCity.getText().toString()
                        );
                        if(isInserted == true){
                            Toast.makeText(AddNewActivity.this, "Data Inserted", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(AddNewActivity.this, "Data Not Inserted", Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    public void viewAll() {
        viewContactsBtn.setOnClickListener(new View.OnClickListener() {
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
                    buffer.append("Name: "+res.getString(0)+"\n");
                    buffer.append("Phone: "+res.getString(1)+"\n");
                    buffer.append("Street: "+res.getString(2)+"\n");
                    buffer.append("Email: "+res.getString(3)+"\n");
                    buffer.append("City: "+res.getString(4)+"\n");
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
                        Integer deletedData = myDb.deleteData(editPhone.getText().toString());
                        if(deletedData > 0){
                            Toast.makeText(AddNewActivity.this, "Data Deleted", Toast.LENGTH_LONG).show();
                        }
                        else{
                            Toast.makeText(AddNewActivity.this, "Data Not Deleted", Toast.LENGTH_LONG).show();
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