package com.example.ham;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

public class FoodPage extends Fragment {
    private int btnVal;
    MainActivity myAct;
    public static int PERSONAL_ID = 1;
    public FoodPage() {
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

        View view = inflater.inflate(R.layout.fragment_food_page, container, false);
        myAct = (MainActivity) getActivity();
        Button hopcafeBtn = view.findViewById(R.id.hopcafeBtn);
        hopcafeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle result = new Bundle();
                result.putInt("bundleKey1", 1);
                MapFragment mapFrag = new MapFragment();
                mapFrag.setArguments(result);
                getParentFragmentManager().setFragmentResult("resultKey", result);
                myAct.bottomNavigationView.setSelectedItemId(R.id.nav_map);
                FragmentTransaction transaction = getActivity().getSupportFragmentManager().beginTransaction();
                transaction.replace(R.id.fragment_container, mapFrag).commit();
            }
        });
        return view;
    }
}