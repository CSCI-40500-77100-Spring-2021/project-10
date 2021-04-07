package services.authhandler;

import android.content.Context;
import android.util.Log;

import com.amplifyframework.AmplifyException;
import com.amplifyframework.auth.cognito.AWSCognitoAuthPlugin;

import java.io.IOException;

public class Amplify {
    public static void configure(Context context) throws IOException {
        try {
            com.amplifyframework.core.Amplify.addPlugin(new AWSCognitoAuthPlugin());
            com.amplifyframework.core.Amplify.configure(context);

            Log.i("MyAmplifyApp", "Initialized Amplify");
        } catch (AmplifyException e) {
            Log.e("MyAmplifyApp", "Could not initialize Amplify", e);
        }
    }
}