package com.crecode.cleaningservices.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.crecode.cleaningservices.HelperClasses.MyDatabaseHelper;
import com.crecode.cleaningservices.R;

import java.util.Calendar;

public class AddNewJobActivity extends AppCompatActivity {

    EditText company,service,property,days,i_pay,customer_pay;
    Button btn_Save;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    TextView date;
    String date_pick;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_job);
        getSupportActionBar().setTitle("Add New Job");

        company=findViewById(R.id.company);
        service=findViewById(R.id.service);
        property=findViewById(R.id.property);
        days=findViewById(R.id.days);
        date=findViewById(R.id.date);
        i_pay=findViewById(R.id.i_pay);
        customer_pay=findViewById(R.id.customer_pay);

        btn_Save=findViewById(R.id.save);

        btn_Save.setOnClickListener(view -> {

            String Company=company.getText().toString();
            String Service=service.getText().toString();
            String Property=property.getText().toString();
            String Date=date.getText().toString();
            int Days= Integer.parseInt(days.getText().toString());
            int I_pay=Integer.parseInt(i_pay.getText().toString());
            int Customer_pay=Integer.parseInt(customer_pay.getText().toString());

            MyDatabaseHelper myDB = new MyDatabaseHelper(AddNewJobActivity.this);
            myDB.addBook(Company,Service, Property, Date,Days,I_pay,Customer_pay);


        });


        getAndSetDate();

    }

    private void getAndSetDate() {

        //getting date of birth

        date.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Calendar yCalendar = Calendar.getInstance();
                int year =  yCalendar.get(Calendar.YEAR);
                int month =  yCalendar.get(Calendar.MONTH);
                int day =  yCalendar.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(AddNewJobActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener,year,month,day);
                datePickerDialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                datePickerDialog.show();
            }
        });

        // setting date of birth

        onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int day) {
                month = month +1;
                date_pick = year + "/" + month + "/" + day;
                date.setText(date_pick);
            }
        };
    }
}