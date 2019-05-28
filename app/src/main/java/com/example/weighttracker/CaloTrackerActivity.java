package com.example.weighttracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Calendar;

public class CaloTrackerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    TextView current_calo, note, average_calo, min_calo, max_calo, sum_calo;
    Button edit;
    DatePicker date_picker;
    static String date = "";
    static CaloTracker tracker, zeroTracker;
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
        zeroTracker = new CaloTracker("", 0, "");
        current_calo = (TextView)findViewById(R.id.calo_tracker_current_calo);
        note = (TextView)findViewById(R.id.calo_tracker_note);
        average_calo = (TextView)findViewById(R.id.calo_tracker_average_calo);
        min_calo = (TextView)findViewById(R.id.calo_tracker_min_calo);
        max_calo = (TextView)findViewById(R.id.calo_tracker_max_calo);
        sum_calo = (TextView)findViewById(R.id.calo_tracker_sum);
        date_picker = (DatePicker)findViewById(R.id.calo_tracker_date_picker);
        edit = (Button)findViewById(R.id.calo_tracker_edit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final DatabaseAccess_QA db = DatabaseAccess_QA.getInstance(getApplicationContext());

                if(date!=""){
                    tracker = db.getCaloTrackerByDate(date);
                    //Chưa có ngày thì tạo mới
                    if(tracker.equals(zeroTracker)){
//                        Toast.makeText(getApplicationContext(), "Chưa lưu nhật ký về ngày này", Toast.LENGTH_LONG).show();
                        View viewdialog = LayoutInflater.from(CaloTrackerActivity.this).inflate(R.layout.calo_tracker_edit, null, false);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CaloTrackerActivity.this);
                        alertDialog.setView(viewdialog);
                        final EditText calo_tracker_new_calo = (EditText)viewdialog.findViewById(R.id.calo_tracker_new_calo);
                        final EditText calo_tracker_new_note = (EditText)viewdialog.findViewById(R.id.calo_tracker_new_note);
                        TextView calo_tracker_date = (TextView)viewdialog.findViewById(R.id.calo_tracker_date);
                        calo_tracker_date.setText(date);
                        alertDialog.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                int new_calo_value;
                                String new_calo = calo_tracker_new_calo.getText().toString();
                                String new_note = calo_tracker_new_note.getText().toString();
                                if (new_calo.length()==0){
                                    new_calo_value = 0;
                                }
                                else{
                                    new_calo_value = Integer.valueOf(new_calo);
                                }
                                if(new_note.length()==0){
                                    new_note = "";
                                }
                                db.addNewCaloTracker(date, new_calo_value, new_note);
                                findSumCalo();
                                findMaxCalo();
                                findMinCalo();
                                findAverageCalo();
                            }
                        }).setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                    }
                    //Đã có ngày thì chỉnh sửa, nếu cả 2 cùng có giá trị 0 thì xóa khỏi DB
                    else{
                        View viewdialog = LayoutInflater.from(CaloTrackerActivity.this).inflate(R.layout.calo_tracker_edit, null, false);
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(CaloTrackerActivity.this);
                        alertDialog.setView(viewdialog);
                        final EditText calo_tracker_new_calo = (EditText)viewdialog.findViewById(R.id.calo_tracker_new_calo);
                        calo_tracker_new_calo.setText(String.valueOf(tracker.getCalo()));
                        final EditText calo_tracker_new_note = (EditText)viewdialog.findViewById(R.id.calo_tracker_new_note);
                        calo_tracker_new_note.setText(tracker.getNote());
                        TextView calo_tracker_date = (TextView)viewdialog.findViewById(R.id.calo_tracker_date);
                        calo_tracker_date.setText(date);
                        alertDialog.setPositiveButton("Lưu", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String new_calo = calo_tracker_new_calo.getText().toString();
                                int new_calo_value;
                                String new_note = calo_tracker_new_note.getText().toString();
                                //trống cả 2 tham số thì xóa
                                if((new_calo.length()==0)&&(new_note.length()==0)){
                                    db.deleteCaloTracker(date);
                                } else if ((new_calo.length()>0)&&(new_note.length()==0)){
                                    new_calo_value = Integer.valueOf(new_calo);
                                    new_note = "";
                                    db.updateCaloTracker(date, new_calo_value, new_note);
                                } else if ((new_calo.length()==0)&&(new_note.length()>0)){
                                    new_calo_value = 0;
                                    db.updateCaloTracker(date, new_calo_value, new_note);
                                }
                                else{
                                    new_calo_value = Integer.valueOf(new_calo);
                                    db.updateCaloTracker(date, new_calo_value, new_note);
                                }
                                findSumCalo();
                                findMaxCalo();
                                findMinCalo();
                                findAverageCalo();
                            }
                        }).setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).show();
                    }
//
                }
                else
                    Toast.makeText(getApplicationContext(), "Hãy chọn ngày", Toast.LENGTH_LONG).show();
            }
        });
        setupDatePicker();
    }
    public void findMinCalo(){
        DatabaseAccess_QA db = DatabaseAccess_QA.getInstance(getApplicationContext());
        Integer minCalo = new Integer(db.findMinCalo());
        min_calo.setText("Lượng calo tiêu thụ thấp nhất: " + minCalo.toString());
    }
    public void findMaxCalo(){
        DatabaseAccess_QA db = DatabaseAccess_QA.getInstance(getApplicationContext());
        Integer maxCalo = new Integer(db.findMaxCalo());
        max_calo.setText("Lượng calo tiêu thụ cao nhất: " + maxCalo.toString());
    }
    public void findSumCalo(){
        DatabaseAccess_QA db = DatabaseAccess_QA.getInstance(getApplicationContext());
        Integer sumCalo = new Integer(db.findSumCalo());
        sum_calo.setText("Tổng lượng calo đã tiêu thụ: " + sumCalo.toString());
    }
    public void findAverageCalo(){
        DatabaseAccess_QA db = DatabaseAccess_QA.getInstance(getApplicationContext());
        Integer averageCalo = new Integer(db.findAverageCalo());
        average_calo.setText("Lượng calo tiêu thụ trung bình: " + averageCalo.toString());
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
                date = d + "/" + m + "/" + y;
                DatabaseAccess_QA db = DatabaseAccess_QA.getInstance(getApplicationContext());
                tracker = db.getCaloTrackerByDate(date);
                current_calo.setText("Lượng Calo tiêu thụ trong ngày: " + tracker.getCalo());
                note.setText("Ghi chú: " + tracker.getNote());
                findSumCalo();
                findMinCalo();
                findMaxCalo();
                findAverageCalo();
                if (tracker.equals(zeroTracker)){
                    Toast.makeText(getApplicationContext(), "Chưa có nhật ký ở ngày này", Toast.LENGTH_LONG).show();
                }
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
