package com.sits.pizzaburger.ui.slideshow;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.sits.pizzaburger.AdapterRVC;
import com.sits.pizzaburger.MainActivity;
import com.sits.pizzaburger.R;
import com.sits.pizzaburger.RecyclerAdapter;
import com.sits.pizzaburger.SubmenuDetail;
import com.sits.pizzaburger.ui.home.submenu;

import java.nio.Buffer;
import java.util.ArrayList;
import java.util.List;

public class SlideshowFragment extends Fragment {

    private SlideshowViewModel slideshowViewModel;
    Dialog mypopup ;
public  static int price = 0 ;
    AdapterRVC recyclerAdapter ;
    List<SubmenuDetail> cartitems=new ArrayList<>();
    private DatabaseReference db;
    static TextView total ;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        slideshowViewModel =
                ViewModelProviders.of(this).get(SlideshowViewModel.class);
        final View root = inflater.inflate(R.layout.fragment_slideshow, container, false);
        final TextView textView = root.findViewById(R.id.text_slideshow);
        slideshowViewModel.getText().observe(getViewLifecycleOwner(), new Observer<String>() {
            @Override
            public void onChanged(@Nullable String s) {
                textView.setText(s);
            }
        });

        Button ordernow = root.findViewById(R.id.ordernow) ;
        Animation animBlink = AnimationUtils.loadAnimation(getContext(),
                R.anim.anim);
        ordernow.startAnimation(animBlink);

mypopup = new Dialog(getContext());

total = root.findViewById(R.id.total) ;

SubmenuDetail a1 = new SubmenuDetail("abcd","sdsd","sds",90,90,"gffg");
cartitems.add(a1) ;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        db = FirebaseDatabase.getInstance().getReference("User");
        db.child(user.getPhoneNumber()).child("cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                   cartitems.clear();
               price = 0 ;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    SubmenuDetail h1 = postSnapshot.getValue(SubmenuDetail.class);
                    cartitems.add(h1);
                    price = price+Integer.parseInt(String.valueOf(h1.getPrice())) ;



                }

                total.setText(price+" Rs only");

                final RecyclerView recyclerView  = (RecyclerView) root.findViewById(R.id.rvcart);
                recyclerAdapter = new AdapterRVC(cartitems, getContext());
                recyclerView.setHasFixedSize(true);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(recyclerAdapter);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });


     /*   final RecyclerView recyclerView  = (RecyclerView) root.findViewById(R.id.rvcart);
        recyclerAdapter = new AdapterRVC(cartitems, getContext());
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(recyclerAdapter);*/
ordernow.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        final FirebaseUser user = mAuth.getCurrentUser();
        String uid = user.getUid();
        db = FirebaseDatabase.getInstance().getReference("User");
        db.child(user.getPhoneNumber()).child("cart").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                List<SubmenuDetail> orderitems = new ArrayList<>() ;
                orderitems.clear();
                SubmenuDetail a1 = new SubmenuDetail("No food","No food","No food",90,90,"No food");
                orderitems.add(a1) ;
                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                    SubmenuDetail h1 = postSnapshot.getValue(SubmenuDetail.class);
                    orderitems.add(h1);

                }

                DatabaseReference mdatabase1 = database.getReference("Order");

                mdatabase1.child(user.getPhoneNumber()).push().setValue(orderitems);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });



        TextView txtclose;
        ImageView call ;
        Button btnFollow;
        mypopup.setContentView(R.layout.popuplayout);
        call = mypopup.findViewById(R.id.phone) ;
        txtclose =(TextView) mypopup.findViewById(R.id.textView2);
        txtclose.setText("X");
        txtclose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mypopup.dismiss();
            }
        });

        call.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+918085335643"));//change the number
                startActivity(callIntent);

            }
        });

        mypopup.show();


      //  Toast.makeText(getContext(), "Added to cart : ", Toast.LENGTH_LONG).show();

    }
});


        return root;
    }

   public static void updatetotal(){

        total.setText(price+" Rs only");


    }


}


