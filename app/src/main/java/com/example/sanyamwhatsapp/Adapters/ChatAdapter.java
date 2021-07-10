package com.example.sanyamwhatsapp.Adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.sanyamwhatsapp.Modles.MessagesModal;
import com.example.sanyamwhatsapp.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class ChatAdapter extends RecyclerView.Adapter{
    ArrayList<MessagesModal> messagesModals;
    Context context;
    String recId;


    int SENDER_VIEW_TYPE =1;
    int RECEIVER_VIEW_TYPE =2;

    public ChatAdapter(ArrayList<MessagesModal> messagesModals, Context context) {
        this.messagesModals = messagesModals;
        this.context = context;
    }

    public ChatAdapter(ArrayList<MessagesModal> messagesModals, Context context, String recId) {
        this.messagesModals = messagesModals;
        this.context = context;
        this.recId = recId;
    }

    @NonNull

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull  ViewGroup parent, int viewType) {
        if(viewType==SENDER_VIEW_TYPE){
            View view = LayoutInflater.from(context).inflate(R.layout.sample_sander,parent,false);
            return new SenderViewHolder(view);
        }
        else {
            View view = LayoutInflater.from(context).inflate(R.layout.sample_reciver,parent,false);
            return new RecieverViewHolder(view);

        }
    }

    @Override
    public int getItemViewType(int position) {

        if(messagesModals.get(position).getUid().equals(FirebaseAuth.getInstance().getUid()))
        {
            return SENDER_VIEW_TYPE;
        }else{
            return RECEIVER_VIEW_TYPE;
        }


    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        MessagesModal messagesModal = messagesModals.get(position);

        holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                new AlertDialog.Builder(context).setTitle("Delete").setMessage("Are you sure you want to delete this message")
                        .setPositiveButton("yes", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                FirebaseDatabase database = FirebaseDatabase.getInstance();
                                String sender = FirebaseAuth.getInstance().getUid() + recId;
                                database.getReference().child("chats").child(sender).child(messagesModal.getMessageID()).setValue(null);

                            }
                        }).setNegativeButton("No", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                }).show();

                return false;
            }
        });

        if(holder.getClass()==SenderViewHolder.class){
            ((SenderViewHolder)holder).senderMsg.setText(messagesModal.getMessage());

        }else{
            ((RecieverViewHolder)holder).receiverMsg.setText(messagesModal.getMessage());
        }
    }

    @Override
    public int getItemCount() {
        return messagesModals.size();
    }


    public  class RecieverViewHolder extends RecyclerView.ViewHolder {
        TextView receiverMsg , receiverTime;
        public RecieverViewHolder(@NonNull View itemView) {
            super(itemView);
            receiverMsg= itemView.findViewById(R.id.recMsg);
            receiverTime= itemView.findViewById(R.id.recTime);
        }
    }
    public  class SenderViewHolder extends RecyclerView.ViewHolder {
        TextView senderMsg , senderTime;
        public SenderViewHolder(@NonNull View itemView) {
            super(itemView);
            senderMsg = itemView.findViewById(R.id.senderTxt);
            senderTime= itemView.findViewById(R.id.senderTime);
        }
    }

}
