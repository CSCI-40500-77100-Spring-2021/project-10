package services.authhandler;

import android.util.Log;

import com.amazonaws.mobileconnectors.cognitoauth.Auth;
import com.google.gson.Gson;

import org.jetbrains.annotations.NotNull;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Headers;
import okhttp3.OkHttpClient;
import okhttp3.Request;
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

    //TODO ^ for post and delete
}