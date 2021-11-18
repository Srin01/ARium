package com.example.secondar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import com.google.ar.core.Anchor;
import com.google.ar.sceneform.AnchorNode;
import com.google.ar.sceneform.rendering.Color;
import com.google.ar.sceneform.rendering.MaterialFactory;
import com.google.ar.sceneform.rendering.ModelRenderable;
import com.google.ar.sceneform.ux.ArFragment;
import com.google.ar.sceneform.ux.TransformableNode;

import java.util.ArrayList;
import java.util.function.Consumer;

public class MainActivity extends AppCompatActivity {


    private ArFragment arFragment;
    private ArrayList<Integer> imagesPath = new ArrayList<Integer>();
    private ArrayList<String> namesPath = new ArrayList<>();
    private ArrayList<String> modelNames = new ArrayList<>();
    AnchorNode anchorNode;
    private Button btnRemove;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        int n = getIntent().getIntExtra("number", 0);
        arFragment = (ArFragment) getSupportFragmentManager().findFragmentById(R.id.fragment);
        btnRemove = (Button) findViewById(R.id.remove);
        getImages();

        arFragment.setOnTapArPlaneListener((hitResult, plane, motionEvent) -> {

            Anchor anchor = hitResult.createAnchor();

            ModelRenderable.builder()
                    .setSource(this, Uri.parse(Common.model))
                    .build()
                    .thenAccept(new Consumer<ModelRenderable>() {
                        @Override
                        public void accept(ModelRenderable modelRenderable) {
                            MainActivity.this.addModelToScene(anchor, modelRenderable);
                        }
                    });

        });


        btnRemove.setOnClickListener(view -> removeAnchorNode(anchorNode));
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

    }


    private void addModelToScene(Anchor anchor, ModelRenderable modelRenderable) {

        anchorNode = new AnchorNode(anchor);
        TransformableNode node = new TransformableNode(arFragment.getTransformationSystem());
        node.getScaleController().setMaxScale(0.8f);
        node.getScaleController().setMinScale(0.1f);
        node.setParent(anchorNode);
        node.setRenderable(modelRenderable);
        arFragment.getArSceneView().getScene().addChild(anchorNode);
        node.select();

    }

    public void removeAnchorNode(AnchorNode nodeToremove) {
        if (nodeToremove != null) {
            arFragment.getArSceneView().getScene().removeChild(nodeToremove);
            nodeToremove.getAnchor().detach();
            nodeToremove.setParent(null);
            nodeToremove = null;
        }
    }
}
