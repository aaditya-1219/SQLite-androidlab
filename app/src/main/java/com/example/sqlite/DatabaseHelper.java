package com.example.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "Student.db";
    public static final String TABLE_NAME = "student_table";
    public static final String COL_1 = "NAME";
    public static final String COL_2 = "PHONE";
    public static final String COL_3 = "STREET";
    public static final String COL_4 = "EMAIL";
    public static final String COL_5 = "CITY";

    public DatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + TABLE_NAME + " (NAME TEXT, PHONE INTEGER PRIMARY KEY, STREET TEXT, EMAIL TEXT, CITY TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean insertData(String name, String phone, String street, String email, String city){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, phone);
        contentValues.put(COL_3, street);
        contentValues.put(COL_4, email);
        contentValues.put(COL_5, city);
        long result = db.insert(TABLE_NAME, null, contentValues);
        if(result == -1){
            return false;
        }
        else{
            return true;
        }
    }

    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor res = db.rawQuery("select * from "+TABLE_NAME, null);
        return res;
    }

    public boolean updateData(String name, String phone, String street, String email, String city) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(COL_1, name);
        contentValues.put(COL_2, phone);
        contentValues.put(COL_3, street);
        contentValues.put(COL_4, email);
        contentValues.put(COL_5, city);
        db.update(TABLE_NAME, contentValues, "ID = ?", new String[]{phone});
        return true;
    }

    public Integer deleteData(String phone) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "ID = ?", new String[] {phone});
    }
}
