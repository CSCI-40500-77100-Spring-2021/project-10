package services.authhandler;

import android.util.Log;

import com.amazonaws.mobileconnectors.cognitoauth.Auth;
import com.amplifyframework.auth.AuthSession;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;
import java.io.PipedOutputStream;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import okhttp3.ResponseBody;

public class APIRequest {
    public static final String TAG = "APIRequest";
    public static String ROOT_URL = "https://dbkw974ay1.execute-api.us-east-1.amazonaws.com/prod/";

    private static final OkHttpClient client = new OkHttpClient();

    //Gets from given root + given url
    public static void get(String url, APIServiceResponseEvent responseEvent) {
        AuthHandler.retrieveJWTToken(new JWTCallback() {
            @Override
            public void onSuccess(String token) {
                Request request = new Request.Builder()
                    .url(ROOT_URL + url)
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
            public void onFailure(String failMessage) {
                Log.i(TAG, "Jwtfailed");
            }
        });
    }

    public static final MediaType MEDIA_TYPE_MARKDOWN = MediaType.parse("text/x-markdown; charset=utf-8");

    //take body as input and make generic, go define each type of post image, string, like, etc
    public static void post(String url, APIServiceResponseEvent responseEvent) {
        String postBody = ""
                + "Releases\n"
                + "--------\n"
                + "\n"
                + " * _1.0_ May 6, 2013\n"
                + " * _1.1_ June 15, 2013\n"
                + " * _1.2_ August 11, 2013\n";
        RequestBody body = RequestBody.create(postBody, MEDIA_TYPE_MARKDOWN);

        AuthHandler.retrieveJWTToken(new JWTCallback() {
            @Override
            public void onSuccess(String token) {
                Request request = new Request.Builder()
                        .url(ROOT_URL + url)
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
            public void onFailure(String failMessage) {
                Log.i(TAG,"fukin suck");
            }
        });

    }

    //TODO ^ for post and delete
}