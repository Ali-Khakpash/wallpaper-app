package com.example.g50.primary_wallpaper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.net.Uri;
import android.support.design.widget.TabLayout;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class MainActivity extends AppCompatActivity implements tab_1.OnFragmentInteractionListener ,tab_2.OnFragmentInteractionListener ,tab_3.OnFragmentInteractionListener ,tab_4.OnFragmentInteractionListener {

    public Toolbar Toolbar1;


    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.start_page);
        if(!isconnected(MainActivity.this) ) buildDialog(MainActivity.this).show();
        Toolbar1 = (Toolbar) findViewById(R.id.toolbar1);
        Toolbar1.setTitle("");
        Toolbar1.setTitleMarginTop(0);
        setSupportActionBar(Toolbar1);
        Toolbar1.setTitleMarginTop(12);

        TabLayout tablayout= (TabLayout) findViewById(R.id.tablayout);     ///// remember to check API Level Condition
        tablayout.addTab(tablayout.newTab().setText("1"));
        tablayout.addTab(tablayout.newTab().setText("2"));
        tablayout.addTab(tablayout.newTab().setText("3"));
        tablayout.addTab(tablayout.newTab().setText("4"));
        tablayout.setTabGravity(TabLayout.GRAVITY_FILL);

        final ViewPager viewpager =(ViewPager) findViewById(R.id.viewpager);
        final PagerAdapter adapter = new pageadapter(getSupportFragmentManager(),tablayout.getTabCount());
        viewpager.setAdapter(adapter);


        viewpager.setOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tablayout));
        tablayout.setOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {

                viewpager.setCurrentItem(tab.getPosition() );

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }

        });




    }


    @Override
    public void onBackPressed(){
        onbackpressed(MainActivity.this).show();
    }


    @Override
    public boolean onCreateOptionsMenu (Menu menu) {
        MenuInflater menuinflater = getMenuInflater();
        menuinflater.inflate(R.menu.menu, menu);

        return true;

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {

            // case R.id.category:
            //    Toast.makeText(MainActivity.this, "wallpaperset", Toast.LENGTH_LONG).show();

            case R.id.communicate:

                Intent intent = new Intent("com.example.g50.primary_wallpaper.communicate");
                 startActivity(intent);

        }


        return true;
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }




    public boolean isconnected (Context context) {
        ConnectivityManager cm = (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = cm.getActiveNetworkInfo();
        if(networkInfo!=null && networkInfo.isConnectedOrConnecting()){
            android.net.NetworkInfo wifi = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
            android.net.NetworkInfo mobile = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
            if(mobile!=null && mobile.isConnectedOrConnecting() || wifi!=null && wifi.isConnectedOrConnecting()) return  true;
            else  return  false;
        }
        else return false;
    }



    public AlertDialog.Builder buildDialog (Context context) {
        AlertDialog.Builder builder =new AlertDialog.Builder(context);
        builder.setTitle("عدم دسترسی به اینترنت");
        builder.setMessage("لطفا به اینترنت وصل شوید...");
        builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                //  finish();
                dialog.cancel();
            }
        });

        return builder;
    }



    public AlertDialog.Builder onbackpressed  (Context context) {
        AlertDialog.Builder builder =new AlertDialog.Builder(context);
        builder.setTitle("خروج");
        builder.setMessage("آیا می خواهید از برنامه خارج شوید؟");

        builder.setPositiveButton("بله", new DialogInterface.OnClickListener() {

            @Override
            public void onClick(DialogInterface dialog, int which) {

                finish();
                // dialog.cancel();
            }
        });

        builder.setNegativeButton("خیر" , new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                dialog.cancel();
            }

        });


        return builder;
    }



}
