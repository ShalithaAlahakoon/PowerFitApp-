package com.example.schedule_management;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;

public class ViewDetails extends AppCompatActivity {

    private TextView txtID;
    private TextView txtDate;
    private TextView txtTime;
    private TextView txtDes;

    private Button btndelete;


    DatabaseReference ref, deleteref, up;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_details);


        txtID = (TextView) findViewById(R.id.editID);
        txtDate = (TextView) findViewById(R.id.editDate);
        txtTime = (TextView) findViewById(R.id.editTime);
        txtDes = (TextView) findViewById(R.id.editDes);

        btndelete = (Button) findViewById(R.id.btndelete);


        Intent intent = getIntent();
        String key = intent.getStringExtra("key");

        ref = FirebaseDatabase.getInstance("https://schedule-management-9dd87-default-rtdb.firebaseio.com/").getReference("schedule");


        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        txtID.setText(ds.child("ID").getValue(String.class));
                        txtDate.setText(ds.child("Date").getValue(String.class));
                        txtTime.setText(ds.child("Time").getValue().toString());
                        txtDes.setText(ds.child("Description").getValue().toString());

                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };

        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteref = FirebaseDatabase.getInstance("https://schedule-management-9dd87-default-rtdb.firebaseio.com/").getReference("schedule").child("-M_Ti2isLox5rfqdvq49");
                deleteref.removeValue();
                Toast.makeText(getApplicationContext(), "DELETE SUCCESSFULL", Toast.LENGTH_SHORT).show();

            }
        });

        findViewById(R.id.btndelete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ViewDetails.this,delete_page.class);
                startActivity(intent);
            }
        });


    }
}