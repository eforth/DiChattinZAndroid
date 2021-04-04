package com.vectorjm.dichattinzandroid;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    private final String TAG = "HomeViewModel";

    // Access a Cloud FirebaseFirestore instance from your Activity
    FirebaseFirestore db = FirebaseFirestore.getInstance();

    private MutableLiveData<List<ChatBout>> chatBouts;

    public LiveData<List<ChatBout>> getChatBouts() {
        if (chatBouts == null) {
            chatBouts = new MutableLiveData<List<ChatBout>>();
            loadChatBouts();
        }
        return chatBouts;
    }

    private void loadChatBouts() {

        // Access posts collection
        db.collection("posts").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {

                    QuerySnapshot documents = task.getResult();

                    // Exit if documents is equal to null
                    if (documents == null) return;

                    // create list to hold chatBouts
                    List<ChatBout> list = new ArrayList<>();

                    for (QueryDocumentSnapshot document : documents)  {

                        // Convert QueryDocumentSnapshot to ChatBout
                        ChatBout chatBout = Utils.convertToChatBout(document);

                        // Add chatBout only if it does not exist in the chatBouts
                        if (!list.contains(chatBout)) {
                            Log.i(TAG, chatBout.toString());
                            list.add(chatBout);
                        }
                    }

                    chatBouts.postValue(list);
                }
            }
        });
    }
}