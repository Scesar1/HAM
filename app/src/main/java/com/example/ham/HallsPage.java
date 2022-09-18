package com.example.ham;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

public class HallsPage extends Fragment {
    public static int PERSONAL_ID = 3;

    public HallsPage() {
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
        View view = inflater.inflate(R.layout.fragment_halls_page, container, false);
        Button gilmanBtn = view.findViewById(R.id.gilmanBtn);
        MainActivity myAct = (MainActivity) getActivity();
        gilmanBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle result = new Bundle();
                result.putInt("bundleKey3", 1);
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