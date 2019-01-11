package com.example.tecnics.chat_application;

import android.util.Log;
import android.widget.EditText;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectToWebServer {
    URL url;
    HttpURLConnection connection;
    InputStream inputStream;
    BufferedReader reader;
    String result;
    String line;
    String username;
    String receiverName;
    String senderName;
    String message;

    public String getResponseFromFile(String fileName,String username)
    {


        try
        {
            if (fileName.equals("DisplayMessageFromDatabase.php")) {
                url = new URL("http://192.241.244.177/ABMChatApplication/" + fileName + "?username=" + username);

            } else if (fileName.equals("Register.php")) {
                url = new URL("http://192.241.244.177/ABMChatApplication/" + fileName + "?username=" + username);
            } else {
                //EditText receiverNameObj = (EditText) findViewById(R.id.receiverName);
                //        snackBar();
                //String receiverName = receiverNameObj.getText().toString();
                Log.e("HI", receiverName);
                //EditText inputMessageObj = (EditText) findViewById(R.id.inputMessageTextBox);
                //String message = inputMessageObj.getText().toString();


                url = new URL("http://192.241.244.177/ABMChatApplication/" + fileName + "?sender=" + username + "&receiver=" + receiverName + "&message=" + message);
            }
            connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            inputStream = new BufferedInputStream(connection.getInputStream());
            //            setMessageInTextView("Error!");
            reader = new BufferedReader(new InputStreamReader(inputStream));
            StringBuilder builder = new StringBuilder();
            while ((line = reader.readLine()) != null) {
                builder.append(line);
            }
            inputStream.close();
            result = builder.toString();
//            setMessageInTextView(result);

            Log.d("Bhanu", builder.toString());
            return result;

        }
        catch (Exception exception)
        {
            Log.e("Hello", "Erorr!");

        }
        return "";



    }

    public void setUrlQueryStrings(String receiverName, String message)
    {
        this.receiverName = receiverName;
        this.message = message;


    }
}
