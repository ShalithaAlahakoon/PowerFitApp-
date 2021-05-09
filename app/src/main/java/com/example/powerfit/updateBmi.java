package com.example.powerfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class updateBmi extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_bmi);
    }

    public void SaveBMID(View view){
        Intent i = new Intent(this, com.example.powerfit.MainActivity.class);
        startActivity(i);
    }
}