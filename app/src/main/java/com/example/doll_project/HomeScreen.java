package com.example.doll_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.auth.FirebaseAuth;

public class HomeScreen extends AppCompatActivity implements BottomNavigationView.OnItemSelectedListener, RecyclerViewInterface {

        BottomNavigationView bottomNavigationView;
        String email;
        RecyclerViewInterface recyclerViewInterface;
        View view;

        int i = 0;

        FirebaseAuth mAuth;

        @SuppressLint("CutPasteId")
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_home_screen);


            bottomNavigationView = findViewById(R.id.bottomNavigationView);

            bottomNavigationView.setOnItemSelectedListener(this);
            bottomNavigationView.setSelectedItemId(R.id.home);

            //Adapter adapter = new Adapter(KidsArrayList.getKidsData(), this,recyclerViewInterface);

            mAuth = FirebaseAuth.getInstance();



        //RecyclerView recycle = findViewById(R.id.recycleview);
        //recycle.setAdapter(adapter);
            BottomNavigationView bottomNav = findViewById(R.id.bottomNavigationView);
        //recycle.setLayoutManager(new LinearLayoutManager((this)));
            Intent i = getIntent();
            email = i.getStringExtra("email");

            PlayBackgroundSound(view);
            if (FirebaseAuth.getInstance().getCurrentUser() == null){
                Intent stopIntent = new Intent(HomeScreen.this,BackgroundMusicService.class);
                stopService(stopIntent);
            }
        }

        final HomeFragment homeFragment = new HomeFragment();
        final UserFragment userFragment = new UserFragment();
        final ChatGPTFragment heartMonitorFragment = new ChatGPTFragment();
        final CameraFragment cameraFragment = new CameraFragment();





        @Override
        public void onPointerCaptureChanged(boolean hasCapture) {
            super.onPointerCaptureChanged(hasCapture);
        }

        @SuppressLint("NonConstantResourceId")
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {



            if (item.getItemId()==R.id.home){

                /* Bundle mBundle = new Bundle();
                mBundle.putString(
                        "email",
                        email);
                homeFragment.setArguments(mBundle);

                 */



                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, homeFragment)
                        .commit();
                return true;
            }
            if (item.getItemId()==R.id.chatGpt){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, heartMonitorFragment)
                        .commit();
                return true;
            }

            if (item.getItemId()==R.id.camera){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, cameraFragment)
                        .commit();
                return true;
            }
            if (item.getItemId()==R.id.profile){
                Bundle mBundle = new Bundle();
                mBundle.putString(
                        "email",
                        email.toString());
                userFragment.setArguments(mBundle);
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frameLayout, userFragment)
                        .commit();
                return true;
            }

            if (item.getItemId()==R.id.music){
                if(i == 0){
                    stopService(new Intent(HomeScreen.this, BackgroundMusicService.class));
                    i++;
                }

                else if(i == 1)
                {
                    Intent intent = new Intent(HomeScreen.this, BackgroundMusicService.class);
                    startService(intent);
                    i--;
                }

                return true;
            }
            return false;
        }

    public void PlayBackgroundSound(View view) {
        Intent intent = new Intent(HomeScreen.this, BackgroundMusicService.class);
        startService(intent);
    }

    @Override
    public void onClickListener(int position) {

    }

    @Override
    public void onItemClick(int position) {

    }
}

