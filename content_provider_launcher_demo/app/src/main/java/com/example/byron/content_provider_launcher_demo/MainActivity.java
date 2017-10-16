package com.example.byron.content_provider_launcher_demo;

import android.content.ContentValues;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    public static final Uri URL = Uri.parse("content://com.provider.byron");
    private EditText et;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //找到组件编辑框
        et = (EditText) findViewById(R.id.et);
        //找到组件写入按钮
        Button bt_insert = (Button) findViewById(R.id.bt_insert);
        //为写入按钮添加侦听事件
        bt_insert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //将编辑框内容保存到变量txt中
                String txt = et.getText().toString();
                //实例化ContentValues，然后添加数据
                ContentValues values = new ContentValues();
                Toast.makeText(getApplicationContext(),txt,Toast.LENGTH_SHORT).show();
                values.put("name",txt);
                //将ContenValues传递到数据库中
                getContentResolver().insert(URL,values);
            }
        });
    }
}
