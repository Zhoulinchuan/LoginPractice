package com.example.zlc.loginpractice;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

import static android.R.attr.tag;

/**
 * Created by ZLC on 2017/2/18.
 */

public class UserInfoUtils {
    private static String tag = "savePasswordService";


    public static boolean save(String name,String pwd){

        Log.d(tag, "设置文件的读写权限");

        try {

            String result = name + ":" + pwd;

            String sdPath = Environment.getExternalStorageDirectory().getPath();//获取sd存储路径
            //创建File类
            /**
             * 存到程序自带数据区
             */
            //File file = new File("/data/data/com.example.zlc.loginpractice/info.txt");
            /**
             * 存到sd卡
             */
            //File file = new File("/mnt/sdcard/info.txt");
            File file = new File(sdPath,"info.txt");
            //创建文件输出流
            FileOutputStream fos = new FileOutputStream(file);
            fos.write(result.getBytes());
            fos.close();
            return true;
        } catch (Exception e) {
            Log.d(tag, "设置读写权限失败");
            e.printStackTrace();
            return false;
        }
    }


    public static Map<String,String> readInfo(){

        try {
            Map<String,String> map = new HashMap<String,String>();
            String sdPath = Environment.getExternalStorageDirectory().getPath();//获取sd存储路径
            /**
             * 存到程序自带数据区
             */
            //File file = new File("/data/data/com.example.zlc.loginpractice/info.txt");
            /**
             * 存到sd卡
             */
            //File file = new File("/mnt/sdcard/info.txt");
            File file = new File(sdPath,"info.txt");
            FileInputStream fis = new FileInputStream(file);
            BufferedReader bufr = new BufferedReader(new InputStreamReader(fis));
            String content = bufr.readLine();//读取数据

            //切割数据
            String[] splits = content.split(":");
            String name = splits[0];
            String pwd = splits[1];
            //把 name 和 pwd 放入map中
            map.put("name",name);
            map.put("pwd",pwd);
            fis.close();

            return map;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
