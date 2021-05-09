package com.example.powerfit;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.w3c.dom.Text;

public class AddMember extends AppCompatActivity {

    EditText Name,Contact,Email,Height,Weight,Amount;
    Spinner MembershipType;
    RadioButton male,female;
    Button btnSave;
    DatabaseReference dbRef;
    Member member;
    String Gender;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_member);

        Name = findViewById(R.id.editTxtName);
        Contact = findViewById(R.id.editTxtContact);
        Email = findViewById(R.id.editTxtEmail);
        Height = findViewById(R.id.editTxtHeight);
        Weight = findViewById(R.id.editTextWeight);
        Amount = findViewById(R.id.editTxtPayement);
        MembershipType = findViewById(R.id.spinner2);

        male = findViewById(R.id.radioButtonMale);
        female = findViewById(R.id.radioButtonFemale);


        btnSave = findViewById(R.id.BtnSave);
        member = new Member();


        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                dbRef = FirebaseDatabase.getInstance("https://powerfit-1ff1e-default-rtdb.firebaseio.com/").getReference().child("Member");
                try {
                    if (TextUtils.isEmpty(Name.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter Name",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(Email.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter Email",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(Height.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter Height",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(Contact.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter contact ",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(Weight.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter Weight ",Toast.LENGTH_SHORT).show();
                    else if(TextUtils.isEmpty(Amount.getText().toString()))
                        Toast.makeText(getApplicationContext(),"Please Enter Amount ",Toast.LENGTH_SHORT).show();
                    else{


                        member.setName(Name.getText().toString().trim());
                        member.setEmail(Email.getText().toString().trim());
                        member.setGender(Gender);
                        member.setMembership(MembershipType.getSelectedItem().toString());
                        member.setHeight(Integer.parseInt(Height.getText().toString().trim()));
                        member.setWeight(Integer.parseInt(Weight.getText().toString().trim()));
                        member.setPayement(Integer.parseInt(Amount.getText().toString().trim()));

                        String m1 =male.getText().toString();
                        String m2 =female.getText().toString();

                        if(male.isChecked()){
                            member.setGender(m1);
                        }
                        else if(female.isChecked()){
                            member.setGender(m2);
                        }
                       dbRef.push().setValue(member);



                        Toast.makeText(getApplicationContext(),"DATA SAVED SUCCESSFULLY",Toast.LENGTH_SHORT).show();
                        clearControls();
                    }
                }catch(NumberFormatException e){
                    Toast.makeText(getApplicationContext(),"invalid contact number",Toast.LENGTH_SHORT).show();
                }

            }
        });

    }

    //method to clear all user inputs

    private void clearControls(){

        Name.setText("");
        Email.setText("");
        Contact.setText("");
        Height.setText("");
        Weight.setText("");
        Gender=("");
        Amount.setText("");
    }
}