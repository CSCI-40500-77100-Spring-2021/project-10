package com.example.mealsnap_prototype_2.activities.fragments;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.mealsnap_prototype_2.R;
import com.example.mealsnap_prototype_2.models.gallery.GalleryPhoto;
import com.example.mealsnap_prototype_2.models.gallery.GalleryPhotoAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class GalleryFragment extends Fragment {

    private static final String TAG = "GalleryFragment";
    private RecyclerView rvGalleryPhotos;
    private GalleryPhotoAdapter galleryPhotoAdapter;
    private List<GalleryPhoto> galleryPhotoList;

    public GalleryFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rvGalleryPhotos = view.findViewById(R.id.rvGalleryPhotos);

        galleryPhotoList = new ArrayList<>();
        galleryPhotoAdapter = new GalleryPhotoAdapter(getContext(), galleryPhotoList);

        //the data source here is galleryPhotoList, here put dummy data to display

        rvGalleryPhotos.setAdapter(galleryPhotoAdapter);

        rvGalleryPhotos.setLayoutManager(new LinearLayoutManager(getContext()));

    }
}