package com.crecode.cleaningservices.Activities;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.crecode.cleaningservices.HelperClasses.MyDatabaseHelper;
import com.crecode.cleaningservices.R;

import java.util.Calendar;

public class UpdateNewJobActivity extends AppCompatActivity {

    EditText company,service,property,days,i_pay,customer_pay;
    Button btn_Save,u_delete;
    private DatePickerDialog.OnDateSetListener onDateSetListener;
    TextView date;
    String date_pick,id;
    String Company,Service,Property,Date;
    int Days,I_pay,Customer_pay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_new_job);
        getSupportActionBar().setTitle("Update New Job");

        //First we call this
        getAndSetIntentData();

        //Set actionbar title after getAndSetIntentData method
        ActionBar ab = getSupportActionBar();
        if (ab != null) {
            ab.setTitle(Company);
        }



        btn_Save=findViewById(R.id.u_save);
        u_delete=findViewById(R.id.u_delete);

        btn_Save.setOnClickListener(view -> {

            MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateNewJobActivity.this);

            Company=company.getText().toString();
            Service=service.getText().toString();
            Property=property.getText().toString();
            Date=date.getText().toString();
            Days= Integer.parseInt(days.getText().toString());
            I_pay=Integer.parseInt(i_pay.getText().toString());
            Customer_pay=Integer.parseInt(customer_pay.getText().toString());

            myDB.updateData(id, Company, Service, Property,Date,Days,I_pay,Customer_pay);

        });

        u_delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                confirmDialog();
            }
        });

        getAndSetDate();

    }

    private void getAndSetIntentData() {

        company=(EditText) findViewById(R.id.u_company);
        service=(EditText) findViewById(R.id.u_service);
        property=(EditText) findViewById(R.id.u_property);
        days=(EditText) findViewById(R.id.u_days);
        date=(TextView) findViewById(R.id.u_date);
        i_pay=(EditText) findViewById(R.id.u_i_pay);
        customer_pay=(EditText) findViewById(R.id.u_customer_pay);

        if(getIntent().hasExtra("id") || getIntent().hasExtra("company")
                || getIntent().hasExtra("service")
                || getIntent().hasExtra("property")
                || getIntent().hasExtra("date")
                || getIntent().hasExtra("days")
                || getIntent().hasExtra("i_pay")
                || getIntent().hasExtra("cust_pay")
                || getIntent().hasExtra("pages")
        ){
            //Getting Data from Intent
            id = getIntent().getStringExtra("id");
            Company = getIntent().getStringExtra("company");
            Service = getIntent().getStringExtra("service");
            Property = getIntent().getStringExtra("property");
            Date = getIntent().getStringExtra("date");
            Days = Integer.parseInt(getIntent().getStringExtra("days"));
            I_pay = Integer.parseInt(getIntent().getStringExtra("i_pay"));
            Customer_pay = Integer.parseInt(getIntent().getStringExtra("cust_pay"));
            
            //Setting Intent Data
            company.setText(Company);
            service.setText(Service);
            property.setText(Property);
            date.setText(Date);
            days.setText(String.valueOf(Days));
            i_pay.setText(String.valueOf(I_pay));
            customer_pay.setText(String.valueOf(Customer_pay));
            Log.d("MY DATA", company+" "+service+" "+property);
        }else{
            Toast.makeText(this, "No data.", Toast.LENGTH_SHORT).show();
        }
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + Company + " ?");
        builder.setMessage("Are you sure you want to delete " + Company + " ?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(UpdateNewJobActivity.this);
                myDB.deleteOneRow(id);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        builder.create().show();
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

                DatePickerDialog datePickerDialog = new DatePickerDialog(UpdateNewJobActivity.this,android.R.style.Theme_Holo_Light_Dialog_MinWidth,onDateSetListener,year,month,day);
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

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(UpdateNewJobActivity.this,ListOfJobsActivity.class));
        finish();
    }
}