package com.example.ciphernetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.io.*;
import java.net.UnknownHostException;

public class MainServer extends AppCompatActivity
{
    //======NETWORKING=======
    ServerSocket serverSocket;
    Thread Thread1 = null;

    public static String SERVER_IP = "";
    public static final int SERVER_PORT = 8080;

    String message,userName;

    /////////////////
    TextView heading;
    EditText simple,cipher;
    RadioButton text,number;
    Button enDE,send;
    RelativeLayout relativeLayout;
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_server);

        heading = findViewById(R.id.heading);
        simple = findViewById(R.id.text1);
        cipher = findViewById(R.id.text2);
        enDE = findViewById(R.id.button);
        send = findViewById(R.id.send);
        text = findViewById(R.id.text);
        number = findViewById(R.id.number);
        relativeLayout = findViewById(R.id.rl);

        //======NETWORKING=======
        Bundle bundle = getIntent().getExtras();
        userName = bundle.getString("user");//INTENT

        Thread1 = new Thread(new Thread1());
        Thread1.start();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                message = cipher.getText().toString().trim();
                cipher.setText("");
                simple.setText("");
                if (!message.isEmpty()) {
                    new Thread(new Thread3(message)).start();
                }
            }
        });
        //////////////
        relativeLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainServer.this,"Don't touch Anywhere",Toast.LENGTH_SHORT).show();
            }
        });
        enDE.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (text.isChecked())
                {
                    Ceaser C = new Ceaser();
                    String s1 = simple.getText().toString();
                    String s2 = cipher.getText().toString();
                    if (s2.equals(""))//encryption condition
                    {
                        String s = simple.getText().toString();
                        String res = C.encryption(s);
                        cipher.setText(res);
                    } else if (s1.equals(""))//decryption condition
                    {
                        String s = cipher.getText().toString();
                        String res = C.decryption(s);
                        if(res.equals(""))
                            Toast.makeText(MainServer.this,"Select Appropriate Format..!",Toast.LENGTH_SHORT).show();
                        else
                            simple.setText(res);
                    } else
                    {
                        String s = simple.getText().toString();
                        String res = C.encryption(s);
                        cipher.setText(res);
                    }
                } else if (number.isChecked())
                {
                    MyEnDe M = new MyEnDe();
                    String s1 = simple.getText().toString();
                    String s2 = cipher.getText().toString();
                    if (s2.equals(""))//encryption condition
                    {
                        String s = simple.getText().toString();
                        String res = M.encryption(s);
                        cipher.setText(res);
                    } else if (s1.equals(""))
                    {
                        String s = cipher.getText().toString();
                        String res = M.decryption(s);
                        if(res.equals("100"))
                            Toast.makeText(MainServer.this,"Select Appropriate Format..!",Toast.LENGTH_SHORT).show();
                        else
                            simple.setText(res);
                    } else {
                        String s = simple.getText().toString();
                        String res = M.encryption(s);
                        cipher.setText(res);
                    }
                }
            }
        });

    }

    //=====NETWORKING=========

    private DataInputStream input  = null;
    private DataOutputStream output   = null;

    class Thread1 implements Runnable
    {
        @Override
        public void run()
        {
            Socket socket;
            try {
                serverSocket = new ServerSocket(SERVER_PORT);
                runOnUiThread(new Runnable() {

                    @Override
                    public void run() {
                        cipher.setText("Not Connected");
                        if(SERVER_IP.equals("0.0.0.0"))
                            SERVER_IP = "192.168.43.1";
                    }
                });
                try {
                    socket = serverSocket.accept();
                    input = new DataInputStream(new BufferedInputStream(socket.getInputStream()));
                    output = new DataOutputStream(socket.getOutputStream());

                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            cipher.setText("Connected\n");
                        }
                    });
                    new Thread(new Thread2()).start();

                    new Thread(new Thread3(userName+" Connected")).start();//important step for getting usernme of another person connected

                } catch (IOException e) {
                    e.printStackTrace();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private class Thread2 implements Runnable
    {
        //int count=-1;
        @Override
        public void run()
        {
            while (true)
            {
                try {
                    final String message = input.readUTF();

                    if (message != null) {
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                cipher.setText( message );
                            }
                        });
                    } else {
                        Thread1 = new Thread(new Thread1());
                        Thread1.start();
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                    //Toast.makeText(MainServer.this," "+message,Toast.LENGTH_SHORT).show();
                //count++;
            }
        }
    }
    class Thread3 implements Runnable
    {
        private String message;

        Thread3(String message) {
            this.message = message;
        }

        @Override
        public void run() {
            try
            {
                output.writeUTF(message);
                output.flush();}catch(IOException e){  e.printStackTrace();;  }
        }
    }

    //////////////////

    public void clear(View view)
    {
        simple.setText("");
        cipher.setText("");
        Toast.makeText(MainServer.this,"Clear All....",Toast.LENGTH_SHORT).show();
    }

    public void thanks(View view)
    {
        Toast.makeText(MainServer.this,"Thank You....",Toast.LENGTH_SHORT).show();
    }
}
