package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class congrattulationsActivity extends AppCompatActivity {

    ImageView image_suc , logo_suc;
    TextView text_suc, textView_suc;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_congrattulations);

        image_suc = findViewById(R.id.img_suc);
        logo_suc = findViewById(R.id.logo_suc);
        text_suc = findViewById(R.id.txt_suc);
        textView_suc = findViewById(R.id.text1_suc);



    }

    public void Next(View view) {
        Intent nt = new Intent(congrattulationsActivity.this,help_Activity.class);
        startActivity(nt);
    }
}