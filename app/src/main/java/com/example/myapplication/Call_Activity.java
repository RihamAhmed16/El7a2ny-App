package com.example.myapplication;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class Call_Activity extends AppCompatActivity {
    Spinner sp;
    ImageView imaglogo;
    EditText editText;
    ImageButton call;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        sp= findViewById(R.id.spinner);   // I would like get this Data  from Database then put it in spinner (from Relevant_Authorities table)

        imaglogo = findViewById(R.id.img_lo);
        editText = findViewById(R.id.edit);
        call = findViewById(R.id.img_btn);

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String phone = editText.getText().toString();

                if (phone. isEmpty()){

                    Toast.makeText(getApplicationContext(),"Please Enter Number!",Toast.LENGTH_LONG).show();

                }else {

                    String x = "tel:"+ phone;
                    Intent intent = new Intent(Intent.ACTION_CALL);
                    intent.setData(Uri.parse("tel:"+x));
                    startActivity(intent);

                }
            }
        });
    }
}