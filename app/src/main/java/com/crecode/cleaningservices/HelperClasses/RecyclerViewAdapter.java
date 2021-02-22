package com.crecode.cleaningservices.HelperClasses;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.crecode.cleaningservices.Activities.AccountCompanyActivity;
import com.crecode.cleaningservices.Activities.AccountPropertyActivity;
import com.crecode.cleaningservices.Activities.AddNewJobActivity;
import com.crecode.cleaningservices.Activities.ListOfJobsActivity;
import com.crecode.cleaningservices.Model.ModelClass;
import com.crecode.cleaningservices.R;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.HolderClass> {

    private Context mContext;
    private ArrayList<ModelClass> modelClass;

    public RecyclerViewAdapter(Context mContext, ArrayList<ModelClass> modelClass) {
        this.mContext = mContext;
        this.modelClass = modelClass;
    }

    public RecyclerViewAdapter() {
    }


    @NonNull
    @Override
    public HolderClass onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_card,parent,false);
        return new HolderClass(view);
    }

    @Override
    public void onBindViewHolder(@NonNull HolderClass holder, int position) {

        ModelClass model=modelClass.get(position);
        //holder.card_view_image.setImageResource(model.getImage());
        holder.item_title.setText(model.getTitle());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (modelClass.get(position).getTitle().equals("ADD NEW JOB")){
                    Intent intent=new Intent(mContext, AddNewJobActivity.class);
                    mContext.startActivity(intent);

                }
                else if (modelClass.get(position).getTitle().equals("LIST OF JOB")){
                    Intent intent=new Intent(mContext, ListOfJobsActivity.class);
                    mContext.startActivity(intent);
                }
                else if (modelClass.get(position).getTitle().equals("ACCOUNT COMPANY")){
                    Intent intent=new Intent(mContext, AccountCompanyActivity.class);
                    mContext.startActivity(intent);
                }
                else if (modelClass.get(position).getTitle().equals("ACCOUNT PROPERTY")){
                    Intent intent=new Intent(mContext, AccountPropertyActivity.class);
                    mContext.startActivity(intent);
                }
                else if (modelClass.get(position).getTitle().equals("ALL LIST(company's ,property's services)")){

                    Toast.makeText(mContext, "In Progress", Toast.LENGTH_SHORT).show();
                }
                else if (modelClass.get(position).getTitle().equals("ADD NEW COMPANY")){

                    Toast.makeText(mContext, "In Progress", Toast.LENGTH_SHORT).show();
                }
                else if (modelClass.get(position).getTitle().equals("ADD NEW PROPERTY")){

                    Toast.makeText(mContext, "In Progress", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    @Override
    public int getItemCount() {
        return modelClass.size();
    }

    public static class HolderClass extends RecyclerView.ViewHolder{

        ImageView card_view_image;
        TextView item_title;

        public HolderClass(@NonNull View itemView) {
            super(itemView);

           // card_view_image=itemView.findViewById(R.id.card_view_image);
            item_title=itemView.findViewById(R.id.item_title);

        }
    }
}
