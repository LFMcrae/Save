package com.example.roony.save;

import android.os.Bundle;

import android.support.v7.app.AppCompatActivity;

import android.view.View;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.charset.StandardCharsets;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnWrite= (Button) findViewById(R.id.write);
        Button btnRead= (Button) findViewById(R.id.read);
        final EditText editText=(EditText) this.findViewById(R.id.name);
        final EditText editText1=(EditText) this.findViewById(R.id.number);

        btnWrite.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                OutputStream out = null;
                try {
                    FileOutputStream fileOutputStream = openFileOutput("FileName", MODE_PRIVATE);
                    out = new BufferedOutputStream(fileOutputStream);
                    String content = "name:"+editText.getText().toString()+",num:"+editText1.getText().toString();
                    try {
                        out.write(content.getBytes(StandardCharsets.UTF_8));
                        Toast.makeText(MainActivity.this, "输入成功", Toast.LENGTH_SHORT).show();
                    } finally {
                        if (out != null)
                            out.close();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });


        btnRead.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                InputStream in=null;
                try {
                    FileInputStream fileInputStream = openFileInput("FileName");
                    in=new BufferedInputStream(fileInputStream);
                    int c;
                    StringBuilder stringBuilder=new StringBuilder("");
                    try{
                        while ((c=in.read())!=-1) {
                            stringBuilder.append((char)c);
                        }
                        Toast.makeText(MainActivity.this,stringBuilder.toString(),Toast.LENGTH_LONG).show();
                    }
                    finally {
                        if(in!=null)
                            in.close();
                    }
                }
                catch (Exception e){
                    e.printStackTrace();
                }
            }
        });






    }
}


