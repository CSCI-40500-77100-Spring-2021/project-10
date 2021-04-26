package com.example.mealsnap_prototype_2.models.gallery;

class GalleryPhoto {
    private String id;
    private String title;
    private String description;
    private String imageUrl;
    private boolean alreadyLiked;
    private int likeCount;

    public String getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public boolean isAlreadyLiked() {
        return alreadyLiked;
    }

    public int getLikeCount() {
        return likeCount;
    }

    @Override
    public String toString() {
        return "GalleryPhoto{" +
                "id='" + id + '\'' +
                ", title='" + title + '\'' +
                ", description='" + description + '\'' +
                ", imageUrl='" + imageUrl + '\'' +
                ", alreadyLiked=" + alreadyLiked +
                ", likeCount=" + likeCount +
                '}';
    }
}
