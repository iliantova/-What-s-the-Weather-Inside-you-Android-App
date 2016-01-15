package com.psychoapp.iliev.psychoapp.dummy.HttpAsyncHelpers;

import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by iliev on 1/14/2016.
 */
public class RetreiveFeedTask extends AsyncTask<String, Void, String> {

    private Exception exception;

    protected void onPreExecute() {
    }

    protected String doInBackground(String... urls) {

        String status = null;

        // Do some validation here
        String API_URL = "http://psyhosgit.apphb.com/api/Account/Register";
        String username = "MAIMUNA";
        String password = "123456";
        String confirmPassword = "123456";
        String email = "maimuna@maimuna.com";

        HttpURLConnection urlConnection = null;
        BufferedReader bufferedReader = null;

        try {
            URL url = new URL(API_URL);

            urlConnection = (HttpURLConnection) url.openConnection();

            urlConnection.setRequestMethod("POST");

            urlConnection.setRequestProperty("Username", username);
            urlConnection.setRequestProperty("Password", password);
            urlConnection.setRequestProperty("ConfirmPassword", confirmPassword);
            urlConnection.setRequestProperty("Email", email);

            urlConnection.setDoOutput(true);
            urlConnection.getOutputStream();
            //urlConnection.setInstanceFollowRedirects(false);
            urlConnection.connect();

            status = urlConnection.getResponseMessage().toString();

        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }

        return status;
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }

        Log.i("TOPLO INFO OT SERVERA ", response);
    }
}