package com.example.v310.pic;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    static ImageView IV_PIC;
    public Button get_btn,send_btn,get_pic,pic_btn;
    EditText et_time;
    static TextView tv_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        IV_PIC=findViewById(R.id.pic);
        get_pic=findViewById(R.id.btn_pic);
        send_btn=findViewById(R.id.send_btn);
        et_time=findViewById(R.id.ed_time);
        get_btn=findViewById(R.id.get_btn);
        tv_time=findViewById(R.id.tv_time);
        pic_btn=findViewById(R.id.photo_btn);
        pic_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String cmd="photo:1";
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
                String cmd="time:"+et_time.getText().toString();
                new  MyThread(MainActivity.this).execute("cmd",cmd);
            }
        });

    }




}
