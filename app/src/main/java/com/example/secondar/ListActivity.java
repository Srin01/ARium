package com.example.secondar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.GridView;

import com.example.secondar.adapter.ItemsGridViewAdapter;
import com.example.secondar.expert.FurnitureExpert;
import com.google.ar.sceneform.AnchorNode;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    FurnitureExpert furnitureExpert;
    GridView eventsGridView;
    Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getImages();
    }

    private void getImages() {
        toolbar = findViewById(R.id.toolbar);
        eventsGridView = findViewById(R.id.events_gridView);
        furnitureExpert = FurnitureExpert.getInstance();
        initaiteRecyclerview();
    }

    private void initaiteRecyclerview() {
        ItemsGridViewAdapter adapter = new ItemsGridViewAdapter(this);
        eventsGridView.setAdapter(adapter);

        eventsGridView.setOnItemClickListener((adapterView, view, position, id) -> {
            Common.model = furnitureExpert.getFurniture(position).getModelName();
            Intent intent = new Intent(this, MainActivity.class);
            this.startActivity(intent);
            intent.putExtra("number", furnitureExpert.getFurniture(position).getModelName());
        });

    }
}