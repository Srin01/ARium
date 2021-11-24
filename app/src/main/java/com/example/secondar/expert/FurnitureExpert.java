package com.example.secondar.expert;

import android.util.Log;

import com.example.secondar.R;
import com.example.secondar.model.Furniture;

import java.util.ArrayList;

public class FurnitureExpert
{
    ArrayList<Furniture> furnitures = new ArrayList<>();
    private static FurnitureExpert single_instance = null;

    private FurnitureExpert(){
        furnitures.add(new Furniture(R.drawable.dtable, "Desk Table ", "bureau3.sfb","2,000"));
        furnitures.add(new Furniture(R.drawable.lamp, "Lamp", "lamp.sfb","2,500"));
        furnitures.add(new Furniture(R.drawable.desklamp, "Desk Lamp", "desklamp.sfb","300"));
        furnitures.add(new Furniture(R.drawable.mchair, "Modern Chair", "mchair.sfb","2,400"));
        furnitures.add(new Furniture(R.drawable.gtable, "Glass Table", "table_wood_and_iron.sfb","1,200"));
        furnitures.add(new Furniture(R.drawable.couchwilliam, "Couch William", "CouchWilliam.sfb","25,000"));
        furnitures.add(new Furniture(R.drawable.rchairjpg, "Rolling Chair", "office-chair.sfb","2,700"));
        furnitures.add(new Furniture(R.drawable.rockingchairjpg, "Rocking Chair", "rocking_chair.sfb","3,700"));
        furnitures.add(new Furniture(R.drawable.bookcase, "Book Case", "mobile9080W.sfb","1,500"));
        furnitures.add(new Furniture(R.drawable.bookshelf, "Book Shelf", "model.sfb","5,200"));
    }

    public static FurnitureExpert getInstance()
    {
        if(single_instance == null) {
            single_instance = new FurnitureExpert();
            Log.d("myTag", "getInstance: new Furniture expert created " + single_instance.hashCode());
        }
        return single_instance;
    }

    public FurnitureExpert(ArrayList<Furniture> furnitures) {
        this.furnitures = furnitures;
    }

    public ArrayList<Furniture> getFurnitures() {
        return furnitures;
    }

    public void setFurnitures(ArrayList<Furniture> furnitures) {
        this.furnitures = furnitures;
    }

    public void addFurniture(Furniture furniture){
        furnitures.add(furniture);
    }

    public Furniture getFurniture(int position){
        return furnitures.get(position);
    }

    public int totalFurnitures(){
        return furnitures.size();
    }
}
