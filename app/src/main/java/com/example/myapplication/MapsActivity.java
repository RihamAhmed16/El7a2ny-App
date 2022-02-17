package com.example.myapplication;


import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapsActivity extends AppCompatActivity implements OnMapReadyCallback {


    LatLng m;
    private GoogleMap mMap;
    public static String la, lo;
    private Object Menu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera


        mMap.setOnMapClickListener(new GoogleMap.OnMapClickListener() {
            @Override
            public void onMapClick(LatLng latLng) {

                GpsTracker g = new GpsTracker(MapsActivity.this);
                int strokeColor=0xffff0000;
                int shadeColor=0x44ff0000;
                Circle c=mMap.addCircle(new CircleOptions().center(new LatLng(g.getLatitude(),g.getLongitude()))
                                .radius(200).strokeColor(strokeColor).fillColor(shadeColor));

                m = new LatLng(g.getLatitude(), g.getLongitude());
                mMap.addMarker(new MarkerOptions().position(m).title("current Location"));
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(m, 7));
                la = String.valueOf(latLng.latitude);
                lo = String.valueOf(latLng.longitude);
            }
        });


    }

}