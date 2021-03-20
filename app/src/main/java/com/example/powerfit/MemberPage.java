package com.example.powerfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class MemberPage extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_member_page);
    }
    public void AddMembers(View view){
        Intent i =new Intent(this,AddMember.class);
        startActivity(i);


        Toast.makeText(this, "Loading Form...", Toast.LENGTH_SHORT).show();

    }
}