package com.example.secondar;

import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class RecyclerviewAdapter extends RecyclerView.Adapter<RecyclerviewAdapter.ViewHolder>{

    private ArrayList<String> textNames;
    private ArrayList<Integer> imagesPath;
    private Context context;
    private ArrayList<String> modelNames;


    public RecyclerviewAdapter(Context context,ArrayList<String> textNames, ArrayList<Integer> imagesPath,ArrayList<String> modelNames) {
        this.textNames = textNames;
        this.imagesPath = imagesPath;
        this.modelNames = modelNames;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_list_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(imagesPath.get(position));
        holder.textView.setText(textNames.get(position));

        holder.imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Common.model = modelNames.get(position);
                Intent intent = new Intent(context, MainActivity.class);
                context.startActivity(intent);
                intent.putExtra("number", modelNames.get(position));
            }
        });
    }

    @Override
    public int getItemCount() {
        return imagesPath.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder{

        ImageView imageView;
        TextView textView;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.imageview);
            textView = itemView.findViewById(R.id.text);
        }
    }
}
