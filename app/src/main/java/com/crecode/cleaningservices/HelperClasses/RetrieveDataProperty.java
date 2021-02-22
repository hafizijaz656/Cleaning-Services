package com.crecode.cleaningservices.HelperClasses;


import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.crecode.cleaningservices.R;

import java.util.ArrayList;

public class RetrieveDataProperty extends RecyclerView.Adapter<RetrieveDataProperty.MyViewHolder> {

    private final Context context;
    Activity activity;
    ArrayList<Object> cleaning_id, Address1,Address2, Service;

    public RetrieveDataProperty(Activity activity, Context context, ArrayList<Object> cleaning_id,
                                ArrayList<Object> address1,
                                ArrayList<Object> address2,
                                ArrayList<Object> service){
        this.activity = activity;
        this.context = context;
        this.cleaning_id = cleaning_id;
        this.Address1 = address1;
        this.Address2 = address2;
        this.Service = service;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.property_design, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.book_id_txt.setText(String.valueOf(cleaning_id.get(position)));
        holder.txt_address1.setText(String.valueOf(Address1.get(position)));
        holder.txt_address2.setText(String.valueOf(Address2.get(position)));
        holder.txt_service.setText(String.valueOf(Service.get(position)));

//        //Recyclerview onClickListener
//        holder.btn_process.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//
//                Intent intent = new Intent(context, UpdateNewJobActivity.class);
//                intent.putExtra("id", String.valueOf(cleaning_id.get(position)));
//                intent.putExtra("company",String.valueOf(company.get(position)));
//                intent.putExtra("service",String.valueOf(service.get(position)));
//                intent.putExtra("property", String.valueOf(property.get(position)));
//                intent.putExtra("date", String.valueOf(date.get(position)));
//                intent.putExtra("days", String.valueOf(days.get(position)));
//                intent.putExtra("i_pay", String.valueOf(i_pay.get(position)));
//                intent.putExtra("cust_pay", String.valueOf(cust_pay.get(position)));
//                activity.startActivityForResult(intent, 1);
//            }
//        });


    }

    @Override
    public int getItemCount() {
        return cleaning_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, txt_address1,txt_address2, txt_service;
        LinearLayout mainLayout_p;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            txt_address1 = itemView.findViewById(R.id.txt_address1);
            txt_address2=itemView.findViewById(R.id.txt_address2);
            txt_service = itemView.findViewById(R.id.txt_service);

            mainLayout_p = itemView.findViewById(R.id.mainLayout_p);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout_p.setAnimation(translate_anim);
        }

    }

}
