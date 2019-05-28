package com.example.weighttracker;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

public class CanxiAdapter extends RecyclerView.Adapter<CanxiAdapter.ViewHolder> {
    private DietActivity context;
    private List<ThucPham> thucPhamList;

    public CanxiAdapter(List<ThucPham> thucPhams, DietActivity context) {
        this.context = context;
        this.thucPhamList = thucPhams;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Nạp layout cho View biểu diễn phần tử canxi
        View canxiView =
                inflater.inflate(R.layout.item_canxi, parent, false);

        ViewHolder viewHolder = new ViewHolder(canxiView);
        return viewHolder;

    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final ThucPham thucPham = thucPhamList.get(position);

        holder.textName.setText(thucPham.getTen());
        final byte[] hinhAnh = thucPham.getHinh();
        final Bitmap bitmap = BitmapFactory.decodeByteArray(hinhAnh,0,hinhAnh.length);
        holder.imageCanxi.setImageBitmap(bitmap);

        holder.imageCanxi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                context.DialogCanxi(thucPham.getId(),thucPham.getTen(), thucPham.getMoTa(), bitmap);
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
        public ImageView imageCanxi;
//        public TextView textDescription;
        public Button detail_button;

        public ViewHolder(View itemView) {
            super(itemView);
            itemview = itemView;
//            textDescription = itemView.findViewById(R.id.txtCanxiDescription);
            textName = itemView.findViewById(R.id.txtCanxi);
            imageCanxi = itemView.findViewById(R.id.imgCanxi);
        }
    }

}
