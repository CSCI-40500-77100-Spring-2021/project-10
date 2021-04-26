package com.example.mealsnap_prototype_2.services.appapi;

import android.util.Log;

import com.example.mealsnap_prototype_2.interfaces.ResultCallback;
import com.example.mealsnap_prototype_2.models.user.Auth;

import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

// TODO: Create specialized APIRequestError class
public class APIRequest {
    private static final String TAG = "APIRequest";
    private static final OkHttpClient client = new OkHttpClient();
    private static final String ROOT_URL = "https://dbkw974ay1.execute-api.us-east-1.amazonaws.com/prod/";

    /**
     * Generates appropriate request url given a route
     *
     * @param route Request Route. Ex: /echo | echo
     * @return Reformatted URL
     */
    private static String GetRequestURL(String route) {
        if (route.length() > 0 && route.charAt(0) == '/') {
            return ROOT_URL + route.substring(1); // Removes leading slash
        }
        return ROOT_URL + route;
    }

    //Gets from given root + given url
    public static void get(String url, ResultCallback<ResponseBody, IOException> responseEvent) {
        Auth.retrieveJWTToken(new ResultCallback<String, Exception>() {
            @Override
            public void onSuccess(String token) {
                Request request = new Request.Builder()
                        .url(GetRequestURL(url))
                        .header("Authorization", token)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try (ResponseBody responseBody = response.body()) {
                            Log.i(TAG, response.body().toString());
                            if (!response.isSuccessful())
                                throw new IOException("Unexpected code " + response);

                            responseEvent.onSuccess(responseBody);
                            Headers responseHeaders = response.headers();
                            for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                                Log.i(TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
                            }
                        }
                    }
                });
            }

            @Override
            public void onError(Exception error) {
                responseEvent.onError(new IOException(error.getMessage()));
            }
        });
    }

    public static void post(String url, JSONObject postBody, ResultCallback<ResponseBody, IOException> responseEvent) {
        RequestBody body = RequestBody.create(postBody.toString(), MediaType.parse("application/json; charset=utf-8"));
        Log.i(TAG, postBody.toString());

        Auth.retrieveJWTToken(new ResultCallback<String, Exception>() {
            @Override
            public void onSuccess(String token) {
                Request request = new Request.Builder()
                        .url(GetRequestURL(url))
                        .header("Authorization", token)
                        .post(body)
                        .build();

                client.newCall(request).enqueue(new Callback() {
                    @Override
                    public void onFailure(Call call, IOException e) {
                        e.printStackTrace();
                    }

                    @Override
                    public void onResponse(Call call, Response response) throws IOException {
                        try (ResponseBody responseBody = response.body()) {
                            if (!response.isSuccessful())
                                throw new IOException("Unexpected code " + response);

                            responseEvent.onSuccess(responseBody);
                            Headers responseHeaders = response.headers();
                            for (int i = 0, size = responseHeaders.size(); i < size; i++) {
                                Log.i(TAG, responseHeaders.name(i) + ": " + responseHeaders.value(i));
                            }
                        }
                    }
                });
            }

            @Override
            public void onError(Exception e) {
                responseEvent.onError(new IOException(e.getMessage()));
            }
        });

    }
}