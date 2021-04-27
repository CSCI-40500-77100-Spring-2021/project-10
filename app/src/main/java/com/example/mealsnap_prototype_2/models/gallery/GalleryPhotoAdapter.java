package com.example.mealsnap_prototype_2.models.gallery;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.mealsnap_prototype_2.R;

import java.util.List;

public class GalleryPhotoAdapter extends RecyclerView.Adapter<GalleryPhotoAdapter.ViewHolder> {
    private Context context;
    private List<GalleryPhoto> galleryPhotoList;

    public GalleryPhotoAdapter(Context context, List<GalleryPhoto> galleryPhotoList) {
        this.context = context;
        this.galleryPhotoList = galleryPhotoList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_galleryphoto, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GalleryPhoto galleryPhoto = galleryPhotoList.get(position);
        holder.bind(galleryPhoto);
    }

    @Override
    public int getItemCount() {
        return galleryPhotoList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder{
        private ImageView ivPhoto;
        private TextView tvTitle;
        private TextView tvDescription;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            ivPhoto = itemView.findViewById(R.id.ivPhoto);
            tvTitle = itemView.findViewById(R.id.tvTitle);
            tvDescription = itemView.findViewById(R.id.tvDescription);
        }

        public void bind(GalleryPhoto galleryPhoto){

            tvDescription.setText(galleryPhoto.getDescription());
            tvTitle.setText(galleryPhoto.getTitle());

            String imageUrl = galleryPhoto.getImageUrl();
            if(imageUrl != null){
                Glide.with(context).load(imageUrl).into(ivPhoto);
            }
        }
    }
}
