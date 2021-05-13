package com.example.powerfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.powerfit.Database.BmiDetails;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.security.PublicKey;
import java.util.ArrayList;

public class allview extends AppCompatActivity {


    DatabaseReference dbRead;
    private ListView listdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_allview);


        listdata = (ListView) findViewById(R.id.listData);

        //CONNECT FIREBASE
        dbRead = FirebaseDatabase.getInstance("https://powerfit-e3cf8-default-rtdb.firebaseio.com/").getReference().child("BmiDetails");

        ValueEventListener event = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                readData(dataSnapshot);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        dbRead.addListenerForSingleValueEvent(event);

    }

    private void readData(DataSnapshot dataSnapshot) {
        if (dataSnapshot.exists()) {
            ArrayList<String> listusers = new ArrayList<>();
            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                BmiDetails btnew = new BmiDetails(ds.child("memberId").getValue().toString(), ds.child("month").getValue().toString(), ds.child("weight").getValue(float.class), ds.child("height").getValue(float.class), ds.child("bmi").getValue(float.class));
                listusers.add("\nMember Id : " + btnew.getMemberId() + "\nMonth : " + btnew.getMonth() + "\nWeight : " + btnew.getWeight() + " Kg" + "\nHeight : " + btnew.getHeight() + " CM" + "\nBMI Value : " + btnew.getBmi() + "\n");
            }

            ArrayAdapter arrayAdapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, listusers);
            listdata.setAdapter(arrayAdapter);

        } else {
            Log.d("BmiDetails", "no data available");
        }
    }

    public void back(View view) {
        Intent i31 = new Intent(this, com.example.powerfit.MainActivity.class);
        startActivity(i31);

    }
}
