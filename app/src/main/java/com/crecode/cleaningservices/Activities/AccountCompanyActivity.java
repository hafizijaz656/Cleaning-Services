package com.crecode.cleaningservices.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.location.Address;
import android.os.Bundle;
import android.service.controls.actions.FloatAction;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.crecode.cleaningservices.HelperClasses.MyDatabaseHelper;
import com.crecode.cleaningservices.HelperClasses.MyDatabaseHelperComp;
import com.crecode.cleaningservices.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class AccountCompanyActivity extends AppCompatActivity {

    EditText edtAbc,edtFrm,edtWeWork;
    Button btnPay_C;
    TextView pay;
    int ABC,FRM,WeWORK;
    FloatingActionButton floatingActionButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_account_company);
        getSupportActionBar().setTitle("Account Company");

        edtAbc=findViewById(R.id.edtAbc);
        edtFrm=findViewById(R.id.edtFrm);
        edtWeWork=findViewById(R.id.edtWeWork);
        pay=findViewById(R.id.pay);
        floatingActionButton=findViewById(R.id.showCompRecord);

        btnPay_C=findViewById(R.id.btnPay_C);

        btnPay_C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ABC= Integer.parseInt(edtAbc.getText().toString());
                FRM= Integer.parseInt(edtFrm.getText().toString());
                WeWORK = Integer.parseInt(edtWeWork.getText().toString());

                MyDatabaseHelperComp myDB = new MyDatabaseHelperComp(AccountCompanyActivity.this);
                myDB.addCompanyAccount(ABC,FRM, WeWORK);


            }
        });

        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(AccountCompanyActivity.this,ShowCompanyDataActivity.class));
            }
        });


        TextWatcher textWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                if (!edtAbc.getText().toString().equals("")
                        && !edtFrm.getText().toString().equals("")
                        && !edtWeWork.getText().toString().equals("")) {

                    int h12 = Integer.parseInt(edtAbc.getText().toString());
                    int h13 = Integer.parseInt(edtFrm.getText().toString());
                    int tv3 = Integer.parseInt(edtWeWork.getText().toString());

                   pay.setText("$ "+String.valueOf(tv3 + h12 + h13));
                }
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };
        edtAbc.addTextChangedListener(textWatcher);
        edtFrm.addTextChangedListener(textWatcher);
        edtWeWork.addTextChangedListener(textWatcher);


    }
}