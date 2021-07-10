package com.example.sanyamwhatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import com.example.sanyamwhatsapp.Adapters.ChatAdapter;
import com.example.sanyamwhatsapp.Modles.MessagesModal;
import com.example.sanyamwhatsapp.databinding.ActivityChatDetailBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;

public class ChatDetailActivity extends AppCompatActivity {
   ActivityChatDetailBinding binding;
    FirebaseDatabase database ;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        binding = ActivityChatDetailBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        database = FirebaseDatabase.getInstance();
        auth = FirebaseAuth.getInstance();

        final String  senderId = auth.getUid();
        String reciverId = getIntent().getStringExtra("userId");
        String username = getIntent().getStringExtra("userName");
        String profilePic = getIntent().getStringExtra("profilePic");

      binding.userName.setText(username);

        Picasso.get().load(profilePic).placeholder(R.drawable.avtaar).into(binding.profileImage);


        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent  intent = new Intent(ChatDetailActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        final ArrayList<MessagesModal> messagesModals = new ArrayList<>();
        final ChatAdapter chatAdapter = new ChatAdapter(messagesModals,this,reciverId);
        binding.chatResyclerView.setAdapter(chatAdapter);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.chatResyclerView.setLayoutManager(linearLayoutManager);


        final  String senderRoom = senderId+reciverId;
        final  String receiverRoom = reciverId+senderId;

        database.getReference().child("chats").child(senderRoom).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                messagesModals.clear(); 
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    MessagesModal model = snapshot1.getValue(MessagesModal.class);

                    model.setMessageID(snapshot1.getKey());

                    messagesModals.add(model);
                }
                chatAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });


        binding.sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
             String msg =    binding.writemsg.getText().toString();
             final MessagesModal modal = new MessagesModal(senderId,msg);
             modal.setTime(new Date().getTime());
             binding.writemsg.setText("");
             database.getReference().child("chats").child(senderRoom).push().setValue(modal).addOnSuccessListener(new OnSuccessListener<Void>() {
                 @Override
                 public void onSuccess(Void unused) {
                     database.getReference().child("chats").child(receiverRoom).push().setValue(modal).addOnSuccessListener(new OnSuccessListener<Void>() {
                         @Override
                         public void onSuccess(Void unused) {

                         }
                     });
                 }
             });
            }
        });


    }
}