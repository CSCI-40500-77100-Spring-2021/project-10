package services.authhandler;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthSession;

public interface RestoreUser {
    void onSuccess(AuthSession result);
    void onFailure(AuthException authError);
}
