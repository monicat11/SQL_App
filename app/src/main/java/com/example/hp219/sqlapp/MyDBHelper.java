package com.example.hp219.sqlapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by hp219 on 14-08-2016.
 */
public class MyDBHelper extends SQLiteOpenHelper{
    private static final int DATABASE_VERSION=1;
    private static final String DATABASE_NAME="product_info.db";
    private static final String TABLE_NAME="products";
    private static final String COLUMN_ID="_id";
    private static final String COLUMN_NAME="productname";

    public MyDBHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        String query="CREATE TABLE "+ TABLE_NAME+"("+COLUMN_ID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_NAME+" TEXT"+");";
        db.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS"+TABLE_NAME);
        onCreate(db);

    }
    public void addProducts(Products product)
    {
        ContentValues values=new ContentValues();
        values.put(COLUMN_NAME,product.get_productname());
        SQLiteDatabase db=getWritableDatabase();
        db.insert(TABLE_NAME,null,values);
        db.close();

    }
    public void delete(String productName)
    {
        SQLiteDatabase db=getWritableDatabase();
        db.execSQL("DELETE FROM "+TABLE_NAME+" WHERE "+COLUMN_NAME+"=\""+productName+"\";");
    }
    public String databaseToString()
    {
        String dbString="";
        SQLiteDatabase db=getWritableDatabase();
        String query="SELECT * FROM "+TABLE_NAME+" WHERE 1";
        Cursor c=db.rawQuery(query,null);
        c.moveToFirst();
        while (!c.isAfterLast())
        {
            if(c.getString(c.getColumnIndex("productname"))!=null)
            {
                dbString+=c.getString(c.getColumnIndex("productname"));
                dbString+="\n";

            }
        }
        db.close();
        return dbString;
    }
}
