package com.example.weighttracker;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseAccess_HL {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess_HL instance;
    Cursor c = null;

    //private constructor so that object creation from outside the class is avoided
    private DatabaseAccess_HL(Context context){
        this.openHelper = new DatabaseOpenHelper_HL(context);

    }

    //to return the single instance of database
    public static DatabaseAccess_HL getInstance(Context context){
        if(instance == null){
            instance = new DatabaseAccess_HL(context);
        }

        return  instance;
    }

    //to open the database
    public void open(){
        this.db = openHelper.getWritableDatabase();
    }

    //closing the database connection
    public void close(){
        if(db != null){
            this.db.close();
        }
    }

//    public void INSERT_DOAN(String ten, String mota, byte[] hinh){
//        String sql = "INSERT INTO Tonghop VALUES(null, ?, ?, ?)";
//        SQLiteStatement statement = db.compileStatement(sql);
//        statement.clearBindings();
//        statement.bindString(1,ten);
//        statement.bindString(2,mota);
//        statement.bindBlob(3,hinh);
//        statement.executeInsert();
//    }

    public  void  QueryData(String sql){
        db.execSQL(sql);
    }

    public Cursor GetData(String sql){
        return db.rawQuery(sql,null);
    }
}
