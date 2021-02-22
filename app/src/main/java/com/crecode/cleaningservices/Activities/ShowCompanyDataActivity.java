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
import com.crecode.cleaningservices.HelperClasses.MyDatabaseHelperComp;
import com.crecode.cleaningservices.HelperClasses.RetrieveDataCompany;
import com.crecode.cleaningservices.R;

import java.util.ArrayList;

public class ShowCompanyDataActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    RetrieveDataCompany adapter;

    ImageView empty_imageview;
    TextView no_data;

    MyDatabaseHelperComp myDB;
    ArrayList<Object> cleaning_id, abc, frm, we_clean;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_company_data);
        getSupportActionBar().setTitle("All Data");

        recyclerView = findViewById(R.id.recyclerView_show_Company);
        empty_imageview = findViewById(R.id.empty_imageview_c);
        no_data = findViewById(R.id.no_data_c);

        myDB = new MyDatabaseHelperComp(ShowCompanyDataActivity.this);
        cleaning_id = new ArrayList<>();
        abc = new ArrayList<>();
        frm = new ArrayList<>();
        we_clean = new ArrayList<>();

        storeDataInArrays();

        adapter = new RetrieveDataCompany(ShowCompanyDataActivity.this, this, cleaning_id,
                abc,
                frm,
                we_clean);

        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(ShowCompanyDataActivity.this));

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1) {
            recreate();
        }
    }

    void storeDataInArrays() {
        Cursor cursor = myDB.readAllData();
        if (cursor.getCount() == 0) {
            empty_imageview.setVisibility(View.VISIBLE);
            no_data.setVisibility(View.VISIBLE);
        } else {
            while (cursor.moveToNext()) {
                cleaning_id.add(cursor.getString(0));
                abc.add(cursor.getString(1));
                frm.add(cursor.getString(2));
                we_clean.add(cursor.getString(3));
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
        if (item.getItemId() == R.id.delete_all) {
            confirmDialog();
        }
        return super.onOptionsItemSelected(item);
    }

    void confirmDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete All?");
        builder.setMessage("Are you sure you want to delete all Data?");
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                MyDatabaseHelperComp myDB = new MyDatabaseHelperComp(ShowCompanyDataActivity.this);
                myDB.deleteAllData();
                //Refresh Activity
                Intent intent = new Intent(ShowCompanyDataActivity.this, ShowCompanyDataActivity.class);
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