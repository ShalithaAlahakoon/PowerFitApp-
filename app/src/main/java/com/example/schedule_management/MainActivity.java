package com.example.schedule_management;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    private Button create;

    //Move Create page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        create = (Button) findViewById(R.id.BtnCreate);
        create.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               openInsertView();
            }
        });

        //Move View page
        findViewById(R.id.BtnView).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,ViewDetails.class);
                startActivity(intent);
            }
        });
    }
    public void openInsertView(){
        Intent intent = new Intent(this, InsertDeta.class);
        startActivity(intent);
    }

}