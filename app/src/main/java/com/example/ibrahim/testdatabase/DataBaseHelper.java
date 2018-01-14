package com.example.ibrahim.testdatabase;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


public class DataBaseHelper extends SQLiteOpenHelper {

    public final static String TAG = "MaBase";
    public final static String TABLE_NAME = "people_table";
    public final static String COL1 = "ID";
    public final static String COL2 = "name";

    public DataBaseHelper(Context context){
        super(context,TABLE_NAME,null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME+" ("+COL1+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COL2+" TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS "+TABLE_NAME);
        onCreate(db);
    }

    //méthode pour ajouter une nouvelle entrée dans la table de notre base
    public boolean addData(String item){
        SQLiteDatabase mydb = this.getWritableDatabase();
        ContentValues contentvalues = new ContentValues();
        contentvalues.put(COL2,item);

        Log.d(TAG,"addData: Adding "+item+" to "+TABLE_NAME);
        long result = mydb.insert(TABLE_NAME,null,contentvalues);

        //if data as inserted incorrectly it will return -1
        if(result == -1){
            return false;
        }else{
            return true;
        }
    }

    //méthode pour récupérer toutes les entrées de notre base
    public Cursor getData(){
        SQLiteDatabase mydb = this.getWritableDatabase();
        String query = "SELECT * FROM "+TABLE_NAME;
        Cursor data = mydb.rawQuery(query,null);
        return data;
    }
}
