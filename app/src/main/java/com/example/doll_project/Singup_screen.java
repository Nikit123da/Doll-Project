package com.example.doll_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Map;


public class Singup_screen extends AppCompatActivity {

    EditText loginEmailAdress,loginPassword,loginPasswordConformation,phoneNumber,name;
    TextView singInIntent;
    Button SingUpButton;

    FirebaseFirestore db;
    private FirebaseAuth firebaseAuth;

    @SuppressLint("MissingInflatedId")
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_singup_screen);

        firebaseAuth = FirebaseAuth.getInstance();

        loginEmailAdress = findViewById(R.id.loginEmailAdress);
        phoneNumber = findViewById(R.id.phoneNumber);
        name = findViewById(R.id.name);
        loginPassword = findViewById(R.id.loginPassword);
        loginPasswordConformation = findViewById(R.id.loginPasswordConformation);

        db = FirebaseFirestore.getInstance();


        singInIntent = findViewById(R.id.singInIntent);

        SingUpButton = findViewById(R.id.SingUpButton);
        //sharedPreferences = getSharedPreferences(SHARED_PREF_NAME, MODE_PRIVATE);

            singInIntent.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    startActivity(new Intent(Singup_screen.this,Login_screen.class));
                    finish();
                }
            });



                SingUpButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v)

                    {

                        if(TextUtils.isEmpty(loginPassword.getText()) || TextUtils.isEmpty(loginEmailAdress.getText()) || TextUtils.isEmpty(phoneNumber.getText())
                                || TextUtils.isEmpty(name.getText()) || TextUtils.isEmpty(loginPasswordConformation.getText()))

                        {
                            Toast.makeText(Singup_screen.this, "Please fill the empty field", Toast.LENGTH_SHORT).show();
                        }

                        else {

                            registerNewUser();

                            addDataToFirestore(name.getText().toString(),loginEmailAdress.getText().toString(),phoneNumber.getText().toString(), null);
                        }
                    }
                });
            }






        private void addDataToFirestore(String Name, String Email, String Phone, Map<String, int[]> map) {

            // creating a collection reference
            // for our Firebase Firestore database.
            CollectionReference dbUsers = db.collection("users");

            // adding our data to our courses object class.
            User user = new User(Name, Email, Phone, map);

            // below method is use to add data to Firebase Firestore.
            dbUsers.add(user).
                    addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                @Override
                public void onSuccess(DocumentReference documentReference) {
                    // after the data addition is successful
                    // we are displaying a success toast message.
                    Toast.makeText(Singup_screen.this, "Your User has been added to Firebase Firestore", Toast.LENGTH_SHORT).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // this method is called when the data addition process is failed.
                    // displaying a toast message when data addition is failed.
                    Toast.makeText(Singup_screen.this, "Fail to add course \n" + e, Toast.LENGTH_SHORT).show();
                }
            });
        }




        private void registerNewUser() {

            // Take the value of two edit texts in Strings
            String email, password, loginPasswordConformation;

            email = loginEmailAdress.getText().toString();
            password = loginPassword.getText().toString();
            //loginPasswordConformation = loginPasswordConformation




            // Validations for input email and password
            if (TextUtils.isEmpty(email)) {
                Toast.makeText(getApplicationContext(),
                                "Please enter email!!",
                                Toast.LENGTH_LONG)
                        .show();
                return;
            }
            if (TextUtils.isEmpty(password)) {
                Toast.makeText(getApplicationContext(),
                                "Please enter password!!",
                                Toast.LENGTH_LONG)
                        .show();
                return;
            }

            /*if () {
                Toast.makeText(getApplicationContext(),
                                "Please enter password!!",
                                Toast.LENGTH_LONG)
                        .show();
                return;
            }

             */

            // create new user or register new user
            firebaseAuth
                    .createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(new OnCompleteListener<AuthResult>() {

                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                Toast.makeText(getApplicationContext(),
                                                "Registration successful!",
                                                Toast.LENGTH_LONG)
                                        .show();

                                // if the user created intent to login activity
                                Intent intent
                                        = new Intent(Singup_screen.this,
                                        MainActivity.class);
                                startActivity(intent);
                            } else {

                                // Registration failed
                                Toast.makeText(
                                                getApplicationContext(),
                                                "Registration failed!!"
                                                        + " Please try again later",
                                                Toast.LENGTH_LONG)
                                        .show();

                            }
                        }
                    });
        }
}







