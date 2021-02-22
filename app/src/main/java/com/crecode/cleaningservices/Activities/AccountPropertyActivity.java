package com.crecode.cleaningservices.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crecode.cleaningservices.HelperClasses.MyDatabaseHelper;
import com.crecode.cleaningservices.HelperClasses.MyDatabaseHelperPro;
import com.crecode.cleaningservices.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountPropertyActivity extends AppCompatActivity {

    EditText edtAddress1,edtAddress2,edtService;
    Button btnPay_pro;
    TextView totalpay_p;
    int edtAddres1,edtAddres2,edtServic;
    FloatingActionButton floatingActionButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_property);
        getSupportActionBar().setTitle("Account Property");


        edtAddress1=findViewById(R.id.edtAddress1);
        edtAddress2=findViewById(R.id.edtAddress2);
        edtService=findViewById(R.id.edtService);
        floatingActionButton=findViewById(R.id.showProRecord_p);
        totalpay_p=findViewById(R.id.totalpay_p);

        btnPay_pro=findViewById(R.id.btnPay_pro);

        btnPay_pro.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                edtAddres1= Integer.parseInt(edtAddress1.getText().toString());
                edtAddres2= Integer.parseInt(edtAddress2.getText().toString());
                edtServic= Integer.parseInt(edtService.getText().toString());

                MyDatabaseHelperPro myDB = new MyDatabaseHelperPro(AccountPropertyActivity.this);
                myDB.addPropertyAccount(edtAddres1,edtAddres2, edtServic);


            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountPropertyActivity.this,ShowPropertyDataActivity.class));
            }
        });

        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtAddress1.getText().toString().equals("")
                        && !edtAddress2.getText().toString().equals("")
                        && !edtService.getText().toString().equals("")) {

                    int h12 = Integer.parseInt(edtAddress1.getText().toString());
                    int h13 = Integer.parseInt(edtAddress2.getText().toString());
                    int tv3 = Integer.parseInt(edtService.getText().toString());

                    totalpay_p.setText("$ "+String.valueOf(tv3 + h12 + h13));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edtAddress1.addTextChangedListener(textWatcher);
        edtAddress2.addTextChangedListener(textWatcher);
        edtService.addTextChangedListener(textWatcher);
    }
}