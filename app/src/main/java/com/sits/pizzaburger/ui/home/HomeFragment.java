package com.sits.pizzaburger.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.sits.pizzaburger.R;

public class HomeFragment extends Fragment {

    private HomeViewModel homeViewModel;
    MaterialButton buttonp, buttonb,buttons,buttonbev ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        homeViewModel =
                ViewModelProviders.of(this).get(HomeViewModel.class);
        View root = inflater.inflate(R.layout.fragment_home, container, false);
        final TextView textView = root.findViewById(R.id.text_home);
        homeViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });


        buttonb = (MaterialButton) root.findViewById(R.id.buttonb) ;
        buttonp = (MaterialButton) root.findViewById(R.id.buttonp) ;
        buttons = (MaterialButton) root.findViewById(R.id.buttons) ;
        buttonbev = (MaterialButton) root.findViewById(R.id.buttonbev) ;

        buttonp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dish = "pizza" ;
                opensubmenu(dish) ;
            }
        });

        buttonb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dish = "burger" ;
                opensubmenu(dish) ;
            }
        });
        buttons.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dish = "sandwitch" ;
                opensubmenu(dish) ;
            }
        });

        buttonbev.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String dish = "beverages " ;
                opensubmenu(dish) ;
            }
        });



        return root;
    }

    void opensubmenu(String dish){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        Intent intent = new Intent(getContext(), submenu.class);
        intent.putExtra("dishname", dish);
        startActivity(intent);

    }
}