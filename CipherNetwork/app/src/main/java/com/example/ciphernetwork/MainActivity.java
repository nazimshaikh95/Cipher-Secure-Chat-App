package com.example.ciphernetwork;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {
    Button sender,reciever;
    EditText uname;
    String unm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        sender = findViewById(R.id.sender);
        reciever = findViewById(R.id.reciever);
        uname = findViewById(R.id.uname);

        sender.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unm = uname.getText().toString().trim() ;
                Intent intent = new Intent( MainActivity.this, MainServer.class);
                Bundle bundle = new Bundle();
                bundle.putString("user",unm);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        reciever.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                unm = uname.getText().toString().trim() ;
                Intent intent = new Intent( MainActivity.this, MainClient.class);
                Bundle bundle = new Bundle();
                bundle.putString("user",unm);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}
