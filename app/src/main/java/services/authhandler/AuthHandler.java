package services.authhandler;

import android.util.Log;

import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;

public class AuthHandler{

    public static void signUp(String email, String username, String password, AuthEvents eventStatus){
        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), email)
                .build();
        Amplify.Auth.signUp(username, password, options,
                result -> {
                eventStatus.onSuccess();
                Log.i("AuthQuickStart", "Result: " + result.toString());
                },
                error -> {
                eventStatus.onFailure(error);
                Log.e("AuthQuickStart", "Sign up failed", error);
                }
        );
    }

    public static void signIn(String username, String password, AuthEvents eventStatus){
        Amplify.Auth.signIn(
                username,
                password,
                result -> {
                    eventStatus.onSuccess();
                    Log.i("AuthQuickstart", result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
                },
                error -> {
                    eventStatus.onFailure(error);
                    Log.e("AuthQuickstart", error.toString());
                }
        );
    }

    public static void signOut(AuthEvents eventStatus){
        Amplify.Auth.signOut(
                () -> {
                    eventStatus.onSuccess();
                    Log.i("AuthQuickstart", "Signed out successfully");
                },
                error -> {
                    eventStatus.onFailure(error);
                    Log.e("AuthQuickstart", error.toString());
                }
        );
    }

    /* if want later
    public static void confirmSignUp(String username, String confirmationCode){
        Amplify.Auth.confirmSignUp(
                username,
                confirmationCode,
                result -> Log.i("AuthQuickstart", result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete"),
                error -> Log.e("AuthQuickstart", error.toString())
        );
    }
    */

}
