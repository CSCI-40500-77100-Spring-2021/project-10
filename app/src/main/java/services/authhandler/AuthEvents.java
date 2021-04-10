package services.authhandler;

import com.amplifyframework.auth.AuthException;

public interface AuthEvents {
    void onSuccess();
    void onFailure(AuthException authError);
}