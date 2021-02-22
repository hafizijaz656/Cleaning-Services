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

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.crecode.cleaningservices.Activities.UpdateNewJobActivity;
import com.crecode.cleaningservices.R;

import java.util.ArrayList;

public class RetrieveDataCompany extends RecyclerView.Adapter<RetrieveDataCompany.MyViewHolder> {

    private final Context context;
    Activity activity;
    ArrayList<Object> cleaning_id, ABC,FRM, WE_CLEAN;

    public RetrieveDataCompany(Activity activity, Context context, ArrayList<Object> cleaning_id,
                               ArrayList<Object> abc,
                               ArrayList<Object> frm,
                               ArrayList<Object> we_clean){
        this.activity = activity;
        this.context = context;
        this.cleaning_id = cleaning_id;
        this.ABC = abc;
        this.FRM = frm;
        this.WE_CLEAN = we_clean;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.company_design, parent, false);
        return new MyViewHolder(view);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        holder.book_id_txt.setText(String.valueOf(cleaning_id.get(position)));
        holder.txt_abc.setText(String.valueOf(ABC.get(position)));
        holder.txt_frm.setText(String.valueOf(FRM.get(position)));
        holder.txt_wework.setText(String.valueOf(WE_CLEAN.get(position)));

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

        TextView book_id_txt, txt_abc,txt_frm, txt_wework;
        LinearLayout mainLayout_c;

        MyViewHolder(@NonNull View itemView) {
            super(itemView);
            book_id_txt = itemView.findViewById(R.id.book_id_txt);
            txt_abc = itemView.findViewById(R.id.txt_abc);
            txt_frm=itemView.findViewById(R.id.txt_frm);
            txt_wework = itemView.findViewById(R.id.txt_wework);

            mainLayout_c = itemView.findViewById(R.id.mainLayout_c);
            //Animate Recyclerview
            Animation translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
            mainLayout_c.setAnimation(translate_anim);
        }

    }

}
