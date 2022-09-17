package com.example.ham;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.os.Bundle;

public class MainActivity<FusedLocationProviderClient> extends AppCompatActivity {

    private Fragment mapFrag;
    private FragmentTransaction transaction;
    private FusedLocationProviderClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mapFrag = new MapFragment();

        getSupportFragmentManager()
                .beginTransaction().replace(R.id.fragment_container, mapFrag)
                .commit();

    }
}