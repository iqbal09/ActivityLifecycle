package com.bekup.medan;

import android.app.ProgressDialog;
import android.net.ParseException;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.bekup.medan.setget.Phone;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

public class JadwalSholat extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jadwal_sholat);

        new JadwalSholat.JSONAsyncTask().execute("http://ibacor.com/api/pray-times?address=bandung&timezone=7&method=0&year=2016&month=2&day=6");

    }


    class JSONAsyncTask extends AsyncTask<String, Void, Boolean> {


        ProgressDialog dialog;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            dialog = new ProgressDialog(JadwalSholat.this);
            dialog.setMessage("Sedang Mengambil Data...");
            dialog.show();
            dialog.setCancelable(false);
        }

        @Override
        protected Boolean doInBackground(String... urls) {
            try {

                //------------------>>
                HttpGet httppost = new HttpGet(urls[0]);
                HttpClient httpclient = new DefaultHttpClient();
                HttpResponse response = httpclient.execute(httppost);

                // StatusLine stat = response.getStatusLine();
                int status = response.getStatusLine().getStatusCode();

                if (status == 200) {
                    HttpEntity entity = response.getEntity();
                    String data = EntityUtils.toString(entity);


                    JSONObject jsono = new JSONObject(data);
                    JSONObject job = jsono.getJSONObject("result");
                    System.out.println("===============");
                    System.out.println(job.toString());
                    System.out.println("SUBUHHH 1 "+job.getString("fajr"));

                    final int n = job.length();
                    for (int i = 0; i < n; ++i) {
                        System.out.println("SUBUHHH "+job.getString("fajr"));

                     //   final JSONObject jadwalnya = job.getJSONObject(i);

                    }

//                    for (int i = 0; i < job.length(); i++) {
//                        JSONObject object = job.getJSONObject(job.getString());
//
//                        System.out.println("SUBUHHHHH " + object.getString("status"));
//
////                        Phone phone = new Phone();
////                        phone.setTitle(object.getString("title"));
////                        phone.setImage(object.getString("img"));
////                        phone.setSlug(object.getString("slug"));
////                        phoneList.add(phone);
//                    }
                    return true;
                }

                //------------------>>

            } catch (ParseException e1) {
                e1.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return false;
        }

        protected void onPostExecute(Boolean result) {
            dialog.cancel();
            if(result == false)
                Toast.makeText(JadwalSholat.this, "Unable to fetch data from server", Toast.LENGTH_LONG).show();

        }
    }
}
