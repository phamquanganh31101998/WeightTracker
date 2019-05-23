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

public class FoodActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.food_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.food_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.food_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.food_nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bmi) {
            Intent i = new Intent(FoodActivity.this, BMIActivity.class);
            startActivity(i);
        } else if (id == R.id.weight) {
            Intent i = new Intent(FoodActivity.this, WeightActivity.class);
            startActivity(i);
        } else if (id == R.id.calo) {
            Intent i = new Intent(FoodActivity.this, CaloActivity.class);
            startActivity(i);
        } else if (id == R.id.diet) {
            Intent i = new Intent(FoodActivity.this, DietActivity.class);
            startActivity(i);
        } else if (id == R.id.food) {
            Intent i = new Intent(FoodActivity.this, FoodActivity.class);
            startActivity(i);
        } else if (id == R.id.author) {
            Intent i = new Intent(FoodActivity.this, AuthorActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.food_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
