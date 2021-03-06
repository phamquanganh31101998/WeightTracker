package com.example.weighttracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageButton;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    ImageButton bmi, calo_calculate, weight, calo, diet, food;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        //code here
        bmi = (ImageButton)findViewById(R.id.home_bmi);
        bmi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, BMIActivity.class);
                startActivity(i);
            }
        });
        calo_calculate = (ImageButton)findViewById(R.id.home_calo_calculate);
        calo_calculate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CaloCalculateActivity.class);
                startActivity(i);
            }
        });
        weight = (ImageButton)findViewById(R.id.home_weight);
        weight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, WeightActivity.class);
                startActivity(i);
            }
        });
        calo = (ImageButton)findViewById(R.id.home_calo);
        calo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, CaloTrackerActivity.class);
                startActivity(i);
            }
        });
        diet = (ImageButton)findViewById(R.id.home_diet);
        diet.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, DietActivity.class);
                startActivity(i);
            }
        });
        food = (ImageButton)findViewById(R.id.home_food);
        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(i);
            }
        });
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.main, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//
//        //noinspection SimplifiableIfStatement
//        if (id == R.id.action_settings) {
//            return true;
//        }
//
//        return super.onOptionsItemSelected(item);
//    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bmi) {
            Intent i = new Intent(MainActivity.this, BMIActivity.class);
            startActivity(i);
        } else if (id == R.id.weight) {
            Intent i = new Intent(MainActivity.this, WeightActivity.class);
            startActivity(i);
        } else if (id == R.id.calo) {
            Intent i = new Intent(MainActivity.this, CaloTrackerActivity.class);
            startActivity(i);
        } else if (id == R.id.diet) {
            Intent i = new Intent(MainActivity.this, DietActivity.class);
            startActivity(i);
        } else if (id == R.id.food) {
            Intent i = new Intent(MainActivity.this, FoodActivity.class);
            startActivity(i);
        } else if (id == R.id.author) {
            Intent i = new Intent(MainActivity.this, AuthorActivity.class);
            startActivity(i);
        } else if (id == R.id.calo_calculate){
            Intent i = new Intent(MainActivity.this, CaloCalculateActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
}
