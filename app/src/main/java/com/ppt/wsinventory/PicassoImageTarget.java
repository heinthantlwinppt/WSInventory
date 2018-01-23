package com.ppt.wsinventory;

import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Environment;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by User on 23/01/2018.
 */

public class PicassoImageTarget implements Target {
    private final String name;
    private final File pathdir;

    public PicassoImageTarget(String name, File pathdir) {
        this.name = name;
        this.pathdir = pathdir;
    }

    @Override
    public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {


        File my_file = new File(pathdir,name+".png");

        try {
            FileOutputStream stream = new FileOutputStream(my_file);
            bitmap.compress(Bitmap.CompressFormat.JPEG,100,stream);
            stream.flush();
            stream.close();
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
