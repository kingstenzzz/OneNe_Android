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
            index=api.Get_Photo_index("ppp");//pic的index
             s=index.substring(10,index.length()-2) ;//通过index获取图片
             bitmap1=api.Getbin(s);

        }
        else if (strings[0].equals("cmd"))
        {
            if(MainActivity.Ht_flag) {api.postcmd(strings[1]);MainActivity.Ht_flag=false;}
            if(MainActivity.Lt_flag){api.postcmd(strings[2]);MainActivity.Lt_flag=false;}
            if(MainActivity.Hh_flag){api.postcmd(strings[3]);MainActivity.Hh_flag=false;}
            if(MainActivity.Lh_flag){api.postcmd(strings[4]);MainActivity.Lh_flag=false;}

        }
        else if (strings[0].equals("photo"))
        {
            api.postcmd(strings[1]);

        }
        else if (strings[0].equals("door"))
        {
            api.postcmd(strings[1]);

        }
        else if (strings[0].equals("get"))
        {
            data=api.Getvalue(",Humidity,Temperature,Door,Temp_Max,Temp_Min,Hum_Max,Hum_Min");//time数据流
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
        else if(p.equals("cmd")||p.equals("door"))
        {
            Toast.makeText(context,"开门成功",Toast.LENGTH_LONG).show();

        }
        else if (p.equals("get"))
        {
            MainActivity.tv_temp.setText(api.temp+"度");
            MainActivity.tv_hum.setText(api.hum+"%");
            if(api.door.equals("1"))
                MainActivity.tv_door.setText("开");
            else
                MainActivity.tv_door.setText("关");
            MainActivity.et_Htemp.setText(api.H_temp);
            MainActivity.et_Ltemp.setText(api.L_temp);
            MainActivity.et_Hhum.setText(api.H_hum);
            MainActivity.et_Lhum.setText(api.L_hum);

        }


    }
}