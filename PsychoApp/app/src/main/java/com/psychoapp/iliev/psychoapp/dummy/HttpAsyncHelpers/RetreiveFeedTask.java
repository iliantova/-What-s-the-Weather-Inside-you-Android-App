package com.psychoapp.iliev.psychoapp.dummy.HttpAsyncHelpers;

import android.os.AsyncTask;
import android.util.Log;
import android.view.View;

import java.io.BufferedReader;
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

        // Do some validation here
        String API_URL = "https://psyhosgit.apphb.com/api/Account/Register";
        String username = "MAIMUNA";
        String password = "123456";
        String confirmPassword = "123456";
        String email = "maimuna@maimuna.com";

        try {
            URL url = new URL(API_URL + "Username=" + username + "&Password=" + password + "&ConfirmPassword=" + confirmPassword + "Email=" + email);

            HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
            try {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));
                StringBuilder stringBuilder = new StringBuilder();
                String line;
                while ((line = bufferedReader.readLine()) != null) {
                    stringBuilder.append(line).append("\n");
                }
                bufferedReader.close();
                return stringBuilder.toString();
            }
            finally{
                urlConnection.disconnect();
            }
        }
        catch(Exception e) {
            Log.e("ERROR", e.getMessage(), e);
            return null;
        }
    }

    protected void onPostExecute(String response) {
        if(response == null) {
            response = "THERE WAS AN ERROR";
        }

        Log.i("INFO", response);
    }
}