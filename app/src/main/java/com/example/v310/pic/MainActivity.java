package com.example.v310.pic;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.Layout;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    static ImageView IV_PIC;
    public Button get_btn,send_btn,get_pic,pic_btn,door_btn;
    static EditText et_Ltemp,et_Htemp,et_Lhum,et_Hhum;
    static TextView tv_temp,tv_hum,tv_door;
    String cmd="",cmd1,cmd2,cmd3,cmd4;
     static  boolean Ht_flag,Lt_flag,Hh_flag,Lh_flag;
     LinearLayout main_layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IV_PIC=findViewById(R.id.pic);
        get_pic=findViewById(R.id.btn_pic);
        send_btn=findViewById(R.id.send_btn);
        door_btn=findViewById(R.id.door_btn);
        et_Ltemp=findViewById(R.id.et_Ltemp);
        et_Htemp=findViewById(R.id.et_Htemp);
        et_Lhum=findViewById(R.id.et_Lhum);
        et_Hhum=findViewById(R.id.et_Hhum);
        get_btn=findViewById(R.id.get_btn);
        tv_temp=findViewById(R.id.tv_temp);
        tv_hum=findViewById(R.id.tv_hum);
        tv_door=findViewById(R.id.tv_door);
        pic_btn=findViewById(R.id.photo_btn);
        main_layout=findViewById(R.id.main_layout);



        et_Htemp.setText("100");
        et_Ltemp.setText("0");
        et_Hhum.setText("100");
        et_Lhum.setText("0");


        et_Htemp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Hh_flag=true;
                Log.e("1", "changed");

            }
        });
        et_Ltemp.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Lt_flag=true;

            }
        });

        et_Hhum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {


            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()) {
                    if (Integer.parseInt(charSequence.toString()) < 0 || Integer.parseInt(charSequence.toString()) > 100) {

                        Toast.makeText(getApplicationContext(), "阈值范围为0-100,重新输入", Toast.LENGTH_LONG).show();
                        et_Hhum.setText("");
                    }

                    }

            }

            @Override
            public void afterTextChanged(Editable editable) {
                Hh_flag=true;
            }
        });

        et_Lhum.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }
            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                if(!charSequence.toString().isEmpty()) {
                    if (Integer.parseInt(charSequence.toString()) < 0 || Integer.parseInt(charSequence.toString()) > 100) {
                        Toast.makeText(getApplicationContext(), "阈值范围为0-100,重新输入", Toast.LENGTH_LONG).show();
                        et_Lhum.setText("");
                    }

                }
            }

            @Override
            public void afterTextChanged(Editable editable) {
                Lh_flag=true;

            }
        });

        /////////////////////////////
        new MyThread(MainActivity.this).execute("get");


        door_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                cmd="door:1";
                new  MyThread(MainActivity.this).execute("door",cmd);
            }
        });

        pic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               cmd="photo:1";
                new  MyThread(MainActivity.this).execute("photo",cmd);
            }
        });
        get_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyThread(MainActivity.this).execute("get");
            }
        });
        get_pic.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new MyThread(MainActivity.this).execute("pic");
            }
        });
        send_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                main_layout.requestFocus();//取消焦点
                cmd1= "Temp_Max:"+et_Htemp.getText().toString();
                cmd2= "Temp_Min:"+et_Ltemp.getText().toString();
                cmd3= "Hum_Max:"+et_Hhum.getText().toString();
                cmd4= "Hum_Min:"+et_Lhum.getText().toString();
                new  MyThread(MainActivity.this).execute("cmd",cmd1,cmd2,cmd3,cmd4);


            }
        });

    }




}
