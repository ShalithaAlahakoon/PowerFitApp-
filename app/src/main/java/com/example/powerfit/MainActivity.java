package com.example.powerfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
    public void Members(View view){
        Intent i =new Intent(this,MemberPage.class);
        startActivity(i);


        Toast.makeText(this, "You just clicked the Member button", Toast.LENGTH_SHORT).show();

    }
}