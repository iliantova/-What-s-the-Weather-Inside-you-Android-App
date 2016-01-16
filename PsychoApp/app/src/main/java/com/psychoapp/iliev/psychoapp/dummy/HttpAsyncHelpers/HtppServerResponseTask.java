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

        BufferedReader reader = null;

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

            byte[] postData = passedParams.toString().getBytes("UTF-8");
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

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            StringBuffer buffer = new StringBuffer();
            if (inputStream == null) {
                // Nothing to do.
                return null;
            }
            reader = new BufferedReader(new InputStreamReader(inputStream));

            String line;
            while ((line = reader.readLine()) != null) {
                // Since it's JSON, adding a newline isn't necessary (it won't affect parsing)
                // But it does make debugging a *lot* easier if you print out the completed
                // buffer for debugging.
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                // Stream was empty.  No point in parsing.
                return null;
            }

            // TODO use adding string to result[1] to retrieve the outputStraem from server and use custom jsonParser
            result[1] = buffer.toString();

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

        Log.i("TOPLO INFO OT SERVERA ", result[1]);
    }
}