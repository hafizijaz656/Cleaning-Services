package com.crecode.cleaningservices.HelperClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelper extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "DataBase1.db";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_NAME = "my_Cleaning_Services";
    private static final String COLUMN_ID = "_id";
    private static final String COLUMN_COMPANY = "company";
    private static final String COLUMN_SERVICE = "service";
    private static final String COLUMN_PROPERTY = "property";
    private static final String COLUMN_DATE = "date";
    private static final String COLUMN_DAYS = "days";
    private static final String COLUMN_I_PAY = "i_pay";
    private static final String COLUMN_CUST_PAY = "cust_pay";
    //COLUMN FOR ACCOUNT COMPANY
    private static final String COLUMN_ABC = "abc";
    private static final String COLUMN_FRM = "frm";
    private static final String COLUMN_WE_CLEAN = "WeWork";
    //COLUMN FOR ACCOUNT COMPANY
    private static final String COLUMN_ADDRESS1 = "address1";
    private static final String COLUMN_ADDRESS2 = "address2";
    private static final String COLUMN_SERVICE_P = "property_service";

    public MyDatabaseHelper(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                       COLUMN_COMPANY + " TEXT, " +
                       COLUMN_SERVICE + " TEXT, " +
                       COLUMN_PROPERTY + " TEXT, " +
                       COLUMN_DATE + " TEXT, " +
                       COLUMN_DAYS + " INTEGER, " +
                       COLUMN_I_PAY + " INTEGER, " +
                       COLUMN_CUST_PAY + " INTEGER, " +
                       COLUMN_ABC + " INTEGER, " +
                       COLUMN_FRM + " INTEGER, " +
                       COLUMN_WE_CLEAN + " INTEGER, " +
                       COLUMN_ADDRESS1 + " INTEGER, " +
                       COLUMN_ADDRESS2 + " INTEGER, " +
                       COLUMN_SERVICE_P + " INTEGER);";
        db.execSQL(query);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public void addBook(String company,String service,String property,String date, int days,int i_pay,int cust_pay){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_COMPANY, company);
        cv.put(COLUMN_SERVICE, service);
        cv.put(COLUMN_PROPERTY, property);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_DAYS, days);
        cv.put(COLUMN_I_PAY, i_pay);
        cv.put(COLUMN_CUST_PAY, cust_pay);
        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    //COMPANY ACCOUNT DATA
    public void addCompanyAccount(int abc,int frm,int we_clean){
        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ABC, abc);
        cv.put(COLUMN_FRM, frm);
        cv.put(COLUMN_WE_CLEAN, we_clean);

        long result = db1.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    //PROPERTY ACCOUNT DATA
    public void addPropertyAccount(int Address1,int Address2,int Service){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(COLUMN_ADDRESS1, Address1);
        cv.put(COLUMN_ADDRESS2, Address2);
        cv.put(COLUMN_SERVICE_P, Service);

        long result = db.insert(TABLE_NAME,null, cv);
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Added Successfully!", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if(db != null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public void updateData(String row_id, String company, String service,
                           String property, String date, int days, int i_pay, int cust_pay){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_COMPANY, company);
        cv.put(COLUMN_SERVICE, service);
        cv.put(COLUMN_PROPERTY, property);
        cv.put(COLUMN_DATE, date);
        cv.put(COLUMN_DAYS, days);
        cv.put(COLUMN_I_PAY, i_pay);
        cv.put(COLUMN_CUST_PAY, cust_pay);

        long result = db.update(TABLE_NAME, cv, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else {
            Toast.makeText(context, "Updated Successfully!", Toast.LENGTH_SHORT).show();
        }

    }

    public void deleteOneRow(String row_id){
        SQLiteDatabase db = this.getWritableDatabase();
        long result = db.delete(TABLE_NAME, "_id=?", new String[]{row_id});
        if(result == -1){
            Toast.makeText(context, "Failed to Delete.", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted.", Toast.LENGTH_SHORT).show();
        }
    }

    public void deleteAllData(){
        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL("DELETE FROM " + TABLE_NAME);
    }

}
