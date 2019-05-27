package com.example.weighttracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DatabaseAccess_QA {
    private SQLiteOpenHelper openHelper;
    private SQLiteDatabase db;
    private static DatabaseAccess_QA instance;
    Cursor c = null;

    //private constructor so that object creation from outside the class is avoided
    private DatabaseAccess_QA(Context context) {
        this.openHelper = new DatabaseOpenHelper_QA(context);
    }

    //to return the single instance of database
    public static DatabaseAccess_QA getInstance(Context context) {
        if (instance == null) {
            instance = new DatabaseAccess_QA(context);
        }
        return instance;
    }

    //to open database
    public void open() {
        this.db = openHelper.getWritableDatabase();
    }

    //closing the database
    public void close() {
        if (db != null) {
            db.close();
        }
    }
//
//    public String findFood(String food){
//        StringBuffer result = new StringBuffer();
//        c = db.rawQuery("SELECT * FROM food_table where name LIKE '%" + food + "%'", new String[]{});
//        int sum = 0;
//        if(c==null){
//            result.append("Không có món ăn này");
//        }
//        else{
//            while(c.moveToNext()){
//                String f = c.getString(1) + " có lượng calo là " +c.getString(3) +"/" + c.getString(2) + "\n";
//                result.append(f);
//                sum += Integer.parseInt(c.getString(3));
//            }
//        }
//        result.append("Tổng lượng Calo bạn đã tìm là: " + sum);
//        return result.toString();
//    }

    public void addNewFood(String name, String unit, int calo){
        open();
        ContentValues cv = new ContentValues();
        cv.put("name", name);
        cv.put("unit", unit);
        cv.put("calo", calo);
        db.insert("food_table", null, cv);
        close();
    }

    //Lấy dữ liệu từ Cursor set vào đối tượng Food (chuyển từ Cursor sang Food)

    public Food set(Cursor c){
        String name = c.getString(1);
        String unit = c.getString(2);
        int calo = c.getInt(3);
        Food obj = new Food(name, unit, calo);
        return obj;
    }

    public LinkedList<Food> searchFood(String food){
        open();
        LinkedList<Food> ls = new LinkedList<>();
        Cursor c = db.rawQuery("SELECT * FROM food_table where name LIKE '%" + food + "%' ORDER BY name", new String[]{});
        c.moveToFirst();
        while(!c.isAfterLast()){
            Food obj = set(c);
            ls.add(obj);
            c.moveToNext();
        }
        close();
        return ls;
    }

    public LinkedList<Food> getAllFood(){
        open();
        LinkedList<Food> ls = new LinkedList<>();
        Cursor c = db.rawQuery("SELECT * FROM food_table ORDER BY name", new String[]{});
        c.moveToFirst();
        while(!c.isAfterLast()){
            Food obj = set(c);
            ls.add(obj);
            c.moveToNext();
        }
        close();
        return ls;
    }

    public void deleteFood(String name){
        open();
        db.delete("food_table", "name = ?", new String[]{name});
        close();
    }

    public List<String> getAllLabels(){
        open();
        List<String> labels = new ArrayList<String>();

        //Select All Query
        Cursor c = db.rawQuery("SELECT * FROM food_table ORDER BY name", new String[]{});
        c.moveToFirst();
        while(!c.isAfterLast()){
            labels.add(c.getString(1));
            c.moveToNext();
        }
        close();
        return labels;
    }

    public Food getFoodByName(String name){
        open();
        Cursor c = db.rawQuery("SELECT * FROM food_table WHERE name = ?", new String[]{name});
        c.moveToFirst();
        Food food = set(c);
        close();
        return food;
    }
}
