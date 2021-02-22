package com.crecode.cleaningservices.HelperClasses;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class MyDatabaseHelperComp extends SQLiteOpenHelper {

    private final Context context;
    private static final String DATABASE_NAME = "DataBase2.db";
    private static final int DATABASE_VERSION = 2;

    private static final String TABLE_NAME = "my_Cleaning_Company";
    private static final String COLUMN_ID = "_id";
    //COLUMN FOR ACCOUNT COMPANY
    private static final String COLUMN_ABC = "abc";
    private static final String COLUMN_FRM = "frm";
    private static final String COLUMN_WE_CLEAN = "WeWork";
    //COLUMN FOR ACCOUNT COMPANY
    private static final String COLUMN_ADDRESS1 = "address1";
    private static final String COLUMN_ADDRESS2 = "address2";
    private static final String COLUMN_SERVICE_P = "property_service";

    public MyDatabaseHelperComp(@Nullable Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String query = "CREATE TABLE " + TABLE_NAME +
                        " (" + COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
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
    public void addPropertyAccount(String Address1,String Address2,String Service){
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

    public void updateData(String row_id, int abc, int frm,int we_clean){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(COLUMN_ABC, abc);
        cv.put(COLUMN_FRM, frm);
        cv.put(COLUMN_WE_CLEAN, we_clean);

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
