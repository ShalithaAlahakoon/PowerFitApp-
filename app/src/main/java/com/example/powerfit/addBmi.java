package com.example.powerfit;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.powerfit.Database.BmiDetails;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class addBmi extends AppCompatActivity {

    public static final String EXTRA_Message = "com.example.powerfit";


    EditText Weight, Height, MemId;
    Button btnadd;
    DatabaseReference dbRef;
    BmiDetails btd;
    Spinner Month;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bmi);


        Spinner spinner = (Spinner) findViewById(R.id.spinner2);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.names, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        MemId = findViewById(R.id.memId);
        Month = findViewById(R.id.spinner2);
        Weight = findViewById(R.id.weight);
        Height = findViewById(R.id.height);


        float wei, hei;
        wei = Float.parseFloat(Weight.getText().toString());
        hei = Float.parseFloat(Height.getText().toString());

        float bmi = wei / (hei * hei);

        btnadd = findViewById(R.id.buttonadd);

        btd = new BmiDetails();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance("https://powerfit-e3cf8-default-rtdb.firebaseio.com/").getReference().child("BmiDetails");
                try {
                    if (TextUtils.isEmpty(MemId.getText().toString()))
                        Toast.makeText(getApplicationContext(), "please enter id", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(Month.getSelectedItem().toString()))
                        Toast.makeText(getApplicationContext(), "please enter month", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(Weight.getText().toString()))
                        Toast.makeText(getApplicationContext(), "please enter weight", Toast.LENGTH_SHORT).show();
                    else if (TextUtils.isEmpty(Height.getText().toString()))
                        Toast.makeText(getApplicationContext(), "please enter height", Toast.LENGTH_SHORT).show();
                    else {
                                btd.setMemberId(MemId.getText().toString().trim());
                                btd.setMonth(Month.getSelectedItem().toString().trim());
                                btd.setWeight(Float.parseFloat(Weight.getText().toString().trim()));
                                btd.setHeight(Float.parseFloat(Height.getText().toString().trim()));
                                btd.setBmi(bmi);

                                dbRef.push().setValue(btd);
                                Toast.makeText(getApplicationContext(), "Insert successful", Toast.LENGTH_SHORT).show();
                                clearControls();

                    }
                } catch (NumberFormatException e) {
                    Toast.makeText(getApplicationContext(), "invalid", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    private void clearControls() {

        MemId.setText("");
        Month.setSelection(0);
        Weight.setText("");
        Height.setText("");

        Intent i3 = new Intent(this, com.example.powerfit.MainActivity.class);
        startActivity(i3);

    }

}
