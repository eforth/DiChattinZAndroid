package com.vectorjm.dichattinzandroid;

public class ChatBoutImage extends ChatBout {

    private String imageUrl;

    public ChatBoutImage() {
        super.setType("image");
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

}
