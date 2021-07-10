package com.example.sanyamwhatsapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.example.sanyamwhatsapp.Adapters.ChatAdapter;
import com.example.sanyamwhatsapp.Modles.MessagesModal;
import com.example.sanyamwhatsapp.databinding.ActivityGroupChatBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Date;

public class GroupChat extends AppCompatActivity {
    ActivityGroupChatBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityGroupChatBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();

        binding.back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(GroupChat.this,MainActivity.class);
                startActivity(intent);
            }
        });

        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final  String senderId = FirebaseAuth.getInstance().getUid();
        binding.userName.setText("Friends Group");
        final ArrayList<MessagesModal> messagesModals = new ArrayList<>();
        final ChatAdapter adapter = new ChatAdapter(messagesModals,this);
        binding.chatResyclerView.setAdapter(adapter);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        binding.chatResyclerView.setLayoutManager(layoutManager);

        database.getReference().child("Group chats").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                messagesModals.clear();
                for (DataSnapshot snapshot1 : snapshot.getChildren()){
                    MessagesModal model = snapshot1.getValue(MessagesModal.class);
                    messagesModals.add(model);
                }
                adapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        binding.sent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String message = binding.writemsg.getText().toString();
                final MessagesModal modal = new MessagesModal(senderId , message);
                modal.setTime(new Date().getTime());
                binding.writemsg.setText("");

                database.getReference().child("Group chats").push().setValue(modal).addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {

                    }
                });
            }
        });
    }
}