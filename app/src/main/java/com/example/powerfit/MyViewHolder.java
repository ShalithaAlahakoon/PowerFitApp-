package com.example.powerfit;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolder  extends RecyclerView.ViewHolder {


    TextView cardName,cardEmail,cardMembership;
    View v;


    public MyViewHolder(@NonNull View itemView) {
        super(itemView);

        cardName = itemView.findViewById(R.id.cardName);
        cardEmail = itemView.findViewById(R.id.cardEmail);
        cardMembership =itemView.findViewById(R.id.cardMembership);

        v = itemView;

    }
}
