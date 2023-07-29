package com.example.quizapp;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.appcompat.app.AppCompatActivity;

public class RegistrationActivity extends AppCompatActivity {

    private ImageView imageView;
    private EditText editTextUsername;
        private EditText editTextPassword;
        private Button buttonRegister;

        private DatabaseHelper databaseHelper;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_registration);

            databaseHelper = new DatabaseHelper(this);

           ImageView imageView = findViewById(R.id.imageView);;
            editTextUsername = findViewById(R.id.editTextUsername);
            editTextPassword = findViewById(R.id.editTextPassword);
            buttonRegister = findViewById(R.id.buttonRegister);

            buttonRegister.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String username = editTextUsername.getText().toString().trim();
                    String password = editTextPassword.getText().toString().trim();

                    if (username.isEmpty() || password.isEmpty()) {
                        Toast.makeText(RegistrationActivity.this, "Please enter all fields", Toast.LENGTH_SHORT).show();
                    } else {
                        boolean result = databaseHelper.insertUser(username, password);
                        if (result) {
                            Toast.makeText(RegistrationActivity.this, "Registration successful", Toast.LENGTH_SHORT  ).show();
                            Intent intent = new Intent(RegistrationActivity.this, LoginActivity.class);
                            startActivity(intent);
                            finish();
                        } else {
                            Toast.makeText(RegistrationActivity.this, "Registration failed", Toast.LENGTH_SHORT).show();
                        }
                    }
                }
            });
        }
    }


