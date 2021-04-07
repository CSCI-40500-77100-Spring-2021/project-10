package services.authhandler;

import android.util.Log;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.mealsnap_prototype_1.ConfigActivity;

import org.jetbrains.annotations.NotNull;
import org.json.JSONObject;

import java.io.IOError;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class APIRequest {
    public static final String TAG = "APIRequest";
    public static String ROOT_URL = "https://dbkw974ay1.execute-api.us-east-1.amazonaws.com/prod/";

    static OkHttpClient client = new OkHttpClient();

    //Gets from given root + given url
    public static void get(String url, APIServiceResponseEvent responseEvent) {
        //Content of request
        Request request = new Request.Builder()
                .url(ROOT_URL + url)
                .build();

        //Set auth here
        //TODO

        //Request made
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(@NotNull Call call, @NotNull IOException e) {
                e.printStackTrace();
                Log.i(TAG, "Failed to get from URL");
                responseEvent.onFailure(e);
            }
            @Override
            public void onResponse(@NotNull Call call, @NotNull Response response) throws IOException {
                if(response.isSuccessful()){
                    if (!response.isSuccessful()) {
                        IOException e = new IOException("Failed to get from URL");
                        responseEvent.onFailure(e);
                    }
                    else {
                        responseEvent.onSuccess(response.body());
                    }
                }
            }
        });
    }

    //TODO ^ for post and delete
}
