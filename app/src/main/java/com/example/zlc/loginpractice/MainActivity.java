package com.example.zlc.loginpractice;

import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;
import java.text.Format;
import java.util.Map;
import java.util.logging.Formatter;

public class MainActivity extends AppCompatActivity {

    private EditText et_name;
    private EditText et_password;
    private CheckBox cb_check;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        et_name = (EditText) findViewById(R.id.et_username);
        et_password = (EditText) findViewById(R.id.et_userpassword);
        cb_check = (CheckBox) findViewById(R.id.cb_ischeck);
        TextView tv_useable_size = (TextView) findViewById(R.id.tv_useable_size);
        TextView tv_total_size = (TextView) findViewById(R.id.tv_total_size);

        //获取总空间和可用大小
        File file = Environment.getExternalStorageDirectory();
        Long useableSpace = file.getUsableSpace();
        Long totalSpace = file.getTotalSpace();

        String formatUseableSpace = android.text.format.Formatter.formatFileSize(this,useableSpace);
        String formatTotalSpace = android.text.format.Formatter.formatFileSize(this,totalSpace);

        tv_useable_size.setText("可用空间："+formatUseableSpace);
        tv_total_size.setText("总空间："+formatTotalSpace);

        //读取数据
        Map<String,String> maps = UserInfoUtils.readInfo();
        if (maps!=null){

            String name = maps.get("name");
            String pwd = maps.get("pwd");

            et_name.setText(name);
            et_password.setText(pwd);
        }


    }


    public void Login(View v){
        String name = et_name.getText().toString().trim();
        String pwd = et_password.getText().toString().trim();

        if (TextUtils.isEmpty(name) || TextUtils.isEmpty(pwd)){

            Toast.makeText(MainActivity.this, "用户名或密码为空", 1).show();
            return;
        }else{

            System.out.println("登录");

            if (cb_check.isChecked()){
                if (Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState())){
                    Toast.makeText(MainActivity.this, "sd卡可用", Toast.LENGTH_LONG).show();

                    boolean result = UserInfoUtils.save(name,pwd);

                    if(result){

                        Toast.makeText(MainActivity.this, "保存成功", 1).show();

                    }else {
                        Toast.makeText(MainActivity.this, "保存失败", 1).show();
                    }

                }else {
                    Toast.makeText(MainActivity.this, "sd卡不可用", Toast.LENGTH_LONG).show();
                }


            }else {

            Toast.makeText(MainActivity.this, "请勾记住用户名和密码", 1).show();
                return;
            }
        }
    }
}
