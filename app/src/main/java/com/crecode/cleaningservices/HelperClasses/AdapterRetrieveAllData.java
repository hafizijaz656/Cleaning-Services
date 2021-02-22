package com.crecode.cleaningservices.HelperClasses;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.crecode.cleaningservices.Activities.UpdateNewJobActivity;
import com.crecode.cleaningservices.R;

import java.util.ArrayList;

public class AdapterRetrieveAllData extends RecyclerView.Adapter<AdapterRetrieveAllData.MyViewHolder> {

    private final Context context;
    Activity activity;
    ArrayList<Object> cleaning_id, company,service, property, date,days,i_pay,cust_pay;

    public AdapterRetrieveAllData(Activity activity, Context context, ArrayList<Object> cleaning_id,
                                  ArrayList<Object> company,
                                  ArrayList<Object> service,
                                  ArrayList<Object> property,
                                  ArrayList<Object> date,
                                  ArrayList<Object> days,
                                  ArrayList<Object> i_pay,
                                  ArrayList<Object> cust_pay){
        this.activity = activity;
        this.context = context;
        this.cleaning_id = cleaning_id;
        this.company = company;
        this.service = service;
        this.property = property;
        this.date = date;
        this.days = days;
        this.i_pay = i_pay;
        this.cust_pay = cust_pay;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.book_id_txt.setText(String.valueOf(cleaning_id.get(position)));
        holder.company_txt.setText(String.valueOf(company.get(position)));
        holder.service_txt.setText(String.valueOf(service.get(position)));
        holder.property_txt.setText(String.valueOf(property.get(position)));
        holder.date_txt.setText(String.valueOf(date.get(position)));

        //Recyclerview onClickListener
        holder.btn_process.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(context, UpdateNewJobActivity.class);
                intent.putExtra("id", String.valueOf(cleaning_id.get(position)));
                intent.putExtra("company",String.valueOf(company.get(position)));
                intent.putExtra("service",String.valueOf(service.get(position)));
                intent.putExtra("property", String.valueOf(property.get(position)));
                intent.putExtra("date", String.valueOf(date.get(position)));
                intent.putExtra("days", String.valueOf(days.get(position)));
                intent.putExtra("i_pay", String.valueOf(i_pay.get(position)));
                intent.putExtra("cust_pay", String.valueOf(cust_pay.get(position)));
                activity.startActivityForResult(intent, 1);
            }
        });


    }

    @Override
    public int getItemCount() {
        return cleaning_id.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView book_id_txt, company_txt,service_txt, property_txt, date_txt;
        RelativeLayout mainLayout;
        Button btn_process;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            company_txt = itemView.findViewById(R.id.companyShow);
            service_txt=itemView.findViewById(R.id.services_Show);
            property_txt = itemView.findViewById(R.id.propertyShow);
            date_txt = itemView.findViewById(R.id.dateShow);
            btn_process=itemView.findViewById(R.id.btn_process);

            mainLayout = itemView.findViewById(R.id.mainLayout);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout.setAnimation(translate_anim);
        }

    }

}
