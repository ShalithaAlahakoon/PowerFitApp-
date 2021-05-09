package com.example.powerfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;

public class MainActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


    }

    public void AddBMID(View view){
        Intent i = new Intent(this, com.example.powerfit.addBmi.class);
        startActivity(i);
    }
    public void viewd(View view){
        Intent i6 = new Intent(this, com.example.powerfit.allview.class);
        startActivity(i6);
    }
    public void Search(View view){
        Intent i7 = new Intent(this, com.example.powerfit.searchBmi.class);
        startActivity(i7);
    }
}