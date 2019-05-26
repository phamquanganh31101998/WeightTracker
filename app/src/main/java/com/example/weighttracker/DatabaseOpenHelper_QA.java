package com.example.weighttracker;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper_QA extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "quanganh_database.db";
    private static final int VERSION = 1;
    DatabaseOpenHelper_QA(Context context){
        super(context, DATABASE_NAME, null, VERSION);
    }
}
