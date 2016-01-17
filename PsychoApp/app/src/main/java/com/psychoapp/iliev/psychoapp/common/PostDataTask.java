package com.psychoapp.iliev.psychoapp.common;

import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class PostDataTask {
    public static final MediaType JSON
            = MediaType.parse("application/json; charset=utf-8");

    OkHttpClient client = new OkHttpClient();

    public String post(String url, String json) throws IOException {
        RequestBody body = RequestBody.create(JSON, json);
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .build();
        Response response = client.newCall(request).execute();
        return response.body().string();
    }

    public String bowlingJson(String username, String password, String confirmPassword, String email) {
        return "{'Username':'" + username + "'}," +
                "{'Password':'" + password + "'}," +
                "{'ConfirmPassword':'" + confirmPassword + "'}," +
                "{'Email':'" + email + "'},";
    }

    public static void main() throws IOException {
        PostDataTask example = new PostDataTask();
        String json = example.bowlingJson("blaaaaaaa", "123456", "123456", "blabla@bla.com");
        String response = example.post("http://psyhosgit.apphb.com/api/Account/Register", json);
        System.out.println(response);
    }
}
