package com.example.assignment3disystems;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.database.SQLException;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

import java.io.IOException;

public class LoginActivity extends AppCompatActivity {
    EditText username;
    TextInputEditText password;
    Button btnlogin;
    DatabaseHelper DB;

    String validateEmail = "[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        username = findViewById(R.id.username1);
        password = findViewById(R.id.password1);
        btnlogin = findViewById(R.id.btnsignin1);
        DB = new DatabaseHelper(this);


        String user = "walterwhite@gmail.com";
        String pass = "white1234";
        DB.insertData(user, pass);

        String user1 = "jessepinkman@gmail.com";
        String pass1 = "jesse1234";
        DB.insertData(user1, pass1);

        String user2 = "skylerwhite@gmail.com";
        String pass2 = "skyler1234";
        DB.insertData(user2, pass2);

        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String user = username.getText().toString();
                String pass = password.getText().toString();

                if(user.equals("")||pass.equals(""))
                    Toast.makeText(LoginActivity.this, "Please enter all the fields", Toast.LENGTH_SHORT).show();
                else{
                    if (user.trim().matches(validateEmail)) {

                        if (DB.checkusernamepassword(user, pass)) {
                            Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                            startActivity(intent);
                        } else {
                            Toast.makeText(LoginActivity.this, "Invalid Credentials", Toast.LENGTH_LONG).show();
                        }
                    } else {
                        Toast.makeText(LoginActivity.this, "Please enter Email Address properly", Toast.LENGTH_LONG).show();
                    }
                }
            }
        });
    }
}