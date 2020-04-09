package com.example.g50.primary_wallpaper;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

/**
 * Created by g50 on 5/27/2018.
 */



public class pageadapter  extends FragmentStatePagerAdapter {

    int numtabs;

    public pageadapter(FragmentManager fm , int numberoftabs) {
        super(fm);
        this.numtabs=numberoftabs;

    }



    @Override
    public Fragment getItem(int position) {

        switch (position) {

            case  0 :
                tab_1 tab1 = new tab_1();
                return tab1  ;

            case  1 :
               tab_2 tab2 = new tab_2();
                return tab2  ;

            case  2 :
                tab_3 tab3 = new tab_3();
                return tab3  ;


           case 3:
                tab_4 tab4 = new tab_4 ();
                return tab4;

            default:
                return null;
        }



    }



    @Override
    public int getCount() {
        return numtabs ;
    }
}
