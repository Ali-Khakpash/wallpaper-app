package com.example.g50.primary_wallpaper;

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by g50 on 5/31/2018.
 */



public class RecyclerAdapter_2 extends RecyclerView.Adapter <RecyclerAdapter_2.recyclerViewViewHolder>{

    public Context context;
    private ArrayList<data_provider_2> arrayList = new ArrayList<data_provider_2>();




    public  RecyclerAdapter_2 (ArrayList<data_provider_2> arrayList, Context context ){

        this.arrayList = arrayList;
        this.context=context;
    }




    @Override
    public  recyclerViewViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(context).inflate(R.layout.recyclerview_item_layout_2,parent,false); // // don't ever use
        // // fucking context
        recyclerViewViewHolder recyclerViewViewHolder =new recyclerViewViewHolder(view);

        return recyclerViewViewHolder;

    }

    @Override
    public void onBindViewHolder(recyclerViewViewHolder holder , final int position) {

        final data_provider_2  data_provider =arrayList.get(position);
        Picasso.with(context).load(data_provider.getImageurl()).fit().into(holder.imageView1);
        Picasso.with(context).load(data_provider.getImageurl2()).fit().into(holder.imageView2);
        holder.parentlayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                //  final data_provider  data_provider =arrayList.get(position);
                Intent intent = new Intent(context,detail_contents_1.class);

                intent.putExtra("url_1",data_provider.getImageurl());

                //   intent.putExtra("image_url2",  arrayList.get(position));
                context.startActivity(intent);

                //  Intent intentd = new Intent(context,Detail_Contents.class);


            }
        });




        holder.parentlayout2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(context,detail_contents_2.class);

                intent.putExtra("url_2",data_provider.getImageurl2());

                context.startActivity(intent);



            }
        });
















    }


    @Override
    public int getItemCount() {
        return arrayList.size();
    }



    public  class   recyclerViewViewHolder  extends RecyclerView.ViewHolder {

        TextView maintext;
        ImageView imageView1;
        ImageView imageView2;
        ImageView imageView3;
        ImageView imageView4;
        RelativeLayout parentlayout;
        RelativeLayout parentlayout2;

        public recyclerViewViewHolder(View view) {

            super(view);

            imageView1 = (ImageView) view.findViewById(R.id.imageview_2_1);
            imageView2 = (ImageView) view.findViewById(R.id.imageview_2_2);
            parentlayout = (RelativeLayout) view.findViewById(R.id.rel_2_1);
            parentlayout2= (RelativeLayout) view.findViewById(R.id.rel_2_2);

        }


    }


}
