package services.authhandler;

import java.io.IOException;
import okhttp3.ResponseBody;

public interface APIServiceResponseEvent{
    void onSuccess(ResponseBody responseBody);
    void onFailure(IOException ioException);
}
