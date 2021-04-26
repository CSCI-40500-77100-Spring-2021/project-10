package services.authhandler;

import java.io.IOException;
import okhttp3.ResponseBody;

public interface APIServiceResponseEvent{
    void onSuccess(ResponseBody requestBody);
    void onFailure(IOException ioException);
}
