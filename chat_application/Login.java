package com.example.tecnics.chat_application;

import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;

public class Login extends AppCompatActivity {
    URL url;
    HttpURLConnection connection;
    InputStream inputStream;
    BufferedReader reader;
    String result;
    String line;
    String username;
    private Button connectBtn;
    ConnectToWebServer conn = new ConnectToWebServer();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        This line is important
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().permitNetwork().build());
        setContentView(R.layout.activity_login);
//        ##################I wrote#####################
//        StringBuilder sb=null;
        Intent intent = getIntent();
        this.username = intent.getStringExtra("username");
        printToast(username);
        //working fine.
        String response = conn.getResponseFromFile("DisplayMessageFromDatabase.php",username);
//        Creating action for Connect Btuuon
        connectBtn = (Button)findViewById(R.id.connectBtn);
        connectBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                EditText inputMessageTextObj = (EditText)findViewById(R.id.inputMessageTextBox);
                inputMessageTextObj.setVisibility(v.VISIBLE);
            }
        });


//        #########################################################
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                printToast("hi");
                // Writing this for better modularization.
                EditText receiverNameObj = (EditText) findViewById(R.id.receiverName);
                //        snackBar();
                String receiverName = receiverNameObj.getText().toString();
                Log.e("HI", receiverName);
                EditText inputMessageObj = (EditText) findViewById(R.id.inputMessageTextBox);
                String message = inputMessageObj.getText().toString();
                conn.setUrlQueryStrings(receiverName, message);
                String response = conn.getResponseFromFile("SendMessage.php", username);
                //#############################################33
//                connectToServer("SendMessage.php",username);
                snackBar("Message sent.",view);

                EditText inputMessageTextObj = (EditText)findViewById(R.id.inputMessageTextBox);
                inputMessageTextObj.setText("");
//


            }
        });
    }

    public void printToast(String message)
    {
        Toast.makeText(Login.this, message, Toast.LENGTH_LONG).show();

    }
    public void snackBar(String message,View view)
    {
        Snackbar.make(view, message, Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
    }
    public void setMessageInTextView(String message)
    {
        TextView receivedMessageObj = findViewById(R.id.receivedMessage);
        receivedMessageObj.setText(message);
    }
    public void log(String message)
    {
        Log.e("Tag",message);
    }
}
