package com.example.doll_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login_screen extends AppCompatActivity {

    EditText Email,Password;

    TextView singupintentButton;
    Button loginButton;

    private FirebaseAuth firebaseAuth;

    @SuppressLint({"MissingInflatedId", "WrongViewCast"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        LinearLayout linearLayout = findViewById(R.id.mainLayout);
        AnimationDrawable animationDrawable = (AnimationDrawable) linearLayout.getBackground();
        CardView divLayout = findViewById(R.id.Div);

        firebaseAuth = FirebaseAuth.getInstance();
        animationDrawable.setEnterFadeDuration(2500);
        animationDrawable.setExitFadeDuration(2500);
        animationDrawable.start();

        divLayout.setAlpha(0f);
        divLayout.setTranslationY(500);
        divLayout.animate().alpha(1f).translationYBy(-500).setDuration(1500);

        Email = findViewById(R.id.emailAdress);
        Password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        singupintentButton = findViewById(R.id.singUp);


        singupintentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                startActivity(new Intent(Login_screen.this,Singup_screen.class));
                finish();
            }
        });

        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email,password;
                email = String.valueOf(Email.getText());
                password = String.valueOf(Password.getText());

                if(TextUtils.isEmpty(email)) {
                    Toast.makeText(Login_screen.this,"Enter Email",Toast.LENGTH_SHORT).show();
                    return;
                }

                if(TextUtils.isEmpty(password)) {
                    Toast.makeText(Login_screen.this,"Enter Password",Toast.LENGTH_SHORT).show();
                    return;
                }



                firebaseAuth.signInWithEmailAndPassword(email,password)
                        .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if (task.isSuccessful()) {
                                    Toast.makeText(Login_screen.this, "registration successfull ", Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Login_screen.this, HomeScreen.class);
                                    intent.putExtra("email",email);
                                    startActivity(intent);

                                } else {
                                    Toast.makeText(Login_screen.this, "Authentication Failed", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
            }

        });


    }
}
