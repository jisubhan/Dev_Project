package org.techtown.courage;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jisub on 2017-12-01.
 */

public class NatureModel {
    private int imageID;
    private String title;

    public int getImageID(){
        return imageID;
    }

    public void setImageID(int imageID){
        this.imageID = imageID;
    }

    public String getTitle(){
        return title;
    }
    public void setTitle(String title){
        this.title = title;
    }

    public static List<NatureModel> getObjectList(){

        List<NatureModel> dataList = new ArrayList<>();
        int[] images = getImages();

        for(int i =0;i<images.length;i++){
            NatureModel nature = new NatureModel();
            nature.setImageID(images[i]);
            nature.setTitle("Picture "+i);
            dataList.add(nature);
        }

        return dataList;
    }
    public static int[] getImages(){
        int [] images={
                R.drawable.santa,R.drawable.santa};

        return images;
    }
}
