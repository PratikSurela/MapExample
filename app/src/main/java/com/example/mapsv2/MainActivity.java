package com.example.mapsv2;

import android.content.Intent;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

public class MainActivity extends FragmentActivity {

    private GoogleMap map;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();

        String lat = i.getExtras().getString("lat");
        String lng = i.getExtras().getString("long");

        String lat1 = i.getExtras().getString("lat1");
        String lng1 = i.getExtras().getString("long1");

        SupportMapFragment fm = (SupportMapFragment)getSupportFragmentManager().findFragmentById(R.id.maps_fragment);
        map = fm.getMap();
        map.setMyLocationEnabled(true);

        LatLng l1 = new LatLng(Double.parseDouble(lat), Double.parseDouble(lng));
        MarkerOptions options = new MarkerOptions();

        LatLng l2 = new LatLng(Double.parseDouble(lat1), Double.parseDouble(lng1));
        MarkerOptions options1 = new MarkerOptions();
        // Setting the position of the marker
        options.position(l1);
        options1.position(l2);

        options.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
        map.addMarker(options.title("Marker 1"));
        map.addMarker(options1.title("Marker 2"));

        map.moveCamera(CameraUpdateFactory.newLatLngZoom(l1, 10));
        map.moveCamera(CameraUpdateFactory.newLatLngZoom(l2, 10));
   }
}