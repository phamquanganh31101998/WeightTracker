package com.example.weighttracker;

import android.app.Dialog;
import android.content.Intent;
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

import java.util.Calendar;

public class WeightActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    DatePicker date_picker;
    TextView weight_dialog, weight_ngay;
    EditText edtWeight;
    Button btnWeight_yes, btnWeight_no;
    AutoCompleteTextView autoTextView;

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

        date_picker = (DatePicker)findViewById(R.id.date_picker_weight);
        setupDatePicker();
    }

    public void setupDatePicker(){
        Calendar calendar = Calendar.getInstance();
        // Lấy ra năm - tháng - ngày hiện tại
        int year = calendar.get(calendar.YEAR);
        final int month = calendar.get(calendar.MONTH);
        int day = calendar.get(calendar.DAY_OF_MONTH);
        // Khởi tạo sự kiện lắng nghe khi DatePicker thay đổi
        date_picker.init(year,month,day,new DatePicker.OnDateChangedListener() {
            @Override
            public void onDateChanged(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                int d = dayOfMonth;
                int m = monthOfYear + 1;
                int y = year;
//                Toast.makeText(WeightActivity.this, "Đã chọn ngày " + d + "/" + m + "/" + y, Toast.LENGTH_SHORT).show();
                DialogNgay(d,m,y);
            }
        });
    }

    public void DialogNgay(int d, int m, int y){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.weight_dialog);
        String date = "Ngày " + d + " Tháng " + m + " Năm " + y;
        weight_ngay = dialog.findViewById(R.id.txt_weight_dialog_ngay);
        btnWeight_yes = dialog.findViewById(R.id.btn_weight_dialog);
        btnWeight_no = dialog.findViewById(R.id.btn_weight_huy);

        weight_ngay.setText(date);
//        Toast.makeText(this, date, Toast.LENGTH_SHORT).show();
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
