package com.example.powerfit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class searchBmi extends AppCompatActivity {

    DatabaseReference ref;
    private AutoCompleteTextView txtSearch;
    private RecyclerView listdata;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search_bmi);

        //CONNECT FIREBASE
        ref = FirebaseDatabase.getInstance("https://powerfit-e3cf8-default-rtdb.firebaseio.com/").getReference().child("BmiDetails");
        txtSearch = (AutoCompleteTextView) findViewById(R.id.txtSearch);
        listdata = (RecyclerView) findViewById(R.id.listData);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        listdata.setLayoutManager(layoutManager);

        populateSearch();
    }

    private void populateSearch() {

        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    ArrayList<String> names = new ArrayList<>();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        String n = ds.child("memberId").getValue(String.class);
                        names.add(n);

                    }
                    ArrayAdapter adapter = new ArrayAdapter(getApplicationContext(), android.R.layout.simple_list_item_1, names);
                    txtSearch.setAdapter(adapter);
                    txtSearch.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                        @Override
                        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                            String selection = parent.getItemAtPosition(position).toString();
                            getUsers(selection);
                        }
                    });
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        ref.addListenerForSingleValueEvent(eventListener);
    }

    private void getUsers(String selection) {

        Query query = ref.orderByChild("memberId").equalTo(selection);
        ValueEventListener eventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {

                    ArrayList<UserInfo> userInfos = new ArrayList<>();
                    for (DataSnapshot ds : dataSnapshot.getChildren()) {
                        UserInfo userInfo = new UserInfo(ds.child("memberId").getValue(String.class)
                                , ds.child("month").getValue(String.class), ds.getKey());
                        userInfos.add(userInfo);
                    }
                    MyAdapter adapter = new MyAdapter(userInfos, searchBmi.this);
                    listdata.setAdapter(adapter);

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        query.addListenerForSingleValueEvent(eventListener);
    }

    class UserInfo {
        public String memberId;
        public String month;
        public String key;

        public UserInfo(String memberId, String month, String key) {
            this.memberId = memberId;
            this.month = month;
            this.key = key;
        }

        public String getMemberId() {
            return memberId;
        }

        public String getMonth() {
            return month;
        }

        public String getKey() {
            return key;
        }
    }

    public static class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

        private ArrayList<UserInfo> mDataset;
        private Context mContext;

        public static class MyViewHolder extends RecyclerView.ViewHolder {
            TextView memberId;
            TextView month;

            public MyViewHolder(View v) {
                super(v);
                memberId = (TextView) v.findViewById(R.id.memberId);
                month = (TextView) v.findViewById(R.id.month);
            }
        }

        public MyAdapter(ArrayList<UserInfo> dataSet, Context mContext) {
            this.mDataset = dataSet;
            this.mContext = mContext;
        }

        @Override
        public MyAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

            LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
            View view = layoutInflater.inflate(R.layout.row_style, parent, false);
            return new MyViewHolder(view);
        }

        @Override
        public void onBindViewHolder(MyViewHolder holder, int position) {

            UserInfo thisuser = mDataset.get(position);
            holder.memberId.setText(thisuser.getMemberId());
            holder.month.setText(thisuser.getMonth());
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext,viewBmi.class);
                    intent.putExtra("key",thisuser.getKey());
                    mContext.startActivity(intent);
                }
            });
        }
        @Override
        public int getItemCount() {
            return mDataset.size();
        }
    }
}