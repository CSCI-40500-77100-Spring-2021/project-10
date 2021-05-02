package com.example.mealsnap_prototype_2.models.user;

import android.util.Log;

import com.example.mealsnap_prototype_2.interfaces.ResultCallback;
import com.example.mealsnap_prototype_2.services.appapi.APIRequest;
import com.google.gson.Gson;

import java.io.IOException;

import okhttp3.ResponseBody;

public class User {
    private static final String TAG = "ModelUser";

    private final String id;
//    private final String username;

    private User(String id, String username) {
        this.id = id;
//        this.username = username;
    }

    /**
     * Find User by their username
     *
     * @param username Searched for username
     * @param callback ResultCallback, @NULLABLE USER IF NONE EXISTS
     */
    public static void FindUserByUsername(String username, ResultCallback<User, UserError> callback) {
        String url = "search/user?username=" + username;
        APIRequest.get(url, new ResultCallback<ResponseBody, IOException>() {
            @Override
            public void onSuccess(ResponseBody result) {
                Log.i(TAG, "Received user response");
                try {
                    Gson gson = new Gson();
                    FetchFindUserResponse response = gson.fromJson(result.string(), FetchFindUserResponse.class);
                    if (response.users.length == 0) {
                        callback.onError(new UserError(UserErrorType.UserNotFound, "User doesn't exists"));
                        return;
                    }
                    callback.onSuccess(response.users[0]);
                } catch (IOException e) {
                    e.printStackTrace();
                    String errorMessage = e.getMessage() != null ? e.getMessage() : "Error Parsing Response";
                    callback.onError(new UserError(UserErrorType.APIError, errorMessage));
                }
            }

            @Override
            public void onError(IOException error) {
                Log.e(TAG, "Error happened");
                callback.onError(new UserError(UserErrorType.APIError, error.getMessage()));
            }
        });
    }

    public String getId() {
        return id;
    }

//    public String getUsername() {
//        return username;
//    }

    @Override
    public String toString() {
        return "User{" +
                "id='" + id + '\'' +
                '}';
    }

    private class FetchFindUserResponse {
        User[] users;
    }
}
