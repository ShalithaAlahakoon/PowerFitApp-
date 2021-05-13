package com.example.powerfitex;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.text.TextUtilsCompat;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddNew extends AppCompatActivity {

    EditText  txtExID, txtExName, txtDetails;
    Spinner txtBdyPrt, txtEq;
    DatabaseReference dbRef;
    Exercise Exr;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new);

        txtExID =findViewById(R.id.editTextID);
        txtBdyPrt =findViewById(R.id.spinnerBPart);
        txtExName =findViewById(R.id.editTextName);
        txtEq =findViewById(R.id.spinnerEquipment);
        txtDetails =findViewById(R.id.editTextDetails);

        Exr = new Exercise();

    }

    public void Back (View view){

        Intent intent = new Intent(this, HomeExercise.class);
        startActivity(intent);
    }

    public void insert (View view){

        dbRef = FirebaseDatabase.getInstance().getReference().child("Exercise");

            try {
                if (TextUtils.isEmpty(txtExID.getText().toString()))
                    Toast.makeText(getApplicationContext(), "please enter id", Toast.LENGTH_SHORT).show();
                else if(TextUtils.isEmpty(txtExName.getText().toString()))
                    Toast.makeText(getApplicationContext(), "please enter name", Toast.LENGTH_SHORT).show();
                else {
                    Exr.setExID(txtExID.getText().toString().trim());
                    Exr.setBodyPart(txtBdyPrt.getSelectedItem().toString().trim());
                    Exr.setExName(txtExName.getText().toString().trim());
                    Exr.setEquipment(txtEq.getSelectedItem().toString().trim());
                    Exr.setDetails(txtDetails.getText().toString().trim());

                    dbRef.push().setValue(Exr);

                    Intent intent = new Intent(this, HomeExercise.class);
                    startActivity(intent);


                    Toast.makeText(getApplicationContext(), "Successfully Inserted", Toast.LENGTH_SHORT).show();
                    clearControls();
                }

        }
        catch (NumberFormatException e) {
            Toast.makeText(getApplicationContext(), "invalid" , Toast.LENGTH_SHORT ).show();
        }
    }

    private void clearControls() {

        txtExID.setText("");
        txtBdyPrt.setSelection(0);
        txtExName.setText("");
        txtEq.setSelection(0);
        txtDetails.setText("");

    }


}