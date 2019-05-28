package com.example.weighttracker;

import android.app.Dialog;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class DietActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    CanxiAdapter canxiAdapter;
    ProteinAdapter proteinAdapter;
    VitaminAdapter vitaminAdapter;
    ArrayList<ThucPham> canxiList, proteinList, vitaminList;
    public static DatabaseAccess_HL databaseAccess;
    TextView txtName, txtTongHop1, txtTongHop2, txtTongHop3;
    ImageView imgTongHop1, imgTongHop2, imgTongHop3;
    TextView txtMoTa;
    Button btnHuy, btnThem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.diet_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.diet_toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.diet_drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.diet_nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        databaseAccess = DatabaseAccess_HL.getInstance(getApplicationContext());
        databaseAccess.open();

        listCanxi();
        listProtein();
        listCanxi();
        listVitamin();
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.bmi) {
            Intent i = new Intent(DietActivity.this, BMIActivity.class);
            startActivity(i);
        } else if (id == R.id.weight) {
            Intent i = new Intent(DietActivity.this, WeightActivity.class);
            startActivity(i);
        } else if (id == R.id.calo) {
            Intent i = new Intent(DietActivity.this, CaloTrackerActivity.class);
            startActivity(i);
        } else if (id == R.id.diet) {
            Intent i = new Intent(DietActivity.this, DietActivity.class);
            startActivity(i);
        } else if (id == R.id.food) {
            Intent i = new Intent(DietActivity.this, FoodActivity.class);
            startActivity(i);
        } else if (id == R.id.author) {
            Intent i = new Intent(DietActivity.this, AuthorActivity.class);
            startActivity(i);
        } else if (id == R.id.calo_calculate){
            Intent i = new Intent(DietActivity.this, CaloCalculateActivity.class);
            startActivity(i);
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.diet_drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    public void listCanxi(){
        canxiList = new ArrayList<>();
        canxiAdapter = new CanxiAdapter(canxiList, this);
        recyclerView = (RecyclerView) findViewById(R.id.reViewCanxi);
        recyclerView.setAdapter(canxiAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Cursor dataInfo = databaseAccess.GetData("SELECT * FROM Canxi");

        while (dataInfo.moveToNext()){
            canxiList.add(new ThucPham(
                    dataInfo.getInt(0),
                    dataInfo.getString(1),
                    dataInfo.getString(2),
                    dataInfo.getBlob(3)
            ));
        }

        canxiAdapter.notifyDataSetChanged();
    }

    public void listProtein(){
        proteinList = new ArrayList<>();
        proteinAdapter = new ProteinAdapter(proteinList, this);
        recyclerView = (RecyclerView) findViewById(R.id.reViewProtein);
        recyclerView.setAdapter(proteinAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Cursor dataInfo = databaseAccess.GetData("SELECT * FROM Protein");

        while (dataInfo.moveToNext()){
            proteinList.add(new ThucPham(
                    dataInfo.getInt(0),
                    dataInfo.getString(1),
                    dataInfo.getString(2),
                    dataInfo.getBlob(3)
            ));
        }

        proteinAdapter.notifyDataSetChanged();
    }

    public void listVitamin(){
        vitaminList = new ArrayList<>();
        vitaminAdapter = new VitaminAdapter(vitaminList, this);
        recyclerView = (RecyclerView) findViewById(R.id.reViewVitamin);
        recyclerView.setAdapter(vitaminAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Cursor dataInfo = databaseAccess.GetData("SELECT * FROM Vitamin");

        while (dataInfo.moveToNext()){
            vitaminList.add(new ThucPham(
                    dataInfo.getInt(0),
                    dataInfo.getString(1),
                    dataInfo.getString(2),
                    dataInfo.getBlob(3)
            ));
        }

        vitaminAdapter.notifyDataSetChanged();
    }

    public void DialogCanxi(final int id, final String ten, final String mota, final Bitmap bitmap){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thucan);

        txtName = dialog.findViewById(R.id.dialog_name_canxi);
        txtMoTa = dialog.findViewById(R.id.dialog_mota_canxi);
        btnHuy = dialog.findViewById(R.id.btnDong);
        btnThem = dialog.findViewById(R.id.btnThem);

        txtName.setText(ten);
        txtMoTa.setText(mota);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgTongHop1 = findViewById(R.id.imgViewTongHop1);
                txtTongHop1 = findViewById(R.id.txtTongHop1);
                txtTongHop1.setText(ten);
                imgTongHop1.setImageBitmap(bitmap);
                Toast.makeText(DietActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void DialogProtein(final int id, final String ten, final String mota, final Bitmap bitmap){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thucan);

        txtName = dialog.findViewById(R.id.dialog_name_canxi);
        txtMoTa = dialog.findViewById(R.id.dialog_mota_canxi);
        btnHuy = dialog.findViewById(R.id.btnDong);
        btnThem = dialog.findViewById(R.id.btnThem);

        txtName.setText(ten);
        txtMoTa.setText(mota);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgTongHop2 = findViewById(R.id.imgViewTongHop2);
                txtTongHop2 = findViewById(R.id.txtTongHop2);
                txtTongHop2.setText(ten);
                imgTongHop2.setImageBitmap(bitmap);
                Toast.makeText(DietActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });

        dialog.show();
    }

    public void DialogVitamin(final int id, final String ten, final String mota, final Bitmap bitmap){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_thucan);

        txtName = dialog.findViewById(R.id.dialog_name_canxi);
        txtMoTa = dialog.findViewById(R.id.dialog_mota_canxi);
        btnHuy = dialog.findViewById(R.id.btnDong);
        btnThem = dialog.findViewById(R.id.btnThem);

        txtName.setText(ten);
        txtMoTa.setText(mota);

        btnHuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imgTongHop3 = findViewById(R.id.imgViewTongHop3);
                txtTongHop3 = findViewById(R.id.txtTongHop3);
                txtTongHop3.setText(ten);
                imgTongHop3.setImageBitmap(bitmap);
                Toast.makeText(DietActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
                dialog.dismiss();
            }
        });
        dialog.show();
    }
}
