package com.example.sanyamwhatsapp.Adapters;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sanyamwhatsapp.ChatDetailActivity;
import com.example.sanyamwhatsapp.Modles.Users;
import com.example.sanyamwhatsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class UserAdapter extends  RecyclerView.Adapter<UserAdapter.viewHolder>{

    ArrayList<Users> list;
    Context context;
    private Object Context;

    public UserAdapter(ArrayList<Users> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull

    @Override
    public viewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.sample_show_user,parent,false);
        return new viewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  UserAdapter.viewHolder holder, int position) {
        Users users = list.get(position);
        Picasso.get().load(users.getProfilePic()).placeholder(R.drawable.avtaar).into(holder.imageView);
        holder.useName.setText(users.getUserName());


        FirebaseDatabase.getInstance().getReference().child("chats").child(FirebaseAuth.getInstance().getUid()+users.getUserId())
                .orderByChild("time").limitToLast(1).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                if(snapshot.hasChildren()){
                    for (DataSnapshot snapshot1 : snapshot.getChildren()){
                        holder.lastMsg.setText(snapshot1.child("message").getValue(String.class));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(context, ChatDetailActivity.class);
                intent.putExtra("userId",users.getUserId());
                intent.putExtra("profilePic",users.getProfilePic());
                intent.putExtra("userName",users.getUserName());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class viewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView useName,lastMsg;
        public viewHolder(@NonNull  View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.profile_image);
            useName = itemView.findViewById(R.id.userNameList);
            lastMsg = itemView.findViewById(R.id.lastMsg);
        }
    }


}
