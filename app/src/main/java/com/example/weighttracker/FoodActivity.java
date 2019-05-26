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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class FoodActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private LinkedList<Food> mFood;
    private ListView food_list;
    EditText input;
    Button find;
    TextView result;
    ImageButton add_food_btn;
    FoodAdapter adapter;
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


        //code here
        food_list = (ListView)findViewById(R.id.food_list);
        input = (EditText)findViewById(R.id.input);
        find = (Button)findViewById(R.id.find);
        result = (TextView)findViewById(R.id.result);
        mFood = new LinkedList<>();
        adapter = new FoodAdapter(this, R.layout.food_item, mFood);
        food_list.setAdapter(adapter);

//        add_food_btn = (ImageButton)findViewById(R.id.add_food_btn);
//        add_food_btn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                View viewdialog = LayoutInflater.from(FoodActivity.this).inflate(R.layout.food_add, null, false);
//                AlertDialog.Builder alertDialog = new AlertDialog.Builder(FoodActivity.this);
//                alertDialog.setView(viewdialog);
//                final EditText new_food_name = (EditText)viewdialog.findViewById(R.id.new_food_name);
//                final EditText new_food_unit = (EditText)viewdialog.findViewById(R.id.new_food_unit);
//                final EditText new_food_calo = (EditText)viewdialog.findViewById(R.id.new_food_calo);
//                alertDialog.setPositiveButton("Add", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        DatabaseAccess_QA db = DatabaseAccess_QA.getInstance(getApplicationContext());
//                        db.addNewFood(new_food_name.getText().toString(), new_food_unit.getText().toString(), new_food_calo.getText().toString());
//                        Toast.makeText(FoodActivity.this, "Add new food successful", Toast.LENGTH_LONG).show();
//                        dialogInterface.dismiss();
//                    }
//                }).setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialogInterface, int i) {
//                        dialogInterface.dismiss();
//                    }
//                }).show();
//            }
//        });
        find.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(input.getText().toString().length()>0){
                    DatabaseAccess_QA db = DatabaseAccess_QA.getInstance(getApplicationContext());
//                    String r = db.findFood(input.getText().toString());
//                    result.setText(r);
                    mFood.clear();
                    mFood.addAll(db.searchFood(input.getText().toString()));
                    adapter.notifyDataSetChanged();
                }
                else{
                    DatabaseAccess_QA db = DatabaseAccess_QA.getInstance(getApplicationContext());
                    mFood.clear();
                    mFood.addAll(db.getAllFood());
                    adapter.notifyDataSetChanged();
                }
            }
        });
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
            Intent i = new Intent(FoodActivity.this, CaloTrackerActivity.class);
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
        } else if (id == R.id.calo_calculate){
            Intent i = new Intent(FoodActivity.this, CaloCalculateActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.food_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
