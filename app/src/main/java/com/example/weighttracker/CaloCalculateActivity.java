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
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class CaloCalculateActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    EditText quantity;
    TextView calories, Food_cal, total_calo;
    Button calculate,calthis;
    Button add;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    int total = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calo_calculate_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.calo_calculate_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.calo_calculate_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.calo_calculate_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //code here
        quantity = (EditText) findViewById(R.id.etquantity);
        //calories = (TextView) findViewById(R.id.calories);
        Food_cal = (TextView) findViewById(R.id.Food_cal);
        total_calo = (TextView) findViewById(R.id.total_calo);
        add = (Button) findViewById(R.id.add);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity.getText().toString().length() > 0){
                    String label = spinner.getSelectedItem().toString();
                    DatabaseAccess_QA db = DatabaseAccess_QA.getInstance(getApplicationContext());
                    Food f = db.getFoodByName(label);
                    int calo_new = f.getCalo() * Integer.parseInt(quantity.getText().toString());
                    total = total + calo_new;
                    Toast.makeText(getApplicationContext(),"Đã thêm " + quantity.getText().toString() + " "
                            + f.getUnit() + " " + f.getName(),Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(CaloCalculateActivity.this, "Hãy nhập số lượng",
                            Toast.LENGTH_LONG).show();
                }
            }
        });

        calthis =(Button) findViewById(R.id.calthis);
        calthis.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(quantity.getText().toString().length() > 0){
                    String label = spinner.getSelectedItem().toString();
                    DatabaseAccess_QA db = DatabaseAccess_QA.getInstance(getApplicationContext());
                    Food f = db.getFoodByName(label);

//                    Toast.makeText(CaloCalculateActivity.this, "Bạn đã ăn "+ quantity.getText().toString()
//                                    + " "+ f.getUnit() + " "+ f.getName(),
//                            Toast.LENGTH_LONG).show();
                    int caloOfThis = f.getCalo() * Integer.parseInt(quantity.getText().toString());
                    Food_cal.setText(quantity.getText().toString() + " " + f.getUnit() + " " + f.getName()
                            + " có năng lượng: " + (new Integer(caloOfThis)).toString() + " calo");
                } else {
                    Toast.makeText(CaloCalculateActivity.this, "Hãy nhập số lượng",
                            Toast.LENGTH_LONG).show();
                }
            }
        });
        calculate = (Button) findViewById(R.id.calculate);
        calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                total_calo.setText("Tổng năng lượng: " + total + " calo");
            }
        });
        spinner = (Spinner) findViewById(R.id.spinner);
        loadSpinnerData();
        spinner.setOnItemSelectedListener(new Spinner.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String label = parent.getItemAtPosition(position).toString();
                Toast.makeText(parent.getContext(), "Bạn đã chọn: " + label,
                        Toast.LENGTH_LONG).show();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
    }

    private void loadSpinnerData(){
        DatabaseAccess_QA db = DatabaseAccess_QA.getInstance(getApplicationContext());
        List<String> labels = db.getAllLabels();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, labels);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bmi) {
            Intent i = new Intent(CaloCalculateActivity.this, BMIActivity.class);
            startActivity(i);
        } else if (id == R.id.weight) {
            Intent i = new Intent(CaloCalculateActivity.this, WeightActivity.class);
            startActivity(i);
        } else if (id == R.id.calo) {
            Intent i = new Intent(CaloCalculateActivity.this, CaloTrackerActivity.class);
            startActivity(i);
        } else if (id == R.id.diet) {
            Intent i = new Intent(CaloCalculateActivity.this, DietActivity.class);
            startActivity(i);
        } else if (id == R.id.food) {
            Intent i = new Intent(CaloCalculateActivity.this, FoodActivity.class);
            startActivity(i);
        } else if (id == R.id.author) {
            Intent i = new Intent(CaloCalculateActivity.this, AuthorActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.calo_calculate_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
