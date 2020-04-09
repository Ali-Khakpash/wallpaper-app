package com.example.g50.primary_wallpaper;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

/**
 * Created by g50 on 5/31/2018.
 */




public class data_provider_2{



    private String  imageurl1;
    private String  imageurl2;


    public data_provider_2(String imageurl1, String imageurl2 ){

        this.imageurl1=imageurl1;
        this.imageurl2=imageurl2;

    }




    public String getImageurl() {
        return imageurl1;
    }

    public void setImageurl(String imageurl) {
        this.imageurl1 = imageurl;
    }



    public String getImageurl2() {
        return imageurl2;
    }

    public void setImageurl2(String imageurl2) {
        this.imageurl2 = imageurl2;
    }




}
