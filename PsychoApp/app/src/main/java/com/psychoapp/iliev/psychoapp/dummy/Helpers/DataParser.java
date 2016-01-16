package com.psychoapp.iliev.psychoapp.dummy.Helpers;

import android.text.format.Time;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;

public class DataParser {
    private static final String LOG_TAG = DataParser.class.getSimpleName();

    /**
     * Take the String representing the complete forecast in JSON Format and
     * pull out the data we need to construct the Strings needed for the wireframes.
     *
     * Fortunately parsing is easy:  constructor takes the JSON string and converts it
     * into an Object hierarchy for us.
     */
    public static String getQuestionsFromJsonString(String questionsJsonStr, int numQuestions)
            throws JSONException {

        // These are the names of the JSON objects that need to be extracted.
        final String QUIZ_QUESTION_TEXT = "Text";

        JSONObject rootJsonObj = new JSONObject(questionsJsonStr);
        JSONArray questionsJsonArray = rootJsonObj.getJSONArray(QUIZ_QUESTION_TEXT);

//        String[] resultStrs = new String[numQuestions];
//        for(int i = 0; i < numQuestions; i++) {
//
//            g
//        }

        return rootJsonObj.toString();

    }
}