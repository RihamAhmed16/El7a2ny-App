package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.airbnb.lottie.LottieAnimationView;

public class MainActivity extends AppCompatActivity {

LottieAnimationView lottieAnimationView ;
ImageView imageView;
TextView text,textView;
    Animation anim ;
    private static int splash_screen = 5000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        imageView=findViewById(R.id.photo);
        lottieAnimationView = findViewById(R.id.animation_view);
        //text = findViewById(R.id.txt_myApp);
       // textView = findViewById(R.id.txt1_myApp);

        anim = AnimationUtils.loadAnimation(this,R.anim.top);
        lottieAnimationView.setAnimation(anim);
      //  anim= AnimationUtils.loadAnimation(this,R.anim.bottom);
     //   text.setAnimation(anim);
        anim= AnimationUtils.loadAnimation(this,R.anim.bottom);
        imageView.setAnimation(anim);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);

               Pair[] pairs = new Pair[2];
               pairs[0]= new Pair<View,String>(lottieAnimationView,"animation_view");
               pairs[1]=new Pair<View,String>(imageView,"txt_logo");
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.LOLLIPOP) {
                    ActivityOptions activityOptions =ActivityOptions.makeSceneTransitionAnimation(MainActivity.this,pairs);
                    startActivity(intent);
                }
            }
            }
        ,splash_screen);
    }



}