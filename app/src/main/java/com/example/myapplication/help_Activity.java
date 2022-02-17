package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.airbnb.lottie.LottieAnimationView;

public class help_Activity extends AppCompatActivity {

    Button btnhelp, btnReport;
    TextView txtdetails, texthelp, textreport;
    LottieAnimationView lottiehelp, lottiereport;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_);

        btnhelp = findViewById(R.id.btnhelp);
        btnReport = findViewById(R.id.btnReport);
        texthelp = findViewById(R.id.txt_help);
        txtdetails = findViewById(R.id.textdetails);
        textreport = findViewById(R.id.txtreport);
        lottiereport = findViewById(R.id.lottieReport);
        lottiehelp = findViewById(R.id.lottiehelp);

    }
        public void Helping (View view){
            startActivity(new Intent(help_Activity.this, Services_Activity.class));

        }

        public void Reporting (View view){
            startActivity(new Intent(help_Activity.this, Call_Activity.class));

        }
    }
