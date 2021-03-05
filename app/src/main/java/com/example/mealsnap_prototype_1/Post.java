package com.example.mealsnap_prototype_1;

public class Post {
    int pImage;
    String pTitle;
    String pDesc;

    public Post(int pImage, String pTitle, String pDesc) {
        this.pImage = pImage;
        this.pTitle = pTitle;
        this.pDesc = pDesc;
    }

    public int getpImage() {
        return pImage;
    }

    public void setpImage(int pImage) {
        this.pImage = pImage;
    }

    public String getpTitle() {
        return pTitle;
    }

    public void setpTitle(String pTitle) {
        this.pTitle = pTitle;
    }

    public String getpDesc() {
        return pDesc;
    }

    public void setpDesc(String pDesc) {
        this.pDesc = pDesc;
    }
}
