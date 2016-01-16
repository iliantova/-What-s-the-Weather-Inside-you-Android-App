package com.psychoapp.iliev.psychoapp.dummy.HttpAsyncHelpers;

import android.annotation.TargetApi;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;

public class HttpServerResponseTask extends AsyncTask<String, Void, String[]> {

    private Exception exception;
    private final String HTTP_POST_METHOD = "POST";
    private final String HTTP_GET_METHOD = "GET";
    private final String HTTP_PUT_METHOD = "PUT";

    private static final String CONTENT_TYPE_X_FORM = "application/x-www-form-urlencoded";
    private static final String CONTENT_TYPE_JSON = "application/json";

    private final String API_URL_register = "http://psyhosgit.apphb.com/api/Account/Register";
    private final String API_URL_token = "http://psyhosgit.apphb.com/token";
    private final String API_URL_questions_getTenRandom = "http://psyhosgit.apphb.com/api/Questions";

    protected void onPreExecute() {
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected String[] doInBackground(String... urls) {

        // result[0] will be response message from server (control)
        // result[1] will string with be the actual content from the outputStream from server
        String[] result = {"", ""};
        String response = null;

        // Do some validation here

        HttpURLConnection urlConnection = null;

        String passedParams = null;
        String API_KEY = null;
        String HTTP_METHOD = null;
        String CONTENT_TYPE = null;

        byte[] postData = null;
        String request = null;
        URL url  = null;
        int postDataLength = 0;

        BufferedReader reader = null;

        try {
            if (urls[0].equals("LOGIN_PARAMS")) {
                // sample code for passing login body parameters
                passedParams  =
                        "Username="+urls[1]
                        +"&Password="+urls[2]
                        +"&grant_type="+"password";
                API_KEY = API_URL_token;
                HTTP_METHOD = HTTP_POST_METHOD;
                CONTENT_TYPE = CONTENT_TYPE_X_FORM;

            } else if (urls[0].equals("REGISTER_PARAMS")) {
                passedParams  =
                        "Username="+urls[1]
                        +"&Password="+urls[2]
                        +"&ConfirmPassword="+urls[3]
                        +"&Email="+urls[4];
                API_KEY = API_URL_register;
                HTTP_METHOD = HTTP_POST_METHOD;
                CONTENT_TYPE = CONTENT_TYPE_X_FORM;

            } else if (urls[0].equals("QUESTIONS_10_RANDOM_PARAMS")) {
                API_KEY = API_URL_questions_getTenRandom;
                HTTP_METHOD = HTTP_GET_METHOD;
                CONTENT_TYPE = CONTENT_TYPE_JSON;

            } else {
                return null;
            }

            // if method is not GET send request body ... else no request body
            if(!HTTP_METHOD.equals(HTTP_GET_METHOD)) {
                postData = passedParams.toString().getBytes("UTF-8");
                postDataLength = postData.length;
                request = API_KEY;
                url  = new URL(request);
            } else {
                request = API_KEY;
                url  = new URL(request);
            }

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setDoOutput(false);
            urlConnection.setInstanceFollowRedirects(false);
            urlConnection.setRequestMethod(HTTP_METHOD);

            // if method is GEt then add autorization header
            if(HTTP_METHOD.equals(HTTP_GET_METHOD)) {
                urlConnection.setRequestProperty("Authorization", urls[1]);
            }

            urlConnection.setRequestProperty("Content-Type", CONTENT_TYPE);
            urlConnection.setRequestProperty("charset", "UTF-8");

            urlConnection.setRequestProperty("Content-Length", Integer.toString(postDataLength));

            urlConnection.setUseCaches(false);

            urlConnection.connect();

            // create request body only for POST and PUT
            if(HTTP_METHOD.equals(HTTP_POST_METHOD) || HTTP_METHOD.equals(HTTP_PUT_METHOD)) {
                try( DataOutputStream wr = new DataOutputStream( urlConnection.getOutputStream())) {
                    wr.write( postData );
                } catch (Exception e) {
                    Log.e("DataOutputStream error", e.getMessage(), e);
                    return null;
                }
            }

            response = urlConnection.getResponseMessage().toString();
            result[0] = response;

            // Read the input stream into a String
            InputStream inputStream = urlConnection.getInputStream();
            Log.e("INPUT STREAM", inputStream.toString());
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

            JSONArray jsonRootObject = null;
            try {
                jsonRootObject = new JSONArray(result[1]);
                for (int i = 0; i < jsonRootObject.length(); i++) {
                    JSONObject jsonobject = jsonRootObject.getJSONObject(i);
                    String name = jsonobject.getString("Text");
                    Log.e("JSON obj YRAAAAAA", name);
                }

            } catch (JSONException e) {
                e.printStackTrace();
            }


        }
        catch(Exception e) {
            Log.e("ResponseTask ERROR", e.getMessage(), e);
            return null;
        }

        return result;
    }

    protected void onPostExecute(String[] result) {
        String res = "";
        if(result == null || result[0].equals("")) {
            res = "NO Response from Server";
            Log.i("onPostExecute Error :", res);
            return;
        }

        Log.i("onPostExecute Result: ", result[1]);
    }
}