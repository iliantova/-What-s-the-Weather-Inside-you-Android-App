package com.psychoapp.iliev.psychoapp.dummy.HttpAsyncHelpers;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by iliev on 1/14/2016.
 */
public class RetreiveFeedTask extends AsyncTask<String, Void, String> {

    private Exception exception;

    protected void onPreExecute() {
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected String doInBackground(String... urls) {

        String response = null;

        // Do some validation here
        String API_URL_register = "http://psyhosgit.apphb.com/api/Account/Register";
        String API_URL_token = "http://psyhosgit.apphb.com/token";

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        try {
            // sample code for passing login body parameters
            String loginParams  =
                    "Username="+urls[0]
                    +"&Password="+urls[1]
                    +"&grant_type="+"password";

            byte[] postData = loginParams.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            String request = API_URL_token;
            URL url  = new URL(request);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(true);
            urlConnection.setInstanceFollowRedirects(false);
            urlConnection.setRequestMethod("POST");
            urlConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
            urlConnection.setRequestProperty("charset", "UTF-8");

            urlConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));

            urlConnection.setUseCaches(false);

            urlConnection.connect();

            try( DataOutputStream wr = new DataOutputStream( urlConnection.getOutputStream())) {
                wr.write( postData );
            }

            response = urlConnection.getResponseMessage().toString();

        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
        return response;
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }

        Log.i("TOPLO INFO OT SERVERA ", response);
    }
}