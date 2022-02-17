package com.example.myapplication;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class AdapterServices extends ArrayAdapter<Services> {

    Context c;
    ArrayList<Services> info;

    public AdapterServices(Context context, ArrayList<Services> cont) {
        super(context, R.layout.activity_services_,cont);
        c=context;
        info=cont;
    }

    class Holder
    {
        ImageView Services_image;
        TextView name_Services;


    }

    public View getView(int position, View convertView, ViewGroup parent) {
        // Get the data item for this position

        Services data = getItem(position);
        // Check if an existing view is being reused, otherwise inflate the view

        Holder viewHolder; // view lookup cache stored in tag

        if (convertView == null) {

            viewHolder = new Holder();
            LayoutInflater inflater = LayoutInflater.from(getContext());
            convertView = inflater.inflate(R.layout.activity_services_, parent, false);

            viewHolder.name_Services = (TextView) convertView.findViewById(R.id.txt_Services);

            viewHolder.Services_image = (ImageView) convertView.findViewById(R.id.img_Services);


            convertView.setTag(viewHolder);

        } else
        {
            viewHolder = (Holder) convertView.getTag();
        }

        PicassoClient.downloadImage(c,data.getService_img(),viewHolder.Services_image);
        viewHolder.name_Services.setText(data.getService_Name());



        // Return the completed view to render on screen
        return convertView;
    }


}
