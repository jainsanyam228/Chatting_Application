package com.example.sanyamwhatsapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.sanyamwhatsapp.Modles.Users;
import com.example.sanyamwhatsapp.databinding.ActivitySettingBinding;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.HashMap;

public class SettingActivity extends AppCompatActivity {
    ActivitySettingBinding binding;
FirebaseStorage storage;
FirebaseAuth auth;
FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySettingBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        getSupportActionBar().hide();
        storage = FirebaseStorage.getInstance();
        auth = FirebaseAuth.getInstance();
        database = FirebaseDatabase.getInstance();

        binding.backarrow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent( SettingActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
        binding.savebtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String status = binding.etAbout.getText().toString();
                String userName = binding.etUserName.getText().toString();
                HashMap<String, Object> obj = new HashMap<>();
                obj.put("userName" , userName);
                obj.put("status" , status);

                database.getReference().child("User").child(FirebaseAuth.getInstance().getUid()).updateChildren(obj);
                Toast.makeText(SettingActivity.this,"Profile Updated",Toast.LENGTH_LONG).show();

            }
        });

        database.getReference().child("User").child(FirebaseAuth.getInstance().getUid()).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull  DataSnapshot snapshot) {
                Users users = snapshot.getValue(Users.class);
                Picasso.get().load(users.getProfilePic()).placeholder(R.drawable.avtaar).into(binding.profileImage);

                binding.etAbout.setText(users.getStatus());
                binding.etUserName.setText(users.getUserName());
            }

            @Override
            public void onCancelled(@NonNull  DatabaseError error) {

            }
        });
        binding.plus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setAction(Intent.ACTION_GET_CONTENT);
                intent.setType("image/*");
                startActivityForResult(intent,33);

            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data.getData() != null) {

            Uri sFile = data.getData();
            binding.profileImage.setImageURI(sFile);

            final StorageReference reference = storage.getReference().child("profile picture").child(FirebaseAuth.getInstance().getUid());
            reference.putFile(sFile).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                   reference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                       @Override
                       public void onSuccess(Uri uri) {
                           database.getReference().child("User").child(FirebaseAuth.getInstance().getUid()).child("profilePic").setValue(uri.toString());
                           Toast.makeText(SettingActivity.this,"Update",Toast.LENGTH_LONG);
                       }
                   });
                }
            });
        }
    }
}