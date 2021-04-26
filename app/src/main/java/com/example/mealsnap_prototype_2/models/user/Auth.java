package com.example.mealsnap_prototype_2.models.user;

import android.util.Log;

import com.amplifyframework.auth.AuthException;
import com.amplifyframework.auth.AuthSession;
import com.amplifyframework.auth.AuthUserAttributeKey;
import com.amplifyframework.auth.cognito.AWSCognitoAuthSession;
import com.amplifyframework.auth.options.AuthSignUpOptions;
import com.amplifyframework.core.Amplify;
import com.example.mealsnap_prototype_2.interfaces.ResultCallback;

public class Auth {

    private static final String TAG = "AuthHandler";

    public static void signUp(String email, String username, String password, ResultCallback<Boolean, AuthException> eventStatus){
        AuthSignUpOptions options = AuthSignUpOptions.builder()
                .userAttribute(AuthUserAttributeKey.email(), email)
                .build();
        Amplify.Auth.signUp(username, password, options, result -> {
                eventStatus.onSuccess(true);
                Log.i(TAG, "Result: " + result.toString());
            }, error -> {
                eventStatus.onError(error);
                Log.e(TAG, "Sign up failed", error);
            }
        );
    }

    public static void signIn(String username, String password, ResultCallback<Boolean, AuthException> eventStatus){
        Amplify.Auth.signIn(username, password, result -> {
                eventStatus.onSuccess(true);
                Log.i(TAG, result.isSignInComplete() ? "Sign in succeeded" : "Sign in not complete");
            }, error -> {
                eventStatus.onError(error);
                Log.e(TAG, error.toString());
            }
        );
    }

    public static void signOut(ResultCallback<Boolean, AuthException> eventStatus){
        Amplify.Auth.signOut(() -> {
                eventStatus.onSuccess(true);
                Log.i(TAG, "Signed out successfully");
            }, error -> {
                eventStatus.onError(error);
                Log.e(TAG, error.toString());
            }
        );
    }

    /* if want later
    public static void confirmSignUp(String username, String confirmationCode){
        Amplify.Auth.confirmSignUp(username, confirmationCode, result -> {
                Log.i(TAG, result.isSignUpComplete() ? "Confirm signUp succeeded" : "Confirm sign up not complete");
            }, error -> {
                Log.e(TAG, error.toString());
            }
        );
    }
   */

    public static void retrieveJWTToken(ResultCallback<String, Exception> callback){
        Amplify.Auth.fetchAuthSession(
            result -> {
                callback.onSuccess(((AWSCognitoAuthSession) result).getUserPoolTokens().getValue().getIdToken());
                Log.i("AuthQuickStart", "IdentityId: " + ((AWSCognitoAuthSession) result).getUserPoolTokens().getValue().getIdToken());
            },
            error ->{
                callback.onError(new Exception(error.getMessage()));
                Log.e("AuthQuickStart", error.toString());
            }
        );
    }

    public static void restoreUser(ResultCallback<AuthSession, AuthException> restoreUser){
        Amplify.Auth.fetchAuthSession(
                result -> {
                    if(result.isSignedIn()) {
                        restoreUser.onSuccess(result);
                    }
                    else{
                        restoreUser.onError(new AuthException("User is not signed in","Go sign in"));
                    }


                },
                error -> {
                    restoreUser.onError(error);
                    Log.e("AuthQuickStart", error.toString());
                }
        );
    }

}
