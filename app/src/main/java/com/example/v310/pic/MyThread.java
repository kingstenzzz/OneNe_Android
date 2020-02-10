package com.example.v310.pic;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;

import android.widget.Toast;


public class MyThread extends AsyncTask <String,Void,String> {


    API api=new API();
    String s;
    Bitmap bitmap1;
    Context context;
    String data;
    public  MyThread(Context context)
    {
        this.context=context;
    }
    @Override
    protected String doInBackground(String... strings) {

        if (strings[0].equals("pic"))
        {
            String index;
            index=api.Getvalue("pic2");//pic的index
             s=index.substring(10,index.length()-2) ;//通过index获取图片
             bitmap1=api.Getbin(s);




        }
        else if (strings[0].equals("cmd"))
        {
            api.postcmd(strings[1]);

        }
        else if (strings[0].equals("photo"))
        {
            api.postcmd(strings[1]);

        }
        else if (strings[0].equals("get"))
        {
            data=api.Getvalue("time");//time数据流
        }
        return strings[0];
    }

    @Override
    protected void onPostExecute(String p) {
        super.onPostExecute(p);
        if(p.equals("pic")) {
            MainActivity.IV_PIC.setImageBitmap(bitmap1);
            System.out.println("输出图片");
        }
        else if(p.equals("cmd")||p.equals("photo"))
        {
            Toast.makeText(context,"下发成功",Toast.LENGTH_LONG).show();

        }
        else if (p.equals("get"))
        {
            MainActivity.tv_time.setText(data+"s");

        }
    }
}