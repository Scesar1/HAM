package com.example.ham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMapOptions;
import com.google.android.gms.maps.SupportMapFragment;

public class MainActivity extends AppCompatActivity {

    private Fragment mapFrag;
    private FragmentTransaction transaction;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFrag = new MapFragment();

        GoogleMapOptions options = new GoogleMapOptions();

        getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragment_container, mapFrag)
                .commit();


    }
}