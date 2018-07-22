package com.example.mapsv2;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.json.JSONArray;
import org.json.JSONObject;

/**
 * Created by prats on 6/4/2015.
 */
public class JSONActivity extends Activity {

    EditText editText1,editText2;
    Button button1;
    String cityname = null,cityname1 = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // TODO Auto-generated method stub
        super.onCreate(savedInstanceState);
        setContentView(R.layout.loginlayout);

        editText1 = (EditText) findViewById(R.id.editText1);
        editText2 = (EditText) findViewById(R.id.editText2);
        button1 = (Button) findViewById(R.id.button1);

        button1.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String cname = editText1.getText().toString().trim();
                String cname1 = editText2.getText().toString().trim();

                if(cname.length()>0){
                    cityname = cname;
                    cityname1 = cname1;
                    new getlatlongtask().execute();
                }
            }
        });
    }

    public class getlatlongtask extends AsyncTask<String, String, String> {


        private ProgressDialog pd;
        JSONObject jobj,jobj1;
        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            pd = new ProgressDialog(JSONActivity.this);
            pd.setMessage("Please wait...");
            pd.setCancelable(false);
            pd.show();
        }

        @Override
        protected String doInBackground(String... params) {
            String weburl = "http://maps.google.com/maps/api/geocode/json?address="+cityname+"&sensor=false";
            String weburl1 = "http://maps.google.com/maps/api/geocode/json?address="+cityname1+"&sensor=false";
            jobj = new GetJSONObject() .getJSONfromURL(weburl);
            jobj1 = new GetJSONObject() .getJSONfromURL(weburl1);
            return null;
        }
        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);

            if(jobj!=null && jobj1!=null){

                try {


                    JSONArray jArray = jobj.getJSONArray("results");
                    JSONObject j1 = jArray.getJSONObject(0);
                    JSONObject j2 = j1.getJSONObject("geometry");
                    JSONObject j3 = j2.getJSONObject("location");
                    String lat = j3.getString("lat");
                    String lng = j3.getString("lng");

                    JSONArray jArray1 = jobj1.getJSONArray("results");
                    JSONObject j11 = jArray1.getJSONObject(0);
                    JSONObject j22 = j11.getJSONObject("geometry");
                    JSONObject j33 = j22.getJSONObject("location");
                    String lat1 = j33.getString("lat");
                    String lng1 = j33.getString("lng");

                    if(lat.length()>0 && lng.length()>0 && lat1.length()>0 && lng1.length()>0){
                        Intent i = new Intent(JSONActivity.this,MainActivity.class);

                        i.putExtra("lat", lat);
                        i.putExtra("long", lng);
                        i.putExtra("lat1", lat1);
                        i.putExtra("long1", lng1);
                        startActivity(i);
                        pd.dismiss();
                    }

                } catch (Exception e) {
                    // TODO: handle exception
                }
            }
            pd.dismiss();
        }
    }
}