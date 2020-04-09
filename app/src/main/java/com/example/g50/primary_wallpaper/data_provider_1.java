package com.example.g50.primary_wallpaper;

/**
 * Created by g50 on 5/27/2018.
 */

public class data_provider_1{



    private String  imageurl1;
    private String  imageurl2;


    public data_provider_1(String imageurl1, String imageurl2 ){

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
