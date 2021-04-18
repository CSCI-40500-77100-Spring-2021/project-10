package services.authhandler;

import android.content.Context;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;

import java.io.IOException;

public class AmplifyService {
    public static void configure(Context context) throws AmplifyException {

        com.amplifyframework.core.Amplify.addPlugin(new AWSCognitoAuthPlugin());
        com.amplifyframework.core.Amplify.configure(context);
        Log.i("MyAmplifyApp", "Initialized AmplifyService");
    }
}