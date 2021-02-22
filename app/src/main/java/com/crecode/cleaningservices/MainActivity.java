package com.crecode.cleaningservices;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.crecode.cleaningservices.HelperClasses.RecyclerViewAdapter;
import com.crecode.cleaningservices.Model.ModelClass;

import java.util.ArrayList;
import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv_dashboard;
    private ArrayList<ModelClass> items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Objects.requireNonNull(getSupportActionBar()).hide();

        rv_dashboard=findViewById(R.id.rv_dashboard);

        rv_dashboard.setHasFixedSize(true);
        rv_dashboard.setAdapter(new RecyclerViewAdapter(this,items));
        //GridLayoutManager manager = new GridLayoutManager(this, 2, GridLayoutManager.VERTICAL, false);
        rv_dashboard.setLayoutManager(new LinearLayoutManager(this));

        items.add(new ModelClass(R.drawable.course_design_thinking,"ADD NEW JOB"));
        items.add(new ModelClass(R.drawable.course_design_thinking,"LIST OF JOB"));
        items.add(new ModelClass(R.drawable.course_design_thinking,"ACCOUNT COMPANY"));
        items.add(new ModelClass(R.drawable.course_design_thinking,"ACCOUNT PROPERTY"));
        items.add(new ModelClass(R.drawable.course_design_thinking,"ALL LIST(company's ,property's services)"));
        items.add(new ModelClass(R.drawable.course_design_thinking,"ADD NEW COMPANY"));
        items.add(new ModelClass(R.drawable.course_design_thinking,"ADD NEW PROPERTY"));

    }
}