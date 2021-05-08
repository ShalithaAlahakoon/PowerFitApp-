package com.example.powerfitex;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class HomeExercise extends AppCompatActivity {

    EditText inputSearch;
    RecyclerView recyclerView;
    FloatingActionButton floatingbtn;
    DatabaseReference databaseReference;
    FirebaseRecyclerOptions<Exercise> options;
    FirebaseRecyclerAdapter<Exercise,MyViewHolderEx>adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_exercise);

        databaseReference = FirebaseDatabase.getInstance().getReference("Exercise");
        inputSearch = findViewById(R.id.inputSearch);
        recyclerView = findViewById(R.id.recylerViewEx);
        floatingbtn = findViewById(R.id.floatingbtnAddEx);
        recyclerView.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
        recyclerView.setHasFixedSize(true);
        floatingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),AddNew.class));

            }
        });
        
        LoadData();
    }

    private void LoadData() {
        options = new FirebaseRecyclerOptions.Builder<Exercise>().setQuery(databaseReference,Exercise.class).build();
        adapter = new FirebaseRecyclerAdapter<Exercise, MyViewHolderEx>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolderEx holder, int position, @NonNull Exercise model) {
                holder.textViewName.setText(model.getExName());
                holder.textViewBPart.setText(model.getBodyPart());
                holder.v.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(HomeExercise.this,ViewAllExercise.class);
                        intent.putExtra("ExerciseKey",getRef(position).getKey());
                        startActivity(intent);
                    }
                });

            }

            @NonNull
            @Override
            public MyViewHolderEx onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.single_view_exercise,parent,false);
                return new MyViewHolderEx(v);
            }
        };
        adapter.startListening();
        recyclerView.setAdapter(adapter);

    }
}