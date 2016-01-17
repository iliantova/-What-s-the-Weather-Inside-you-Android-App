package com.psychoapp.iliev.psychoapp.dummy.HttpAsyncHelpers;

import android.annotation.TargetApi;
import android.os.AsyncTask;
import android.os.Build;
import android.util.Log;

import com.psychoapp.iliev.psychoapp.dummy.Helpers.DataParser;

import org.json.JSONException;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class HttpServerResponseTask extends AsyncTask<String, Void, List<String>> {

    private Exception exception;
    private final String HTTP_POST_METHOD = "POST";
    private final String HTTP_GET_METHOD = "GET";
    private final String HTTP_PUT_METHOD = "PUT";

    private static final String CONTENT_TYPE_X_FORM = "application/x-www-form-urlencoded";
    private static final String CONTENT_TYPE_JSON = "application/json";

    private final String API_URL_register = "http://psyhosgit.apphb.com/api/Account/Register";
    private final String API_URL_token = "http://psyhosgit.apphb.com/token";
    private final String API_URL_questions_getTenRandom = "http://psyhosgit.apphb.com/api/Questions";

    private final String LOGIN_PARAMS = "LOGIN_PARAMS";
    private final String REGISTER_PARAMS = "REGISTER_PARAMS";
    private final String GET_10_QUESTIONS_PARAMS = "QUESTIONS_10_RANDOM_PARAMS";

    protected void onPreExecute() {
    }

    @TargetApi(Build.VERSION_CODES.KITKAT)
    protected List<String> doInBackground(String... urls) {

        List<String> result = new ArrayList<String>();

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
        String responseJsonStr;

        try {
            if (urls[0].equals(LOGIN_PARAMS)) {
                // sample code for passing login body parameters
                passedParams  =
                        "Username="+urls[1]
                        +"&Password="+urls[2]
                        +"&grant_type="+"password";
                API_KEY = API_URL_token;
                HTTP_METHOD = HTTP_POST_METHOD;
                CONTENT_TYPE = CONTENT_TYPE_X_FORM;

            } else if (urls[0].equals(REGISTER_PARAMS)) {
                passedParams  =
                        "Username="+urls[1]
                        +"&Password="+urls[2]
                        +"&ConfirmPassword="+urls[3]
                        +"&Email="+urls[4];
                API_KEY = API_URL_register;
                HTTP_METHOD = HTTP_POST_METHOD;
                CONTENT_TYPE = CONTENT_TYPE_X_FORM;

            } else if (urls[0].equals(GET_10_QUESTIONS_PARAMS)) {
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

            // if method is GET then add autorization header
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
                buffer.append(line + "\n");
            }

            if (buffer.length() == 0) {
                return null;
            }

            responseJsonStr = buffer.toString();
        }
        catch(Exception e) {
            Log.e("ResponseTask ERROR", e.getMessage(), e);
            return null;
        } finally {
            urlConnection.disconnect();
        }

        if (reader != null) {
            try {
                reader.close();
            } catch (final IOException e) {
                Log.e("IOException ", "Error closing stream", e);
            }
        }

        // data parser follows here
        if (urls[0].equals(GET_10_QUESTIONS_PARAMS)) {
            try {
                result = DataParser.getQuestionsFromJsonString(responseJsonStr, 10);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        } else if (urls[0].equals(LOGIN_PARAMS)) {
            // TODO add dataParser here
            try {
                result = DataParser.getAuthenticatedUserInformation(responseJsonStr);
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        return result;
    }

    protected void onPostExecute(List<String> result) {
        String res = "";
        if(result == null || result.get(0).equals("")) {
            res = "NO Response result from Server";
            Log.i("onPostExecute Error :", res);
        }

    }
}