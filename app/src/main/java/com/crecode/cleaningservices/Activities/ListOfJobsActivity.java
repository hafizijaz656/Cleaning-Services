package com.crecode.cleaningservices.Activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.crecode.cleaningservices.HelperClasses.AdapterRetrieveAllData;
import com.crecode.cleaningservices.HelperClasses.MyDatabaseHelper;
import com.crecode.cleaningservices.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class ListOfJobsActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    ImageView empty_imageview;
    TextView no_data;

    MyDatabaseHelper myDB;
    ArrayList<Object> cleaning_id, company,service, property, date,days,i_pay,cust_pay;
    AdapterRetrieveAllData customAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_of_jobs);
        getSupportActionBar().setTitle("List of Jobs");


        recyclerView = findViewById(R.id.recyclerView);
        empty_imageview = findViewById(R.id.empty_imageview);
        no_data = findViewById(R.id.no_data);

        myDB = new MyDatabaseHelper(ListOfJobsActivity.this);
        cleaning_id = new ArrayList<>();
        company = new ArrayList<>();
        service = new ArrayList<>();
        property = new ArrayList<>();
        date = new ArrayList<>();
        days = new ArrayList<>();
        i_pay = new ArrayList<>();
        cust_pay= new ArrayList<>();

        storeDataInArrays();

        customAdapter = new AdapterRetrieveAllData(ListOfJobsActivity.this,this, cleaning_id,
                company,
                service,
                property,
                date,
                days,
                i_pay,
                cust_pay);

        recyclerView.setAdapter(customAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ListOfJobsActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1){
            recreate();
        }
    }

    void storeDataInArrays(){
        Cursor cursor = myDB.readAllData();
        if(cursor.getCount() == 0){
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        }else{
            while (cursor.moveToNext()){
                cleaning_id.add(cursor.getString(0));
                company.add(cursor.getString(1));
                service.add(cursor.getString(2));
                property.add(cursor.getString(3));
                date.add(cursor.getString(4));
                days.add(cursor.getString(5));
                i_pay.add(cursor.getString(6));
                cust_pay.add(cursor.getString(7));
            }
            empty_imageview.setVisibility(View.GONE);
            no_data.setVisibility(View.GONE);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.my_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.delete_all){
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelper myDB = new MyDatabaseHelper(ListOfJobsActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(ListOfJobsActivity.this, ListOfJobsActivity.class);
                startActivity(intent);
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
}
