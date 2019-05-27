package com.example.weighttracker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

public class VitaminAdapter extends RecyclerView.Adapter<VitaminAdapter.ViewHolder> {
    private DietActivity context;
    private List<ThucPham> thucPhamList;

    public VitaminAdapter(List<ThucPham> thucPhams, DietActivity context) {
        this.context = context;
        this.thucPhamList = thucPhams;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử canxi
        View canxiView =
                inflater.inflate(R.layout.item_vitamin, parent, false);

        ViewHolder viewHolder = new ViewHolder(canxiView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ThucPham thucPham = thucPhamList.get(position);

        holder.textName.setText(thucPham.getTen());
        byte[] hinhAnh = thucPham.getHinh();
        Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.imageVitamin.setImageBitmap(bitmap);

        holder.imageVitamin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogProtein(thucPham.getTen(), thucPham.getMoTa());
            }
        });
    }

    @Override
    public int getItemCount() {
        return thucPhamList.size();
    }

    /**
     * Lớp nắm giữ cấu trúc view
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        private View itemview;
        public TextView textName;
        public ImageView imageVitamin;
        //        public TextView textDescription;
        public Button detail_button;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
//            textDescription = itemView.findViewById(R.id.txtCanxiDescription);
            textName = itemView.findViewById(R.id.txtVitamin);
            imageVitamin = itemView.findViewById(R.id.imgVitamin);
        }
    }

}
