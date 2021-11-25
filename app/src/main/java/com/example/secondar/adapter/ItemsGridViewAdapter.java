package com.example.secondar.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;

import com.example.secondar.Common;
import com.example.secondar.MainActivity;
import com.example.secondar.R;
import com.example.secondar.RecyclerviewAdapter;
import com.example.secondar.expert.FurnitureExpert;
import com.example.secondar.model.Furniture;

public class ItemsGridViewAdapter extends  BaseAdapter {

        private final Context c;
        private LayoutInflater inflater;
        private final FurnitureExpert furnitureExpert;

        public ItemsGridViewAdapter(Context c)
        {
            this.c = c;
            furnitureExpert = FurnitureExpert.getInstance();
        }

        @Override
        public int getCount() {
            return furnitureExpert.totalFurnitures();
        }

        @Override
        public Object getItem(int position) {
            return furnitureExpert.getFurniture(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }


        @Override
        public View getView(int position, View view, ViewGroup viewGroup) {
            if (inflater == null) {
                inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            }

            if (view == null) {
                view = inflater.inflate(R.layout.item, null);
            }
            Furniture furniture = furnitureExpert.getFurniture(position);
            ImageView eventImage = view.findViewById(R.id.item_image_gridView);
            TextView eventName = view.findViewById(R.id.item_name_gridView);
            TextView eventPrice = view.findViewById(R.id.item_price_gridView);

            eventImage.setImageResource(furniture.getImagePath());
            eventName.setText(furniture.getName());
            eventPrice.setText("Rs " + furniture.getPrice());
            return view;
        }

    public static class ViewHolder {

        ImageView imageView;
        TextView textView;

        public ViewHolder(View itemView) {

            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.text);
        }
    }
    }
    