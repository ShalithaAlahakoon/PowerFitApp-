package com.example.powerfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;

import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

public class MemberPage extends AppCompatActivity {

     RecyclerView memberList;
     DatabaseReference dbRef;
     FirebaseRecyclerOptions<Member> options;
     FirebaseRecyclerAdapter<Member,MyViewHolder>adapter;
     EditText inputSearch;






    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_page);

        dbRef = FirebaseDatabase.getInstance().getReference().child("Member");
        dbRef.keepSynced(true);

        memberList = findViewById(R.id.membersRecycle);
        memberList.setHasFixedSize(true);
        memberList.setLayoutManager(new LinearLayoutManager(getApplicationContext()));

        inputSearch = findViewById(R.id.inputSearch);

        inputSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(s.toString()!=null) {

                    LoadData(s.toString());
                }
                else {
                    LoadData(" ");
                }
            }
        });

        LoadData("");

    }

    private void LoadData(String data) {
        Query quary = dbRef.orderByChild("name").startAt(data).endAt(data+"\uf8ff");

        options = new FirebaseRecyclerOptions.Builder<Member>().setQuery(quary,Member.class).build();
        adapter = new FirebaseRecyclerAdapter<Member, MyViewHolder>(options) {
            @Override
            protected void onBindViewHolder(@NonNull MyViewHolder holder, int position, @NonNull Member model) {
                    holder.cardName.setText(model.getName());
                    holder.cardEmail.setText(model.getEmail());
                    holder.cardMembership.setText(model.getMembership());

                    holder.v.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(MemberPage.this,MemberDetails.class);
                            intent.putExtra("MemberKey",getRef(position).getKey());
                            startActivity(intent);

                        }
                    });



            }

            @NonNull
            @Override
            public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

                View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.member_card,parent,false);
                return new MyViewHolder(v);
            }
        };
        adapter.startListening();
        memberList.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();


    }


    public void AddMembers(View view){
        Intent i =new Intent(this,AddMember.class);
        startActivity(i);


        Toast.makeText(this, "Loading Form...", Toast.LENGTH_SHORT).show();

    }
    public void MembersDetails(View view){
        Intent i =new Intent(this,MemberDetails.class);
        startActivity(i);


        Toast.makeText(this, "Loading Form...", Toast.LENGTH_SHORT).show();

    }



}