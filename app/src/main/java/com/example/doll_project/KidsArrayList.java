package com.example.doll_project;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.Map;

public class KidsArrayList {



    public static ArrayList<kidsData> getKidsData()
    {
        ArrayList<kidsData> kidsDataArrayList = new ArrayList<>();

        //ImageView img = new ImageView(context.getApplicationContext());
        kidsData kd1 = new kidsData("Jhon Pork", "good", CreateKidsPoints()) ;
        kidsDataArrayList.add(kd1);

        kidsData kd2 = new kidsData("Nikita Ryabtsev", "bad", CreateKidsPoints()) ;
        kidsDataArrayList.add(kd2);

        kidsData kd3 = new kidsData("Miguel Ohera", "mid", CreateKidsPoints() ) ;
        kidsDataArrayList.add(kd3);

        kidsData kd4 = new kidsData("Guss Fring", "good", CreateKidsPoints()) ;
        kidsDataArrayList.add(kd4);

        kidsData kd5 = new kidsData("Homer Simpson", "Really good", CreateKidsPoints()) ;
        kidsDataArrayList.add(kd5);

        kidsData kd6 = new kidsData("Elon Musk", "Really bad", CreateKidsPoints() ) ;
        kidsDataArrayList.add(kd6);

        kidsData kd7 = new kidsData("Jhony Sins", "mid", CreateKidsPoints() );
        kidsDataArrayList.add(kd7);

        kidsData kd8 = new kidsData("Kendrick Lamar", "good", CreateKidsPoints());
        kidsDataArrayList.add(kd8);

        kidsData kd9 = new kidsData("Ed Sheeran", "good", CreateKidsPoints());
        kidsDataArrayList.add(kd9);

        kidsData kd10 = new kidsData("Jhon Epstin", "bad", CreateKidsPoints());
        kidsDataArrayList.add(kd10);

        //kidsData kd1 = new kidsData("Chinmaya Mohapatra", "good", CreateKidsPoints()) ;
        //kidsDataArrayList.add(kd1);

        return kidsDataArrayList;
    }

    public static void updateArrayList(){

        FirebaseFirestore db;

        db = FirebaseFirestore.getInstance();

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String str = document.getData().toString();

                                String Email = document.getString("Email");

                                if(str.length()>0 &&  Email.equals("a@a.com")){

                                    User c = document.toObject(User.class);
                                    Map<String, Object> map = document.getData();

                                    map.keySet();
                                    //Map<String, Object> Map = map.get(4);

                                    //c.getMap == null (fix)

                                    for(int i = 0;i< c.map.size(); i++){
                                        //getKidsData().add(new kidsData(c.GetUserName(), avgFeeling(map.get(i)), map.get(i)));
                                    }
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });

    }

    /*public static int[] GetDBarray(){

        FirebaseFirestore db;

        db = FirebaseFirestore.getInstance();

        db.collection("users")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                String name, PhoneNumber, Email;
                                String str = document.getData().toString();
                                Email = document.getString("Email");



                                if(str.length()>0 && Email.equals(message)){

                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }


     */


    public static int[] CreateKidsPoints() {

        int[] kidsPoints = new int[10];

        for(int i = 0; i < 10; i++){
            kidsPoints[i] = (int) (Math.random() * 10);
        }
        return  kidsPoints;
    }

    public static int[] getArray(ArrayList<kidsData> kidsDataArrayList, int index){
        return kidsDataArrayList.get(index).getKidsPoints();
    }

    public static  String avgFeeling(int[] arr){
        int sum = 0;
        for (int j : arr) {
            sum += arr[j];
        }

        if(sum / arr.length < 2)
            return "Really bad";
        if(sum / arr.length <= 4 && sum / arr.length > 2)
            return " bad";
        if(sum / arr.length <= 6 && sum / arr.length > 4)
            return "mid";
        if(sum / arr.length <= 8 && sum / arr.length > 6)
            return "good";
        return "Really good";

    }

}
