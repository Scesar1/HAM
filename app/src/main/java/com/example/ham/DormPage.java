package com.example.ham;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;


public class DormPage extends Fragment {
    public static int PERSONAL_ID = 2;

    public DormPage() {
        // Required empty public constructor
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_dorm_page, container, false);
        Button amrIBtn = view.findViewById(R.id.amrIBtn);
        MainActivity myAct = (MainActivity) getActivity();
        amrIBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle result = new Bundle();
                result.putInt("bundleKey2", 1);
                MapFragment mapFrag = new MapFragment();
                mapFrag.setArguments(result);
                myAct.bottomNavigationView.setSelectedItemId(R.id.nav_map);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, mapFrag).commit();
            }
        });

        return view;
    }
}