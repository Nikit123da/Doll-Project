package com.example.doll_project;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.firestore.FirebaseFirestore;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GridLabelRenderer;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.DataPointInterface;
import com.jjoe64.graphview.series.LineGraphSeries;
import com.jjoe64.graphview.series.OnDataPointTapListener;
import com.jjoe64.graphview.series.Series;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class RecyclviewExpention extends AppCompatActivity {

    LineGraphSeries<DataPoint> series;

    public String name, emotion;


    FirebaseFirestore db;

    ImageView icon;

    TextView tvName, tvEmotion;

    Map<String, Object> data = new HashMap<>();


    private int[] arrayList;

    private ArrayList<kidsData> kidsPointsList = new ArrayList<>();

    //private Map<String, Object> array = new HashMap<>();

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.recycle_view);

        //Toast.makeText(RecyclviewExpention.this, "array" + KidsArrayList.CreateKidsPoints()[1][1], Toast.LENGTH_SHORT).show();


        Bundle bundle = getIntent().getExtras();

        String message = bundle.getString("email");


        tvEmotion = findViewById(R.id.tvEmotion);
        tvName = findViewById(R.id.tvName);
        icon = findViewById(R.id.userImg);

        Intent intent = getIntent();

        name = intent.getStringExtra("name");
        emotion = intent.getStringExtra("Emotion");

        Toast.makeText(RecyclviewExpention.this, "Name: " + name, Toast.LENGTH_LONG).show();


        tvName.setText(name);
        tvEmotion.setText(emotion);


        db = FirebaseFirestore.getInstance();
        GraphView graph = findViewById(R.id.graph);


        if(tvEmotion.getText().toString().equals("Really bad")){
            icon.setImageResource(R.drawable.sad_red);
        }
        if(tvEmotion.getText().toString().equals("bad")){
            icon.setImageResource(R.drawable.sad_orange);
        }
        if(tvEmotion.getText().toString().equals("mid")){
            icon.setImageResource(R.drawable.yellow_mid);
        }
        if(tvEmotion.getText().toString().equals("good")){
            icon.setImageResource(R.drawable.light_green_happy);
        }
        if(tvEmotion.getText().toString().equals("Really good")){
            icon.setImageResource(R.drawable.superhappygreenfn);
        }


        DataPoint[] d = new DataPoint[10];


        //int[] map = (int[]) array.get(0);

       /*db.collection("users").document("Kids Array").get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
           @Override
           public void onComplete(@NonNull Task<DocumentSnapshot> task) {
               if (task.isSuccessful()) {
                   DocumentSnapshot document = task.getResult();
                   if (document.exists()) {
                       Log.d(TAG, "DocumentSnapshot data: " + document.getData());

                       if (document.getData() != null) {
                           array = document.getData();

                           for (int j = 0; j < ((int[]) array.get(j)).length; j++) {
                               for (int i = 0; i < ((int[]) array.get(j)).length; i++) {
                                   // d[i]=new DataPoint(0,6);
                                   d[i] = new DataPoint(KidsArrayList.getArray(KidsArrayList.getKidsData(), i)[0][i], KidsArrayList.getArray(KidsArrayList.getKidsData(), i)[1][i]);
                               }
                           }

                       } else {
                           Log.d(TAG, "No such document");
                       }
                   } else {
                       Log.d(TAG, "get failed with ", task.getException());
                   }
               }
           }
        });*/


        //arrayList = new int[10];

        /*db.collection("users").whereEqualTo("arrays", "a")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String str = document.getData().toString();

                                String Email = document.getString("Email");

                                if(str.length()>0 &&  Email.equals(message)) {

                                    int i;
                                    for (i = 0; i < 7; i++) {
                                    }
                                    arrayList = document.getData().t;
                                    if (arrayList != null) {

                                    }
                                }

                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

        for (int j = 0; j <  arrayList.length; j++) {
            for (int i = 0; i < arrayList.length; i++) {
                // d[i]=new DataPoint(0,6);
                d[i] = new DataPoint((arrayList.[i].getKidsPoints()[i][0], i)[0][i], KidsArrayList.getArray(KidsArrayList.getKidsData(), i)[1][i]);
                }
         */


        /*db.collection("users").get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String Email = document.getString("Email");
                                if (message.equals(Email)) {
                                    data = document.getData();
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

         */


        //int[] arr = (int[]) data.get("a1");
//        Toast.makeText(RecyclviewExpention.this, arr.toString(), Toast.LENGTH_LONG).show();






            for (int i = 0; i < KidsArrayList.getKidsData().size(); i++) {
                // d[i]=new DataPoint(0,6);
                //d[i] = new DataPoint( x :   KidsArrayList.getArray(KidsArrayList.getKidsData(), i)[0][i]    ,  y :    KidsArrayList.getArray(KidsArrayList.getKidsData(), i)[1][i]);
                d[i] = new DataPoint(i, KidsArrayList.getArray(KidsArrayList.getKidsData(), i)[i]);

            }



            LineGraphSeries<DataPoint> series = new LineGraphSeries<>(d);

            graph.addSeries(series);

            series.setOnDataPointTapListener(new OnDataPointTapListener() {
                @Override
                public void onTap(Series series, DataPointInterface dataPoint) {

                }
            });
            GridLabelRenderer gridLabel = graph.getGridLabelRenderer();
            gridLabel.setHorizontalAxisTitle("Time");
            gridLabel.setHorizontalAxisTitleTextSize(50);
            gridLabel.setVerticalAxisTitle("Feel");
            gridLabel.setVerticalAxisTitleTextSize(50);

        }




    }


