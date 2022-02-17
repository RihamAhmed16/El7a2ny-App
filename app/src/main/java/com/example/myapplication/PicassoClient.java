package com.example.myapplication;

import android.content.Context;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

public class PicassoClient {
    static String img;



    public static void downloadImage(Context context, String url, ImageView img)
    {

        if(url != null && url.length()>0)
        {

            Picasso.with(context).load(url).placeholder(R.drawable.img).into(img);
        }
        else
        {
            Picasso.with(context).load(R.drawable.img).into(img);
        }
    }
}

