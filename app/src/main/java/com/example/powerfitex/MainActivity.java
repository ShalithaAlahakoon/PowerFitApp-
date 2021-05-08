package com.example.powerfitex;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import android.widget.Toast;



public class MainActivity extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

      }



    public void AddNewExercise (View view){

        Intent intent = new Intent(this, AddNew.class);
        startActivity(intent);

        Toast.makeText(this, "Add New Exercise......", Toast.LENGTH_SHORT).show();
    }

    public void ViewExercise (View view){

        Intent intent = new Intent(this, ViewExercises.class);
        startActivity(intent);

        Toast.makeText(this, "View All Exercises......", Toast.LENGTH_SHORT).show();
    }

}