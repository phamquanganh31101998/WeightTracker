package com.example.weighttracker;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.LinkedList;

public class FoodAdapter extends ArrayAdapter<Food>{
    private LinkedList<Food> mFood;
    private int resource = R.layout.food_item;
    FoodAdapter(Context context, int resource, LinkedList<Food> mFood){
        super(context, resource, mFood);
        this.mFood = mFood;
        this.resource = resource;
    }

    public View getView(final int i, View view, final ViewGroup viewGroup) {
        if (view==null){
            view = LayoutInflater.from(viewGroup.getContext()).inflate(resource, viewGroup, false);
        }
        TextView food_info = (TextView)view.findViewById(R.id.food_info);
        Button food_info_btn = (Button)view.findViewById(R.id.food_info_btn);
        food_info_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Food f = mFood.get(i);
                AlertDialog.Builder builder= new AlertDialog.Builder(viewGroup.getContext());
                builder.setMessage(mFood.get(i).getName() + " có năng lượng là " + f.getCalo()+ " calo/" + f.getUnit());
//                builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
//                    @Override
//                    public void onClick(DialogInterface dialog, int which) {
//                        DatabaseAccess_QA db = DatabaseAccess_QA.getInstance(viewGroup.getContext());
//                        db.deleteFood(f.getName());
//                        mFood.clear();
//                        mFood.addAll(db.getAllFood());
//                    }
//                });
                builder.setNegativeButton("Quay lại", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                builder.show();
            }
        });
        food_info.setText(mFood.get(i).getName());
        return view;
    }

}
