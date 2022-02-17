package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class choiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choice);
    }

    public void head(View view) {
                startActivity(new Intent(choiceActivity.this,LoginActivity.class));

    }

    public void members(View view) {
        startActivity(new Intent(choiceActivity.this,LoginActivity.class));

    }
}