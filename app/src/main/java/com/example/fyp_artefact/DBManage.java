package com.example.fyp_artefact;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

import androidx.annotation.Nullable;

public class DBManage extends SQLiteOpenHelper {

    private Context context;
    private static final String DATABASE_NAME = "items.db";

    //Table Details for Items
    private static final String TABLE_ITEMS = "ITEMS";
    private static final String ITEM_ID = "ID";
    private static final String ITEM_NAME = "item_name";
    private static final String ITEM_PRICE = "price";
    private static final String ITEM_CATEGORY = "category";
    private static final String ITEM_quantity = "quantity";

    public DBManage(@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //creates table for user login
        sqLiteDatabase.execSQL("create table authenticate(store_ID TEXT primary key, password TEXT, store_Name TEXT)");

        //creation of table to store data
        String query = "CREATE TABLE " + TABLE_ITEMS +
                        " (" + ITEM_ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                        ITEM_NAME + " TEXT NOT NULL, " +
                        ITEM_PRICE + " INTEGER NOT NULL, " +
                        ITEM_CATEGORY + " TEXT NOT NULL, " +
                        ITEM_quantity + " INTEGER NOT NULL);";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists authenticate");
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + TABLE_ITEMS);
        onCreate(sqLiteDatabase);
    }

    //add items
    public void addItems(String title, int pr, String cat, int q){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();

        cv.put(ITEM_NAME, title);
        cv.put(ITEM_CATEGORY, cat);
        cv.put(ITEM_PRICE, pr);
        cv.put(ITEM_quantity, q);

        long result = db.insert(TABLE_ITEMS, null, cv);
        if (result == -1){
            Toast.makeText(context, "Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Added", Toast.LENGTH_SHORT).show();
        }
    }

    public Boolean add_users(String sID, String pass, String sn){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("store_ID", sID);
        contentValues.put("password", pass);
        contentValues.put("store_Name", sn);
        long result = db.insert("authenticate", null, contentValues);
        return result != -1;
    }

    void deleteOneItem(String id){
        SQLiteDatabase database = this.getWritableDatabase();
        long result = database.delete(TABLE_ITEMS, " ID=?", new String[]{id});
        if (result == -1){
            Toast.makeText(context, "Deletion Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Deleted", Toast.LENGTH_SHORT).show();
        }
    }

    public Cursor readAllData(){
        String query = "SELECT * FROM " + TABLE_ITEMS;
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db!= null){
            cursor = db.rawQuery(query, null);
        }
        return cursor;
    }

    public Cursor readMens(){
        String men = "MEN";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db!= null){
            cursor = db.rawQuery("select * from ITEMS where category = ?", new String[]{men});
        }
        return cursor;
    }

    public Cursor readWomen(){
        String women = "WOMEN";
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = null;
        if (db!= null){
            cursor = db.rawQuery("select * from ITEMS where category = ?", new String[]{women});
        }
        return cursor;
    }

    public void updateData(String row_ID, String name, String cat, String pr, String q){
        SQLiteDatabase database = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put(ITEM_NAME, name);
        cv.put(ITEM_CATEGORY, cat);
        cv.put(ITEM_PRICE, pr);
        cv.put(ITEM_quantity, q);

        long update = database.update(TABLE_ITEMS, cv, " ID=?", new String[]{row_ID});
        if (update == -1){
            Toast.makeText(context, "Update Failed", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(context, "Successfully Updated", Toast.LENGTH_SHORT).show();
        }
    }

    //checks users credentials
    public Boolean check_SID(String id, String pass){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from authenticate where store_ID = ? and password = ?", new String[]{id, pass});
        return cursor.getCount()>0;
    }

    //checks if users exists or not
    public boolean check_username(String id){
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from authenticate where store_ID = ?", new String[]{id});
        return cursor.getCount()>0;
    }


}
