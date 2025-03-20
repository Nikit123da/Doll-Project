package com.example.doll_project;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HomeFragment extends Fragment implements RecyclerViewInterface {
    private String param1;
    private String param2;
    FirebaseAuth mAuth;

    Button btnLogout;

    FirebaseFirestore db;

    Map<String, Object> mapArrays = new HashMap<>();
    Map<Integer, int[]> mapArrays1 = new HashMap<>();

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            param1 = getArguments().getString("param1");
            param2 = getArguments().getString("param2");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater,
                             ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home,
                container, false);
    }

    public static HomeFragment newInstance(String param1,
                                           String param2) {
        HomeFragment fragment = new HomeFragment();
        Bundle args = new Bundle();
        args.putString("param1", param1);
        args.putString("param2", param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(View view,
                              Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // getting the kidsList
        ArrayList<kidsData> kidsDataArrayList
                = KidsArrayList.getKidsData();

        RecyclerViewInterface recyclerViewInterface = null;



        db = FirebaseFirestore.getInstance();

        mAuth = FirebaseAuth.getInstance();

        //storageReference = storage.getReference();

        btnLogout = view.findViewById(R.id.btnLogout);

        Bundle bundle = getArguments();

        //String message = bundle.getString("email");



        btnLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
                builder.setTitle("CONFIRM LOGOUT!").
                        setMessage("You sure, that you want to logout?");
                builder.setPositiveButton("Yes",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                mAuth.signOut();
                                requireActivity().finish();
                                Toast.makeText(getContext(), "Logout Successful !", Toast.LENGTH_SHORT).show();
                                Intent i = new Intent(getActivity(), Login_screen.class);
                                startActivity(i);

                            }
                        });
                builder.setNegativeButton("No",
                        new DialogInterface.OnClickListener() {
                            public void onClick(DialogInterface dialog, int id) {
                                dialog.cancel();
                            }
                        });
                AlertDialog alert11 = builder.create();
                alert11.show();
            }
        });

        Adapter itemAdapter = new Adapter((kidsDataArrayList), getContext(), this);

        //Toast.makeText(getContext(), kidsDataArrayList.get(0).getImg() + "", Toast.LENGTH_LONG).show();

        // Set the LayoutManager that
        // this RecyclerView will use.
        RecyclerView recyclerView
                = view.findViewById(R.id.recycleview);
        recyclerView.setAdapter(itemAdapter);
        recyclerView.setLayoutManager(
                new LinearLayoutManager(getContext()));

        // adapter instance is set to the
        // recyclerview to inflate the items.

    }

    @Override
    public void onClickListener(int position) {
        Intent intent = new Intent(HomeFragment.this.getActivity(), RecyclviewExpention.class);

        //Toast.makeText(getContext(), "Name: "+ KidsArrayList.getKidsData().get(position).getName() , Toast.LENGTH_LONG).show();


        //Bundle bundle = getArguments();

        //String message = bundle.getString("email");

        Intent i = new Intent(getActivity(), RecyclviewExpention.class);
        //i.putExtra("email", message);


        intent.putExtra("name",KidsArrayList.getKidsData().get(position).getName().toString());
        intent.putExtra("Emotion",KidsArrayList.getKidsData().get(position).getEmotion().toString());
        //intent.putExtra("Image",KidsArrayList.getKidsData().get(position).getImg().);

        startActivity(intent);
    }

    @Override
    public void onItemClick(int position) {

    }

}

