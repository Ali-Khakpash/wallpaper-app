package com.example.g50.primary_wallpaper;

import android.app.AlertDialog;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Path;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import com.cuboid.cuboidcirclebutton.CuboidButton;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.lang.ref.WeakReference;
import java.util.UUID;

import dmax.dialog.SpotsDialog;

public class detail_contents_1 extends AppCompatActivity {
    static  int i = 0 ;
    CuboidButton cuboidButtondownload,cuboidButtonshare,cuboidButtonwallpaper;
    public Context context;
    public  String realpath;
    public Uri uri;
    public File f,file,filepath,dir;
    public save_image_helper sih;
    public save_image_helper_2 sih2 ;
    public  save_image_helper_3 sih3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_contents_1);
        setImageView();


        cuboidButtonwallpaper = (CuboidButton) findViewById(R.id.btnsetwallpaper);
        cuboidButtonwallpaper.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String filename = UUID.randomUUID().toString() + ".jpg";
                String url = getIntent().getStringExtra("url_1");
                AlertDialog dialog = new SpotsDialog(detail_contents_1.this);
                sih2 =new save_image_helper_2(getBaseContext(), dialog, getApplicationContext().getContentResolver(), filename, "Image Desc");
                dialog.show();
                    dialog.show();
                    dialog.setMessage("Downloading...");
                        Picasso.with(getBaseContext()).load(url)
                                .into(sih2);




            }
        });



        cuboidButtonshare = (CuboidButton) findViewById(R.id.btnshare);
        cuboidButtonshare .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String filename = UUID.randomUUID().toString() + ".jpg";
                String url=getIntent().getStringExtra("url_1");
                AlertDialog dialog = new SpotsDialog(detail_contents_1.this);
                sih3 =new save_image_helper_3(getBaseContext(), dialog, getApplicationContext().getContentResolver(), filename, "Image Desc");
                    dialog.show();
                    dialog.setMessage("Downloading...");

                    Picasso.with(getBaseContext()).load(url)
                            .into(sih3);



            }
        });





        cuboidButtondownload  = (CuboidButton) findViewById(R.id.btndownload);
        cuboidButtondownload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String filename = UUID.randomUUID().toString() + ".jpg";
                String url=getIntent().getStringExtra("url_1");
                AlertDialog dialog = new SpotsDialog(detail_contents_1.this);
                sih =new save_image_helper(getBaseContext(), dialog, getApplicationContext().getContentResolver(), filename, "Image Desc");
                        dialog.show();
                    dialog.setMessage("Downloading...");

                    Picasso.with(getBaseContext()).load(url)
                            .into(sih);

            }
        });


        }



    private void setImageView(){
        String url=getIntent().getStringExtra("url_1");
        ImageView imageView1= (ImageView) findViewById(R.id.detailimageview);
        Picasso.with(this).load(url).fit().into(imageView1);

    }





    public class save_image_helper implements Target {

        Context context;
        private WeakReference<AlertDialog> alertDialog;
        private WeakReference<ContentResolver> contentResolver;
        private String name;
        private String description;



        public save_image_helper(Context context ,AlertDialog alertDialog, ContentResolver contentResolver, String name, String description) {
            this.context=context;
            this.alertDialog =  new WeakReference <AlertDialog> (alertDialog);
            this.contentResolver = new WeakReference<ContentResolver>(contentResolver);
            this.name = name;
            this.description = description;

        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            final  String sep = UUID.randomUUID().toString();
            OutputStream output;
            ContentResolver cr =contentResolver.get();
            AlertDialog dialog = alertDialog.get();
             filepath = Environment.getExternalStorageDirectory();
             dir = new File(filepath.getAbsolutePath() + "/Primary_Wall/");
            dir.mkdirs();
            file = new File(dir + File.separator + "/wallpaper_" + sep + ".jpg");
            try {
                output = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
                dialog.dismiss();
                realpath = file.getAbsolutePath();
                f = new File (realpath);
                uri=Uri.fromFile(f);

                 output.flush();
                 output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }


        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }


    }




    public class save_image_helper_2 implements Target {

        Context context;
        private WeakReference<AlertDialog> alertDialog;
        private WeakReference<ContentResolver> contentResolver;
        private String name;
        private String description;



        public save_image_helper_2(Context context ,AlertDialog alertDialog, ContentResolver contentResolver, String name, String description) {
            this.context=context;
            this.alertDialog =  new WeakReference <AlertDialog> (alertDialog);
            this.contentResolver = new WeakReference<ContentResolver>(contentResolver);
            this.name = name;
            this.description = description;

        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            final  String sep = UUID.randomUUID().toString();
            OutputStream output;
            ContentResolver cr =contentResolver.get();
            AlertDialog dialog = alertDialog.get();
            filepath = Environment.getExternalStorageDirectory();
            dir = new File(filepath.getAbsolutePath() + "/Primary_Wall/");
            dir.mkdirs();
            file = new File(dir + File.separator + "/wallpaper_" + sep + ".jpg");
            try {
                output = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
                dialog.dismiss();
                realpath = file.getAbsolutePath();
                f = new File (realpath);
                uri=Uri.fromFile(f);
                Intent intent = new Intent(Intent.ACTION_ATTACH_DATA);
                intent.addCategory(Intent.CATEGORY_DEFAULT);
                ///can't use normal URI, because it requires the Uri from file
                intent.setDataAndType(uri, "image/*");
                intent.putExtra("mimeType", "image/*");
                startActivity(Intent.createChooser(intent, "Set Image"));
                output.flush();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }


    }






    public class save_image_helper_3 implements Target {

        Context context;
        private WeakReference<AlertDialog> alertDialog;
        private WeakReference<ContentResolver> contentResolver;
        private String name;
        private String description;



        public save_image_helper_3(Context context ,AlertDialog alertDialog, ContentResolver contentResolver, String name, String description) {
            this.context=context;
            this.alertDialog =  new WeakReference <AlertDialog> (alertDialog);
            this.contentResolver = new WeakReference<ContentResolver>(contentResolver);
            this.name = name;
            this.description = description;

        }

        @Override
        public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
            final  String sep = UUID.randomUUID().toString();
            OutputStream output;
            ContentResolver cr =contentResolver.get();
            AlertDialog dialog = alertDialog.get();
            filepath = Environment.getExternalStorageDirectory();
            dir = new File(filepath.getAbsolutePath() + "/Primary_Wall/");
            dir.mkdirs();
            file = new File(dir + File.separator + "/wallpaper_" + sep + ".jpg");
            try {
                output = new FileOutputStream(file);
                bitmap.compress(Bitmap.CompressFormat.JPEG, 100, output);
                dialog.dismiss();
                realpath = file.getAbsolutePath();
                f = new File (realpath);
                uri=Uri.fromFile(f);
                Intent share = new Intent(Intent.ACTION_SEND);
                share.setType("image/jpeg");
                //   uri = Uri.parse(Imagepath);
                share.putExtra(Intent.EXTRA_STREAM, uri);
                startActivity(Intent.createChooser(share, "Select"));
                output.flush();
                output.close();
            } catch (IOException e) {
                e.printStackTrace();
            }



        }

        @Override
        public void onBitmapFailed(Drawable errorDrawable) {

        }

        @Override
        public void onPrepareLoad(Drawable placeHolderDrawable) {

        }


    }









}
