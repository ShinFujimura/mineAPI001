package com.example.mineapi001;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;

import java.net.MalformedURLException;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //非同期初期を呼び出す
        String url="https://weather.tsukumijima.net/api/forecast/city/270000";
        try{

            new AsyncHttpRequest(this).execute(new URL(url));
        }catch(MalformedURLException e){
            e.printStackTrace();
        }
    }
}