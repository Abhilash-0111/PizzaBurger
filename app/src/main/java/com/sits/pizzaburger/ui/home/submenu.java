package com.sits.pizzaburger.ui.home;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sits.pizzaburger.MainActivity;
import com.sits.pizzaburger.R;
import com.sits.pizzaburger.RecyclerAdapter;
import com.sits.pizzaburger.SubmenuDetail;

import java.util.ArrayList;
import java.util.List;

public class submenu extends AppCompatActivity {
TextView t1 ;

RecyclerAdapter recyclerAdapter ;
    List<SubmenuDetail> foods=new ArrayList<>();
    private DatabaseReference db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_submenu);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");


        Bundle bundle = getIntent().getExtras();
        String dish = bundle.getString("dishname");

t1 = (TextView)findViewById(R.id.t1) ;
SubmenuDetail pizza1 = new SubmenuDetail("Chilli with red pepprica and green mirchi it also have black and white peeper","image","Indiana pizza ",10,220,"p1") ;
SubmenuDetail pizza2 = new SubmenuDetail("Chilli with red pepprica and green mirchi it also have black and white peeper","image","paneer pizza",10,20,"p1") ;
SubmenuDetail pizza3 = new SubmenuDetail("Chilli with red pepprica and green mirchi it also have black and white peeper","image","mirchi pizza",10,220,"p1") ;
SubmenuDetail pizza4 = new SubmenuDetail("Chilli with red pepprica and green mirchi it also have black and white peeper","image","namkeen pizza",10,220,"p1") ;
SubmenuDetail burger1 = new SubmenuDetail("Chilli with red pepprica and green mirchi it also have black and white peeper","image","big burger",10,220,"p1") ;
SubmenuDetail burger2 = new SubmenuDetail("Chilli with red pepprica and green mirchi it also have black and white peeper","image","small burger",10,220,"p1") ;
SubmenuDetail burger3 = new SubmenuDetail("Chilli with red pepprica and green mirchi it also have black and white peeper","image","majhol burger",10,220,"p1") ;
SubmenuDetail burger4 = new SubmenuDetail("Chilli with red pepprica and green mirchi it also have black and white peeper","image","kha le burger",10,220,"p1") ;


if(dish.equals("burger")){
  //  foods.add(burger1) ;
   // foods.add(burger2) ;
   // foods.add(burger3) ;
   // foods.add(burger4) ;

}

        if(dish.equals("pizza")){
     //       foods.add(pizza1) ;
       //     foods.add(pizza2) ;
         //   foods.add(pizza3) ;
           // foods.add(pizza4) ;

        }


        final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
        recyclerAdapter = new RecyclerAdapter(foods,submenu.this);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(submenu.this));
        recyclerView.setAdapter(recyclerAdapter);


      db = FirebaseDatabase.getInstance().getReference("Food");
        db.child("Pizza").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
             //   foods.clear();

                String s1 = "abcd" ;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                   SubmenuDetail h1 = postSnapshot.getValue(SubmenuDetail.class);
                    foods.add(h1);

                    s1 = h1.getName() ;

                }

                final RecyclerView recyclerView = (RecyclerView) findViewById(R.id.recyclerview1);
                recyclerAdapter = new RecyclerAdapter(foods,submenu.this);
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(submenu.this));
                recyclerView.setAdapter(recyclerAdapter);
                t1.setText(s1);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });




    }
}