package com.example.weighttracker;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class WeightActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DatePicker date_picker;
    TextView weight_dialog, weight_ngay, txt_weight_ngaycu_weight;
    EditText edtWeight;
    Button btnWeight_yes, btnWeight_no;
    AutoCompleteTextView autoTextView;
    ArrayList<CanNang> canNangList;
    DatabaseWeight databaseWeight;
    String getCanNang;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.weight_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.weight_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.weight_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.weight_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        databaseWeight = new DatabaseWeight(this, "ghichu.sqlite",null,1);
        databaseWeight.QueryData("CREATE TABLE IF NOT EXISTS Weight_DB_Update(Id INTEGER PRIMARY KEY AUTOINCREMENT, CanNang NVARCHAR(20), Ngay NVACHAR(20))");

        databaseWeight.QueryData("INSERT INTO Weight_DB_Update VALUES(null, '52', '25\\5\\2019')");
        databaseWeight.QueryData("INSERT INTO Weight_DB_Update VALUES(null, '52', '25\\5\\2019')");
        databaseWeight.QueryData("INSERT INTO Weight_DB_Update VALUES(null, '52', '25\\5\\2019')");

        date_picker = (DatePicker)findViewById(R.id.date_picker_weight);
        setupDatePicker();
    }

    public void setupDatePicker(){
        Calendar calendar = Calendar.getInstance();
        // Lấy ra năm - tháng - ngày hiện tại
        final int year_real = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        final int day = calendar.get(calendar.DAY_OF_MONTH);
        // Khởi tạo sự kiện lắng nghe khi DatePicker thay đổi
        date_picker.init(year_real,month,day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int d = dayOfMonth;
                int m = monthOfYear + 1;
                int y = year;

                if(year > year_real){
                    Toast.makeText(WeightActivity.this, "Bạn không được nhập ngày ở tương lai", Toast.LENGTH_SHORT).show();
                }
                else if(year_real == year){
                    if(monthOfYear > month){
                        Toast.makeText(WeightActivity.this, "Bạn không được nhập ngày ở tương lai", Toast.LENGTH_SHORT).show();
                    }
                    else if(month == monthOfYear){
                        if(dayOfMonth > day){
                            Toast.makeText(WeightActivity.this, "Bạn không được nhập ngày ở tương lai", Toast.LENGTH_SHORT).show();
                        }
                        else if(dayOfMonth == day){
                            DialogNgay(d,m,y);
                        }
                        else {
                            DiaLogNgayCu(d,m,y);
                        }
                    }
                    else {
                        DiaLogNgayCu(d,m,y);
                    }
                }
                else {
                    DiaLogNgayCu(d,m,y);
                }
//                DialogNgay(d,m,y);
            }
        });
    }

    public void DiaLogNgayCu(int d, int m, int y){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.weight_dialog_cu);
        String date = d + "\\" + m + "\\" + y;
        String ngay = date.trim();
        txt_weight_ngaycu_weight = dialog.findViewById(R.id.txt_weight_cannang_cu);
        Cursor dataCanNang = databaseWeight.GetData("SELECT * FROM Weight_DB_Update WHERE Ngay = '"+ ngay +"'");
        while (dataCanNang.moveToNext()){
            getCanNang = dataCanNang.getString(1) + " kg";
        }
//        txt_weight_ngaycu.setText(dataCanNang.getString(1));
        txt_weight_ngaycu_weight.setText(getCanNang);

        dialog.show();
    }

    public void DialogNgay(final int d,final int m,final int y){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.weight_dialog);
        String date = "Ngày " + d + " Tháng " + m + " Năm " + y;

        weight_ngay = dialog.findViewById(R.id.txt_weight_dialog_ngay);
        btnWeight_yes = dialog.findViewById(R.id.btn_weight_dialog);
        btnWeight_no = dialog.findViewById(R.id.btn_weight_huy);
        autoTextView = dialog.findViewById(R.id.auto_textview_weight);


        weight_ngay.setText(date);

        btnWeight_yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String strcannang = autoTextView.getText().toString();
                String ngay = "" + d + "\\" + m + "\\" + y;
                databaseWeight.QueryData("INSERT INTO Weight_DB_Update VALUES(null, '"+ strcannang +"', '"+ ngay +"')");
                Toast.makeText(WeightActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        
        btnWeight_no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });
        dialog.show();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bmi) {
            Intent i = new Intent(WeightActivity.this, BMIActivity.class);
            startActivity(i);
        } else if (id == R.id.weight) {
            Intent i = new Intent(WeightActivity.this, WeightActivity.class);
            startActivity(i);
        } else if (id == R.id.calo) {
            Intent i = new Intent(WeightActivity.this, CaloTrackerActivity.class);
            startActivity(i);
        } else if (id == R.id.diet) {
            Intent i = new Intent(WeightActivity.this, DietActivity.class);
            startActivity(i);
        } else if (id == R.id.food) {
            Intent i = new Intent(WeightActivity.this, FoodActivity.class);
            startActivity(i);
        } else if (id == R.id.author) {
            Intent i = new Intent(WeightActivity.this, AuthorActivity.class);
            startActivity(i);
        } else if (id == R.id.calo_calculate){
            Intent i = new Intent(WeightActivity.this, CaloCalculateActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.weight_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
