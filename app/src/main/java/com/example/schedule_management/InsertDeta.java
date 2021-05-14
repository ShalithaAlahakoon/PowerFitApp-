package com.example.schedule_management;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class InsertDeta extends AppCompatActivity {

    EditText txtID,txtDate,txtDescription;
    Button btnSave,btnShow;
    DatabaseReference dbRef;
    schedule sch;
    Spinner txtSpinnerTime;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_deta);

        txtID = findViewById(R.id.txtID);
        txtDate = findViewById(R.id.txtDate);
        txtSpinnerTime = findViewById(R.id.txtSpinnerTime);
        txtDescription = findViewById(R.id.txtDescription);

        btnSave = findViewById(R.id.BtnSave);
        btnShow = findViewById(R.id.BtnShow);

        sch = new schedule();

        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dbRef = FirebaseDatabase.getInstance("https://schedule-management-9dd87-default-rtdb.firebaseio.com/").getReference().child("schedule");

                try{
                    if(TextUtils.isEmpty(txtID.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please enter an ID", Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(txtDate.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Date", Toast.LENGTH_SHORT).show();

                    else if(TextUtils.isEmpty(txtDescription.getText().toString()))
                        Toast.makeText(getApplicationContext(), "Please Enter a Description", Toast.LENGTH_SHORT).show();
                    else{
                        sch.setID(txtID.getText().toString().trim());
                        sch.setDate(txtDate.getText().toString().trim());
                        sch.setTime(txtSpinnerTime.getSelectedItem().toString());
                        sch.setDescription(txtDescription.getText().toString().trim());

                        //Insert in to the database
                        dbRef.push().setValue(sch);

                        //feedback to the user via toast
                        Toast.makeText(getApplicationContext(), "Data save successfully",Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                }
                catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"Please Enter Correct Details",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void clearControls(){
        txtID.setText("");
        txtDate.setText("");

        txtDescription.setText("");
    }


}