package com.psychoapp.iliev.psychoapp.helpers;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

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
            for (int i = 0; i < numQuestions; i++) {
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

    public static List<String> getAuthenticatedUserInformation(String questionsJsonStr)
            throws JSONException {

        List<String> userInfo = new ArrayList<String>();

        JSONObject rootJson = null;
        try {
            rootJson = new JSONObject(questionsJsonStr);

            String token = rootJson.getString("access_token");
            String tokenType = rootJson.getString("token_type");
            String username = rootJson.getString("userName");
            userInfo.add(token);
            userInfo.add(tokenType);
            userInfo.add(username);

        } catch (JSONException e) {
            e.printStackTrace();
        }

        return userInfo;
    }
}
