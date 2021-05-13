package com.example.powerfitex;

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


public class ViewAllExercise extends AppCompatActivity {

    TextView txtViewNameE,txtViewNameE1, txtViewIdE, txtViewBPE, txtViewEQE, txtViewDetailsE;
    Button btnUpdateE , btnDeleteE;
    DatabaseReference dbRef,Dataref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_exercise);

        txtViewNameE = findViewById(R.id.viewExName);
        txtViewNameE1 = findViewById(R.id.viewExName1);
        txtViewIdE = findViewById(R.id.viewExId);
        txtViewBPE = findViewById(R.id.viewExBodyPart);
        txtViewEQE = findViewById(R.id.viewExEq);
        txtViewDetailsE = findViewById(R.id.viewExDetails);
        btnDeleteE = findViewById(R.id.btnDeleteEx);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Exercise");

        String ExerciseKey = getIntent().getStringExtra("ExerciseKey");
        Dataref = FirebaseDatabase.getInstance().getReference().child("Exercise").child(ExerciseKey);

        dbRef.child(ExerciseKey).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists())
                {
                    String exName = snapshot.child("exName").getValue().toString();
                    String exName1 = snapshot.child("exName").getValue().toString();
                    String exId = snapshot.child("exID").getValue().toString();
                    String exBodyPart = snapshot.child("bodyPart").getValue().toString();
                    String exEq = snapshot.child("equipment").getValue().toString();
                    String exDetails = snapshot.child("details").getValue().toString();

                    txtViewNameE.setText(exName);
                    txtViewNameE1.setText(exName1);
                    txtViewIdE.setText(exId);
                    txtViewBPE.setText(exBodyPart);
                    txtViewEQE.setText(exEq);
                    txtViewDetailsE.setText(exDetails);
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

        btnDeleteE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dataref.removeValue().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(ViewAllExercise.this, "Successfully Deleted", Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),HomeExercise.class));
                    }
                });
            }
        });


    }
}