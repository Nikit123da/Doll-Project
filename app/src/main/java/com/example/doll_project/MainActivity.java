package com.example.doll_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private ImageView logoIV;
    private Button login,singup;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        logoIV = findViewById(R.id.idIVLogo);

        logoIV.getBackground().setAlpha(120);

        login = findViewById(R.id.Login);

        singup = findViewById(R.id.Singup);

        logoIV.setBackgroundColor(getResources().getColor(android.R.color.transparent));

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Login_screen.class);
                startActivity(intent);
            }
        });

        singup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(MainActivity.this,Singup_screen.class);
                startActivity(intent);
            }
        });
    }



}
