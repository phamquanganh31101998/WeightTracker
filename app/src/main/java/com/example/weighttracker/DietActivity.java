package com.example.weighttracker;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

public class DietActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    RecyclerView recyclerView;
    CanxiAdapter canxiAdapter;
    ProteinAdapter proteinAdapter;
    VitaminAdapter vitaminAdapter;
    TongHopAdapter tongHopAdapter;
    ArrayList<ThucPham> canxiList, proteinList, vitaminList, tonghopList;
    public static DatabaseAccess_HL databaseAccess;
    TextView txtName;
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
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
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
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
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
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
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

    public void listTongHop(){
        tonghopList = new ArrayList<>();
        tongHopAdapter = new TongHopAdapter(tonghopList, this);
        recyclerView = (RecyclerView) findViewById(R.id.reViewTongHop);
//        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setAdapter(tongHopAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
        Cursor dataInfo = databaseAccess.GetData("SELECT * FROM TongHop");

        while (dataInfo.moveToNext()){
            tonghopList.add(new ThucPham(
                    dataInfo.getInt(0),
                    dataInfo.getString(1),
                    dataInfo.getString(2),
                    dataInfo.getBlob(3)
            ));
        }

        tongHopAdapter.notifyDataSetChanged();
    }

    public void DialogCanxi(final int id, final String ten, final String mota, final Bitmap bitmap){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_canxi);

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
//                ByteArrayOutputStream byteArray = new ByteArrayOutputStream();
//                bitmap.compress(Bitmap.CompressFormat.PNG,100,byteArray);
//                byte[] hinhAnh = byteArray.toByteArray();
//                DietActivity.databaseAccess.INSERT_DOAN(
//                        ten,
//                        mota,
//                        hinhAnh
//                );
//
                Toast.makeText(DietActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
//                startActivity(new Intent(DietActivity.this, DietActivity.class));
            }
        });

        dialog.show();
    }

    public void DialogProtein(String ten, String mota){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_canxi);

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
                Toast.makeText(DietActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

    public void DialogVitamin(String ten, String mota){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_canxi);

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
                Toast.makeText(DietActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }

    public void DialogTongHop(String ten, String mota){
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_canxi);

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
                Toast.makeText(DietActivity.this, "Thêm thành công", Toast.LENGTH_SHORT).show();
            }
        });
        dialog.show();
    }
}
