package com.example.healthyus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignInPage extends AppCompatActivity {

    String email;
    String password;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in_page);
        final EditText emailId = (EditText) findViewById(R.id.txtEmailAddress);
        final EditText passwordEntry = (EditText) findViewById(R.id.txtPassword);

        Button login = (Button) findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            final TextView warningDisplay = (TextView) findViewById(R.id.txtWarning);
            @Override
            public void onClick(View v) {
                email = emailId.getText().toString();
                password = passwordEntry.getText().toString();
                startActivity(new Intent(SignInPage.this, HomePage.class));


            }
        });
    }
}