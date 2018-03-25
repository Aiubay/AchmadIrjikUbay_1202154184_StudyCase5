package com.example.ubay.achmadirjikubay_1202154184_modul5;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.ArrayAdapter;

import java.util.ArrayList;
import java.util.jar.Attributes;

/**
 * Created by black on 3/25/2018.
 */

public class DbHelper extends SQLiteOpenHelper{

    private static final String DB_NAME ="ToDoDB";
    public static final String TABLE_NAME = "ItemsToDo";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_NAME = "name";
    public static final String COLUMN_DESCRIPTION = "description";
    public static final String COLUMN_PRIORITY = "priority";
    private static final int DB_VERSION=1;
    //create variabel
    public static final String CREATE_DB = "create table "+TABLE_NAME+"(" +COLUMN_ID+" integer primary key, "+COLUMN_NAME+" text, "+COLUMN_DESCRIPTION+" text, "+COLUMN_PRIORITY+" integer)";

    public DbHelper(Context context) {

        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(CREATE_DB);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);

    }
    //method tambah data
    public boolean insertNewItem(String name, String desc, int priority){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME, name);
        values.put(COLUMN_DESCRIPTION, desc);
        values.put(COLUMN_PRIORITY, priority);
        long result = db.insert(TABLE_NAME,null,values);
        if (result==-1){
            return false;
        }else {
            return true;
        }
    }
    //untuk menghapus data
    public void deleteItem(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        String q = "DELETE FROM " + TABLE_NAME + " WHERE " + COLUMN_ID + " = '" + id + "'";
        db.execSQL(q);
    }
    //method get data sqlite
    public Cursor getAllData() {
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery("select * from "+TABLE_NAME,null);
        return c;
    }

}
