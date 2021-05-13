package com.example.powerfit;

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

public class viewBmi extends AppCompatActivity {

    private TextView txtmemId;
    private TextView txtmonth;
    private EditText txtweight;
    private EditText txtheight;
    private TextView txtbmi;
    private Button btndelete , btnupdate;


    DatabaseReference ref , deleteref , up;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_bmi);


        txtmemId = (TextView) findViewById(R.id.editmem);
        txtmonth = (TextView) findViewById(R.id.editmonth1);
        txtweight = (EditText) findViewById(R.id.editweight);
        txtheight = (EditText) findViewById(R.id.editheight);
        txtbmi = (TextView) findViewById(R.id.editbmi1);
        btndelete = (Button) findViewById(R.id.btndelete);
        btnupdate = (Button) findViewById(R.id.btnupdate);

        Intent intent = getIntent();
        String key = intent.getStringExtra("key");

        ref = FirebaseDatabase.getInstance("https://powerfit-e3cf8-default-rtdb.firebaseio.com/").getReference("BmiDetails");
        Query query = ref.orderByKey().equalTo(key);

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists())
                {
                    for (DataSnapshot ds:dataSnapshot.getChildren())
                    {
                        txtmemId.setText(ds.child("memberId").getValue(String.class));
                        txtmonth.setText(ds.child("month").getValue(String.class));
                        txtweight.setText(ds.child("weight").getValue().toString());
                        txtheight.setText(ds.child("height").getValue().toString());
                        txtbmi.setText(ds.child("bmi").getValue().toString());
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addListenerForSingleValueEvent(eventListener);
        btndelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteUser(key);
            }
        });
        
    }

    private void deleteUser(String key) {

        deleteref = FirebaseDatabase.getInstance("https://powerfit-e3cf8-default-rtdb.firebaseio.com/").getReference("BmiDetails").child(key);
        deleteref.removeValue();
        Toast.makeText(getApplicationContext(), "DELETE SUCCESSFULL", Toast.LENGTH_SHORT).show();
        Intent i3 = new Intent(this, com.example.powerfit.MainActivity.class);
        startActivity(i3);
    }


}
