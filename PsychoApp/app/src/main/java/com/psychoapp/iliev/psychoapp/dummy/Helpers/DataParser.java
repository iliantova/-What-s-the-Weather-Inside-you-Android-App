package com.psychoapp.iliev.psychoapp.dummy.Helpers;

import android.text.format.Time;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

public class DataParser {
    private static final String LOG_TAG = DataParser.class.getSimpleName();

    public static List<String> getQuestionsFromJsonString(String questionsJsonStr, int numQuestions)
            throws JSONException {

        List<String> questionsArray = new ArrayList<String>();

        JSONArray rootJson = null;
        try {
            rootJson = new JSONArray(questionsJsonStr);
            for (int i = 0; i < rootJson.length(); i++) {
                JSONObject questionJson = rootJson.getJSONObject(i);
                String question = questionJson.getString("Text");
                questionsArray.add(question);

                JSONArray answersJsonArr = questionJson.getJSONArray("Answers");
                for (int y = 0; y < answersJsonArr.length(); y++) {
                    JSONObject answerJson = answersJsonArr.getJSONObject(y);
                    String answerText = answerJson.getString("Text");
                    String answerValue = answerJson.getString("Value");
                    questionsArray.add(answerText);
                    questionsArray.add(answerValue);
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return questionsArray;
    }
}
