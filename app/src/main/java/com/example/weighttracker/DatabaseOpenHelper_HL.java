package com.example.weighttracker;

import android.content.Context;

import com.readystatesoftware.sqliteasset.SQLiteAssetHelper;

public class DatabaseOpenHelper_HL extends SQLiteAssetHelper {
    private static final String DATABASE_NAME = "ThucDonDinhDuong.db";
    private static final int DATABASE_VERSION = 1;

    public DatabaseOpenHelper_HL(Context context){
        super(context,DATABASE_NAME,null,DATABASE_VERSION);
    }
}
