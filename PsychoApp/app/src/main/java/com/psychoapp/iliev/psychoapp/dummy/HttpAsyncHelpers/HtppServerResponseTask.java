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

public class HtppServerResponseTask extends AsyncTask<String, Void, String[]> {

    private Exception exception;

    protected void onPreExecute() {
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected String[] doInBackground(String... urls) {

        // result[0] will be response message from server (control)
        // result[1] will string with be the actual content from the outputStream from server
        String[] result = {"", ""};
        String response = null;

        // Do some validation here
        String API_URL_register = "http://psyhosgit.apphb.com/api/Account/Register";
        String API_URL_token = "http://psyhosgit.apphb.com/token";

        HttpURLConnection urlConnection = null;
        String passedParams = null;
        String API_KEY = null;

        try {
            if (urls[0].equals("LOGIN_PARAMS")) {
                // sample code for passing login body parameters
                passedParams  =
                        "Username="+urls[1]
                        +"&Password="+urls[2]
                        +"&grant_type="+"password";
                API_KEY = API_URL_token;

            } else if (urls[0].equals("REGISTER_PARAMS")) {
                passedParams  =
                        "Username="+urls[1]
                        +"&Password="+urls[2]
                        +"&ConfirmPassword="+urls[3]
                        +"&Email="+urls[4];
                API_KEY = API_URL_register;
            } else {
                return null;
            }

            byte[] postData = passedParams.getBytes(StandardCharsets.UTF_8);
            int postDataLength = postData.length;
            String request = API_KEY;
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
            result[0] = response;

            // TODO use adding string to result[1] to retreive the outputStrem from server
            result[1] = "";
        }
        catch(Exception e) {
            Log.e("STAMAT ERROR PESHO", e.getMessage(), e);
            return null;
        }

        return result;
    }

    protected void onPostExecute(String[] result) {
        String res = "";
        if(result[0] == null || result[0].equals("")) {
            res = "NO Response from Server";
        }

        Log.i("TOPLO INFO OT SERVERA ", result[0]);
    }
}