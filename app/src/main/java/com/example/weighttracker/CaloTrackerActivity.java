package com.example.weighttracker;

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
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class CaloTrackerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView current_calo, note, average_calo, min_calo, max_calo;
    Button edit;
    DatePicker date_picker;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calo_tracker_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.calo_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.calo_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.calo_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //code here
        current_calo = (TextView)findViewById(R.id.current_calo);
        note = (TextView)findViewById(R.id.note);
        average_calo = (TextView)findViewById(R.id.average_calo);
        min_calo = (TextView)findViewById(R.id.min_calo);
        max_calo = (TextView)findViewById(R.id.max_calo);
        date_picker = (DatePicker)findViewById(R.id.date_picker);
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
                Toast.makeText(CaloTrackerActivity.this, "Đã chọn ngày " + d + "/" + m + "/" + y, Toast.LENGTH_SHORT).show();
            }
        });
    }



    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bmi) {
            Intent i = new Intent(CaloTrackerActivity.this, BMIActivity.class);
            startActivity(i);
        } else if (id == R.id.weight) {
            Intent i = new Intent(CaloTrackerActivity.this, WeightActivity.class);
            startActivity(i);
        } else if (id == R.id.calo) {
            Intent i = new Intent(CaloTrackerActivity.this, CaloTrackerActivity.class);
            startActivity(i);
        } else if (id == R.id.diet) {
            Intent i = new Intent(CaloTrackerActivity.this, DietActivity.class);
            startActivity(i);
        } else if (id == R.id.food) {
            Intent i = new Intent(CaloTrackerActivity.this, FoodActivity.class);
            startActivity(i);
        } else if (id == R.id.author) {
            Intent i = new Intent(CaloTrackerActivity.this, AuthorActivity.class);
            startActivity(i);
        } else if (id == R.id.calo_calculate){
            Intent i = new Intent(CaloTrackerActivity.this, CaloCalculateActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.calo_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
