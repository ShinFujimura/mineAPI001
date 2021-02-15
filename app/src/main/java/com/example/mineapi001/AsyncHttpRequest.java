package com.example.mineapi001;

import android.app.Activity;
import android.os.AsyncTask;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class AsyncHttpRequest extends AsyncTask<URL, Void, JSONArray> {

    private Activity mainActivity;

    public AsyncHttpRequest(MainActivity mainActivity) {
        this.mainActivity=mainActivity;
    }



    @Override
    protected JSONArray doInBackground(URL... urls) {

        final URL url=urls[0];
        HttpURLConnection con=null;

        try{
           // Log.d("TEST","TEST001");
            con=(HttpURLConnection)url.openConnection();
            con.setRequestMethod("GET");
            //con.setInstanceFollowRedirects(false);

            con.connect();
            Log.d("TEST","TEST001");
            Log.d("CON001", String.valueOf(con));




            //レスポンス（JSON文字列)を読み込む準備
            final InputStream in=con.getInputStream();


            final InputStreamReader inReader=new InputStreamReader(in,"UTF-8");
            final BufferedReader bufReader=new BufferedReader(inReader);
            StringBuilder response=new StringBuilder();
            String line = null;



            //１行ずつ読み込もう
            while((line=bufReader.readLine())!=null){
                response.append(line);
            }
            bufReader.close();
            inReader.close();
            in.close();

            //受け取ったJSON文字列をパース
            JSONObject jsonObject=new JSONObject(response.toString());
            JSONArray todayForecasts=jsonObject.getJSONArray("description");

            //return todayForecasts.getString("dateLabel") + "の天気は " + todayForecasts.getString("telop");

            Log.d("Today", String.valueOf(todayForecasts));

            return todayForecasts;
        }catch(IOException | JSONException e){
            e.printStackTrace();
        }

        return null;
    }
}
