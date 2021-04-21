package services.authhandler;

import java.io.IOException;

import okhttp3.ResponseBody;

public interface JWTCallback {
    void onSuccess(String token);
    void onFailure(String failMessage);
}
