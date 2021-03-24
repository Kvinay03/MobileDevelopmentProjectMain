package com.example.healthyus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class SignUpPage extends AppCompatActivity {

    String diseaseAllergyEntry;
    String medicationEntry;
    String userEmailAddress;
    String userPassword;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up_page);
        final EditText diseaseAndAllergies = (EditText) findViewById(R.id.txtHealthInfo);
        final EditText medications = (EditText) findViewById(R.id.txtMedication);
        final EditText emailIdCreate = (EditText) findViewById(R.id.txtEmailIdCreate);
        final EditText passwordCreate = (EditText) findViewById(R.id.txtPasswordCreate);
        Button buttonDirectToSignIn = (Button) findViewById(R.id.btnDirectToSignInPage);
        dbhelper dbhelper;
        dbhelper = new dbhelper(this);
        buttonDirectToSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                diseaseAllergyEntry = diseaseAndAllergies.getText().toString();
                medicationEntry = medications.getText().toString();
                userEmailAddress = emailIdCreate.getText().toString();
                userPassword = passwordCreate.getText().toString();
                    dbhelper.signUp(dbhelper.getWritableDatabase(), "Bob", "TheBuilder", "asdf", "asdfgmail.com", "6042431324", "1939-04-23");

                startActivity(new Intent(SignUpPage.this, SignInPage.class));
            }
        });
    }
}