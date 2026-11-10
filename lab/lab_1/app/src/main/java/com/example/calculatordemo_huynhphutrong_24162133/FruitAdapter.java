package com.example.calculatordemo_huynhphutrong_24162133;

import android.content.Context;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

// Adapter tự viết: nối dữ liệu Fruit với layout item_fruit.xml
public class FruitAdapter extends ArrayAdapter<Fruit> {

    public FruitAdapter(@NonNull Context context, @NonNull List<Fruit> fruits) {
        super(context, 0, fruits);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext())
                    .inflate(R.layout.item_fruit, parent, false);
        }
        Fruit fruit = getItem(position);

        TextView tvEmoji = convertView.findViewById(R.id.tvEmoji);
        TextView tvName = convertView.findViewById(R.id.tvName);
        TextView tvOrigin = convertView.findViewById(R.id.tvOrigin);
        TextView tvPrice = convertView.findViewById(R.id.tvPrice);

        tvEmoji.setText(fruit.emoji);
        tvEmoji.setBackgroundTintList(ColorStateList.valueOf(fruit.color));
        tvName.setText(fruit.name);
        tvOrigin.setText("Xuất xứ: " + fruit.origin);
        tvPrice.setText(fruit.price);
        return convertView;
    }
}
