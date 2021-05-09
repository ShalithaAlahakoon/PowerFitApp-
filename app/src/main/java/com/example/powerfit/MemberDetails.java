package com.example.powerfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MemberDetails extends AppCompatActivity {

    TextView detailsName,detailsEmail,detailsContact,detailsHeight,detailsWeight,detailsGender,detailsAmount;
    Button btnDelete,btnEdit;
    DatabaseReference ref,dbRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_details);

        detailsName = findViewById(R.id.detailsName);
        detailsEmail = findViewById(R.id.detailsEmail);

        detailsHeight = findViewById(R.id.detailsHeight);
        detailsWeight = findViewById(R.id.detailsWeight);
        detailsGender = findViewById(R.id.detailsGender);
        detailsAmount = findViewById(R.id.detailsAmount);

        btnDelete = findViewById(R.id.btnDelete);
        btnEdit = findViewById(R.id.btnEdit);
        ref = FirebaseDatabase.getInstance().getReference().child("Member");

        String MemberKey = getIntent().getStringExtra("MemberKey");
        dbRef = FirebaseDatabase.getInstance().getReference().child("Member").child(MemberKey);


        ref.child(MemberKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {

                if(snapshot.exists()){
                    String name = snapshot.child("name").getValue().toString();
                    String email = snapshot.child("email").getValue().toString();
                    String gender = snapshot.child("gender").getValue().toString();
                    String height = snapshot.child("height").getValue().toString();
                    String weight = snapshot.child("weight").getValue().toString();
                    String amount = snapshot.child("payement").getValue().toString();
                    String membership = snapshot.child("membership").getValue().toString();



                    detailsName.setText(name);
                    detailsEmail.setText(email);
                    detailsHeight.setText(height);
                    detailsGender.setText(gender);
                    detailsWeight.setText(weight);
                    detailsAmount.setText(amount);


                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                            Toast.makeText(getApplicationContext(),"DATA DELETED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),MemberPage.class));
                    }
                });
            }
        });
    }

}