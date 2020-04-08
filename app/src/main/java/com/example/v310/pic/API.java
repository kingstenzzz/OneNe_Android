package com.example.v310.pic;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Message;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public    class API {
    final String DeviceID = "30964714";//你的平台id
    final String ApiKey = "Uv=e=yMBymo8In9FVA4Ub16Oleo=";
    String temp="0",hum="0",door,H_temp,L_temp,H_hum,L_hum;



/*

 获取数据，这里是获取间隔

 */
    public String Getvalue(String data) {  //获取数据流zzz的数据，返回zzz的当前值，默认是数值型
        String respon = "";

        try {
           // http://api.heclouds.com/devices/20474930/datastreams?datastream_ids=aaa,bbb HTTP/1.1
            URL url = new URL("http://api.heclouds.com/devices/" + DeviceID + "/datastreams?datastream_ids=" + data);//1创建一个URL对象，并传入目标网络地址
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();      //2然后用openConnection方法
            conn.setConnectTimeout(15 * 1000);   //设置连接超时的时间
            conn.setRequestMethod("GET");      //3HTTP请求的方法是GET
            conn.setRequestProperty("api-key", ApiKey); //4消息头
            if (conn.getResponseCode() == 200) {  //返回码是200，网络正常
                InputStream is = conn.getInputStream();            //读取获得的输入流
                ByteArrayOutputStream os = new ByteArrayOutputStream();//字节数组输出流在内存中创建一个字节数组缓冲区，
                int len = 0;                                          //所有发送到输出流的数据保存在该字节数组缓冲区中。
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {        // // 将内容读到buffer中，读到末尾为-1
                    os.write(buffer, 0, len);
                }
                is.close();//关闭
                os.close();

                JSONObject root = new JSONObject(os.toString());
                JSONArray array =root.getJSONArray("data");

                JSONObject object = array.getJSONObject(0);

                    temp = object.optString("current_value", null);
                    object = array.getJSONObject(1);
                    hum = object.optString("current_value");
                    object = array.getJSONObject(2);
                    door = object.optString("current_value");
                    object = array.getJSONObject(3);
                    H_temp = object.optString("current_value", null);
                    object = array.getJSONObject(4);
                    L_temp = object.optString("current_value");
                    object = array.getJSONObject(5);
                    H_hum = object.optString("current_value");
                    object = array.getJSONObject(6);
                    L_hum = object.optString("current_value");
                    respon="temp:"+temp+"hum:"+hum+"door"+door;
                    Log.e("1", root.toString());


            } else {
                //返回码不是200，网络异常
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
      /*  MainActivity.tv_temp.setText(temp+"%");
        MainActivity.tv_hum.setText(hum+"%");

*/
        return respon;
    }


    public String Get_Photo_index(String data) {  //获取数据流zzz的数据，返回zzz的当前值，默认是数值型
        String respon = "";

        try {
            // http://api.heclouds.com/devices/20474930/datastreams?datastream_ids=aaa,bbb HTTP/1.1
            URL url = new URL("http://api.heclouds.com/devices/" + DeviceID + "/datastreams/" + data);//1创建一个URL对象，并传入目标网络地址
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();      //2然后用openConnection方法
            conn.setConnectTimeout(15 * 1000);   //设置连接超时的时间
            conn.setRequestMethod("GET");      //3HTTP请求的方法是GET
            conn.setRequestProperty("api-key", ApiKey); //4消息头
            if (conn.getResponseCode() == 200) {  //返回码是200，网络正常
                InputStream is = conn.getInputStream();            //读取获得的输入流
                ByteArrayOutputStream os = new ByteArrayOutputStream();//字节数组输出流在内存中创建一个字节数组缓冲区，
                int len = 0;                                          //所有发送到输出流的数据保存在该字节数组缓冲区中。
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {        // // 将内容读到buffer中，读到末尾为-1
                    os.write(buffer, 0, len);
                }
                is.close();//关闭
                os.close();

                JSONObject root = new JSONObject(os.toString());
                JSONObject resultObject=root.getJSONObject("data");
                respon=resultObject.optString("current_value");
                Log.e("1", root.toString());

            } else {
                //返回码不是200，网络异常
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
      /*  MainActivity.tv_temp.setText(temp+"%");
        MainActivity.tv_hum.setText(hum+"%");

*/
        return respon;
    }



    public void Post(String s, long zzz) {  //上传数值zzz到S数据流
        String ddd = new String(",;" + s + "," + zzz);
        String respon = null;
        byte[] data = ddd.getBytes();
        try {
            URL url = new URL("http://api.heclouds.com/devices/" + DeviceID + "/datapoints?type=5");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(15 * 1000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("api-key", ApiKey);
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            conn.setChunkedStreamingMode(5);
            conn.connect();
            OutputStream outStream = conn.getOutputStream();
            outStream.write(data);
            outStream.flush();
            outStream.close();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                is.close();
                os.close();
                respon = os.toString();

                //正常则返回{"errno":0,"error":"succ"}，此函数为void，用不上这个
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
//发送命令控制拍照和间隔
    public void postcmd(String cmd) {
        byte[] data = cmd.getBytes();
        try {
            URL url = new URL("http://api.heclouds.com/cmds?device_id="+DeviceID+"&qos=1&timeout=300&type=0");
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(15 * 1000);
            conn.setDoOutput(true);
            conn.setDoInput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("api-key", ApiKey);
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            conn.setChunkedStreamingMode(5);
            conn.connect();
            OutputStream outStream = conn.getOutputStream();
            outStream.write(data);
            outStream.flush();
            outStream.close();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {
                    os.write(buffer, 0, len);
                }
                is.close();
                os.close();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

//获取图片
    public Bitmap Getbin(String index) {  //获取index值的二进制文件
//Image Photo=null;
        Drawable drawable = null;
        Bitmap bitmap = null;
        byte[] p = null;
        //String p=null;
        try {
            URL url = new URL("http://api.heclouds.com/bindata/" + index);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(15 * 1000);
            conn.setRequestMethod("GET");
            conn.setRequestProperty("api-key", ApiKey);

            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();

                ByteArrayOutputStream os = new ByteArrayOutputStream();

                int len = 0;
                byte buffer[] = new byte[20480];
                while ((len = is.read(buffer)) != -1)

                {
                    os.write(buffer, 0, len);
                }
                is.close();
                os.close();
                // p=os.toString();
                p = os.toByteArray();
                bitmap = BitmapFactory.decodeByteArray(p, 0, p.length);
                drawable = new BitmapDrawable(bitmap);

            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bitmap; //返回二进制文件的字节数组

    }
  /*  public Drawable Getbin() {  //获取index值的二进制文件
        Drawable drawable=null;
        try {
            URL url=new URL("http://p0.so.qhmsg.com/t015bae9a005b1c40a5.png");
            URLConnection conn=url.openConnection();

            InputStream in=url.openStream();
            drawable=Drawable.createFromStream(in,null);
            in.close();
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        catch (IOException e2)
        {}
        return  drawable; //返回二进制文件的字节数组

    }*/

    public void Postcmd(String cmd) {
        byte[] data = cmd.getBytes();
        try {
            URL url = new URL("http://api.heclouds.com/cmds?device_id = " + DeviceID);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setConnectTimeout(15 * 1000);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestMethod("POST");
            conn.setRequestProperty("api-key", ApiKey);
            conn.setRequestProperty("Content-Length", String.valueOf(data.length));
            conn.setChunkedStreamingMode(10);
            conn.connect();
            OutputStream outStream = conn.getOutputStream();
            outStream.write(data);
            outStream.flush();
            outStream.close();
            if (conn.getResponseCode() == 200) {
                InputStream is = conn.getInputStream();
                ByteArrayOutputStream os = new ByteArrayOutputStream();
                int len = 0;
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {        // // 将内容读到buffer中，读到末尾为-1
                    os.write(buffer, 0, len);
                }
                is.close();//关闭
                os.close();


            }
        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    public Boolean Getdevice() {  //获取数据流zzz的数据，返回zzz的当前值，默认是数值型
        Boolean respon = true;
        try {
            URL url = new URL("http://api.heclouds.com/devices/" + DeviceID);//1创建一个URL对象，并传入目标网络地址
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();      //2然后用openConnection方法
            conn.setConnectTimeout(15 * 1000);   //设置连接超时的时间
            conn.setRequestMethod("GET");      //3HTTP请求的方法是GET
            conn.setRequestProperty("api-key", ApiKey); //4消息头
            if (conn.getResponseCode() == 200) {  //返回码是200，网络正常
                InputStream is = conn.getInputStream();            //读取获得的输入流
                ByteArrayOutputStream os = new ByteArrayOutputStream();//字节数组输出流在内存中创建一个字节数组缓冲区，
                int len = 0;                                          //所有发送到输出流的数据保存在该字节数组缓冲区中。
                byte buffer[] = new byte[1024];
                while ((len = is.read(buffer)) != -1) {        // // 将内容读到buffer中，读到末尾为-1
                    os.write(buffer, 0, len);
                }
                is.close();//关闭
                os.close();
                JSONObject root = new JSONObject(os.toString());   //将缓冲区的内容转换为字符串，然后字符串构建JSON对象
                respon = root.getJSONObject("data").getBoolean("online");
                Log.e("返回数据", "" + root);
            } else {
                //返回码不是200，网络异常
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
        return respon;
    }
}
