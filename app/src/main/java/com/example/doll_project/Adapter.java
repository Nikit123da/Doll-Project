package com.example.doll_project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adapter extends RecyclerView.Adapter<Adapter.MyViewHolder> {
    private ArrayList<kidsData> list;
    private Context context;;

    ArrayList<kidsData> kidsDataArrayList
            = KidsArrayList.getKidsData();

    private final RecyclerViewInterface recyclerViewInterface;

    public Adapter(ArrayList<kidsData> list,Context context,RecyclerViewInterface recyclerViewInterface) {
        this.context = context;
        this.list = list;
        this.recyclerViewInterface = recyclerViewInterface;
    }

    // This method creates a new ViewHolder object for each item in the RecyclerView
    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        // Inflate the layout for each item and return a new ViewHolder object
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.recycle_view, parent, false);

        return new Adapter.MyViewHolder(itemView, recyclerViewInterface);
    }

    public Context getRecyclerViewInterface(){
        return context;
    }

    // This method returns the total
    // number of items in the data set
    @Override
    public int getItemCount() {
        return list.size();
    }

    // This method binds the data to the ViewHolder object
    // for each item in the RecyclerView
    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        kidsData currentEmp = list.get(position);
        holder.name.setText(currentEmp.getName());
        holder.emotion.setText(currentEmp.getEmotion());


            if (kidsDataArrayList.get(position).getEmotion().equals("Really bad"))
                holder.img.setImageResource(R.drawable.sad_red);

            if (kidsDataArrayList.get(position).getEmotion().equals("bad"))
                holder.img.setImageResource(R.drawable.sad_orange);

            if (kidsDataArrayList.get(position).getEmotion().equals("mid"))
                holder.img.setImageResource(R.drawable.yellow_mid);

            if (kidsDataArrayList.get(position).getEmotion().equals("good"))
                holder.img.setImageResource(R.drawable.light_green_happy);

            if (kidsDataArrayList.get(position).getEmotion().equals("Really good"))

                holder.img.setImageResource(R.drawable.superhappygreenfn);



    }

    // This class defines the ViewHolder object for each item in the RecyclerView
    public static class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView name;
        private TextView emotion;
        private ImageView img;

        public MyViewHolder(@NonNull View itemView,RecyclerViewInterface recyclerViewInterface) {
            super(itemView);
            name = itemView.findViewById(R.id.tvName);
            emotion = itemView.findViewById(R.id.tvEmotion);
            img = itemView.findViewById(R.id.userImg);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if(recyclerViewInterface != null){
                        int pos = getBindingAdapterPosition();

                        if(pos != RecyclerView.NO_POSITION)
                        {
                            recyclerViewInterface.onClickListener(pos);
                        }
                    }
                }
            });
        }
    }

}
