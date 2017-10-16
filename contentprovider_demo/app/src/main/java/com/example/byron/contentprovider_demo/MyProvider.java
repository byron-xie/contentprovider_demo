package com.example.byron.contentprovider_demo;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by Byron on 2017/10/16.
 */

public class MyProvider extends ContentProvider{
    //静态常量，内容提供者存储数据需要的URL，就是AndroidManifest中设置的authorities
    public static final Uri URL = Uri.parse("content://com.provider.byron");
    private SQLiteDatabase sqLiteDatabase;//数据库
    private int isOk =0;//判断数据库中是否存在tab这个表，如果存在，就不再创建。
    @Override
    public boolean onCreate() {
        //打开或创建一个数据库，名字为data.db，私有的
        sqLiteDatabase = getContext().openOrCreateDatabase("data.db", Context.MODE_PRIVATE,null);
        //查找出这个数据库中的所有表
        Cursor cursor = sqLiteDatabase.rawQuery("select name from sqlite_master where type='table';", null);
        //将查询出的结果的指针移到第一个，然后循环
        cursor.moveToFirst();
        while(cursor.moveToNext()){
            //遍历出表名
            String name = cursor.getString(0);
            System.out.println("存在表："+ name);
            //如果存在则不执行下面的if语句中的创建表代码
            if (name == "tab")
                isOk=1;
        }
        //创建表代码
//        if (isOk==0)
//            sqLiteDatabase.execSQL("create table tab (_id INTEGER PRIMARY KEY AUTOINCREMENT,name TEXT NOT NULL)");
        //这里要返回true
        return true;
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues contentValues) {
        //添加数据到表tab中
        Log.e("MyProvider","me catch this message launcher my activity");
        Intent intent = new Intent(getContext(), MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        getContext().startActivity(intent);
        sqLiteDatabase.insert("tab","_id",contentValues);
        return null;
    }

    @Nullable
    @Override
    public Cursor query(Uri uri, String[] strings, String s, String[] strings1, String s1) {
        //查找tab中的数据
        Cursor cursor = sqLiteDatabase.query("tab",null,null,null,null,null,null);
        return cursor;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public int delete(Uri uri, String s, String[] strings) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues contentValues, String s, String[] strings) {
        return 0;
    }
}
