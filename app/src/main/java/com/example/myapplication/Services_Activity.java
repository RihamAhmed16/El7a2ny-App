package com.example.myapplication;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class Services_Activity extends AppCompatActivity {
    public static String Ser_No;

    ListView lst;
    Services model;
    GetServices gr=new GetServices();
    AdapterServices adapterServices;
    ArrayList<Services> arr;
TextView txtservice,txtservice2;
ImageView imageservice,imageservice2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_services_);

        txtservice=findViewById(R.id.txt_Services);
        txtservice2=findViewById(R.id.txt_Service2);
        imageservice=findViewById(R.id.img_Services);
        imageservice2=findViewById(R.id.img_Service2);

    }
}