package com.sits.pizzaburger;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.atomic.AtomicMarkableReference;


public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder>{
    List<SubmenuDetail> listdata=new ArrayList<>();
    int imgarray[] = {R.drawable.pizza2,R.drawable.pizza3,R.drawable.pizza4,R.drawable.pizza1,R.drawable.pizza1,R.drawable.pizza2,R.drawable.pizza3,R.drawable.pizza4,R.drawable.pizza1,R.drawable.pizza1,R.drawable.pizza2,R.drawable.pizza3,R.drawable.pizza4,R.drawable.pizza1,R.drawable.pizza1,R.drawable.pizza2,R.drawable.pizza3,R.drawable.pizza4,R.drawable.pizza1,R.drawable.pizza1} ;
    Context context1 ;
    private DatabaseReference mDatabase;

    public RecyclerAdapter(Context context) {
        this.context1 = context;
    }

    // RecyclerView recyclerView;
    public RecyclerAdapter(List<SubmenuDetail> listdata, Context context) {
        this.listdata = listdata;
        this.context1 = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.submenurv, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final SubmenuDetail myListData = listdata.get(position);
        holder.name.setText(listdata.get(position).getName());
       // Resources res = getResources();
        holder.image.setImageDrawable(ContextCompat.getDrawable(context1, ((imgarray[position]))));
        holder.description.setText(listdata.get(position).getDescription());
        holder.ordernow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(view.getContext(),"Order now"+listdata.get(position).getName(),Toast.LENGTH_LONG).show();
                Intent callIntent = new Intent(Intent.ACTION_CALL);
                callIntent.setData(Uri.parse("tel:+918085335643"));//change the number
               // view.getContext().startActivity();
             //   Uri uri = Uri.parse(myListData.getLink()); // missing 'http://' will cause crashed
              ///  Intent intent = new Intent(Intent.ACTION_VIEW, uri);
               // context1.startActivity(intent);
            }
        });
try {



    holder.addtocart.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {



            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            FirebaseUser user = mAuth.getCurrentUser();
            String uid = user.getUid();

            FirebaseDatabase database = FirebaseDatabase.getInstance();

            //Getting Reference to a User node, (it will be created if not already there)
            DatabaseReference mdatabase1 = database.getReference("User");


            mDatabase = FirebaseDatabase.getInstance().getReference("User");
            mdatabase1.child(user.getPhoneNumber()).child("cart").push().setValue(listdata.get(position));

            Toast.makeText(view.getContext(), "Added to cart : " + listdata.get(position).getName(), Toast.LENGTH_LONG).show();


            //   Uri uri = Uri.parse(myListData.getLink()); // missing 'http://' will cause crashed
            ///  Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            // context1.startActivity(intent);
        }
    });
}catch (Exception e){}

    }


    @Override
    public int getItemCount() {
        return listdata.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView name;
        public TextView description;
        ImageView image ;
        public MaterialButton ordernow, addtocart ;
        final Context context ;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.description = (TextView) itemView.findViewById(R.id.description);
            this.ordernow = (MaterialButton) itemView.findViewById(R.id.ordernow);
            this.addtocart = (MaterialButton) itemView.findViewById(R.id.addtocart);
            this.image = (ImageView)itemView.findViewById(R.id.image) ;

        }
    }
}
