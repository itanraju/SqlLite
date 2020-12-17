package com.example.sqllite;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DbHelper extends SQLiteOpenHelper {
    public DbHelper(@Nullable Context context) {
        super(context, "Userdata.db", null,1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        db.execSQL("create table userdetail (name text primary key,gmail text,msg text)");

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("drop table if exists userdetail");

    }

    public  Boolean inserUserData(String name,String gmail,String msg)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("gmail",gmail);
        contentValues.put("msg",msg);

        long result=db.insert("userDetail",null,contentValues);
        if(result==-1)
        {
            return  false;
        }
        else
        {
            return true;
        }

    }

    public  Boolean updateUserData(String name,String gmail,String msg)
    {
        SQLiteDatabase db=this.getWritableDatabase();
        ContentValues contentValues=new ContentValues();
        contentValues.put("name",name);
        contentValues.put("gmail",gmail);
        contentValues.put("msg",msg);

        Cursor cursor=db.rawQuery("select * from userdetail where name=?",new String[]{name});
        if(cursor.getCount()>0)
        {
            long result=db.update("userDetail",contentValues,"name=?",new String[]{name});
            if(result==-1)
            {
                return  false;
            }
            else
            {
                return true;
            }

        }
        else {
            return false;

        }}

        public  Boolean deleteUserData(String name)
        {
            SQLiteDatabase db=this.getWritableDatabase();

            Cursor cursor=db.rawQuery("select * from userdetail where name=?",new String[]{name});
            if(cursor.getCount()>0)
            {
                long result=db.delete("userdetail","name=?",new String[]{name});
                if(result==-1)
                {
                    return  false;
                }
                else
                {
                    return true;
                }

            }
            else {
                return false;
            }}

            public Cursor viewData()
            {
                SQLiteDatabase db=this.getWritableDatabase();

                Cursor cursor=db.rawQuery("select * from userdetail",null);

                return  cursor;
            }


}
