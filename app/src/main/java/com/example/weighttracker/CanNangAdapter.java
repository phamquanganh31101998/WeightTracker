package com.example.weighttracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class CanNangAdapter extends BaseAdapter {
    private Context conText;
    private int layout;
    private List<CanNang> canNangList;

    public CanNangAdapter(Context conText, int layout, List<CanNang> traiCayList) {
        this.conText = conText;
        this.layout = layout;
        this.canNangList = traiCayList;
    }

    @Override
    public int getCount() {
        return canNangList.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{
        TextView txtNgay, txtCanNang;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;

        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater) conText.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(layout,null);
            holder = new ViewHolder();

            holder.txtCanNang = (TextView) convertView.findViewById(R.id.txt_weight_cannang_cu);
            convertView.setTag(holder);
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        CanNang canNang = canNangList.get(position);

        holder.txtNgay.setText(canNang.getNgay());
        holder.txtCanNang.setText(canNang.getCannang());

        return convertView;
    }
}
