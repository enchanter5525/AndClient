package com.example.tecnics.chat_application;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;

public class Register extends AppCompatActivity {

    String username;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        Intent intent = getIntent();
        this.username = intent.getStringExtra("username");

        Login login = new Login();
        login.connectToServer("Register.php",username);
        Toast.makeText(Register.this , "Registration successful "+username+".", Toast.LENGTH_LONG).show();
        Intent mainActiviity = new Intent(this, MainActivity.class);
        startActivity(mainActiviity);

    }
}
