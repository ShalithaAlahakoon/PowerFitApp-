package com.example.powerfitex;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class MyViewHolderEx extends RecyclerView.ViewHolder {
    TextView textViewName, textViewBPart;
    View v;
    public MyViewHolderEx(@NonNull View itemView) {
        super(itemView);
        textViewName = itemView.findViewById(R.id.textView_single_ex_name);
        textViewBPart = itemView.findViewById(R.id.textView_single_ex_bp);
        v=itemView;
    }
}
