package com.example.mealsnap_prototype_2.models.gallery;

import android.util.Log;
import android.widget.Gallery;

import androidx.annotation.Nullable;

import com.example.mealsnap_prototype_2.interfaces.ResultCallback;
import com.google.gson.Gson;

import java.io.EOFException;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import okhttp3.ResponseBody;
import com.example.mealsnap_prototype_2.services.appapi.APIRequest;

import org.json.JSONException;
import org.json.JSONObject;

public class UserGallery {
    private static final String TAG = UserGallery.class.getSimpleName();
    @Nullable
    String nextPage; // Nullable
    List<GalleryPhoto> galleryPhotoItems;

    private UserGallery(FetchUserPhotoAPIResponse response) {
        galleryPhotoItems = new LinkedList(Arrays.asList(response.result));
        nextPage = response.page.getNext();
    }

    /**
     * Retrieves Gallery for a User
     *
     * @param userId   Target User's Id
     * @param callback On Request Complete
     */
    public static void GetUserGallery(
            String userId,
            ResultCallback<UserGallery, IOException> callback
    ) {
        Log.i(TAG, "Getting Photo for " + userId);
        String url = "user/" + userId + "/gallery";
        APIRequest.get(url, new ResultCallback<ResponseBody, IOException>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                try {
                    FetchUserPhotoAPIResponse response = ParseAPIResponse(responseBody);
                    UserGallery gallery = new UserGallery(response);
                    callback.onSuccess(gallery);
                } catch (IOException e) {
                    callback.onError(e);
                    e.printStackTrace();
                }
            }

            @Override
            public void onError(IOException ioException) {
                callback.onError(ioException);
            }
        });
    }

    public static void AddImage(
        String title,
        String description,
        String base64ImageData,
        ResultCallback<GalleryPhoto, Exception> callback
    ) throws JSONException {
        JSONObject requestBody = new JSONObject();
        requestBody.put("title", title);
        requestBody.put("description", description);
        requestBody.put("encodedImage", base64ImageData);
        APIRequest.post("me/gallery/add-entry", requestBody, new ResultCallback<ResponseBody, IOException>() {
            @Override
            public void onSuccess(ResponseBody body) {
                Gson gson = new Gson();
                try {
                    GalleryPhoto addedPhoto = gson.fromJson(body.string(), GalleryPhoto.class);
                    callback.onSuccess(addedPhoto);
                } catch (IOException e) {
                    e.printStackTrace();
                    callback.onError(new Exception("Unable to parse response to json"));
                }
            }

            @Override
            public void onError(IOException error) {
                callback.onError(error);
            }
        });
    }

    private static FetchUserPhotoAPIResponse ParseAPIResponse(ResponseBody body) throws IOException {
        Gson gson = new Gson();
        FetchUserPhotoAPIResponse response = gson.fromJson(body.string(), FetchUserPhotoAPIResponse.class);
        return response;
    }

    /**
     * Get all gallery photos for current user gallery
     *
     * @return List<GalleryPhoto>
     */
    public List<GalleryPhoto> items() {
        return galleryPhotoItems;
    }

    /**
     * Load More Images
     *
     * @param callback What to do when completed
     * @throws EOFException If no more pages left
     * @throws IOException  if API Error
     */
    public void loadMore(ResultCallback<Boolean, Exception> callback) {
        if (nextPage == null) {
            // TODO: Throw End Of Page error
            EOFException endOfPage = new EOFException();
            callback.onError(endOfPage);
            return;
        }
        APIRequest.get(nextPage, new ResultCallback<ResponseBody, IOException>() {
            @Override
            public void onSuccess(ResponseBody responseBody) {
                try {
                    FetchUserPhotoAPIResponse response = ParseAPIResponse(responseBody);
                    nextPage = response.page.getNext();
                    galleryPhotoItems.addAll(Arrays.asList(response.result));
                    callback.onSuccess(true);
                } catch (IOException e) {
                    callback.onError(e);
                }
            }

            @Override
            public void onError(IOException ioException) {
                callback.onError(ioException);
            }
        });
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (GalleryPhoto photo : galleryPhotoItems) {
            sb.append("\t").append(photo.toString()).append("\n");
        }
        return "UserGallery{\n" +
                "\tTotal items=" + galleryPhotoItems.size() + ", nextPage='" + nextPage + '\n' +
                "\n" +
                sb.toString() +
                '}';
    }
}
