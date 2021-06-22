package com.sits.pizzaburger;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.button.MaterialButton;
import com.google.firebase.FirebaseError;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.sits.pizzaburger.ui.slideshow.SlideshowFragment;

import java.util.ArrayList;
import java.util.List;

public class AdapterRVC extends RecyclerView.Adapter<AdapterRVC.ViewHolder>{
   static List<SubmenuDetail> listdata=new ArrayList<>();
    Context context1 ;
    private DatabaseReference mDatabase;

    public AdapterRVC(Context context) {
        this.context1 = context;
    }

    // RecyclerView recyclerView;
    public AdapterRVC(List<SubmenuDetail> listdata, Context context) {
        this.listdata = listdata;
        this.context1 = context;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View listItem= layoutInflater.inflate(R.layout.rvc, parent, false);
        ViewHolder viewHolder = new ViewHolder(listItem);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final SubmenuDetail myListData = listdata.get(position);
        holder.name.setText(listdata.get(position).getName());
        holder.quantity.setText("1");
        holder.price.setText(""+listdata.get(position).getPrice());
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


               removeitem(position) ;

                Toast.makeText(view.getContext(),"Item removed from your cart :"+listdata.get(position).getName(),Toast.LENGTH_LONG).show();
                removeAt(position);

             //   Uri uri = Uri.parse(myListData.getLink()); // missing 'http://' will cause crashed
              ///  Intent intent = new Intent(Intent.ACTION_VIEW, uri);
               // context1.startActivity(intent);
            }
        });
try {
    holder.decrease.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {




            Toast.makeText(view.getContext(), "Added to cart : " + listdata.get(position).getName(), Toast.LENGTH_LONG).show();


            //   Uri uri = Uri.parse(myListData.getLink()); // missing 'http://' will cause crashed
            ///  Intent intent = new Intent(Intent.ACTION_VIEW, uri);
            // context1.startActivity(intent);
        }
    });

    holder.increaase.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View view) {




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
        public TextView quantity;
        public TextView price;
        public MaterialButton increaase, decrease,remove ;
        final Context context ;
        public ViewHolder(View itemView) {
            super(itemView);
            context = itemView.getContext();
            this.name = (TextView) itemView.findViewById(R.id.name);
            this.quantity = (TextView) itemView.findViewById(R.id.quantity);
            this.price = (TextView) itemView.findViewById(R.id.price);
            this.increaase = (MaterialButton) itemView.findViewById(R.id.increase);
            this.decrease = (MaterialButton) itemView.findViewById(R.id.decrease);
            this.remove = (MaterialButton) itemView.findViewById(R.id.remove);

        }



    }


    public void removeAt(int position) {

    }

    public  void removeitem(final int position) {
        Query db ;
        FirebaseAuth mAuth = FirebaseAuth.getInstance();
        FirebaseUser user = mAuth.getCurrentUser();
        db = FirebaseDatabase.getInstance().getReference("User").child(user.getPhoneNumber()).child("cart") ;//orderByValue().equalTo(listdata.get(postion).Name);
        db.addListenerForSingleValueEvent(
                new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {

                        for (DataSnapshot postSnapshot : dataSnapshot.getChildren()) {

                            SubmenuDetail h1 = postSnapshot.getValue(SubmenuDetail.class);
                            if(h1.getName().equals(listdata.get(position).getName())){

                                postSnapshot.getRef().removeValue();
                                break;







                            }



                        }
                          //  dataSnapshot.getRef().removeValue();



                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {

                    }


                });



      /*  SlideshowFragment.price = SlideshowFragment.price - Integer.parseInt(listdata.get(position).getPrice());
        listdata.remove(position);
        SlideshowFragment.updatetotal();
        notifyItemRemoved(position);
        notifyItemRangeChanged(position, listdata.size());*/


    }


}
