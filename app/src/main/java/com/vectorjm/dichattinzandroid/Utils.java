package com.vectorjm.dichattinzandroid;

import com.google.firebase.Timestamp;
import com.google.firebase.firestore.QueryDocumentSnapshot;

public class Utils {

    public static ChatBout convertToChatBout(QueryDocumentSnapshot queryDocumentSnapshot) {

        if (queryDocumentSnapshot == null) return null;

        String postType = (String) queryDocumentSnapshot.get("postType");

        if (postType != null && postType.equals("image")) {
            return convertToChatBoutImage(queryDocumentSnapshot);
        }

        return null;
    }

    private static ChatBoutImage convertToChatBoutImage(QueryDocumentSnapshot queryDocumentSnapshot) {

        ChatBoutImage chatBoutImage = new ChatBoutImage();

        String userId = (String) queryDocumentSnapshot.get("userId");
        Timestamp timestamp = (queryDocumentSnapshot.get("timestamp") == null) ? null : queryDocumentSnapshot.getTimestamp("timestamp");
        String description = (String) queryDocumentSnapshot.get("description");
        int comments = (queryDocumentSnapshot.get("comments") == null) ? 0 : queryDocumentSnapshot.getLong("comments").intValue();
        int likes = (queryDocumentSnapshot.get("likes") == null) ? 0 : queryDocumentSnapshot.getLong("likes").intValue();
        String imageUrl = (String) queryDocumentSnapshot.get("imageUrl");

        chatBoutImage.setUserId(userId);
        chatBoutImage.setDescription(description);
        chatBoutImage.setComments(comments);
        chatBoutImage.setLikes(likes);
        chatBoutImage.setImageUrl(imageUrl);
        chatBoutImage.setId(queryDocumentSnapshot.getId());

        return chatBoutImage;
    }
}
