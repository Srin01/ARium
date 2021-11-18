package com.example.secondar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;

import com.google.ar.sceneform.AnchorNode;

import java.util.ArrayList;

public class ListActivity extends AppCompatActivity {

    private ArrayList<Integer> imagesPath = new ArrayList<Integer>();
    private ArrayList<String> namesPath = new ArrayList<>();
    private ArrayList<String> modelNames = new ArrayList<>();
    AnchorNode anchorNode;
    private Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);
        getImages();
    }

    private void getImages() {

        imagesPath.add(R.drawable.table);
        imagesPath.add(R.drawable.bookshelf);
        imagesPath.add(R.drawable.lamp);
        imagesPath.add(R.drawable.odltv);
        imagesPath.add(R.drawable.clothdryer);
        imagesPath.add(R.drawable.chair);
        imagesPath.add(R.drawable.chair);


        namesPath.add("Table");
        namesPath.add("BookShelf");
        namesPath.add("Lamp");
        namesPath.add("Old Tv");
        namesPath.add("Cloth Dryer");
        namesPath.add("Chair");
        namesPath.add("Modern Chair");


        modelNames.add("table.sfb");
        modelNames.add("model.sfb");
        modelNames.add("vlamps.sfb");
        modelNames.add("tv.sfb");
        modelNames.add("cloth.sfb");
        modelNames.add("chair.sfb");
        modelNames.add("mchair.sfb");

        initaiteRecyclerview();
    }

    private void initaiteRecyclerview() {

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        RecyclerView recyclerView = (RecyclerView)findViewById(R.id.recyclerview);
        recyclerView.setLayoutManager(layoutManager);
        RecyclerviewAdapter adapter = new RecyclerviewAdapter(this,namesPath,imagesPath,modelNames);
        recyclerView.setAdapter(adapter);

    }


}