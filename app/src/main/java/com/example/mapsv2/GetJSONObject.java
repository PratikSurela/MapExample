package com.example.mapsv2;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by prats on 6/4/2015.
 */
public class GetJSONObject {

    public JSONObject getJSONfromURL(String url){
        InputStream is = null;
        String result = "";
        JSONObject jArray = null;

        //http post
        try{
            //HttpClient httpclient = new DefaultHttpClient();
            //HttpPost httppost = new HttpPost(url);
            //HttpResponse response = httpclient.execute(httppost);
            //HttpEntity entity = response.getEntity();
            URL nurl = new URL(url);
            URLConnection conn = nurl.openConnection();
            conn.setReadTimeout(20000);
            conn.setConnectTimeout(20000);

            //is = entity.getContent();
            is = conn.getInputStream();

        }catch(Exception e){
            jArray=null;
            return jArray;
        }

        //convert response to string
        try{
            BufferedReader reader = new BufferedReader(new InputStreamReader(is),8 * 1024);
            StringBuilder sb = new StringBuilder();
            String line = null;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
            is.close();
            result=sb.toString();
        }catch(Exception e){

        }

        try{

            jArray = new JSONObject(result);
        }catch(JSONException e){
        }

        return jArray;
    }
}
